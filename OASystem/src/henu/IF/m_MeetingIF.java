package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Employee;
import henu.bean.Meeting;

public interface m_MeetingIF {

	List<Meeting> m_meeting(String employeeID) throws ParseException;

	List<Employee> m_employee(int deptID);

	boolean addMeeting(Meeting mm);

	void addMeeting(String id);

	List<Meeting> m_release(String employeeID) throws ParseException;

	Meeting meetingDetail(String meetingID) throws ParseException;

	boolean deleteMeeting(String meetingID);

}
