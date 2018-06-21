package henu.factory;

import henu.IF.m_ActivityIF;
import henu.IF.m_ColleagueIF;
import henu.IF.m_FileIF;
import henu.IF.m_LeaveIF;
import henu.IF.m_MailIF;
import henu.IF.m_MeetingIF;
import henu.IF.m_NoteIF;
import henu.IF.m_TaskIF;
import henu.IF.s_NoteIF;
import henu.impl.m_ActivityImpl;
import henu.impl.m_ColleagueImpl;
import henu.impl.m_FileImpl;
import henu.impl.m_LeaveImpl;
import henu.impl.m_MailImpl;
import henu.impl.m_MeetingImpl;
import henu.impl.m_NoteImpl;
import henu.impl.m_TaskImpl;
import henu.impl.s_NoteImpl;

public class m_Factory {
	public static m_ActivityIF getActivityInstance()
	{
		return new m_ActivityImpl();
	}
	public static m_MailIF getMailInstance()
	{
		return new m_MailImpl();
	}
	public static m_LeaveIF getLeaveInstance()
	{
		return new m_LeaveImpl();
	}
	public static m_FileIF getFileInstance()
	{
		return new m_FileImpl();
	}
	public static m_ColleagueIF getColleagueInstance()
	{
		return new m_ColleagueImpl();
	}
	public static m_MeetingIF getMeetingInstance()
	{
		return new m_MeetingImpl();
	}
	public static m_TaskIF getTaskInstance()
	{
		return new m_TaskImpl();
	}
	public static m_NoteIF getNoteInstance()
	{
		return new m_NoteImpl();
	}
}
