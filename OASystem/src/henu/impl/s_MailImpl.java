package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.s_MailIF;
import henu.bean.Dept;
import henu.bean.Employee;
import henu.bean.Receive;
import henu.bean.Send;
import henu.util.DbcpPool;

public class s_MailImpl implements s_MailIF{

	@Override
	public List<Receive> receiveBox(String employeeID) {
		String sql = "select * from receive a, employee b, employee c "
				+ "where a.receiveTo='"+employeeID+"'  and "
				+ "a.receiveTo = b.employeeID and a.receiveFrom = c.employeeID Order by receiveDate desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Receive> list = new ArrayList<Receive>();
		try {
			while(rs.next())
			{
				Receive r = new Receive();
				r.setReceiveID(rs.getString("a.receiveID"));
				r.setFromName(rs.getString("c.employeeName"));
				r.setToName(rs.getString("b.employeeName"));
				String text = rs.getString("a.receiveText");
				text = text.replaceAll("<br>", ""); 
				text = text.replaceAll("&nbsp;", "");
				r.setReceiveText(text);
				r.setReceiveTitle(rs.getString("a.receiveTitle"));
				String receiveDate = dateFormat.format(rs.getTimestamp("receiveDate"));
				r.setReceiveDate(receiveDate);
				if(rs.getString("read1").equals("1"))
				    r.setRead("已阅");
				else
					r.setRead("未阅");
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public List<Send> sendBox(String employeeID) {
		String sql = "select * from send a, employee b, employee c "
				+ "where a.sendFrom='"+employeeID+"'  and "
				+ "a.sendTo = b.employeeID and a.sendFrom = c.employeeID Order by sendDate desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Send> list = new ArrayList<Send>();
		try {
			while(rs.next())
			{
				Send r = new Send();
				r.setSendID(rs.getString("a.sendID"));
				r.setFromName(rs.getString("c.employeeName"));
				r.setToName(rs.getString("b.employeeName"));
				String text = rs.getString("a.sendText");
				text = text.replaceAll("<br>", ""); 
				text = text.replaceAll("&nbsp;", "");
				r.setSendText(text);
				r.setSendTitle(rs.getString("a.sendTitle"));
				String sendDate = dateFormat.format(rs.getTimestamp("sendDate"));
				r.setSendDate(sendDate);
				list.add(r);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public boolean send(Send s) {
		String sql = "insert into send(sendFrom,sendTo,sendTitle,sendText,sendDate) "
				+ "values(?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int r=0;
		String[] strArray = null; 
		strArray = s.getSendTo().split(",");
		try{
		for(int i=0;i<strArray.length;i++){
			ps.setString(1, s.getSendForm());
			ps.setString(2, strArray[i]);
			ps.setString(3, s.getSendTitle());
			ps.setString(4, s.getSendText());
			ps.setString(5, s.getSendDate());
			r=ps.executeUpdate();
			if(r==0)
				return false;
		}
		ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		sql = "insert into receive(receiveFrom,receiveTo,receiveTitle,receiveText,receiveDate,read1) "
				+ "values(?,?,?,?,?,?)";
		PreparedStatement ps1 = DbcpPool.executePreparedStatement(sql);
		try{
			for(int i=0;i<strArray.length;i++){
				ps1.setString(1, s.getSendForm());
				ps1.setString(2, strArray[i]);
				ps1.setString(3, s.getSendTitle());
				ps1.setString(4, s.getSendText());
				ps1.setString(5, s.getSendDate());
				ps1.setInt(6, 0);
				r=ps1.executeUpdate();
				if(r==0)
					return false;
			}
			ps1.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
		DbcpPool.close();
		if(r==0)
			return false;
		else 
			return true;
	}

	@Override
	public Receive receiveDetail(String receiveID) {
		String sql = "select * from receive a, employee b "
				+ "where a.receiveID='"+receiveID+"' and "
				+ "a.receiveFrom = b.employeeID ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Receive r = new Receive();
		try {
			while(rs.next())
			{
				r.setReceiveID(rs.getString("a.receiveID"));
				r.setFromName(rs.getString("b.employeeName"));
				r.setReceiveText(rs.getString("a.receiveText"));
				r.setReceiveTitle(rs.getString("a.receiveTitle"));
				String receiveDate = dateFormat.format(rs.getTimestamp("receiveDate"));
				r.setReceiveDate(receiveDate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		sql = "update receive set read1=? where receiveID = ?"; 
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setInt(1, 1);
		ps.setString(2, receiveID);
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
		DbcpPool.close();
		return r;
	}

	@Override
	public Send sendDetail(String sendID) {
		String sql = "select * from send a, employee b "
				+ "where a.sendID='"+sendID+"' and "
				+ "a.sendTo = b.employeeID ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Send r = new Send();
		try {
			while(rs.next())
			{
				r.setSendID(rs.getString("a.sendID"));
				r.setToName(rs.getString("b.employeeName"));
				r.setSendText(rs.getString("a.sendText"));
				r.setSendTitle(rs.getString("a.sendTitle"));
				String sendDate = dateFormat.format(rs.getTimestamp("sendDate"));
				r.setSendDate(sendDate);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return r;
	}

	@Override
	public List<Employee> addEmployee(String employeeID) {
		String sql = null;
  		sql = "select * from employee a, job b, dept c "
  				+ "where a.deptID = c.deptID and a.jobID = b.jobID "
  				+ "and workState=1 and a.employeeID!='"+employeeID+"' order by c.deptID ";
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Employee> list = new ArrayList<Employee>();
  	    try {
			while(rs.next())
			{
		  	    Employee em = new Employee();
				em.setEmployeeID(rs.getString("employeeID"));
				em.setEmployeeName(rs.getString("employeeName"));
				em.setDeptName(rs.getString("c.deptName"));
				em.setJobName(rs.getString("b.jobName"));
				list.add(em);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public boolean deleteReceive(String[] item) {
		String sql = null;
		int result = 0 ;
		for(int i=0;i<item.length;i++)  {
		sql = "DELETE FROM receive WHERE receiveID= '"+ item[i] +"'";
		result = DbcpPool.executeUpdate(sql);
		}
		DbcpPool.close();
		if(result>0)
			return true;
		else
			return false;
	}

	@Override
	public boolean deleteSend(String[] item) {
		String sql = null;
		int result = 0 ;
		for(int i=0;i<item.length;i++)  {
		sql = "DELETE FROM send WHERE sendID= '"+ item[i] +"'";
		result = DbcpPool.executeUpdate(sql);
		}
		DbcpPool.close();
		if(result>0)
			return true;
		else
			return false;
	}

}
