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

import henu.IF.a_MeetingIF;
import henu.IF.m_MeetingIF;
import henu.bean.Employee;
import henu.bean.Meeting;
import henu.factory.a_Factory;
import henu.factory.m_Factory;

/**
 * Servlet implementation class a_Meeting
 */
@WebServlet("/a_Meeting")
public class a_Meeting extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Meeting() {
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

	private void myway(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
		
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");		 
		switch(method)
		{
		      case "myMeeting" :  myMeeting(request, response);break;
		      case "meeting_detail":detail(request, response); break;
		}
	}

	private void detail(HttpServletRequest request, HttpServletResponse response) throws IOException, ParseException {
		// TODO Auto-generated method stub
		a_MeetingIF a=a_Factory.getMeetingInstance();
        String meetingID = request.getParameter("meetingID");
        Meeting meeting = a.meetingDetail(meetingID);
        PrintWriter   out   = response.getWriter();
		request.getSession().setAttribute("meeting", meeting);
		out.println("<script>window.location.href='/OASystem/average/meeting_detail.jsp'</script>");
	}

	private void myMeeting(HttpServletRequest request, HttpServletResponse response) throws ParseException, IOException {
		// TODO Auto-generated method stub
		Employee e = new Employee();
		List<Meeting> meeting=new ArrayList<Meeting>();
		e=(Employee) request.getSession().getAttribute("em"); 
		String employeeID=e.getEmployeeID();
		a_MeetingIF m=a_Factory.getMeetingInstance();
		meeting=m.a_meeting(employeeID);
		request.getSession().setAttribute("myMeeting", meeting);
		response.sendRedirect("average/meeting_my.jsp");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
