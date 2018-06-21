package henu.IF;

import java.util.List;

import henu.bean.Dept;
import henu.bean.Employee;

public interface a_ColleagueIF {

	public List<Dept> search();

	public List<Employee> find();
	
	public List<Employee> find(String deptID);
	
	public boolean addEmployee(Employee em);
	
	public Employee find_detail(String employeeID);
	
}
