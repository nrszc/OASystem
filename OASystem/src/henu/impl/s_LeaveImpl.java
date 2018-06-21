package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.List;

import henu.IF.s_LeaveIF;
import henu.bean.Active;
import henu.bean.Leave;
import henu.util.DbcpPool;

public class s_LeaveImpl implements s_LeaveIF{

	//执行SQL查询语句，查询所有待我审批的请假信息，保存并返回List<Leave>
	@Override
	public List<Leave> showLeave(String employeeID) throws ParseException {
		String sql = "select * from leave1 a ,employee b where leaveTo = '"+employeeID+"' "
				+ " and a.LeaveFrom = b.employeeID and result != 0 Order by leaveTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Leave> list = new ArrayList<Leave>();
		try {
			while(rs.next())
			{
				Leave l = new Leave();
				l.setLeaveID(rs.getString("leaveID"));
				l.setFromName(rs.getString("b.employeeName"));
				l.setLeaveText(rs.getString("leaveText"));
				String startTime = dateFormat.format(rs.getTimestamp("startTime"));
				l.setStartTime(startTime);
				String endTime = dateFormat.format(rs.getTimestamp("endTime"));
				l.setEndTime(endTime);
				String leaveTime = dateFormat.format(rs.getTimestamp("leaveTime"));
				l.setLeaveTime(leaveTime);
			    if(rs.getString("result").equals("1"))
				    l.setResult("通过");
				else
					l.setResult("反对");
				l.setLeaveDay(daysBetween(startTime,endTime));
				list.add(l);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}
	
	//计算请假的天数
	public static String daysBetween(String smdate,String bdate) throws ParseException{  
        SimpleDateFormat sdf=new SimpleDateFormat("yyyy-MM-dd");  
        Calendar cal = Calendar.getInstance();    
        cal.setTime(sdf.parse(smdate));    
        long time1 = cal.getTimeInMillis();                 
        cal.setTime(sdf.parse(bdate));    
        long time2 = cal.getTimeInMillis();         
        long between_days=(time2-time1)/(1000*3600*24);  
            
       return String.valueOf(between_days);     
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
				else
				   l.setResult("反对");
				l.setLeaveDay(rs.getString("leaveDay"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return l;
	}

	@Override
	public List<Leave> leave_unapproved(String employeeID) throws ParseException {
		String sql = "select * from leave1 a ,employee b where leaveTo = '"+employeeID+"' "
				+ " and a.LeaveFrom = b.employeeID and result = 0 Order by leaveTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
		List<Leave> list = new ArrayList<Leave>();
		try {
			while(rs.next())
			{
				Leave l = new Leave();
				l.setLeaveID(rs.getString("leaveID"));
				l.setFromName(rs.getString("b.employeeName"));
				l.setLeaveText(rs.getString("leaveText"));
				String startTime = dateFormat.format(rs.getTimestamp("startTime"));
				l.setStartTime(startTime);
				String endTime = dateFormat.format(rs.getTimestamp("endTime"));
				l.setEndTime(endTime);
				String leaveTime = dateFormat.format(rs.getTimestamp("leaveTime"));
				l.setLeaveTime(leaveTime);
				l.setResult("未审批");
				l.setLeaveDay(daysBetween(startTime,endTime));
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
	public Leave leave_detail1(String leaveID) {
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
				String startTime = dateFormat.format(rs.getTimestamp("startTime"));
				l.setStartTime(startTime);
				String endTime = dateFormat.format(rs.getTimestamp("endTime"));
				l.setEndTime(endTime);
				String leaveTime = dateFormat.format(rs.getTimestamp("leaveTime"));
				l.setLeaveTime(leaveTime);
				l.setLeaveDay(rs.getString("leaveDay"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return l;
	}

	@Override
	public boolean approve(Leave l) {
		String sql = null;
		sql = "update leave1 set result=?,"
				+"resultText=? where leaveID=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, l.getResult());
		ps.setString(2, l.getResultText());
		ps.setString(3, l.getLeaveID());
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    DbcpPool.close();
	    if(result>0)
	       return true;
	    else 
	       return false;
	}
     
}
