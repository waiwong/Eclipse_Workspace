package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Works;

public class WorksServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest works_request = null;

	private HttpServletResponse works_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		works_request = request;
		works_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_WORKS_ADD:
				addWorks();
				break;
				
			case Signal.COMMAND_WORKS_EDITINFO:
				editWorksInfo();
				break;
				
			case Signal.COMMAND_WORKS_DEL:
				delWorks();
				break;
				
			default:
				works_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editWorksInfo() throws IOException{
	  HttpSession session = works_request.getSession(true);
	  works_request.setCharacterEncoding("UTF-8");
	  String WId = works_request.getParameter("wid"),
	         title = new String(works_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         level1 = new String(works_request.getParameter("level1").getBytes("ISO-8859-1"), "UTF-8"),
	         level2 = new String(works_request.getParameter("level2").getBytes("ISO-8859-1"), "UTF-8"),
	         level3 = new String(works_request.getParameter("level3").getBytes("ISO-8859-1"), "UTF-8"),
	         photo = new String(works_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
	         author = new String(works_request.getParameter("author").getBytes("ISO-8859-1"), "UTF-8"),
	         path = new String(works_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(WId!=null && title!=null) {
		  Works works=new Works();
		  works.setID(Integer.parseInt(WId));
		  works = works.getWorksByWorksID();
		  if( works!=null ) {	
			  works.setTitle(title); 
			  works.setLevel1(level1);
			  works.setLevel2(level2);
			  works.setLevel3(level3);
			  works.setAuthor(author);
			  works.setPhoto(photo);
			  works.setPath(path);
			
			  try {
				  int result = works.modifyWorks();
				  if( result==1 ) {
					  session.setAttribute("message","修改作品信息成功!");
					  works_response.sendRedirect("works.jsp");
					  } else {
						  session.setAttribute("message","修改作品信息失败，请稍后再试!");
						  works_response.sendRedirect("edit_works.jsp?id="+WId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改作品信息失败，请稍后再试!");
				  works_response.sendRedirect("edit_works.jsp?id="+WId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的作品,请确认作品 ID后重新尝试!");
				  works_response.sendRedirect(works_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  works_response.sendRedirect(works_request.getHeader("referer"));	
				
			}			
		}

	public void addWorks() throws IOException
	{
		HttpSession session = works_request.getSession(true);

        String title = new String(works_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
               level1 = new String(works_request.getParameter("level1").getBytes("ISO-8859-1"), "UTF-8"),
               level2 = new String(works_request.getParameter("level2").getBytes("ISO-8859-1"), "UTF-8"),
               level3 = new String(works_request.getParameter("level3").getBytes("ISO-8859-1"), "UTF-8"),
               photo = new String(works_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
               author = new String(works_request.getParameter("author").getBytes("ISO-8859-1"), "UTF-8"),
               path = new String(works_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
			if(title!=null)//作品名称不能为空
			{

				Works works = new Works();
				works.setTitle(title); 
				works.setLevel1(level1);
				works.setLevel2(level2);
				works.setLevel3(level3);
			    works.setAuthor(author);
				works.setPhoto(photo);
			    works.setPath(path);

				try 
				{
					int result=works.addWorks();
					if (result == 1) 
					{
						session.setAttribute("message","添加 FLASH 作品成功!");
						works_response.sendRedirect("works.jsp");
					  } else
					{
						session.setAttribute("message","添加 FLASH 作品失败，请稍后再试!");
			    	    works_response.sendRedirect("add_works.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定作品名称,操作不能进行!");
	    	    works_response.sendRedirect(works_request.getHeader("referer"));
			}			
		}
		
	
public void delWorks() throws IOException{
	HttpSession session = works_request.getSession(true);

		String WId = works_request.getParameter("wid");
		String msg="";
		if(WId!=null)
		{
			int worksId=Integer.parseInt(WId);
			Works works=new Works();
			works.setID(worksId);
			works = works.getWorksByWorksID();
			try
			{
				int result = works.deleteWorks();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除作品成功!");
		    	    works_response.sendRedirect(works_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除作品失败，请稍后再试!");
		    	    works_response.sendRedirect(works_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定作品ID！";
			works_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

