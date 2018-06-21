package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;


import henu.IF.m_TaskIF;
import henu.IF.s_TaskIF;
import henu.bean.Employee;
import henu.bean.Task;
import henu.factory.m_Factory;
import henu.factory.s_Factory;


/**
 * Servlet implementation class m_Task
 */
@WebServlet("/m_Task")
public class m_Task extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_Task() {
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
		 case "employee":  emplpyee(request, response);   break;
		 case "addTask": addTask(request, response); break;
		 case "myReleaseTask":  myReleaseTask(request, response);   break;
		 case "deletetask" : deletetask(request, response);  break;
		 case "task_detail": task_detail(request, response); break;
		}
	}
private void task_detail(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
	Task release=new Task();
	String tID=request.getParameter("taskID");
	Employee e = new Employee();
	e=(Employee) request.getSession().getAttribute("em"); 
	String employeeName=e.getEmployeeName();
	m_TaskIF t=m_Factory.getTaskInstance();
	release=t.m_releaseDetail(tID,employeeName);
	PrintWriter   out   = response.getWriter();
	request.getSession().setAttribute("task_detail", release);
	  out.println("<script>window.location.href='/OASystem/middle/task_detail.jsp'</script>");
	}

private void deletetask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
	String tID=request.getParameter("taskID");
	m_TaskIF t=m_Factory.getTaskInstance();
	boolean result = t.deleteTask(tID);
	PrintWriter   out   =   response.getWriter(); 
	if(result){
	    out.println("<script>alert('删除成功!');</script>");
	 	myReleaseTask(request, response);
	}
	else
		out.println("<script>alert('删除失败!');history.back();</script>");
	}

private void myReleaseTask(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
	Employee e = new Employee();
	List<Task> release=new ArrayList<Task>();
	e=(Employee) request.getSession().getAttribute("em"); 
	String employeeID=e.getEmployeeID();
	m_TaskIF t=m_Factory.getTaskInstance();
	release=t.m_release(employeeID);
	PrintWriter   out   = response.getWriter();
	request.getSession().setAttribute("myReleaseTask", release);
	  out.println("<script>window.location.href='/OASystem/middle/task_release.jsp'</script>");
	}

private void addTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
	m_TaskIF t=m_Factory.getTaskInstance();
	Employee e = new Employee();
	e=(Employee) request.getSession().getAttribute("em"); 
	String employeeID=e.getEmployeeID();
	System.out.println(employeeID);	
	Task tt=new Task();
	Date day=new Date();    
	SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
	tt.setTaskName(request.getParameter("taskName"));
	tt.setTaskInfo(request.getParameter("taskInfo"));
	tt.setStartTime(request.getParameter("startTime"));
	tt.setEndTime(request.getParameter("endTime"));
	tt.setTaskTime(df.format(day));
	tt.setTaskState("0");
	tt.setFemployeeID(employeeID);
	String id=request.getParameter("TemployeeId");
	boolean result=t.m_addTask(tt,id);
	PrintWriter   out   = response.getWriter();
	if(result)
	{
        out.println("<script>alert('添加成功!');</script>");
        myReleaseTask(request, response);
    }
	else 
	{
		out.println("<script>alert('添加失败!');history.back();</script>"); 
	}
	}

private void emplpyee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	m_TaskIF m=m_Factory.getTaskInstance();
	List<Employee> em = new ArrayList<Employee>();
	Employee e=(Employee) request.getSession().getAttribute("em"); 
	int deptID=e.getDeptID();
	em=m.m_employee(deptID);
	request.getSession().setAttribute("emm", em);
	response.sendRedirect("middle/task_add.jsp");
	}

/*处理我的任务的状态*/
	private void handle(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		String tID=request.getParameter("taskID");
		String taskstate=request.getParameter("taskState");
		m_TaskIF tt=m_Factory.getTaskInstance();
		System.out.println(tID+taskstate);
		tt.handle(tID,taskstate);
		myTask_detail(request, response);
	}
/*我的任务的详情的处理*/
	private void myTask_detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
				
		Task mydetail=new Task();
		String tID=request.getParameter("taskID");
		Employee e = new Employee();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeName=e.getEmployeeName();
		m_TaskIF t=m_Factory.getTaskInstance();
		mydetail=t.mytask_detail(tID,employeeName);
		PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("task_detail",mydetail);
		  out.println("<script>window.location.href='/OASystem/middle/task_mydetail.jsp'</script>");
	}
/*查看我的任务的控制层*/
	private void myTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		List<Task> mytask=new ArrayList<Task>();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		m_TaskIF t=m_Factory.getTaskInstance();
		mytask=t.m_myTask(employeeID);
		PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("myTask", mytask);
		  out.println("<script>window.location.href='/OASystem/middle/task_my.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
