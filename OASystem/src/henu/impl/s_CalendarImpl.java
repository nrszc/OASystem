package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;

import henu.IF.s_CalendarIF;
import henu.bean.Calendar;
import henu.util.DbcpPool;

public class s_CalendarImpl implements s_CalendarIF{

	@Override
	public String Json(String employeeID) {
		String sql = "select * from calendar where employeeID = '"+employeeID+"'";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("\"id\":").append(rs.getString("id")).append(",");  
	                json.append("\"title\":").append("\"").append(rs.getString("title").trim()).append("\"").append(",");  
	                SimpleDateFormat format = null;
					if(rs.getString("allday").equals("1"))
					{
		                json.append("\"allDay\":").append("true").append(",");  
						format = new SimpleDateFormat("yyyy-MM-dd");
					}else
					{
						format = new SimpleDateFormat("yyyy-MM-dd HH:mm");
						
					}
	                json.append("\"start\":").append("\"").append(format.format(rs.getTimestamp("starttime"))).append("\"").append(",");  
	                if(rs.getTimestamp("endtime") != null)
					{
		                json.append("\"end\":").append("\"").append(format.format(rs.getTimestamp("endtime"))).append("\"").append(",");  
					}
	                if(rs.getString("isfinish").equals("0"))
					{
		                json.append("\"isFinish\":").append("\"").append("0").append("\"").append(",");  
		                json.append("\"className\":").append("\"").append("doing").append("\"").append(",");  
					}else
					{
		                json.append("\"isFinish\":").append("\"").append("1").append("\"").append(",");  
		                json.append("\"className\":").append("\"").append("done").append("\"").append(",");  
					}
	                json.append("\"color\":").append("\"").append(rs.getString("color")).append("\"");  
	                json.append("},");  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		json.deleteCharAt(json.length() - 1);  
	    json.append("]");  
		return json.toString();
	}

	@Override
	public String Add(Calendar c) {
		String sql = "insert into calendar(title,starttime,endtime,allday,"
				+ "color,employeeID,isfinish) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, c.getTitle());
			ps.setString(2, c.getStart());
			ps.setString(3, c.getEnd());
			ps.setString(4, c.getAllDay());
			ps.setString(5, c.getColor());
			ps.setString(6, c.getEmployeeID());
			ps.setString(7, c.getIsfinish());
			r=ps.executeUpdate();
			ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		if(r==0)
			return "ERROR";
		else 
			return "ADD";
	}

	@Override
	public String Update(Calendar c) {
		String sql = null;
		sql = "update calendar set title=?,starttime=?,endtime=?,"
				+ "isfinish=?,color=?,allday=? where id = ?"; 
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, c.getTitle());
		ps.setString(2, c.getStart());
		ps.setString(3, c.getEnd());
		ps.setString(4, c.getIsfinish());
		ps.setString(5, c.getColor());
		ps.setString(6, c.getAllDay());
		ps.setString(7, c.getId());
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    DbcpPool.close();
	    if(result>0)
	       return "UPDATE";
	    else 
	       return "ERROR";
	}

}
