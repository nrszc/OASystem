package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.s_JobIF;
import henu.IF.s_PersonIF;
import henu.bean.Employee;
import henu.bean.Job;
import henu.factory.s_Factory;

/**
 * Servlet implementation class s_Person
 */
@WebServlet("/s_Person")
public class s_Person extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Person() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		myway(request,response);
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		System.out.println(method+"tttttttttt");
		switch(method)
		{
		    case "findinfo" : findinfo(request, response);   break;
		    case "updateinfo" : updateinfo(request, response);   break;
		    case "updatepwd" : updatepwd(request, response);   break;
		}
	}

	private void updatepwd(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		s_PersonIF pe = s_Factory.getPersonInstance();
		PrintWriter   out   =   response.getWriter(); 
		String pwd = request.getParameter("newpwd");
	    String pwd1 = request.getParameter("newpwd1");
	    if(!(pwd.equals(pwd1))){
            out.println("<script>alert('两次输入新密码不一致!请重新输入');history.back();</script>"); 
            return;
	    }
		Employee em = new Employee();
		Employee e = (Employee)request.getSession().getAttribute("em");
		em.setEmployeeID(e.getEmployeeID());
		em.setPwd(request.getParameter("pwd"));
		em.setNewpwd(pwd1);
		boolean result = pe.updatePwd(em);
		if(result)
		{
            out.println("<script>alert('修改成功!');</script>");
            out.println("<script>window.location.href='/OASystem/senior/pwd_update.jsp'</script>");
        }
		else 
		{
            out.println("<script>alert('密码错误!');history.back();</script>"); 
		}
	}

	private void updateinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		s_PersonIF pe = s_Factory.getPersonInstance();
		Employee em = new Employee();
		em.setEmployeeID(request.getParameter("employeeID"));
		em.setBirthdate(request.getParameter("birthdate"));
		em.setAddress(request.getParameter("address"));
		em.setEmail(request.getParameter("email"));
		em.setPhone(request.getParameter("phone"));
		em.setQQ(request.getParameter("QQ"));
		boolean result=pe.updateInfo(em);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('修改成功!');</script>");
            findinfo(request,response);
        }
		else 
		{
            out.println("<script>alert('修改失败!');history.back();</script>"); 
		}
	}

	private void findinfo(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		s_PersonIF pe = s_Factory.getPersonInstance();
		Employee e = (Employee)request.getSession().getAttribute("em");
		Employee em= pe.findInfo(e.getEmployeeID());
		request.getSession().setAttribute("ee", em);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/senior/person.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
