package henu.IF;

import java.util.List;

import henu.bean.Employee;

public interface s_PersonIF {
	
	public Employee findInfo(String employeeID);
	
	public boolean updateInfo(Employee em);
	
	public boolean updatePwd(Employee em);

}
