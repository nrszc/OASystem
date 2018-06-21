package henu.impl;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.LoginIF;
import henu.bean.Employee;
import henu.util.DbcpPool;

public class LoginImpl implements LoginIF{

	/*
	 * 登录操作
	 * @see henu.IF.LoginIF#Login(henu.bean.Employee)
	 */
	@Override
	public Employee Login(Employee e) {
		String sql = null;
  		sql = "select * from employee a, job b, dept c"
  		 	+ " where employeeID='"+e.getEmployeeID()+"' and pwd ='"+e.getPwd()+"' "
  		 	+ "and a.jobID = b.jobID and a.deptID = c.deptID";
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    Employee em = new Employee();
  	    try {
			while(rs.next())
			{
				em.setEmployeeID(rs.getString("employeeID"));
				em.setEmployeeName(rs.getString("employeeName"));
				em.setLevel(rs.getString("level"));
				em.setDeptID(rs.getInt("c.deptID"));
				em.setDeptName(rs.getString("c.deptName"));
				em.setJobName(rs.getString("b.jobName"));
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return em;
	}

	@Override
	public List<Employee> Test() {
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

}
