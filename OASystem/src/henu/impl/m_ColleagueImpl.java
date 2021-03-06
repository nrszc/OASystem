package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.m_ColleagueIF;
import henu.bean.Dept;
import henu.bean.Employee;
import henu.util.DbcpPool;

public class m_ColleagueImpl implements m_ColleagueIF{
	@Override
	public List<Dept> search() {
		// TODO Auto-generated method stub
		
		    	String sql = "select * from dept where state=1 and deptID!=1 order by deptID";
				ResultSet rs = DbcpPool.executeQuery(sql);
				List<Dept> list = new ArrayList<Dept>();
				try {
					while(rs.next())
					{
						Dept d = new Dept();
						d.setDeptID(rs.getString("deptID"));
						d.setDeptName(rs.getString("deptName"));
						d.setDeptText(rs.getString("deptText"));
						list.add(d);
					}
				} catch (SQLException e) {
					// TODO Auto-generated catch block
					e.printStackTrace();
				}
				return list ;
			}

	@Override
	public List<Employee> find() {
		// TODO Auto-generated method stub
		
		String sql="select * from employee a,dept b,job c "
				+ "where a.deptID=3 and a.deptID=b.deptID and a.jobID=c.jobID and a.workState=1" ;
		ResultSet rs = DbcpPool.executeQuery(sql);
		List<Employee> list = new ArrayList<Employee>();
		try {
			while(rs.next())
			{
				Employee e = new Employee();
				e.setEmployeeID(rs.getString("employeeID"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setDeptName(rs.getString("deptName"));
				e.setJobName(rs.getString("jobName"));
				e.setSex(rs.getString("sex"));
				e.setPhone(rs.getString("phone"));
				e.setQQ(rs.getString("QQ"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list ;
	}

	@Override
	public List<Employee> find(String deptID) {

		String sql="select * from employee a,dept b,job c "
				+ "where a.deptID='"+deptID+"' and a.deptID=b.deptID and a.jobID=c.jobID and a.workState=1 "
						+ "order by case when c.level='高级' then 0 when c.level='中级' then 1 else 2 end" ;
		ResultSet rs = DbcpPool.executeQuery(sql);
		List<Employee> list = new ArrayList<Employee>();
		try {
			while(rs.next())
			{
				Employee e = new Employee();
				e.setEmployeeID(rs.getString("employeeID"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setDeptName(rs.getString("deptName"));
				e.setJobName(rs.getString("jobName"));
				e.setSex(rs.getString("sex"));
				e.setPhone(rs.getString("phone"));
				e.setQQ(rs.getString("QQ"));
				list.add(e);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list ;
	}
	
	@Override
	public boolean addEmployee(Employee em) {
		// TODO Auto-generated method stub
		String sql = "insert into employee(employeeID,employeeName,sex,jobID,deptID,workState,pwd,"
				+ "phone,address,NOcode,learn) values(?,?,?,?,?,?,?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, em.getEmployeeID());
			ps.setString(2, em.getEmployeeName());
			ps.setString(3, em.getSex());
			ps.setString(4, String.valueOf(em.getJobID()));
			ps.setString(5, String.valueOf(em.getDeptID()));
			ps.setString(6, "1");
			ps.setString(7, em.getPwd());
			ps.setString(8, em.getPhone());
			ps.setString(9, em.getAddress());
			ps.setString(10, em.getNOcode());
			ps.setString(11, em.getLearn());
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
	public Employee find_detail(String employeeID) {

		String sql="select * from employee a,dept b,job c "
				+ "where a.deptID=b.deptID and a.jobID=c.jobID and a.employeeID='"+employeeID+"'";
		ResultSet rs = DbcpPool.executeQuery(sql);
		Employee e = new Employee();
		try {
			while(rs.next())
			{
				e.setEmployeeID(rs.getString("employeeID"));
				e.setEmployeeName(rs.getString("employeeName"));
				e.setDeptName(rs.getString("deptName"));
				e.setJobName(rs.getString("jobName"));
				e.setSex(rs.getString("sex"));
				e.setPhone(rs.getString("phone"));
				e.setQQ(rs.getString("QQ"));
				e.setBirthdate(rs.getString("birthdate"));
				e.setAddress(rs.getString("address"));
				e.setAgreement(rs.getString("agreement"));
				e.setEmail(rs.getString("email"));
				e.setLearn(rs.getString("learn"));
				e.setNOcode(rs.getString("NOcode"));
			}
		} catch (SQLException e1) {
			// TODO Auto-generated catch block
			e1.printStackTrace();
		}
		DbcpPool.close();
		return e ;
	}

		@Override
		public StringBuffer showJob(int deptID) {
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

	
}
