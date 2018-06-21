package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.m_NoteIF;
import henu.bean.Note;
import henu.util.DbcpPool;

public class m_NoteImpl implements m_NoteIF{
	@Override
	public List<Note> myNote( String employeeID) {
		// TODO Auto-generated method stub
		int s=0;
		String sql = "select * from note a,dept b,employee c where c.employeeID='"+employeeID+"' and "
				+ "a.noteScope = b.deptID and a.employeeID=c.employeeID order by noteTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Note> list = new ArrayList<Note>();
		try {
			while(rs.next())
			{
				Note n = new Note();
				n.setNoteID(rs.getString("noteID"));
				n.setDeptName(rs.getString("b.deptName"));
				n.setNoteName(rs.getString("noteName"));
				String text = rs.getString("noteInfo");
				text = text.replaceAll("<br>", ""); 
				text = text.replaceAll("&nbsp;", "");
				n.setNoteInfo(text);
				String noteTime = dateFormat.format(rs.getTimestamp("noteTime"));
				n.setNoteTime(noteTime);
				n.setEmployeeName(rs.getString("c.employeeName"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		System.out.println(s);
		DbcpPool.close();
		return list ;
	}

	@Override
	public boolean addNote(Note n) {
		// TODO Auto-generated method stub
		String sql = "insert into note(employeeID,noteName,noteInfo,noteTime,noteScope) values(?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, n.getEmployeeID());
			ps.setString(2, n.getNoteName());
			ps.setString(3, n.getNoteInfo());
			ps.setString(4, n.getNoteTime());
			ps.setString(5, n.getNoteScope());
			r=ps.executeUpdate();
			ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		if(r==0)
			return false;
		else 
			return true;
	}

	@Override
	public boolean deleteNote(String noteID) {
		// TODO Auto-generated method stub
		int result = 0;
		String sql = "delete from note where noteID='"+noteID+"'";
		result = DbcpPool.executeUpdate(sql);
		if(result>0)
			return true;
		else 
		    return false;
	}

	@Override
	public List<Note> allNote(int deptID) {
		// TODO Auto-generated method stub
		String sql = "select * from note a,dept b,employee c where "
				+ "a.noteScope = b.deptID and (a.noteScope=1 or a.noteScope="+deptID+")"
						+ " and a.employeeID=c.employeeID order by noteTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		List<Note> list = new ArrayList<Note>();
		try {
			while(rs.next())
			{
				Note n = new Note();
				n.setNoteID(rs.getString("noteID"));
				n.setDeptName(rs.getString("b.deptName"));
				n.setNoteName(rs.getString("noteName"));
				String text = rs.getString("noteInfo");
				text = text.replaceAll("<br>", ""); 
				text = text.replaceAll("&nbsp;", "");
				n.setNoteInfo(text);
				String noteTime = dateFormat.format(rs.getTimestamp("noteTime"));
				n.setNoteTime(noteTime);
				n.setEmployeeName(rs.getString("c.employeeName"));
				list.add(n);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list ;
	}

	@Override
	public Note noteDetail(String noteID) {
		String sql = "select * from note a,dept b,employee c where noteID='"+noteID+"' and "
				+ "a.noteScope = b.deptID and a.employeeID=c.employeeID order by noteTime desc";
		ResultSet rs = DbcpPool.executeQuery(sql);
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Note n = new Note();
		try {
			while(rs.next())
			{
				n.setNoteID(rs.getString("noteID"));
				n.setDeptName(rs.getString("b.deptName"));
				n.setNoteName(rs.getString("noteName"));
				n.setNoteInfo(rs.getString("noteInfo"));
				String noteTime = dateFormat.format(rs.getTimestamp("noteTime"));
				n.setNoteTime(noteTime);
				n.setEmployeeName(rs.getString("c.employeeName"));
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return n ;
	}

	
}
