package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.s_JobIF;
import henu.bean.Dept;
import henu.bean.Job;
import henu.util.DbcpPool;

public class s_JobImpl implements s_JobIF{
	
	//用List来保存所有部门信息记录
    public List<Job> showJob() {
    	String sql = "select * from job, dept where job.state=1 and job.deptID=dept.deptID";
		ResultSet rs = DbcpPool.executeQuery(sql);
		List<Job> list = new ArrayList<Job>();
		try {
			while(rs.next())
			{
				Job j = new Job();
				j.setJobID(rs.getString("jobID"));
				j.setDeptName(rs.getString("deptName"));
				j.setJobText(rs.getString("jobText"));
				j.setDeptID(rs.getString("deptID"));
				j.setJobName(rs.getString("jobName"));
				j.setLevel(rs.getString("level"));
				list.add(j);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	//添加新部门
	public boolean addJob(Job j) {
		String sql = "insert into job(deptID,jobText,jobName,level,state) values(?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, j.getDeptID());
			ps.setString(2, j.getJobText());
			ps.setString(3, j.getJobName());
			ps.setString(4, j.getLevel());
			ps.setString(5, "1");
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

	//删除部门
	public boolean deleteJob(String jobID) {
		String sql = null;
		sql = "update job set state=? where jobID = ?"; 
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setInt(1, 0);
		ps.setString(2, jobID);
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

	//修改部门信息
	public boolean updateJob(Job j) {
		String sql = null;
		sql = "update job set jobName=?, deptID=?,"
				+" jobText=?, level=? where jobID=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, j.getJobName());
		ps.setString(2, j.getDeptID());
		ps.setString(3, j.getJobText());
		ps.setString(4, j.getLevel());
		ps.setString(5, j.getJobID());
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

	//返回一个StringBuffer的字符串，作为部门的一个select下拉列表
	@Override
	public StringBuffer showDept() {
		String sql = "select * from dept where state=1 and deptID!=1";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'deptID':").append("'").append(rs.getString("deptID")).append("'").append(",");  
	                json.append("'deptName':").append("'")  
	                        .append(rs.getString("deptName").trim()).append("'");  
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
	public StringBuffer showJob(String deptID) {
		String sql = "select * from job where state=1 and deptID='"+deptID+"' ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'jobID':").append("'").append(rs.getString("jobID")).append("'").append(",");  
	                json.append("'jobName':").append("'")  
	                        .append(rs.getString("jobName").trim()).append("'").append(",");  
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
	public StringBuffer showDept1() {
		String sql = "select * from dept where state=1";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'deptID':").append("'").append(rs.getString("deptID")).append("'").append(",");  
	                json.append("'deptName':").append("'")  
	                        .append(rs.getString("deptName").trim()).append("'");  
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
}
