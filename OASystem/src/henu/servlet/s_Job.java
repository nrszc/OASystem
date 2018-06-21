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

import henu.IF.s_DeptIF;
import henu.IF.s_JobIF;
import henu.bean.Dept;
import henu.bean.Job;
import henu.factory.s_Factory;

/**
 * Servlet implementation class s_Job
 */
@WebServlet("/s_Job")
public class s_Job extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Job() {
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
		    case "job"       : job(request, response);         break;
		    case "addjob"    : addjob(request, response);      break;
		    case "deletejob" : deletejob(request, response);   break;
		    case "updatejob" : updatejob(request, response);   break;
		    case "selectDept": selectDept(request, response);  break;
		    case "selectJob" : selectJob(request, response);   break;
		    case "selectDept1":selectDept1(request, response); break;
		}
	}

	private void selectDept1(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		StringBuffer json = sj.showDept1();
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close();  
	}

	private void selectJob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		String deptID = request.getParameter("deptID");
		System.out.println(deptID);
		StringBuffer json = sj.showJob(deptID);
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
        out.println(json.toString()); 
        out.close(); 
	}

	private void selectDept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		StringBuffer json = sj.showDept();
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close();  
	}

	//更新职位
	private void updatejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		Job j=new Job();
		j.setJobID(request.getParameter("jobID"));
		j.setDeptID(request.getParameter("deptID"));
		j.setJobName(request.getParameter("jobName"));
		j.setJobText(request.getParameter("jobText"));
		j.setLevel(request.getParameter("level"));
		boolean result = sj.updateJob(j);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		     out.println("<script>alert('修改成功!');</script>");
		     job(request, response);
		}
		else
		{
			out.println("<script>alert('修改失败!');history.back();</script>");
		}
	}

	//删除职位
	private void deletejob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		String jobID = request.getParameter("jobID");
		boolean result = sj.deleteJob(jobID);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     out.println("<script>alert('删除成功!');</script>");
		     job(request, response);
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	//增加职位
	private void addjob(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		Job j=new Job();
		j.setDeptID(request.getParameter("deptID"));
		j.setJobName(request.getParameter("jobName"));
		j.setJobText(request.getParameter("jobText"));
		j.setLevel(request.getParameter("level"));
		boolean result=sj.addJob(j);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            job(request, response);
        }
		else 
		{
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	//显示所有职位信息
	private void job(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_JobIF sj = s_Factory.getJobInstance();
		List<Job> list = sj.showJob();
		request.getSession().setAttribute("job", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/senior/job.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
