package henu.IF;

import java.util.List;

import henu.bean.CompanyFile;
import henu.bean.DeptFile;
import henu.bean.Employee;
import henu.bean.MyfileTable;
import henu.bean.PrivateFile;

public interface a_FileIF {
	List<MyfileTable> getMyfile(String fileFrom); //查询员工文件的历史上传
	List<MyfileTable> getDownload(String fileFrom);//查询员工可接收的文件
    public StringBuffer showDept(int deptID); //返回一个StringBuffer的字符串，作为部门的一个select下拉列表
    public List<Employee> showEmployee(String employeeID);  //展示所有员工
	boolean saveDeptfile(DeptFile df);   //保存部门文件
	boolean savePrivatefile(PrivateFile pf);  //保存私人文件
}
