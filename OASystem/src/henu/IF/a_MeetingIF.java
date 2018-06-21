package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Meeting;

public interface a_MeetingIF {

	List<Meeting> a_meeting(String employeeID) throws ParseException;

	Meeting meetingDetail(String meetingID) throws ParseException;

}
