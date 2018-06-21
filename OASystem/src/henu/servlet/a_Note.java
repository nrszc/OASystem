package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.a_NoteIF;
import henu.IF.s_NoteIF;
import henu.bean.Employee;
import henu.bean.Note;
import henu.factory.a_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class a_Note
 */
@WebServlet("/a_Note")
public class a_Note extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public a_Note() {
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
		case "mynote" : mynote(request,response);  break;
		case "addnote": addnote(request,response);  break;
		case "deletenote" : deletenote(request,response);  break;
		case "allnote" : allnote(request,response);  break;
		case "note_detail" : note_detail(request, response); break;
		}
	}

	

	private void note_detail(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_NoteIF sd = s_Factory.getNoteInstance();
		String noteID = request.getParameter("noteID");
		Note n = sd.noteDetail(noteID);
		request.getSession().setAttribute("n", n);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/note_detail.jsp'</script>");
	}

	private void allnote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		a_NoteIF sd = a_Factory.getNoteInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Note> list = sd.allNote(employee.getDeptID());
		request.getSession().setAttribute("note", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/note.jsp'</script>");
	}

	private void deletenote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		String noteID=request.getParameter("noteID");
		a_NoteIF sd = a_Factory.getNoteInstance();
		boolean result = sd.deleteNote(noteID);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('删除成功!');</script>");
            mynote(request, response);
        }
		else 
		{
            out.println("<script>alert('删除失败!');history.back();</script>"); 
		}
	}

	private void addnote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		System.out.println("=======++++++");
		// TODO Auto-generated method stub
		Date day=new Date();    
		SimpleDateFormat df = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");  
		Note n = new Note();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		n.setEmployeeID(employee.getEmployeeID());
		n.setNoteName(request.getParameter("noteName"));
		n.setNoteTime(df.format(day));
		String text = request.getParameter("noteInfo");
		text = text.replace("\n", "<br>");
		text = text.replace(" ", "&nbsp;");
		n.setNoteInfo(text);
		n.setNoteScope(request.getParameter("deptID"));
		a_NoteIF sd = a_Factory.getNoteInstance();
		boolean result = sd.addNote(n);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('发布成功!');</script>");
            mynote(request, response);
        }
		else 
		{
            out.println("<script>alert('发布失败!');history.back();</script>"); 
		}
	}

	private void mynote(HttpServletRequest request, HttpServletResponse response) throws IOException {
		// TODO Auto-generated method stub
		a_NoteIF sd = a_Factory.getNoteInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Note> list = sd.myNote(employee.getEmployeeID());
		request.getSession().setAttribute("note", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/average/note_my.jsp'</script>");
	}
	

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
