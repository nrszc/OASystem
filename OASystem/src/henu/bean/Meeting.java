package henu.bean;

public class Meeting {
	private String meetingID;
	private String MeetingText;
	private String meetingName;
	private String hall;
	private String startTime;
	private String endTime;
	private String createTime;
	private String condition;
	private String employeeID;
	private String isPart;
	public String getIsPart() {
		return isPart;
	}
	public void setIsPart(String isPart) {
		this.isPart = isPart;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public String getHall() {
		return hall;
	}
	public void setHall(String hall) {
		this.hall = hall;
	}
	
	public String getCondition() {
		return condition;
	}
	public void setCondition(String condition) {
		this.condition = condition;
	}
	public String getStartTime() {
		return startTime;
	}
	public void setStartTime(String startTime) {
		this.startTime = startTime;
	}
	public String getEndTime() {
		return endTime;
	}
	public void setEndTime(String endTime) {
		this.endTime = endTime;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getMeetingID() {
		return meetingID;
	}
	public void setMeetingID(String meetingID) {
		this.meetingID = meetingID;
	}
	public String getMeetingText() {
		return MeetingText;
	}
	public void setMeetingText(String meetingText) {
		MeetingText = meetingText;
	}
	public String getMeetingName() {
		return meetingName;
	}
	public void setMeetingName(String meetingName) {
		this.meetingName = meetingName;
	}

}
