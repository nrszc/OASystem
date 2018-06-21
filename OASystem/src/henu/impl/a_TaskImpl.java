package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import henu.IF.a_TaskIF;
import henu.bean.Task;
import henu.util.DbcpPool;

public class a_TaskImpl implements a_TaskIF {

	@Override
	public List<Task> a_myTask(String employeeID) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * FROM task a, employee b "
  				+"WHERE TemployeeID = '"+employeeID+"' and a.FemployeeID=b.employeeID order by taskTime desc";
  		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Task> list= new  ArrayList<Task>();
  	    try {
			while(rs.next())
			{
				//System.out.println("5555");
				Task t=new Task();
				t.setTaskID(rs.getString("taskID"));
				t.setTaskName(rs.getString("taskName"));
				t.setTaskInfo(rs.getString("taskInfo"));
				String endTime = df.format(rs.getTimestamp("endTime"));
				String startTime = df.format(rs.getTimestamp("startTime"));
				String taskTime = df.format(rs.getTimestamp("taskTime"));
				t.setStartTime(startTime);
				t.setEndTime(endTime);
				t.setTaskTime(taskTime);
				t.setFaburen(rs.getString("b.employeeName"));
				t.setTaskState(rs.getString("taskState"));
				if((t.getTaskState().equals("0")||t.getTaskState().equals("1"))&&compTime(endTime))
					t.setTaskState("任务过期未完成");
				else if(t.getTaskState().equals("0"))
				t.setTaskState("待领取");
				else if(t.getTaskState().equals("1"))
				t.setTaskState("进行中");
				else 
				t.setTaskState("完成任务");
				list.add(t);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	@Override
	public Task mytask_detail(String tID, String employeeName) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM task as a,employee as b "
  				+"WHERE taskID = '"+tID+"' "
  				+"AND FemployeeID=employeeID ";
  		Task t=new Task();	
  		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    try {
			while(rs.next())
			{
				//System.out.println("5555");
				t.setTaskID(rs.getString("taskID"));
				t.setTaskName(rs.getString("taskName"));
				t.setTaskInfo(rs.getString("taskInfo"));
				t.setStartTime(rs.getString("startTime"));
				t.setEndTime(rs.getString("endTime"));
				String endTime = df.format(rs.getTimestamp("endTime"));
				t.setTaskTime(rs.getString("taskTime"));
				t.setFaburen(rs.getString("employeeName"));	
				t.setTemployeeID(employeeName);
				t.setTaskState(rs.getString("taskState"));
				if((t.getTaskState().equals("0")||t.getTaskState().equals("1"))&&compTime(endTime))
					t.setTaskState("任务过期未完成");
				else if(t.getTaskState().equals("0"))
				t.setTaskState("待领取");
				else if(t.getTaskState().equals("1"))
				t.setTaskState("进行中");
				else
				t.setTaskState("完成任务");
				if(rs.getString("taskState").equals("0"))
					t.setState("我知道了！请点击");
				else if(rs.getString("taskState").equals("1"))
					t.setState("完成任务！请点击");
				else 
					t.setState("已经完成任务了！");
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return t;
	}

	private boolean compTime(String tasktime) throws ParseException {
		// TODO Auto-generated method stub
		Date now = new Date(); 
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//可以方便地修改日期格式 
		String nowtime = dateFormat.format( now ); 
		Date a = dateFormat.parse(tasktime);  
        Date c = dateFormat.parse(nowtime); 
        //Date类的一个方法，如果a早于b返回true，否则返回false  
        if(a.before(c))  
            return true;  
        else
        	return false;
	}

	@Override
	public void handle(String tID, String taskstate) {
		// TODO Auto-generated method stub
		String sql = null;
		sql="UPDATE `task` SET taskState=taskState+1 where taskID=? ";
		 PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		    try {
		    ps.setString(1,tID);
		    ps.executeUpdate();
			ps.close();
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    DbcpPool.close();
	}

}
