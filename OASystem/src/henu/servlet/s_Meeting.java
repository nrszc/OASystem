package henu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.io.PrintStream;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.s_MeetingIF;
import henu.IF.s_TaskIF;
import henu.bean.Employee;
import henu.factory.s_Factory;
import henu.bean.Meeting;

/**
 * Servlet implementation class Meeting
 */
@WebServlet("/s_Meeting")
public class s_Meeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Meeting() {
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
		// TODO Auto-generated method stub
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");		 
		switch(method)
		{
		      case "myMeeting" :  myMeeting(request, response);   break;
		      case "addmeeting" :  addmeeting(request, response);   break;
		      case "myRelease": myRelease(request, response);   break;
		      case "employee":  emplpyee(request, response);   break;
		      case "meeting_detail" :detail(request, response); break;
		      case "meeting_delete" :delete(request, response); break;
		     
		}
	}
	
	

	private void delete(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
        String meetingID = request.getParameter("meetingID");
		s_MeetingIF m=s_Factory.getMeetingInstance();
		boolean result = m.deleteMeeting(meetingID);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		    out.println("<script>alert('删除成功!');</script>");
		    myRelease(request, response);
		}
		else
			out.println("<script>alert('删除失败!');history.back();</script>");
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		s_MeetingIF m=s_Factory.getMeetingInstance();
        String meetingID = request.getParameter("meetingID");
        Meeting meeting = m.meetingDetail(meetingID);
        PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("meeting", meeting);
		out.println("<script>window.location.href='/OASystem/senior/meeting_detail.jsp'</script>");
	}

	/*获取员工的ID 名称 供选择*/
	private void emplpyee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		s_MeetingIF m=s_Factory.getMeetingInstance();
		List<Employee> em = new ArrayList<Employee>();
		em=m.s_employee();
		request.getSession().setAttribute("emm", em);
		response.sendRedirect("senior/meeting_add.jsp");
	}

	/*查看我发布的控制层操作*/
	private void myRelease(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		List<Meeting> release=new ArrayList<Meeting>();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		s_MeetingIF m=s_Factory.getMeetingInstance();
		release=m.s_release(employeeID);
		PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("myRelease", release);
		  out.println("<script>window.location.href='/OASystem/senior/meeting_release.jsp'</script>");
		//response.sendRedirect("senior/myRelease.jsp");
	}

	/*发布会议的控制层操作*/
	private void addmeeting(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		s_MeetingIF m=s_Factory.getMeetingInstance();
		Employee e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		Meeting mm=new Meeting();
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		mm.setMeetingName(request.getParameter("meetingName"));
		mm.setMeetingText(request.getParameter("meetingText"));
		mm.setStartTime(request.getParameter("startTime"));
		mm.setEndTime(request.getParameter("endTime"));
		mm.setCreateTime(df.format(day));
		mm.setHall(request.getParameter("hall"));
		mm.setEmployeeID(employeeID);
		String id=request.getParameter("employeeId");	
		System.out.println(id);
		boolean result=m.addMeeting(mm);
		m.addMeeting(id);
		PrintWriter   out   = response.getWriter();
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            myRelease(request, response);
        }
		else 
		{
			out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
		
	}

	/*查看我的会议的控制层操作*/
	private void myMeeting(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		List<Meeting> meeting=new ArrayList<Meeting>();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		s_MeetingIF m=s_Factory.getMeetingInstance();
		meeting=m.s_meeting(employeeID);
		request.getSession().setAttribute("myMeeting", meeting);
		response.sendRedirect("senior/meeting_my.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
