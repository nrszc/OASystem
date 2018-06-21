package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Employee;
import henu.bean.Task;

public interface m_TaskIF {

	List<Task> m_myTask(String employeeID) throws ParseException;

	Task mytask_detail(String tID, String employeeName) throws ParseException;

	void handle(String tID, String taskstate);

	List<Employee> m_employee(int deptID);

	boolean m_addTask(Task tt, String id);

	List<Task> m_release(String employeeID) throws ParseException;

	boolean deleteTask(String tID);

	Task m_releaseDetail(String tID, String employeeName) throws ParseException;

	

}
