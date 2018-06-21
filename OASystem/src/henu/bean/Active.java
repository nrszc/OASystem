package henu.bean;

public class Active {
	private String activeID;
	private String activeName;
	private String activeInfo;
	private int activeAgreeNum;
	private int activeDisagreeNum;
	private int activeOthersNum;
	private String agreePercent;
	private String disagreePercent;
	private String othersPercent;
	private String employeeID;
	private String employeeName;
	private String choose;
	public String getChoose() {
		return choose;
	}
	public void setChoose(String choose) {
		this.choose = choose;
	}
	public String getEmployeeName() {
		return employeeName;
	}
	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}
	public int getSum() {
		return sum;
	}
	public void setSum(int sum) {
		this.sum = sum;
	}
	private int state;
	private String createTime;
	private String scope;
	private int sum;
	public String getActiveID() {
		return activeID;
	}
	public void setActiveID(String activeID) {
		this.activeID = activeID;
	}
	public String getActiveName() {
		return activeName;
	}
	public void setActiveName(String activeName) {
		this.activeName = activeName;
	}
	public String getActiveInfo() {
		return activeInfo;
	}
	public void setActiveInfo(String activeInfo) {
		this.activeInfo = activeInfo;
	}
	public int getActiveAgreeNum() {
		return activeAgreeNum;
	}
	public void setActiveAgreeNum(int activeAgreeNum) {
		this.activeAgreeNum = activeAgreeNum;
	}
	public int getActiveDisagreeNum() {
		return activeDisagreeNum;
	}
	public void setActiveDisagreeNum(int activeDisagreeNum) {
		this.activeDisagreeNum = activeDisagreeNum;
	}
	public int getActiveOthersNum() {
		return activeOthersNum;
	}
	public void setActiveOthersNum(int activeOthersNum) {
		this.activeOthersNum = activeOthersNum;
	}
	public String getAgreePercent() {
		return agreePercent;
	}
	public void setAgreePercent(String agreePercent) {
		this.agreePercent = agreePercent;
	}
	public String getDisagreePercent() {
		return disagreePercent;
	}
	public void setDisagreePercent(String disagreePercent) {
		this.disagreePercent = disagreePercent;
	}
	public String getOthersPercent() {
		return othersPercent;
	}
	public void setOthersPercent(String othersPercent) {
		this.othersPercent = othersPercent;
	}
	public String getEmployeeID() {
		return employeeID;
	}
	public void setEmployeeID(String employeeID) {
		this.employeeID = employeeID;
	}
	public int getState() {
		return state;
	}
	public void setState(int state) {
		this.state = state;
	}
	public String getCreateTime() {
		return createTime;
	}
	public void setCreateTime(String createTime) {
		this.createTime = createTime;
	}
	public String getScope() {
		return scope;
	}
	public void setScope(String scope) {
		this.scope = scope;
	}
	
}
