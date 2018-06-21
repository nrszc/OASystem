package henu.IF;

import java.text.ParseException;
import java.util.List;

import henu.bean.Leave;

public interface s_LeaveIF {
	public List<Leave> showLeave(String employeeID) throws ParseException;  //执行SQL查询语句，查询所有我审批过的请假信息，保存并返回List<Leave>
    public Leave leave_detail(String leaveID);  //查询具体某个已审批请假，用Leave返回这个请假的具体信息
    public List<Leave> leave_unapproved(String employeeID) throws ParseException;  //执行SQL查询语句，查询所有我未审批过的请假信息，保存并返回List<Leave>
    public Leave leave_detail1(String leaveID);  //查询具体某个未审批请假，用Leave返回这个请假的具体信息
    public boolean approve(Leave l);
}
