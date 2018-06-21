package henu.IF;

import java.util.List;

import henu.bean.Note;

public interface s_NoteIF {

	public List<Note> myNote(String employee);
	
	public boolean addNote(Note n);
	
	public boolean deleteNote(String noteID);
	
	public List<Note> allNote();

	public Note noteDetail(String noteID);
}
