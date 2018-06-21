package henu.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import henu.IF.a_MeetingIF;
import henu.bean.Meeting;
import henu.util.DbcpPool;

public class a_MeetingImpl implements a_MeetingIF{

	@Override
	public List<Meeting> a_meeting(String employeeID) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM meeting AS a ,`meeting-employee` AS b,employee AS c "
  				+"WHERE a.meetingID = b.meetingID "
  				+ "AND b.employeeID ='"+employeeID+"'"
  				+ "AND a.employeeID =c.employeeID order by createtime desc";
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Meeting> list= new  ArrayList<Meeting>();
  	  SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
       Date now=new Date();// new Date()为获取当前系统时间
  	    try {
			while(rs.next())
			{
				Meeting m=new Meeting();
				m.setMeetingID(rs.getString("a.meetingID"));
				m.setMeetingName(rs.getString("meetingName"));
				m.setMeetingText(rs.getString("a.meetingText"));
				String starttime = df.format(rs.getTimestamp("starttime"));
				String endtime = df.format(rs.getTimestamp("endtime"));
				m.setStartTime(starttime);
				m.setHall(rs.getString("hall"));
				m.setEndTime(endtime);
				String s=rs.getString("starttime");
				String e=rs.getString("endtime");
				Date st=df.parse(s);
				Date et=df.parse(e);
				int i=st.compareTo(now);
				int j=et.compareTo(now);
				if(i<0&&j>0)
					m.setCondition("进行中");
				else if(i>0)
					m.setCondition("未开始");
				else if(j<0)
				    m.setCondition("已结束");
				String createtime = df.format(rs.getTimestamp("createtime"));
				m.setCreateTime(createtime);
				m.setEmployeeID(rs.getString("employeeName"));
				list.add(m);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public Meeting meetingDetail(String meetingID) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM meeting AS a ,employee AS c "
  				+"WHERE a.employeeID = c.employeeID "
  				+ "AND a.meetingID ='"+meetingID+"'";
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date now=new Date();// new Date()为获取当前系统时间
		Meeting m=new Meeting();
  	    try {
			while(rs.next())
			{
				m.setMeetingID(rs.getString("a.meetingID"));
				m.setMeetingName(rs.getString("meetingName"));
				m.setMeetingText(rs.getString("meetingText"));
				String starttime = df.format(rs.getTimestamp("starttime"));
				String endtime = df.format(rs.getTimestamp("endtime"));
				m.setStartTime(starttime);
				m.setHall(rs.getString("hall"));
				m.setEndTime(endtime);
				String s=rs.getString("starttime");
				String e=rs.getString("endtime");
				Date st=df.parse(s);
				Date et=df.parse(e);
				int i=st.compareTo(now);
				int j=et.compareTo(now);
				if(i<0&&j>0)
					m.setCondition("进行中");
				else if(i>0)
					m.setCondition("未开始");
				else if(j<0)
				    m.setCondition("已结束");
				String createtime = df.format(rs.getTimestamp("createtime"));
				m.setCreateTime(createtime);
				m.setEmployeeID(rs.getString("employeeName"));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
  	    //查询参加这个会议的所有人
  	    sql = "select * from employee a, `meeting-employee` as b where "
  	    		+ "a.employeeID=b.employeeID and b.meetingID='"+meetingID+"'";
  	    ResultSet rs1 = DbcpPool.executeQuery(sql);
  	    String isPart = "";
  	    try {
			while(rs1.next())
			{
				System.out.println("#");
				isPart = isPart + rs1.getString("a.employeeName") + "，" ;
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
  	    isPart = isPart.substring(0,isPart.length() - 1);
        m.setIsPart(isPart);
		DbcpPool.close();
		return m;
	}

}
