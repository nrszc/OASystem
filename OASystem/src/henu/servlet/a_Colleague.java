package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.a_ColleagueIF;
import henu.IF.s_ColleagueIF;
import henu.bean.Dept;
import henu.bean.Employee;
import henu.factory.a_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class a_Colleague
 */
@WebServlet("/a_Colleague")
public class a_Colleague extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Colleague() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		    case "search"         : search(request, response);        break;
		    case "searchbydept"   : searchbydept(request, response);  break;
		    case "find_detail"    : find_detail(request, response);   break;
		}
	}

	private void find_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employeeID=request.getParameter("employeeID");
		a_ColleagueIF sc = a_Factory.getColleagueInstance();
		Employee e = sc.find_detail(employeeID);
		request.getSession().setAttribute("e", e);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/colleague_detail.jsp'</script>");
	}

	private void searchbydept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		a_ColleagueIF sc = a_Factory.getColleagueInstance();
		String deptID = request.getParameter("deptID");
		List<Dept> list = sc.search();
		List<Employee> list1 = sc.find(deptID);
		request.getSession().setAttribute("colleague", list1);
		request.getSession().setAttribute("dept", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/colleague.jsp'</script>");	
	}


	//查询部门信息
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		a_ColleagueIF sc = a_Factory.getColleagueInstance();
		List<Dept> list = sc.search();
		List<Employee> list1 = sc.find();
		request.getSession().setAttribute("colleague", list1);
		request.getSession().setAttribute("dept", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/colleague.jsp'</script>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
