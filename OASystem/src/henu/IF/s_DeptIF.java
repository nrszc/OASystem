package henu.IF;

import java.util.List;

import henu.bean.Dept;

public interface s_DeptIF {
    public List<Dept> showDept(); //显示所有部门
    public boolean addDept(Dept d);     //新添加部门
    public boolean deleteDept(String deptID);  //删除部门
    public boolean updateDept(Dept d);  //修改部门信息
}
