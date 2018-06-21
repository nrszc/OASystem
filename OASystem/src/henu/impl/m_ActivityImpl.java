package henu.impl;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.text.DecimalFormat;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;

import henu.IF.m_ActivityIF;
import henu.bean.Active;
import henu.bean.Employee;
import henu.util.DbcpPool;

public class m_ActivityImpl implements m_ActivityIF{
	//用返回一个list，用来显示我发起的活动投票的相关信息
		@Override
		public List<Active> myActivity(String employeeID) {
			String sql = "select * from active a,employee b where "
					+ "a.employeeID='"+employeeID+"' and a.employeeID=b.employeeID Order by createTime desc";
			ResultSet rs = DbcpPool.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Active> list = new ArrayList<Active>();
			float num;
			String result=null;
			try {
				while(rs.next())
				{
					Active d = new Active();
					d.setActiveID(rs.getString("activeID"));
					d.setActiveName(rs.getString("activeName"));
					d.setActiveInfo(rs.getString("activeInfo"));
					d.setActiveAgreeNum(rs.getInt("activeAgreeNum"));
					d.setActiveDisagreeNum(rs.getInt("activeDisagreeNum"));
					d.setActiveOthersNum(rs.getInt("activeOthersNum"));
					d.setSum(d.getActiveAgreeNum()+d.getActiveDisagreeNum()+d.getActiveOthersNum());
					d.setEmployeeName(rs.getString("employeeName"));
					String createTime = dateFormat.format(rs.getTimestamp("createTime"));
					d.setCreateTime(createTime);
					d.setScope(Scope(rs.getString("scope")));
					if(d.getSum()==0)
					{
						d.setAgreePercent("0.0%");
						d.setDisagreePercent("0.0%");
						d.setOthersPercent("0.0%");
					}
					else{
					num =(float)d.getActiveAgreeNum()*100/d.getSum();
	                DecimalFormat df = new DecimalFormat("0.0");
	                d.setAgreePercent(df.format(num)+"%");
	                num =(float)d.getActiveDisagreeNum()*100/d.getSum();
	                d.setDisagreePercent(df.format(num)+"%");
	                num =(float)d.getActiveOthersNum()*100/d.getSum();
	                d.setOthersPercent(df.format(num)+"%");
					}
					list.add(d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbcpPool.close();
			return list;
		}
		
		//获取活动投票的对象范围
		private String Scope(String scope)
		{
			System.out.println(scope);
			String sql = "select deptName from dept where deptID='"+scope+"'";
			ResultSet rs = DbcpPool.executeQuery(sql);
			try {
				while(rs.next())
				{
					scope = rs.getString("deptName");
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return scope;
		}

		//增加一个活动投票
		@Override
		public boolean addActivity(Active a) {
			String sql = "insert into Active(activeInfo,activeName,activeAgreeNum,activeDisagreeNum,"
					+ "activeOthersNum,employeeID,scope,createTime) values(?,?,?,?,?,?,?,?)";
			PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
			int  r=0;
			try{
				ps.setString(1, a.getActiveInfo());
				ps.setString(2, a.getActiveName());
				ps.setInt(3, 0);
				ps.setInt(4, 0);
				ps.setInt(5, 0);
				ps.setString(6, a.getEmployeeID());
				ps.setString(7, a.getScope());
				ps.setString(8, a.getCreateTime());
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

		//删除一个活动投票
		@Override
		public boolean deleteActivity(String activeID) {
			String sql = "DELETE FROM active WHERE activeID= '"+ activeID +"'";
			int result = 0 ;
			result = DbcpPool.executeUpdate(sql);
			DbcpPool.close();
			if(result>0)
				return true;
			else
				return false;
		}

		//用返回一个list，用来显示我从参与过的活动投票的相关信息
		@Override
		public List<Active> Activity_part(String employeeID) {
			String sql = "select * from active a,employee b,activeinfo c where "
					+ "c.employeeID='"+employeeID+"' and c.activeID=a.activeID "
					+ "and a.employeeID=b.employeeID Order by createTime desc";
			ResultSet rs = DbcpPool.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Active> list = new ArrayList<Active>();
			float num;
			String result=null;
			try {
				while(rs.next())
				{
					Active d = new Active();
					d.setActiveID(rs.getString("a.activeID"));
					d.setActiveName(rs.getString("activeName"));
					d.setActiveInfo(rs.getString("activeInfo"));
					d.setActiveAgreeNum(rs.getInt("activeAgreeNum"));
					d.setActiveDisagreeNum(rs.getInt("activeDisagreeNum"));
					d.setActiveOthersNum(rs.getInt("activeOthersNum"));
					d.setSum(d.getActiveAgreeNum()+d.getActiveDisagreeNum()+d.getActiveOthersNum());
					d.setEmployeeName(rs.getString("employeeName"));
					String createTime = dateFormat.format(rs.getTimestamp("createTime"));
					d.setCreateTime(createTime);
					d.setScope(Scope(rs.getString("scope")));
					d.setChoose(rs.getString("choose"));
					if(d.getSum()==0)
					{
						d.setAgreePercent("0.0%");
						d.setDisagreePercent("0.0%");
						d.setOthersPercent("0.0%");
					}
					else{
					num =(float)d.getActiveAgreeNum()*100/d.getSum();
	                DecimalFormat df = new DecimalFormat("0.0");
	                d.setAgreePercent(df.format(num)+"%");
	                num =(float)d.getActiveDisagreeNum()*100/d.getSum();
	                d.setDisagreePercent(df.format(num)+"%");
	                num =(float)d.getActiveOthersNum()*100/d.getSum();
	                d.setOthersPercent(df.format(num)+"%");
					}
					list.add(d);
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbcpPool.close();
			return list;
		}

		//用返回一个list，用来显示我从未参与过的活动投票的相关信息
		@Override
		public List<Active> Activity_unpart(Employee e) {
		    String sql = "select * from active a,employee b where (a.scope="+e.getDeptID()+" or a.scope=1) and a.employeeID=b.employeeID "
		    		+ " and activeID not in (select activeID from activeinfo c where c.employeeID = '"+e.getEmployeeID()+"')";
			ResultSet rs = DbcpPool.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Active> list = new ArrayList<Active>();
			try {
				while(rs.next())
				{
					Active d = new Active();
					d.setActiveID(rs.getString("a.activeID"));
					d.setActiveName(rs.getString("activeName"));
					d.setActiveInfo(rs.getString("activeInfo"));
					String createTime = dateFormat.format(rs.getTimestamp("createTime"));
					d.setCreateTime(createTime);
					d.setEmployeeID(rs.getString("a.employeeID"));
					d.setEmployeeName(rs.getString("b.employeeName"));
					list.add(d);
				}
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			DbcpPool.close();
			return list;
		}

		//返回一个Active的bean类，用来显示具体的活动投票信息
		@Override
		public Active Activity_detail(String activeID) {
			String sql = "select * from active a,employee b where "
					+ "a.activeID='"+activeID+"' and "
					+ "a.employeeID=b.employeeID";
			ResultSet rs = DbcpPool.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			Active d = new Active();
			try {
				while(rs.next())
				{
					d.setActiveID(rs.getString("a.activeID"));
					d.setActiveName(rs.getString("activeName"));
					d.setActiveInfo(rs.getString("activeInfo"));
					String createTime = dateFormat.format(rs.getTimestamp("createTime"));
					d.setCreateTime(createTime);
					d.setEmployeeID(rs.getString("a.employeeID"));
					d.setEmployeeName(rs.getString("b.employeeName"));
				}
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			DbcpPool.close();
			return d;
		}

		//新插入投票信息，再取出该具体信息，返回一个Active类
		@Override
		public Active Activity_result(Active a) {
			String sql = "insert into activeinfo(activeID,employeeID,choose) values(?,?,?)";
			PreparedStatement ps = DbcpPool.executePreparedStatement(sql);
			int  r=0;
			try{
				ps.setString(1, a.getActiveID());
				ps.setString(2, a.getEmployeeID());
				ps.setString(3, a.getChoose());
				r=ps.executeUpdate();
				ps.close();
			}catch(SQLException e)
			{
				e.printStackTrace();
			}
			if(a.getChoose().equals("0"))
			    sql = "update active set activeAgreeNum = activeAgreeNum+1 where activeID = '"+a.getActiveID()+"'";
			else if(a.getChoose().equals("1"))
			    sql = "update active set activeDisagreeNum = activeDisagreeNum+1 where activeID = '"+a.getActiveID()+"'";
			else
				sql = "update active set activeOthersNum = activeOthersNum+1 where activeID = '"+a.getActiveID()+"'";
			PreparedStatement ps1 = DbcpPool.executePreparedStatement(sql);
			try {
				ps1.executeUpdate();
			} catch (SQLException e1) {
				// TODO Auto-generated catch block
				e1.printStackTrace();
			}
			sql = "select * from active a,employee b,activeinfo c where "
					+ "a.activeID='"+a.getActiveID()+"' and c.employeeID='"+a.getEmployeeID()+"'"
					+ " and c.activeID=a.activeID "
					+ "and a.employeeID=b.employeeID Order by createTime desc";
			ResultSet rs = DbcpPool.executeQuery(sql);
			SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
			List<Active> list = new ArrayList<Active>();
			float num;
			String result=null;
			Active d = new Active();
			try {
				while(rs.next())
				{
					d.setActiveID(rs.getString("a.activeID"));
					d.setActiveName(rs.getString("activeName"));
					d.setActiveInfo(rs.getString("activeInfo"));
					d.setActiveAgreeNum(rs.getInt("activeAgreeNum"));
					d.setActiveDisagreeNum(rs.getInt("activeDisagreeNum"));
					d.setActiveOthersNum(rs.getInt("activeOthersNum"));
					d.setSum(d.getActiveAgreeNum()+d.getActiveDisagreeNum()+d.getActiveOthersNum());
					d.setEmployeeName(rs.getString("employeeName"));
					String createTime = dateFormat.format(rs.getTimestamp("createTime"));
					d.setCreateTime(createTime);
					d.setScope(Scope(rs.getString("scope")));
					d.setChoose(rs.getString("choose"));
					if(d.getSum()==0)
					{
						d.setAgreePercent("0.0%");
						d.setDisagreePercent("0.0%");
						d.setOthersPercent("0.0%");
					}
					else{
					num =(float)d.getActiveAgreeNum()*100/d.getSum();
	                DecimalFormat df = new DecimalFormat("0.0");
	                d.setAgreePercent(df.format(num)+"%");
	                num =(float)d.getActiveDisagreeNum()*100/d.getSum();
	                d.setDisagreePercent(df.format(num)+"%");
	                num =(float)d.getActiveOthersNum()*100/d.getSum();
	                d.setOthersPercent(df.format(num)+"%");
					}
				}
				ps1.close();
				rs.close();
			} catch (SQLException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			DbcpPool.close();
			return d;
		}

}
