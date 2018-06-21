package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import henu.IF.LoginIF;
import henu.bean.Employee;
import henu.factory.DaoFactory;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet("/LoginServlet")
public class LoginServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public LoginServlet() {
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
		    case "login" : login(request, response);    break;
		    case "logout": logout(request, response);   break;
		    case "test"  : test(request, response);     break;
		}
	}
    
	private void test(HttpServletRequest request, HttpServletResponse response) throws IOException {
		LoginIF employee = DaoFactory.getLoginInstance();
		List<Employee> em = employee.Test();
		request.getSession().setAttribute("em", em);
		response.sendRedirect("index.jsp");
	}

	//用户注销
	private void logout(HttpServletRequest request, HttpServletResponse response) throws IOException {
		HttpSession session = request.getSession(false);//防止创建Session  
        if(session == null){   //session为空，即员工不在登录状态
            response.sendRedirect("index.html");  
            return;  
        }    //session不为空，员工在登录状态，对session进行销毁
        session.removeAttribute("em");  
        response.sendRedirect("index.html");  
	}

	//用户登录
	private void login(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee e = new Employee();
		e.setEmployeeID(request.getParameter("employeeID"));
		e.setPwd(request.getParameter("password"));      
		LoginIF employee = DaoFactory.getLoginInstance();
		Employee em = employee.Login(e);
		if(em.getEmployeeID()==null)
		{
			PrintWriter   out   =   response.getWriter(); 
            out.println("<script>alert('员工号或密码错误!');history.back();</script>"); 
            return;
		}
		if(em.getLevel().equals("高级"))
		{
			request.getSession().setAttribute("em", em);
			response.sendRedirect("senior/seniorHome.jsp");
		}
		if(em.getLevel().equals("中级"))
		{
			request.getSession().setAttribute("em", em);
			response.sendRedirect("middle/middleHome.jsp");
		}
		if(em.getLevel().equals("普通"))
		{
			request.getSession().setAttribute("em", em);
			response.sendRedirect("average/averageHome.jsp");
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
