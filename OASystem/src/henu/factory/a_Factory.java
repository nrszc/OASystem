package henu.factory;

import henu.IF.a_ActivityIF;
import henu.IF.a_ColleagueIF;
import henu.IF.a_FileIF;
import henu.IF.a_LeaveIF;
import henu.IF.a_MailIF;
import henu.IF.a_MeetingIF;
import henu.IF.a_NoteIF;
import henu.IF.a_TaskIF;
import henu.IF.s_NoteIF;
import henu.impl.a_ActivityImpl;
import henu.impl.a_ColleagueImpl;
import henu.impl.a_FileImpl;
import henu.impl.a_LeaveImpl;
import henu.impl.a_MailImpl;
import henu.impl.a_MeetingImpl;
import henu.impl.a_NoteImpl;
import henu.impl.a_TaskImpl;
import henu.impl.s_NoteImpl;

public class a_Factory {
	public static a_ActivityIF getActivityInstance()
	{
		return new a_ActivityImpl();
	}
	public static a_MailIF getMailInstance()
	{
		return new a_MailImpl();
	}
	public static a_LeaveIF getLeaveInstance()
	{
		return new a_LeaveImpl();
	}
	public static a_FileIF getFileInstance()
	{
		return new a_FileImpl();
	}
	public static a_ColleagueIF getColleagueInstance()
	{
		return new a_ColleagueImpl();
	}
	public static a_MeetingIF getMeetingInstance()
	{
		return new a_MeetingImpl();
	}
	public static a_TaskIF getTaskInstance()
	{
		return new a_TaskImpl();
	}
	public static a_NoteIF getNoteInstance()
	{
		return new a_NoteImpl();
	}
}
