package henu.servlet;

import java.io.IOException;
import java.io.PrintWriter;
import java.io.UnsupportedEncodingException;
import java.util.List;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import henu.IF.s_DeptIF;
import henu.bean.Dept;
import henu.factory.DaoFactory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class s_Dept
 */
@WebServlet("/s_Dept")
public class s_Dept extends HttpServlet {
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public s_Dept() {
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
		    case "dept"       : dept(request, response);         break;
		    case "adddept"    : adddept(request, response);      break;
		    case "deletedept" : deletedept(request, response);   break;
		    case "updatedept" : updatedept(request, response);   break;
		}
	}

	//修改部门信息
	private void updatedept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_DeptIF sd = s_Factory.getDeptInstance();
		Dept d=new Dept();
		d.setDeptID(request.getParameter("deptID"));
		d.setDeptName(request.getParameter("deptName"));
		d.setDeptText(request.getParameter("deptText"));
		boolean result = sd.updateDept(d);
		PrintWriter   out   = response.getWriter(); 
		if(result){
		     out.println("<script>alert('修改成功!');</script>");
		     dept(request, response);
		}
		else
		{
			out.println("<script>alert('修改失败!');history.back();</script>");
		}
	}

	//删除部门
	private void deletedept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_DeptIF sd = s_Factory.getDeptInstance();
		String deptID = request.getParameter("deptID");
		boolean result = sd.deleteDept(deptID);
		PrintWriter   out   =   response.getWriter(); 
		if(result){
		     out.println("<script>alert('删除成功!');</script>");
		     dept(request, response);
		}
		else
		{
			out.println("<script>alert('删除失败!');history.back();</script>");
		}
	}

	//新添加部门
	private void adddept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_DeptIF sd = s_Factory.getDeptInstance();
		Dept d=new Dept();
		d.setDeptID(request.getParameter("deptID"));
		d.setDeptName(request.getParameter("deptName"));
		d.setDeptText(request.getParameter("deptText"));
		boolean result=sd.addDept(d);
		PrintWriter   out   =   response.getWriter(); 
		if(result)
		{
            out.println("<script>alert('添加成功!');</script>");
            dept(request, response);
        }
		else 
		{
            out.println("<script>alert('添加失败!');history.back();</script>"); 
		}
	}

	//显示所有部门
	private void dept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		s_DeptIF sd = s_Factory.getDeptInstance();
		List<Dept> list = sd.showDept();
		request.getSession().setAttribute("dept", list);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/senior/dept.jsp'</script>");
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
