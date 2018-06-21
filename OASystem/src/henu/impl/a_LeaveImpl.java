package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.a_LeaveIF;
import henu.bean.Leave;
import henu.util.DbcpPool;

public class a_LeaveImpl implements a_LeaveIF{
	@Override
	public StringBuffer showEmployee(int deptID) {
		String sql = "select * from employee a, job b "
				+ "where b.level = '中级' and a.workState=1 and a.jobID=b.jobID and a.deptID='"+deptID+"'";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				System.out.println("");
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'employeeID':").append("'").append(rs.getString("a.employeeID")).append("'").append(",");  
	                json.append("'employeeName':").append("'")  
	                        .append(rs.getString("a.employeeName").trim()).append("'");  
	                json.append("},");  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		json.deleteCharAt(json.length() - 1);  
	    json.append("]");  
		return json;
	}

	@Override
	public boolean addLeave(Leave l) {
		String sql = "insert into leave1(leaveFrom,leaveTo,leaveTime,startTime,endTime,leaveText,leaveDay,result) "
				+ "values(?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, l.getLeaveFrom());
			ps.setString(2, l.getLeaveTo());
			ps.setString(3, l.getLeaveTime());
			ps.setString(4, l.getStartTime());
			ps.setString(5, l.getEndTime());
			ps.setString(6, l.getLeaveText());
			ps.setInt(7, Integer.parseInt(l.getLeaveDay()));
			ps.setInt(8, 0);
			r=ps.executeUpdate();
		    ps.close();
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
	public List<Leave> myLeave(String employeeID) {
		String sql = "select * from employee a, leave1 b where "
				+ "a.employeeID=b.leaveTo and b.leaveFrom = '"+employeeID+"' order by b.leaveTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Leave> list = new ArrayList<Leave>();
		try {
			while(rs.next())
			{
				Leave l = new Leave();
				l.setLeaveID(rs.getString("leaveID"));
				l.setToName(rs.getString("a.employeeName"));
				l.setLeaveText(rs.getString("leaveText"));
				l.setResultText(rs.getString("resultText"));
				String startTime = dateFormat.format(rs.getTimestamp("startTime"));
				l.setStartTime(startTime);
				String endTime = dateFormat.format(rs.getTimestamp("endTime"));
				l.setEndTime(endTime);
				String leaveTime = df.format(rs.getTimestamp("leaveTime"));
				l.setLeaveTime(leaveTime);
				if(rs.getInt("result")==1)
				   l.setResult("通过");
				else if(rs.getInt("result")==2)
				   l.setResult("反对");
				else
				   l.setResult("未审批");
				l.setLeaveDay(rs.getString("leaveDay"));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public StringBuffer showEmployee1() {
		String sql = "select * from employee a, job b "
				+ "where b.level = '高级' and a.workState=1 and a.jobID=b.jobID ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'employeeID':").append("'").append(rs.getString("a.employeeID")).append("'").append(",");  
	                json.append("'employeeName':").append("'")  
	                        .append(rs.getString("a.employeeName").trim()).append("'");  
	                json.append("},");  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		json.deleteCharAt(json.length() - 1);  
	    json.append("]");  
		return json;
	}
	
	//查询具体某个请假，用Leave返回这个请假的具体信息
			@Override
			public Leave leave_detail(String leaveID) {
				String sql = "select * from leave1 a ,employee b,employee c where leaveID = '"+leaveID+"' "
						+ " and a.leaveFrom = b.employeeID and a.leaveTo=c.employeeID";
				ResultSet rs = DbcpPool.executeQuery(sql);
				SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
				Leave l = new Leave();
				try {
					while(rs.next())
					{
						l.setLeaveID(rs.getString("leaveID"));
						l.setFromName(rs.getString("b.employeeName"));
						l.setToName(rs.getString("c.employeeName"));
						l.setLeaveText(rs.getString("leaveText"));
						l.setResultText(rs.getString("resultText"));
						String startTime = dateFormat.format(rs.getTimestamp("startTime"));
						l.setStartTime(startTime);
						String endTime = dateFormat.format(rs.getTimestamp("endTime"));
						l.setEndTime(endTime);
						String leaveTime = dateFormat.format(rs.getTimestamp("leaveTime"));
						l.setLeaveTime(leaveTime);
						if(rs.getInt("result")==1)
						   l.setResult("通过");
						else if(rs.getInt("result")==2)
							   l.setResult("反对");
							else
							   l.setResult("未审批");
						l.setLeaveDay(rs.getString("leaveDay"));
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				DbcpPool.close();
				return l;
			}
     
}


