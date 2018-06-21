package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Task;

public interface a_TaskIF {

	List<Task> a_myTask(String employeeID) throws ParseException;

	Task mytask_detail(String tID, String employeeName) throws ParseException;

	void handle(String tID, String taskstate);

}
