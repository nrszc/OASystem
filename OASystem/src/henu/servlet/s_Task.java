package henu.servlet;

import java.io.IOException;

import java.io.PrintWriter;
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

import henu.IF.s_TaskIF;
import henu.bean.Employee;
import henu.bean.Leave;
import henu.bean.Meeting;
import henu.bean.Task;
import henu.factory.s_Factory;

/**
 * Servlet implementation class s_Task
 */
@WebServlet("/s_Task")
public class s_Task extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Task() {
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
		 case "myReleaseTask":  myReleaseTask(request, response);   break;
		 case "employee":  emplpyee(request, response);   break;
		 case "addTask": addTask(request, response); break;
		 case "task_detail": task_detail(request, response); break;
		 case "deletetask" : deletetask(request, response);  break;
		}
	}
private void deletetask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
	String tID=request.getParameter("taskID");
	s_TaskIF t=s_Factory.getTaskInstance();
	boolean result = t.deleteTask(tID);
	PrintWriter   out   =   response.getWriter(); 
	if(result){
	    out.println("<script>alert('删除成功!');</script>");
	 	myReleaseTask(request, response);
	}
	else
		out.println("<script>alert('删除失败!');history.back();</script>");
	}

/*任务的详情处理*/	
private void task_detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
	Task release=new Task();
	String tID=request.getParameter("taskID");
	Employee e = new Employee();
	e=(Employee) request.getSession().getAttribute("em"); 
	String employeeName=e.getEmployeeName();
	s_TaskIF t=s_Factory.getTaskInstance();
	release=t.s_releaseDetail(tID,employeeName);
	PrintWriter   out   = response.getWriter();
	request.getSession().setAttribute("task_detail", release);
	  out.println("<script>window.location.href='/OASystem/senior/task_detail.jsp'</script>");
	}
/*添加任务的处理层*/
private void addTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
	s_TaskIF t=s_Factory.getTaskInstance();
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
	boolean result=t.s_addTask(tt,id);
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
/*获取员工的信息*/
private void emplpyee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
	s_TaskIF t=s_Factory.getTaskInstance();
	List<Employee> em = new ArrayList<Employee>();
	em=t.s_employee();
	request.getSession().setAttribute("emm", em);
	response.sendRedirect("senior/task_add.jsp");
	}
/*我发布的任务的控制层处理*/
private void myReleaseTask(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
	Employee e = new Employee();
	List<Task> release=new ArrayList<Task>();
	e=(Employee) request.getSession().getAttribute("em"); 
	String employeeID=e.getEmployeeID();
	s_TaskIF t=s_Factory.getTaskInstance();
	release=t.s_release(employeeID);
	PrintWriter   out   = response.getWriter();
	request.getSession().setAttribute("myReleaseTask", release);
	  out.println("<script>window.location.href='/OASystem/senior/task_release.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
