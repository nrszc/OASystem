package henu.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.m_ColleagueIF;
import henu.IF.s_ColleagueIF;
import henu.IF.s_JobIF;
import henu.bean.Dept;
import henu.bean.Employee;
import henu.factory.m_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class m_Colleague
 */
@WebServlet("/m_Colleague")
public class m_Colleague extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_Colleague() {
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
		    case "addemployee"    : addemployee(request,response);    break;
		    case "find_detail"    : find_detail(request, response);   break;
		    case "selectJob" : selectJob(request, response);   break;
		}
	}
	
	private void selectJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ColleagueIF sj = m_Factory.getColleagueInstance();
		Employee  e = (Employee)request.getSession().getAttribute("em");
		StringBuffer json = sj.showJob(e.getDeptID());
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
        out.println(json.toString()); 
        out.close(); 
	}

	private void find_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		String employeeID=request.getParameter("employeeID");
		m_ColleagueIF sc = m_Factory.getColleagueInstance();
		Employee e = sc.find_detail(employeeID);
		request.getSession().setAttribute("e", e);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/colleague_detail.jsp'</script>");
	}

	private void searchbydept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_ColleagueIF sc = m_Factory.getColleagueInstance();
		String deptID = request.getParameter("deptID");
		List<Dept> list = sc.search();
		List<Employee> list1 = sc.find(deptID);
		request.getSession().setAttribute("colleague", list1);
		request.getSession().setAttribute("dept", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/colleague.jsp'</script>");	
	}

	//添加员工
	private void addemployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		m_ColleagueIF c= m_Factory.getColleagueInstance();
		Employee em = new Employee();
		em.setDeptID(Integer.parseInt(request.getParameter("deptID")));
		em.setJobID(Integer.parseInt(request.getParameter("jobID")));
		em.setEmployeeID(request.getParameter("employeeID"));
		em.setEmployeeName(request.getParameter("employeeName"));
		em.setSex(request.getParameter("sex"));
		em.setPwd(request.getParameter("password"));
		em.setLearn(request.getParameter("learn"));
		em.setNOcode(request.getParameter("NOcode"));
		em.setPhone(request.getParameter("phone"));
		em.setAddress(request.getParameter("address"));
		boolean result=c.addEmployee(em);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            search(request, response);
        }
		else 
		{
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}


	//查询部门信息
	private void search(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		s_ColleagueIF sc = s_Factory.getColleagueInstance();
		List<Dept> list = sc.search();
		List<Employee> list1 = sc.find();
		request.getSession().setAttribute("colleague", list1);
		request.getSession().setAttribute("dept", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/colleague.jsp'</script>");	
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
