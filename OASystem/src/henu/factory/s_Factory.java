package henu.factory;

import henu.IF.s_ActivityIF;
import henu.IF.s_CalendarIF;
import henu.IF.s_ColleagueIF;
import henu.IF.s_DeptIF;
import henu.IF.s_FileIF;
import henu.IF.s_JobIF;
import henu.IF.s_LeaveIF;
import henu.IF.s_MailIF;
import henu.IF.s_MeetingIF;
import henu.IF.s_NoteIF;
import henu.IF.s_PersonIF;
import henu.IF.s_TaskIF;
import henu.impl.s_ActivityImpl;
import henu.impl.s_CalendarImpl;
import henu.impl.s_ColleagueImpl;
import henu.impl.s_DeptImpl;
import henu.impl.s_FileImpl;
import henu.impl.s_JobImpl;
import henu.impl.s_LeaveImpl;
import henu.impl.s_MailImpl;
import henu.impl.s_MeetingImpl;
import henu.impl.s_NoteImpl;
import henu.impl.s_PersonImpl;
import henu.impl.s_TaskImpl;

public class s_Factory {
	public static s_DeptIF getDeptInstance()
	{
		return new s_DeptImpl();
	}
	
	public static s_JobIF getJobInstance()
	{
		return new s_JobImpl();
	}
	
	public static s_ActivityIF getActivityInstance()
	{
		return new s_ActivityImpl();
	}
	
	public static s_LeaveIF getLeaveInstance()
	{
		return new s_LeaveImpl();
	}
	
	public static s_MailIF getMailInstance()
	{
		return new s_MailImpl();
	}
	
	public static s_CalendarIF getCalendarInstance()
	{
		return new s_CalendarImpl();
	}
	public static s_ColleagueIF getColleagueInstance()
	{
		return new s_ColleagueImpl();
	}
	public static s_NoteIF getNoteInstance()
	{
		return new s_NoteImpl();
	}
	public static s_FileIF getFileInstance()
	{
		return new s_FileImpl();
	}
	public static s_MeetingIF getMeetingInstance()
	{
		return new s_MeetingImpl();
	}
	public static s_TaskIF getTaskInstance()
	{
		return new s_TaskImpl();
	}
	public static s_PersonIF getPersonInstance()
	{
		return new s_PersonImpl();
	}
	
}
