package henu.IF;

import java.util.List;

import henu.bean.Employee;
import henu.bean.Receive;
import henu.bean.Send;

public interface s_MailIF {
	public List<Receive> receiveBox(String employeeID); //查询接收的所有邮件并保存到List中去，返回一个List
	public List<Send> sendBox(String employeeID);  //查询个人发送的所有邮件并保存到List中去，返回一个List
	public boolean send(Send s); //新发送一个邮件，将邮件信箱保存到数据库
	public Receive  receiveDetail(String receiveID); //查询一个具体的接收邮件的具体信息，返回一个bean类
	public Send sendDetail(String sendID); //查询一个具体的发送邮件记录的具体信息，返回一个bean类
    public List<Employee> addEmployee(String employeeID); //查询所有不包括自己的员工
    public boolean deleteReceive(String[] item);  //删除多个接收邮件记录
    public boolean deleteSend(String[] item);  //删除多个发送邮件记录
}
