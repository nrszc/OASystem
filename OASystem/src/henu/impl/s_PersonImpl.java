package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.s_PersonIF;
import henu.bean.Employee;
import henu.util.DbcpPool;

public class s_PersonImpl implements s_PersonIF {

	@Override
	public Employee findInfo(String employeeID) {
		// TODO Auto-generated method stub
		
		String sql="select * from employee a, job b, dept c where "
				+ "a.employeeID='"+employeeID+"' and a.jobID=b.jobID and a.deptID=c.deptID";
		ResultSet rs = DbcpPool.executeQuery(sql);
		Employee em = new Employee();
		try {
			while(rs.next())
			{
				em.setEmployeeID(rs.getString("employeeID"));
				em.setEmployeeName(rs.getString("employeeName"));
				em.setDeptName(rs.getString("c.deptName"));
				em.setDeptID(rs.getInt("a.deptID"));
				em.setJobID(rs.getInt("a.jobID"));
				em.setJobName(rs.getString("b.jobName"));
				em.setSex(rs.getString("sex"));
				System.out.println(em.getSex());
				em.setPhone(rs.getString("phone"));
				em.setBirthdate(rs.getString("birthdate"));
				em.setEmail(rs.getString("email"));
				em.setNOcode(rs.getString("NOcode"));
				em.setLearn(rs.getString("learn"));
				em.setAddress(rs.getString("address"));
				em.setQQ(rs.getString("QQ"));
				em.setAgreement(rs.getString("agreement"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return em ;
	}

	@Override
	public boolean updateInfo(Employee em) {
		// TODO Auto-generated method stub
		String	sql = "update employee set Phone=?,birthdate=?,address=?,QQ=?,email=? where employeeID=?";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, em.getPhone());
		ps.setString(2, em.getBirthdate());
		ps.setString(3, em.getAddress());
		ps.setString(4, em.getQQ());
		ps.setString(5, em.getEmail());
		ps.setString(6, em.getEmployeeID());
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

	@Override
	public boolean updatePwd(Employee em) {
		// TODO Auto-generated method stub
		String sql = null;
 		 sql = "select count(*) from employee where employeeID='"+em.getEmployeeID()+"' and pwd ='"+em.getPwd()+"'";
 	     ResultSet rs = DbcpPool.executeQuery(sql);
 	     int count = 0;
 	     try {
			if(rs.next())
			{
				count = rs.getInt("count(*)");
			}
			
			rs.close();
		} catch (SQLException e) {
			e.printStackTrace();
		}
		 int result = 0;
		if(count>0)
		{
			System.out.println("11111111");
			System.out.println(em.getNewpwd());
			sql = "update employee set pwd=? where employeeID=?";
		    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		   
		    try {
			ps.setString(1, em.getNewpwd());
			ps.setString(2, em.getEmployeeID());
			result = ps.executeUpdate();
			ps.close();
			
		    } catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		    }
		    DbcpPool.close();
		}
		System.out.println(result);
		if(result>0)
		       return true;
		    else 
		       return false;
		
	}

}
