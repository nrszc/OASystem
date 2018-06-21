package henu.IF;

import java.util.List;

import henu.bean.Dept;
import henu.bean.Employee;

public interface m_ColleagueIF {

	public List<Dept> search();

	public List<Employee> find();
	
	public List<Employee> find(String deptID);

	public Employee find_detail(String employeeID);

	boolean addEmployee(Employee em);

	public StringBuffer showJob(int deptID);
}



