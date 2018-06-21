package henu.impl;
import henu.IF.s_MeetingIF;
import henu.bean.Employee;
import henu.bean.Meeting;
import henu.util.DbcpPool;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;


public class s_MeetingImpl implements s_MeetingIF {

	@Override
	/*查看我的会议的信息*/
	public List<Meeting> s_meeting(String employeeID) throws ParseException {
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
	/*获取我发布的会议的信息*/
	public List<Meeting> s_release(String employeeID) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM meeting AS a ,employee AS b "
  				+"WHERE a.employeeID = b.employeeID "
  				+ "AND b.employeeID ='"+employeeID+"' order by createtime desc";
  		ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Meeting> list= new  ArrayList<Meeting>();
  	    SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
        Date now=new Date();// new Date()为获取当前系统时间
  	    try {
			while(rs.next())
			{
				//System.out.println("5555");
				Meeting m=new Meeting();
				m.setMeetingID(rs.getString("meetingID"));
				m.setMeetingName(rs.getString("meetingName"));
				m.setMeetingText(rs.getString("meetingText"));
				String starttime = dateFormat.format(rs.getTimestamp("starttime"));
				String endtime = dateFormat.format(rs.getTimestamp("endtime"));
				m.setStartTime(starttime);
				m.setHall(rs.getString("hall"));
				m.setEndTime(endtime);
				String s=rs.getString("starttime");
				String e=rs.getString("endtime");				
				Date st=dateFormat.parse(s);
				Date et=dateFormat.parse(e);
				int i=st.compareTo(now);
				int j=et.compareTo(now);
				if(i<0&&j>0)
					m.setCondition("进行中");
				else if(i>0)
					m.setCondition("未开始");
				else if(j<0)
				    m.setCondition("已结束");
				String createtime = dateFormat.format(rs.getTimestamp("createtime"));
				m.setCreateTime(createtime);
				m.setEmployeeID(rs.getString("b.employeeName"));
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
	/*获取员工的信息*/
	public List<Employee> s_employee() {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "select * from employee a, job b, dept c "
  				+ "where a.deptID = c.deptID and a.jobID = b.jobID "
  				+ "and workState=1 order by c.deptID";
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
	/*把会议的信息添加到Meeting表*/
	public boolean addMeeting(Meeting mm) {
		String sql = "insert into meeting(meetingText,meetingName,hall,startTime,endTime,employeeID,createTime) values(?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, mm.getMeetingText());
			ps.setString(2, mm.getMeetingName());
			ps.setString(3, mm.getHall());
			ps.setString(4, mm.getStartTime());
			ps.setString(5, mm.getEndTime());
			ps.setString(6, mm.getEmployeeID());
			ps.setString(7, mm.getCreateTime());
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
	/*把所需开会的人员的信息添加到meeting-employee表中。*/
	public void addMeeting(String id) {
		// TODO Auto-generated method stub
		String sq="select max(meetingID) from meeting ";
		ResultSet rs = DbcpPool.executeQuery(sq);	
		int  meetingID=0;
		try {
			while(rs.next())
			{
		
				meetingID=rs.getInt("max(meetingID)");
				System.out.println(meetingID);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		String sql = "insert into `meeting-employee`(meetingID,employeeID) values(?,?)";
		
		String[] idArray = id.split(",");
		 for (int i = 0; i < idArray.length; i++) 
		 {
			 PreparedStatement ps = DbcpPool.executePreparedStatement(sql);	
			 try{
					ps.setInt(1, meetingID);
					ps.setString(2,idArray[i] );
					ps.executeUpdate();
					ps.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
			
		 }
			DbcpPool.close();		
	}

	@Override
	public Meeting meetingDetail(String meetingID) throws ParseException {
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

	@Override
	public boolean deleteMeeting(String meetingID) {
		String sql = "DELETE FROM meeting WHERE meetingID= '"+ meetingID +"'";
		int result = 0 ;
		result = DbcpPool.executeUpdate(sql);
		DbcpPool.close();
		if(result>0)
			return true;
		else
			return false;
	}

	
}
