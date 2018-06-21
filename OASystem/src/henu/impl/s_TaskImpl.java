package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import henu.IF.s_TaskIF;
import henu.bean.Employee;
import henu.bean.Task;
import henu.util.DbcpPool;

public class s_TaskImpl implements s_TaskIF {

	@Override
	/*高级人员的发布任务*/
	public List<Task> s_release(String employeeID) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM task AS a,employee AS b,job c, dept d "
  				+"WHERE a.FemployeeID = '"+employeeID+"' "
  				+"and b.deptID = d.deptID and b.jobID = c.jobID "
  				+"and a.TemployeeID=b.employeeID "
  				+ "and workState=1 order by a.taskTime DESC ";	
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Task> list= new  ArrayList<Task>();
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  	    try {
			while(rs.next())
			{
				//System.out.println("5555");
				Task t=new Task();
				t.setTaskID(rs.getString("taskID"));
				t.setTaskName(rs.getString("taskName"));
				t.setTaskInfo(rs.getString("taskInfo"));
				String taskTime = df.format(rs.getTimestamp("taskTime"));
				t.setTaskTime(taskTime);
				String endTime = df.format(rs.getTimestamp("endTime"));
				String startTime = df.format(rs.getTimestamp("startTime"));
				t.setEndTime(endTime);
				t.setStartTime(startTime);
				t.setTaskState(rs.getString("taskState"));
			    System.out.println(t.getTaskState());
					if((t.getTaskState().equals("0")||t.getTaskState().equals("1"))&&compTime(endTime))
						t.setTaskState("任务过期未完成");
					else if(t.getTaskState().equals("0"))
					t.setTaskState("待领取");
					else if(t.getTaskState().equals("1"))
					t.setTaskState("进行中");
					else 
					t.setTaskState("完成任务");
				String dept=null;
				String job=null;
				String name=null;
				dept=rs.getString("d.deptName");
				job=rs.getString("c.jobName");
				name=rs.getString("employeeName");
				t.setStaff(dept+job+name);			
				list.add(t);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}
	
	private boolean compTime(String tasktime) throws ParseException {
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
	/*获取员工的信息*/
	public List<Employee> s_employee() {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "select * from employee a, job b, dept c "
  				+ "where a.deptID = c.deptID and a.jobID = b.jobID and b.level!='高级'  "
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
	/*添加任务信息到数据库*/
	public boolean s_addTask(Task tt, String id) {
		
		// TODO Auto-generated method stub
		String sql = "insert into task(taskName,taskInfo,startTime,endTime,taskTime,taskState,FemployeeID,TemployeeID) values(?,?,?,?,?,?,?,?)";
		
		int  r=0;
		String[] idArray = id.split(",");
		 for (int i = 0; i < idArray.length; i++) 
		 {
			 try{
				 PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
					ps.setString(1, tt.getTaskName());
					ps.setString(2, tt.getTaskInfo());
					ps.setString(3, tt.getStartTime());
					ps.setString(4, tt.getEndTime());
					ps.setString(5, tt.getTaskTime());
					ps.setString(6, tt.getTaskState());
					ps.setString(7, tt.getFemployeeID());
					ps.setString(8,idArray[i] );			
					r=ps.executeUpdate();
					ps.close();
				}catch(SQLException e)
				{
					e.printStackTrace();
				}
		 }		
		DbcpPool.close();
		if(r==0)
			return false;
		else 
			return true;
	}

	@Override
	/*查看发布任务的详情*/
	public Task s_releaseDetail(String tID,String employeeName) throws ParseException {
		// TODO Auto-generated method stub
		String sql = null;
  		sql = "SELECT * "
  				+"FROM task as a,employee as b "
  				+"WHERE taskID = '"+tID+"' "
  				+"AND TemployeeID=employeeID ";
  		Task t=new Task();	
    	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    try {
			while(rs.next())
			{
				t.setTaskName(rs.getString("taskName"));
				t.setTaskInfo(rs.getString("taskInfo"));
				String starttime = df.format(rs.getTimestamp("starttime"));
				String endTime = df.format(rs.getTimestamp("endTime"));
				String taskTime = df.format(rs.getTimestamp("taskTime"));
				t.setStartTime(starttime);
				t.setEndTime(endTime);
				t.setTaskTime(taskTime);
				t.setTaskState(rs.getString("taskState"));
			    System.out.println(t.getTaskState());
					if((t.getTaskState().equals("0")||t.getTaskState().equals("1"))&&compTime(endTime))
						t.setTaskState("任务过期未完成");
					else if(t.getTaskState().equals("0"))
					t.setTaskState("待领取");
					else if(t.getTaskState().equals("1"))
					t.setTaskState("进行中");
					else if (t.getTaskState().equals("2"))
					t.setTaskState("完成任务");
				t.setFaburen(employeeName);	
				t.setTemployeeID(rs.getString("employeeName"));
				
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return t;
	}

	@Override
	public boolean deleteTask(String tID) {
		String sql = "DELETE FROM task WHERE taskID= '"+ tID +"'";
		int result = 0 ;
		result = DbcpPool.executeUpdate(sql);
		DbcpPool.close();
		if(result>0)
			return true;
		else
			return false;
	}

}
