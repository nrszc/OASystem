package henu.servlet;

import java.io.IOException;
import java.io.PrintStream;
import java.io.PrintWriter;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;

import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.jspsmart.upload.File;
import com.jspsmart.upload.Files;
import com.jspsmart.upload.Request;
import com.jspsmart.upload.SmartUpload;
import com.jspsmart.upload.SmartUploadException;

import henu.IF.m_FileIF;
import henu.IF.s_FileIF;
import henu.bean.CompanyFile;
import henu.bean.DeptFile;
import henu.bean.Employee;
import henu.bean.MyfileTable;
import henu.bean.PrivateFile;
import henu.factory.m_Factory;
import henu.factory.s_Factory;

/**
 * Servlet implementation class m_File
 */
@WebServlet("/m_File")
public class m_File extends HttpServlet {
	private static final long serialVersionUID = 1L;
	ServletConfig servletconfig;   
    /**
     * @see HttpServlet#HttpServlet()
     */
    public m_File() {
        super();
        // TODO Auto-generated constructor stub
    }
    
    public void init(ServletConfig config)throws ServletException{
    	this.servletconfig = config;
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		myway(request,response);
	}
	private void myway(HttpServletRequest request, HttpServletResponse response) throws IOException, ServletException  {
		request.setCharacterEncoding("utf-8");
		response.setContentType("text/html;charset=utf-8");
		String method = request.getParameter("method");
		switch(method){//选择操作
			case "file_receive"  : file_receive(request, response);  break;     //查找该员工上传过的历史文件
			case "file_send"     : file_send(request,response); break;//查找该员工可以接收的 文件
			case "uploadfile"    : uploadfile(request,response); break;  //上传文件
			case "download"      : downloadfile(request, response); break;
			case "showdept"      : showdept(request, response);  break;
			case "showemployee"  : showemployee(request, response); break;
		}
      }

	private void downloadfile(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		SmartUpload su = new SmartUpload();
		su.initialize(servletconfig, request, response);
		su.setContentDisposition(null);
		String filename = request.getParameter("fileName");
		try{
			su.downloadFile("D:/OASystem/"+filename);
		}catch(SmartUploadException e)
		{
			e.printStackTrace();
		}
	}

	private void showemployee(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_FileIF ms = m_Factory.getFileInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		List<Employee> list = ms.showEmployee(employee.getEmployeeID());
		request.getSession().setAttribute("list1", list);
		response.sendRedirect("middle/file_upload.jsp");
	}

	private void showdept(HttpServletRequest request, HttpServletResponse response) throws IOException {
		m_FileIF ms = m_Factory.getFileInstance();
		Employee employee = (Employee)request.getSession().getAttribute("em");
		StringBuffer json = ms.showDept(employee.getDeptID());
		PrintStream out = new PrintStream(response.getOutputStream());    
        //搞完把json打印在本Servlet上，之后前台页面读这页的内容就可以了  
		System.out.println(json.toString());
        out.println(json.toString().trim());  
        out.close();  
	}

	private void uploadfile(HttpServletRequest request, HttpServletResponse response) throws IOException {
		
		//上传文件
		SmartUpload su = new SmartUpload();
		try {
			su.initialize(servletconfig, request, response);
		} catch (ServletException e1) {
			e1.printStackTrace();
		}
		su.setAllowedFilesList("doc,docx,txt,png,xls,xlsx,ppt,pptx");
		su.setMaxFileSize(3 * 1024 * 1024);
		su.setTotalMaxFileSize(12 * 1024 * 1024);
		try {
			su.upload();
		} catch (ServletException e2) {
			e2.printStackTrace();
		} catch (IOException e2) {
			e2.printStackTrace();
		} catch (SmartUploadException e2) {
			e2.printStackTrace();
		}
		Request req = su.getRequest();
		Files files = su.getFiles();
		File file = files.getFile(0);
		String extFile = file.getFileExt();
		Date curDate = new Date();
		long d = curDate.getTime();
		String mainFile = String.valueOf(d);
		String fname = mainFile + "." +extFile;
		String filename = "D:/OASystem/" + mainFile + "." + extFile;
		try {
			file.saveAs(filename);
		} catch (IOException e1) {
			e1.printStackTrace();
		} catch (SmartUploadException e1) {
			e1.printStackTrace();
		}	
		//获取上传参数
		Employee fileFrom = (Employee) request.getSession().getAttribute("em");
		String   id    =  fileFrom.getEmployeeID();     //获取员工ID
		SimpleDateFormat t = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
		String ftime = t.format(new Date());
		String ftype = req.getParameter("filetype");
		String ftext = req.getParameter("filetext");
		PrintWriter  out  =  response.getWriter(); 
		m_FileIF ms = m_Factory.getFileInstance();
			//上传部门文件
			if(ftype.equals("dept")) 
			{
				DeptFile df = new DeptFile();
				df.setFileFrom(id);
				df.setFileTime(ftime);
				df.setFileName(fname);
				df.setFileText(ftext);
				df.setDeptID(req.getParameter("deptfile"));
				boolean result = ms.saveDeptfile(df);
				if(result)
				{
					out.println("<script>alert('发送成功!');</script>");	     
				}
				else
				{
					out.println("<script>alert('发送失败!');history.back();</script>");
				}
			    file_send(request, response);
			    return;
			}
			//上传私人文件
			if(ftype.equals("private")) 
			{
				PrivateFile pf = new PrivateFile();
				pf.setFileFrom(id);
				pf.setFileTime(ftime);
				pf.setFileName(fname);
				pf.setFileText(ftext);
				pf.setFileTo(req.getParameter("sendTo"));
				System.out.println(pf.getFileTo()+"yyyyyyyyyyyyyyyyyyyy");
				boolean result = ms.savePrivatefile(pf);
				if(result){
					out.println("<script>alert('发送成功!');</script>");     
				}
				else
				{
					out.println("<script>alert('发送失败!');history.back();</script>");
				}
			    file_send(request, response);
			    return;
				}
	}

	private void file_send(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee fileFrom = (Employee) request.getSession().getAttribute("em");
		String id = fileFrom.getEmployeeID();    //获取员工ID
		m_FileIF ms = m_Factory.getFileInstance();
		List<MyfileTable> list = ms.getMyfile(id);
		request.getSession().setAttribute("mft", list);		
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/file_send.jsp'</script>");
	}

	private void file_receive(HttpServletRequest request, HttpServletResponse response) throws IOException {
		Employee fileFrom = (Employee) request.getSession().getAttribute("em");
		String id = fileFrom.getEmployeeID();//获取员工ID
		m_FileIF ms = m_Factory.getFileInstance();
		List<MyfileTable> a = ms.getDownload(id);
		request.getSession().setAttribute("mf", a);
		PrintWriter   out   =   response.getWriter(); 
		out.println("<script>window.location.href='/OASystem/middle/file_receive.jsp'</script>");
	}


	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
		doGet(request, response);
	}

}
