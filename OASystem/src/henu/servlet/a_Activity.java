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

import henu.IF.s_ActivityIF;
import henu.bean.Active;
import henu.bean.Employee;
import henu.factory.s_Factory;

/**
 * Servlet implementation class a_Activity
 */
@WebServlet("/a_Activity")
public class a_Activity extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Activity() {
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
		    case "activity_part"    : activity_part(request, response);    break;
		    case "activity_unpart"  : activity_unpart(request, response);  break;
		    case "activity_detail"  : activity_detail(request, response);  break;
		    case "activity_result"  : activity_result(request, response);  break;
		}
	}
	
	//插入新活动投票信息，再返回一个储存Active活动投票信息的bean类，再显示到结果界面上
	private void activity_result(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_ActivityIF sd = s_Factory.getActivityInstance();
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
		out.println("<script>window.location.href='/OASystem/average/activity_result.jsp'</script>");
	}

	//显示选中的未参与过的活动投票的相关信息
	private void activity_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_ActivityIF sd = s_Factory.getActivityInstance();
		String activeID = request.getParameter("activeID");
		Active active = sd.Activity_detail(activeID);
		request.getSession().setAttribute("activity", active);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/activity_detail.jsp'</script>");
	}

	//显示我未参与过的活动投票的相关信息
	private void activity_unpart(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_ActivityIF sd = s_Factory.getActivityInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Active> list = sd.Activity_unpart(employee);
		request.getSession().setAttribute("active", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/activity_unpart.jsp'</script>");
	}

	//显示我参与过的活动投票的相关信息
	private void activity_part(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_ActivityIF sd = s_Factory.getActivityInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Active> list = sd.Activity_part(employee.getEmployeeID());
		request.getSession().setAttribute("active", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/activity_part.jsp'</script>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
