package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.m_ActivityIF;
import henu.IF.s_ActivityIF;
import henu.bean.Active;
import henu.bean.Employee;
import henu.factory.m_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class m_Activity
 */
@WebServlet("/m_Activity")
public class m_Activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_Activity() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myway(request, response);
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		    case "myactivity"       : myactivity(request, response);       break;
		    case "addactivity"      : addactivity(request, response);      break;
		    case "deleteactivity"   : deleteactivity(request, response);   break;
		    case "activity_part"    : activity_part(request, response);    break;
		    case "activity_unpart"  : activity_unpart(request, response);  break;
		    case "activity_detail"  : activity_detail(request, response);  break;
		    case "activity_result"  : activity_result(request, response);  break;
		}
	}
	
	//插入新活动投票信息，再返回一个储存Active活动投票信息的bean类，再显示到结果界面上
	private void activity_result(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sd = m_Factory.getActivityInstance();
		Active a = new Active();
		String activeID = request.getParameter("activeID");
		String choose = request.getParameter("choose");
		Employee employee = (Employee)request.getSession().getAttribute("em");
		a.setActiveID(activeID);
		a.setChoose(choose);
		a.setEmployeeID(employee.getEmployeeID());
		Active active = sd.Activity_result(a);
		request.getSession().setAttribute("activity", active);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/activity_result.jsp'</script>");
	}

	//显示选中的未参与过的活动投票的相关信息
	private void activity_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sd = m_Factory.getActivityInstance();
		String activeID = request.getParameter("activeID");
		Active active = sd.Activity_detail(activeID);
		request.getSession().setAttribute("activity", active);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/activity_detail.jsp'</script>");
	}

	//显示我未参与过的活动投票的相关信息
	private void activity_unpart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sd = m_Factory.getActivityInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Active> list = sd.Activity_unpart(employee);
		request.getSession().setAttribute("active", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/activity_unpart.jsp'</script>");
	}

	//显示我参与过的活动投票的相关信息
	private void activity_part(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sd = m_Factory.getActivityInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Active> list = sd.Activity_part(employee.getEmployeeID());
		request.getSession().setAttribute("active", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/activity_part.jsp'</script>");
	}

	//删除我发起的活动投票
	private void deleteactivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sa = m_Factory.getActivityInstance();
		String activeID = request.getParameter("activeID");
		boolean result = sa.deleteActivity(activeID);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     out.println("<script>alert('删除成功!');</script>");
		     myactivity(request, response);
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	//新增我发起的活动投票
	private void addactivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Date date = new Date();
	    //设置要获取到什么样的时间
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
		m_ActivityIF sa = m_Factory.getActivityInstance();
		Active a=new Active();
		a.setActiveName(request.getParameter("activeName"));
		a.setActiveInfo(request.getParameter("activeInfo"));
		a.setCreateTime(sdf.format(date));
		Employee employee = (Employee)request.getSession().getAttribute("em");
		a.setEmployeeID(employee.getEmployeeID());
		a.setScope(Integer.toString(employee.getDeptID()));
		boolean result=sa.addActivity(a);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            myactivity(request, response);
        }
		else 
		{
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	//显示我发起的活动投票
	private void myactivity(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ActivityIF sd = m_Factory.getActivityInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Active> list = sd.myActivity(employee.getEmployeeID());
		request.getSession().setAttribute("active", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/activity.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
