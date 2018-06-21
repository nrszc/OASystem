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

import henu.IF.m_MailIF;
import henu.IF.s_MailIF;
import henu.bean.Employee;
import henu.bean.Receive;
import henu.bean.Send;
import henu.factory.m_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class m_Mail
 */
@WebServlet("/m_Mail")
public class m_Mail extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_Mail() {
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
		    case "mail_receivebox" : mail_receivebox(request, response);    break;
		    case "mail_sendbox"    : mail_sendbox(request, response);       break;
		    case "mail_send"       : mail_send(request, response);          break;
		    case "mail_receivedetail"     : mail_receivedetail(request, response);        break;
		    case "addemployee"     : addemployee(request, response);        break;
		    case "mail_senddetail"     : mail_senddetail(request, response);        break;
		    case "deletereceive"   : deletereceive(request, response);      break;
		    case "deletesend"      : deletesend(request, response);         break;
		}
	}

	private void deletesend(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] item = request.getParameterValues("sendID");  
		System.out.println(item[0]);
		m_MailIF sm = m_Factory.getMailInstance();
        boolean result = sm.deleteSend(item);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		    out.println("<script>alert('删除成功!');</script>");
			mail_sendbox(request, response);
		}
		else
			out.println("<script>alert('删除失败!');history.back();</script>");
	}

	private void deletereceive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String[] item = request.getParameterValues("receiveID");  
		m_MailIF sm = m_Factory.getMailInstance();
        boolean result = sm.deleteReceive(item);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		    out.println("<script>alert('删除成功!');</script>");
			mail_receivebox(request, response);
		}
		else
			out.println("<script>alert('删除失败!');history.back();</script>");

	}

	//查看我发送的邮件的具体信息
	private void mail_senddetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String receiveID = request.getParameter("sendID");
		m_MailIF sm = m_Factory.getMailInstance();
	    Send s = sm.sendDetail(receiveID);
	    request.getSession().setAttribute("send", s);
		response.sendRedirect("middle/mail_senddetail.jsp");
	}

	//读取出邮件接受人
	private void addemployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_MailIF sm = m_Factory.getMailInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Employee> list = sm.addEmployee(employee.getEmployeeID());
		request.getSession().setAttribute("list", list);
		response.sendRedirect("middle/mail_send.jsp");
	}

	//查看具体的邮件内容
	private void mail_receivedetail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String receiveID = request.getParameter("receiveID");
		m_MailIF sm = m_Factory.getMailInstance();
	    Receive r = sm.receiveDetail(receiveID);
	    request.getSession().setAttribute("receive", r);
		response.sendRedirect("middle/mail_receivedetail.jsp");
	}

	//发送新邮件
	private void mail_send(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_MailIF sm = m_Factory.getMailInstance();
		Date date = new Date();
	    //设置要获取到什么样的时间
	    SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		Employee employee = (Employee)request.getSession().getAttribute("em");
		String sendTo = request.getParameter("sendTo");
		String title  = request.getParameter("title");
		String text   = request.getParameter("text");
		text = text.replace("\n", "<br>");
		text = text.replace(" ", "&nbsp;");
		Send s = new Send();
		s.setSendForm(employee.getEmployeeID());
		s.setSendDate(sdf.format(date));
		s.setSendTo(sendTo);
		s.setSendTitle(title);
		s.setSendText(text);
		boolean result = sm.send(s);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		    out.println("<script>alert('发送成功!');</script>");
			mail_receivebox(request, response);
		}
		else
			out.println("<script>alert('发送失败!');history.back();</script>");
	}

	//读取个人发送邮件的全部记录
	private void mail_sendbox(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_MailIF sm = m_Factory.getMailInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Send> list = sm.sendBox(employee.getEmployeeID());
		request.getSession().setAttribute("send", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/mail_sendbox.jsp'</script>");
	}

	//读取接收到全部信箱内容
	private void mail_receivebox(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_MailIF sm = m_Factory.getMailInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Receive> list = sm.receiveBox(employee.getEmployeeID());
		request.getSession().setAttribute("receive", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/mail_receivebox.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
