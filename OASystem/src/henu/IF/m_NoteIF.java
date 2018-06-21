package henu.IF;

import java.util.List;

import henu.bean.Note;

public interface m_NoteIF {
public List<Note> myNote(String employee);
	
	public boolean addNote(Note n);
	
	public boolean deleteNote(String noteID);
	
	public List<Note> allNote(int deptID);

	public Note noteDetail(String noteID);

}
