package henu.bean;

public class Note {
	private String NoteID;
	private String employeeID;
	private String noteName;
	private String noteInfo;
	private String noteTime;
	private String noteScope;
	private String employeeName;
	private String deptName;
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public String getDeptName() {
		return deptName;
	}
	public void setDeptName(String deptName) {
		this.deptName = deptName;
	}
	public String getNoteID() {
		return NoteID;
	}
	public void setNoteID(String noteID) {
		NoteID = noteID;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getNoteName() {
		return noteName;
	}
	public void setNoteName(String noteName) {
		this.noteName = noteName;
	}
	public String getNoteInfo() {
		return noteInfo;
	}
	public void setNoteInfo(String noteInfo) {
		this.noteInfo = noteInfo;
	}
	public String getNoteTime() {
		return noteTime;
	}
	public void setNoteTime(String noteTime) {
		this.noteTime = noteTime;
	}
	public String getNoteScope() {
		return noteScope;
	}
	public void setNoteScope(String noteScope) {
		this.noteScope = noteScope;
	}

}
