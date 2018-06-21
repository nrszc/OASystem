package henu.IF;

import java.util.List;
import henu.bean.Job;


public interface s_JobIF {
	public List<Job> showJob(); //显示所有职位
    public boolean addJob(Job j);     //新添加职位
    public boolean deleteJob(String jobID);  //删除职位
    public boolean updateJob(Job j);  //修改部门职位
    public StringBuffer showDept(); //返回一个StringBuffer的字符串，作为部门的一个select下拉列表
    public StringBuffer showJob(String deptID); //返回一个StringBuffer的字符串，作为部门的一个select下拉列表
	public StringBuffer showDept1();

}
