package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

import henu.IF.s_DeptIF;
import henu.bean.Dept;
import henu.util.DbcpPool;

public class s_DeptImpl implements s_DeptIF{
	
	//用List来保存所有部门信息记录
    public List<Dept> showDept() {
    	String sql = "select * from dept where state=1 ";
		ResultSet rs = DbcpPool.executeQuery(sql);
		List<Dept> list = new ArrayList<Dept>();
		try {
			while(rs.next())
			{
				Dept d = new Dept();
				d.setDeptID(rs.getString("deptID"));
				d.setDeptName(rs.getString("deptName"));
				d.setDeptText(rs.getString("deptText"));
				list.add(d);
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}

	//添加新部门
	public boolean addDept(Dept d) {
		String sql = "insert into dept(deptName,deptText,state) values(?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int  r=0;
		try{
			ps.setString(1, d.getDeptName());
			ps.setString(2, d.getDeptText());
			ps.setString(3, "1");
			r=ps.executeUpdate();
			ps.close();
		}catch(SQLException e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		if(r==0)
			return false;
		else 
			return true;
	}

	//删除部门
	public boolean deleteDept(String deptID) {
		String sql = null;
		sql = "update dept set state=? where deptID = ?"; 
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setInt(1, 0);
		ps.setString(2, deptID);
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    DbcpPool.close();
	    if(result>0)
	       return true;
	    else 
	       return false;
	}

	//修改部门信息
	public boolean updateDept(Dept d) {
		String sql = null;
		sql = "update dept set deptName=?,"
				+"deptText=? where deptID=?";
	    PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
	    int result = 0;
	    try {
		ps.setString(1, d.getDeptName());
		ps.setString(2, d.getDeptText());
		ps.setString(3, d.getDeptID());
		result = ps.executeUpdate();
		ps.close();
	    } catch (SQLException e) {
		// TODO Auto-generated catch block
		e.printStackTrace();
	    }
	    DbcpPool.close();
	    if(result>0)
	       return true;
	    else 
	       return false;
	}
}
