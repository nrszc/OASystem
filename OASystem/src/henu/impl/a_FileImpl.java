package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import henu.IF.a_FileIF;
import henu.bean.CompanyFile;
import henu.bean.DeptFile;
import henu.bean.Employee;
import henu.bean.MyfileTable;
import henu.bean.PrivateFile;
import henu.util.DbcpPool;

public class a_FileImpl implements a_FileIF{
	@Override
	public List<MyfileTable> getDownload(String fileFrom) {
		String sql = null;
		//查找公司文件
		sql = "select * from companyfile a, employee b where a.fileFrom=b.employeeID";
		List<MyfileTable> list = new ArrayList<MyfileTable>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("fileID"));
			mf.setFileName(rs.getString("fileName"));
			mf.setFileText(rs.getString("fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileFrom(rs.getString("employeeName"));
			String fileType = "公司文件";
			mf.setFileType(fileType);
			list.add(mf);
			System.out.println("5555555555555");
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//根据员工Id查找该员工所在部门的部门文件
		sql = "select * from employee a, deptfile b "
				+ "where a.deptID=b.deptID and a.employeeID = '"+fileFrom+"'";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("b.fileID"));
			mf.setFileName(rs.getString("b.fileName"));
			mf.setFileText(rs.getString("b.fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("b.fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileFrom(rs.getString("a.employeeName"));
			String fileType = "部门文件";
			mf.setFileType(fileType);
			list.add(mf);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//查找该员工收到的私人文件
		sql = "select * from employee a, privatefile b "
				+ "where a.employeeID=b.fileFrom and b.fileTo= '"+fileFrom+"'";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("b.fileID"));
			mf.setFileName(rs.getString("b.fileName"));
			mf.setFileText(rs.getString("b.fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("b.fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileFrom(rs.getString("a.employeeName"));
			String fileType = "私人文件";
			mf.setFileType(fileType);
			list.add(mf);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		DbcpPool.close();
		Collections.sort(list, new Comparator() {  
            @Override  
            public int compare(Object o1, Object o2) {  
                MyfileTable str1=(MyfileTable) o1;  
                MyfileTable str2=(MyfileTable) o2;  
                if (str1.getFileTime().compareToIgnoreCase(str2.getFileTime())<0){    
                    return 1;    
                }    
                return -1;    
            }  
        });  
		return list;
	}


	@Override
	public List<MyfileTable> getMyfile(String fileFrom) {
		String sql=null;
		//从companyfile查找该员工上传的记录，通过查找表中的fileFrom
		sql = "select * from companyfile "
				+ "where fileFrom = '"+fileFrom+"'";
		List<MyfileTable>list = new ArrayList<MyfileTable>();
		SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		ResultSet rs = null;
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("fileID"));
			mf.setFileName(rs.getString("fileName"));
			mf.setFileText(rs.getString("fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileTo("全公司");
			String fileType = "公司文件";
			mf.setFileType(fileType);

			
			list.add(mf);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//从deptfile查找该员工上传的记录，通过查找表中的fileFrom
		sql = "select * from deptfile a,dept b "
				+ "where fileFrom = '"+fileFrom+"' and a.deptID=b.deptID";
		
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("fileID"));
			mf.setFileName(rs.getString("fileName"));
			mf.setFileText(rs.getString("fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileTo(rs.getString("b.deptName"));
			String fileType = "部门文件";
			mf.setFileType(fileType);
			list.add(mf);
			}
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		//从privatefile查找该员工上传的记录，通过查找表中的fileFrom
		sql = "select * from privatefile a,employee b "
				+ "where fileFrom = '"+fileFrom+"' and a.fileTo=b.employeeID";
		try{
			rs = DbcpPool.executeQuery(sql);
			while(rs.next()){
			MyfileTable mf = new  MyfileTable();
			mf.setFileID(rs.getString("fileID"));
			mf.setFileName(rs.getString("fileName"));
			mf.setFileText(rs.getString("fileText"));
			String fileTime = dateFormat.format( rs.getTimestamp("fileTime") );
			mf.setFileTime(fileTime);
			mf.setFileTo(rs.getString("b.employeeName"));
			String fileType = "私人文件";
			mf.setFileType(fileType);
			list.add(mf);
			}
			rs.close();
		}catch(Exception e)
		{
			e.printStackTrace();
		}
		
		DbcpPool.close();
		Collections.sort(list, new Comparator() {  
            @Override  
            public int compare(Object o1, Object o2) {  
                MyfileTable str1=(MyfileTable) o1;  
                MyfileTable str2=(MyfileTable) o2;  
                if (str1.getFileTime().compareToIgnoreCase(str2.getFileTime())<0){    
                    return 1;    
                }    
                return -1;    
            }  
        });  
		return list;
	}


	@Override
	public StringBuffer showDept(int deptID) {
		String sql = "select * from dept where deptID="+deptID+"";
		ResultSet rs = DbcpPool.executeQuery(sql);
		StringBuffer json = new StringBuffer();  
        json.append("[");  
		try {
			while(rs.next())
			{
				 json.append('{');  
	                //注意每一个key-value对都要在引号之中，单引号或者双引号都可以  
	                json.append("'deptID':").append("'").append(rs.getString("deptID")).append("'").append(",");  
	                json.append("'deptName':").append("'")  
	                        .append(rs.getString("deptName").trim()).append("'");  
	                json.append("},");  
			}
		} catch (SQLException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		DbcpPool.close();
		json.deleteCharAt(json.length() - 1);  
	    json.append("]");  
		return json;
	}


	@Override
	public List<Employee> showEmployee(String employeeID) {
		String sql = null;
  		sql = "select * from employee a, job b, dept c "
  				+ "where a.deptID = c.deptID and a.jobID = b.jobID "
  				+ "and workState=1 and a.employeeID!='"+employeeID+"' order by c.deptID ";
  	    ResultSet rs = DbcpPool.executeQuery(sql);
  	    List<Employee> list = new ArrayList<Employee>();
  	    try {
			while(rs.next())
			{
		  	    Employee em = new Employee();
				em.setEmployeeID(rs.getString("employeeID"));
				em.setEmployeeName(rs.getString("employeeName"));
				em.setDeptName(rs.getString("c.deptName"));
				em.setJobName(rs.getString("b.jobName"));
				System.out.println("=======");
				list.add(em);
			}
			rs.close();
		} catch (SQLException e1) {
			e1.printStackTrace();
		}
		DbcpPool.close();
		return list;
	}


	@Override
	public boolean saveDeptfile(DeptFile df) {
		String sql = "insert into deptfile(fileTime,fileText,fileFrom,fileName,deptID) values(?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int r=0;
		try {
			ps.setString(1, df.getFileTime());
			ps.setString(2, df.getFileText());
			ps.setString(3, df.getFileFrom());
			ps.setString(4, df.getFileName());
			ps.setString(5, df.getDeptID());
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


	@Override
	public boolean savePrivatefile(PrivateFile pf) {
		String sql = "insert into privatefile(fileTime,fileText,fileFrom,fileTo,fileName) values(?,?,?,?,?)";
		PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
		int r=0;
		String[] strArray = null; 
		strArray = pf.getFileTo().split(",");
		try {
			for(int i=0;i<strArray.length;i++){
			ps.setString(1, pf.getFileTime());
			ps.setString(2, pf.getFileText());
			ps.setString(3, pf.getFileFrom());
			ps.setString(4, strArray[i]);
			ps.setString(5, pf.getFileName());
			r=ps.executeUpdate();
			if(r==0)
				return false;
			}
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
}
