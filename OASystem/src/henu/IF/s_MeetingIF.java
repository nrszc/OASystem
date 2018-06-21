package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Employee;
import henu.bean.Meeting;

public interface s_MeetingIF {
public List<Meeting> s_meeting(String employeeID) throws ParseException;

public List<Meeting> s_release(String employeeID) throws ParseException;

public List<Employee> s_employee();

public boolean addMeeting(Meeting mm);

public void addMeeting(String id);

public Meeting meetingDetail(String meetingID) throws ParseException;

public boolean deleteMeeting(String meetingID);


}
