package henu.IF;

import java.util.List;

import henu.bean.Leave;

public interface a_LeaveIF {
	public StringBuffer showEmployee(int deptID);
	public boolean addLeave(Leave l);
	public List<Leave> myLeave(String employeeID);     //返回我请假的list信息
	public StringBuffer showEmployee1();
	public Leave leave_detail(String leaveID);

}
