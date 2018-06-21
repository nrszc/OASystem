package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.a_TaskIF;
import henu.IF.m_TaskIF;
import henu.bean.Employee;
import henu.bean.Task;
import henu.factory.a_Factory;
import henu.factory.m_Factory;

/**
 * Servlet implementation class a_Task
 */
@WebServlet("/a_Task")
public class a_Task extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Task() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		try {
			myway(request, response);
		} catch (ParseException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");		 
		switch(method)
		{
		 case "myTask":  myTask(request, response);   break;
		 case "myTask_detail":  myTask_detail(request, response);   break;
		 case "handle":  handle(request, response);   break;
		}
	}

	private void handle(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
		String tID=request.getParameter("taskID");
		String taskstate=request.getParameter("taskState");
		a_TaskIF tt=a_Factory.getTaskInstance();
		System.out.println(tID+taskstate);
		tt.handle(tID,taskstate);
		myTask_detail(request, response);
	}

	private void myTask_detail(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Task mydetail=new Task();
		String tID=request.getParameter("taskID");
		Employee e = new Employee();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeName=e.getEmployeeName();
		a_TaskIF t=a_Factory.getTaskInstance();
		mydetail=t.mytask_detail(tID,employeeName);
		PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("task_detail",mydetail);
		  out.println("<script>window.location.href='/OASystem/average/task_mydetail.jsp'</script>");
	}

	private void myTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		List<Task> mytask=new ArrayList<Task>();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		a_TaskIF t=a_Factory.getTaskInstance();
		mytask=t.a_myTask(employeeID);
		PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("myTask", mytask);
		  out.println("<script>window.location.href='/OASystem/average/task_my.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
