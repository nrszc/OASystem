package henu.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.a_LeaveIF;
import henu.IF.m_LeaveIF;
import henu.bean.Employee;
import henu.bean.Leave;
import henu.factory.a_Factory;
import henu.factory.m_Factory;

/**
 * Servlet implementation class a_Leave
 */
@WebServlet("/a_Leave")
public class a_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Leave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		    case "showemployee"     : showemployee(request, response);      break;
		    case "showemployee1"    : showemployee1(request, response);     break;
		    case "leave_add"        : leave_add(request, response);         break;
		    case "leave_my"         : leave_my(request, response);          break;
		    case "leave_detail"     : leave_detail(request, response);      break;
		}
	}
	
	private void showemployee1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		a_LeaveIF ms = a_Factory.getLeaveInstance();
		StringBuffer json = ms.showEmployee1();
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close(); 
	}

	private void leave_my(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee employee = (Employee)request.getSession().getAttribute("em");
		a_LeaveIF sl = a_Factory.getLeaveInstance();
		List<Leave> list = sl.myLeave(employee.getEmployeeID());
		request.getSession().setAttribute("leave", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/leave_my.jsp'</script>");
	}

	private void leave_add(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee employee = (Employee)request.getSession().getAttribute("em");
		Leave l = new Leave();
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");//设置日期格式
		l.setLeaveTime(df.format(new Date()));// new Date()为获取当前系统时间
		l.setLeaveFrom(employee.getEmployeeID());
		l.setLeaveTo(request.getParameter("leaveTo"));
		l.setLeaveText(request.getParameter("leaveText"));
		l.setEndTime(request.getParameter("endTime"));
		l.setStartTime(request.getParameter("startTime"));
		l.setLeaveDay(request.getParameter("day"));
		a_LeaveIF ms = a_Factory.getLeaveInstance();
        boolean result = ms.addLeave(l);
		PrintWriter   out   =   response.getWriter(); 
        if(result)
        {
        	out.println("<script>alert('请假成功!');</script>");
    		out.println("<script>window.location.href='/OASystem/average/leave_add.jsp'</script>");
        }
        else
        {
        	out.println("<script>alert('请假失败!');history.back();</script>");
        }
	}

	private void showemployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		a_LeaveIF ms = a_Factory.getLeaveInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		System.out.println(employee.getDeptID()+"**********************");
		StringBuffer json = ms.showEmployee(employee.getDeptID());
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close();  
	}
	
	private void leave_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		a_LeaveIF ms = a_Factory.getLeaveInstance();
		String leaveID = request.getParameter("leaveID");
		Leave l = ms.leave_detail(leaveID);
		request.getSession().setAttribute("leave", l);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/leave_detail.jsp'</script>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
