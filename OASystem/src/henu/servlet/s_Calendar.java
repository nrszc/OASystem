package henu.servlet;

import java.io.IOException;
import java.io.UnsupportedEncodingException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.s_ActivityIF;
import henu.IF.s_CalendarIF;
import henu.bean.Calendar;
import henu.bean.Employee;
import henu.factory.s_Factory;

/**
 * Servlet implementation class s_Calendar
 */
@WebServlet("/s_Calendar")
public class s_Calendar extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Calendar() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		myway(request, response);
	}

	private void myway(HttpServletRequest request, HttpServletResponse response) throws UnsupportedEncodingException {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method)
		{
		    case "show"   : show(request, response);     break;
		    case "add"    : add(request, response);      break;
		    case "update" : update(request, response);   break;
		}
	}

	private void update(HttpServletRequest request, HttpServletResponse response) {
		String id = request.getParameter("id");
		String title = request.getParameter("title");
		String start = request.getParameter("startTimeStr");
		String end = request.getParameter("endTimeStr");
		String isfinish = request.getParameter("isFinish");
		String color = request.getParameter("color");
		String allDay = request.getParameter("allDay");
		if(allDay.equals("1")&&end.equals(""))
			end = null;
		Calendar c = new Calendar();
		c.setId(id);
		c.setTitle(title);
		c.setStart(start);
		c.setEnd(end);
		c.setIsfinish(isfinish);
		c.setColor(color);
		c.setAllDay(allDay);
		s_CalendarIF sc = s_Factory.getCalendarInstance();
        String update = sc.Update(c);
        try {  
	           response.getWriter().print(update);  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	       }  
	}

	private void add(HttpServletRequest request, HttpServletResponse response) {
		String title = request.getParameter("title");
		String start = request.getParameter("startTimeStr");
		String end = request.getParameter("endTimeStr");
		String isfinish = request.getParameter("isFinish");
		String color = request.getParameter("color");
		String allDay = request.getParameter("allDay");
		if(allDay.equals("1")&&end.equals(""))
			end = null;
		Employee employee = (Employee)request.getSession().getAttribute("em");
		Calendar c = new Calendar();
		c.setTitle(title);
		c.setStart(start);
		c.setEnd(end);
		c.setIsfinish(isfinish);
		c.setColor(color);
		c.setAllDay(allDay);
		c.setEmployeeID(employee.getEmployeeID());
		s_CalendarIF sc = s_Factory.getCalendarInstance();
        String add = sc.Add(c);
        try {  
	           response.getWriter().print(add);  
	       } catch (IOException e) {  
	           e.printStackTrace();  
	       }  
	}

	private void show(HttpServletRequest request, HttpServletResponse response) {
		System.out.println("999999");
		s_CalendarIF sc = s_Factory.getCalendarInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		String json = sc.Json(employee.getEmployeeID());
		System.out.println(json);
		try {  
	           response.getWriter().print(json);  
	       } catch (IOException e) {  
	           e.printStackTrace();  
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
