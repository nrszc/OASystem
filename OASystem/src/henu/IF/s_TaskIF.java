package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Employee;
import henu.bean.Task;

public interface s_TaskIF {

	public	List<Task> s_release(String employeeID) throws ParseException;

	public	List<Employee> s_employee();

	public boolean s_addTask(Task tt, String id);

	public Task s_releaseDetail(String tID, String employeeName) throws ParseException;

	public boolean deleteTask(String tID);

}
