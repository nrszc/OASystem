package henu.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.m_LeaveIF;
import henu.IF.s_FileIF;
import henu.IF.s_LeaveIF;
import henu.bean.Employee;
import henu.bean.Leave;
import henu.factory.m_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class m_Leave
 */
@WebServlet("/m_Leave")
public class m_Leave extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_Leave() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		try {
			myway(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		    case "leave"            : leave(request, response);             break;
		    case "leave_unapproved" : leave_unapproved(request, response);  break;
		    case "leave_detail"     : leave_detail(request, response);      break;
		    case "leave_detail1"    : leave_detail1(request, response);     break;
		    case "approve"          : leave_approve(request, response);     break;
		    case "showemployee"     : showemployee(request, response);      break;
		    case "leave_add"        : leave_add(request, response);         break;
		    case "leave_my"         : leave_my(request, response);          break;
		}
	}
	
	private void leave_my(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee employee = (Employee)request.getSession().getAttribute("em");
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		List<Leave> list = sl.myLeave(employee.getEmployeeID());
		request.getSession().setAttribute("leave", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/leave_my.jsp'</script>");
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
		m_LeaveIF ms = m_Factory.getLeaveInstance();
        boolean result = ms.addLeave(l);
		PrintWriter   out   =   response.getWriter(); 
        if(result)
        {
        	out.println("<script>alert('请假成功!');</script>");
    		out.println("<script>window.location.href='/OASystem/middle/leave_add.jsp'</script>");
        }
        else
        {
        	out.println("<script>alert('请假失败!');history.back();</script>");
        }
	}

	private void showemployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_LeaveIF ms = m_Factory.getLeaveInstance();
		StringBuffer json = ms.showEmployee();
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close();  
	}

	private void leave_approve(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		String leaveID = request.getParameter("leaveID");
		String result = request.getParameter("result");
		String resultText = request.getParameter("resultText");
		Leave le = new Leave();
		le.setLeaveID(leaveID);
		le.setResult(result);
		le.setResultText(resultText);
		boolean c = sl.approve(le);
		PrintWriter   out   =   response.getWriter(); 
		if(c){
		Leave l = sl.leave_detail(leaveID);
		request.getSession().setAttribute("leave", l);
		out.println("<script>alert('审批成功!');</script>");
		out.println("<script>window.location.href='/OASystem/middle/leave_detail.jsp'</script>");
		}
		else
			out.println("<script>alert('审批失败!');history.back();</script>");
	}

	private void leave_detail1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		String leaveID = request.getParameter("leaveID");
		Leave l = sl.leave_detail1(leaveID);
		request.getSession().setAttribute("leave", l);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/leave_detail1.jsp'</script>");
	}

	private void leave_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		String leaveID = request.getParameter("leaveID");
		Leave l = sl.leave_detail(leaveID);
		request.getSession().setAttribute("leave", l);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/leave_detail.jsp'</script>");
	}

	private void leave_unapproved(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		Employee employee = (Employee)request.getSession().getAttribute("em");
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		List<Leave> list = sl.leave_unapproved(employee.getEmployeeID());
		request.getSession().setAttribute("leave", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/leave_unapproved.jsp'</script>");
	}

	//调用实现类返回一个List<Leave>，用来显示所有的请假信息
	private void leave(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		Employee employee = (Employee)request.getSession().getAttribute("em");
		m_LeaveIF sl = m_Factory.getLeaveInstance();
		List<Leave> list = sl.showLeave(employee.getEmployeeID());
		request.getSession().setAttribute("leave", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/leave_approved.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
