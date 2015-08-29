package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Chinese;

public class ChineseServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest chinese_request = null;

	private HttpServletResponse chinese_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		chinese_request = request;
		chinese_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_CHINESE_ADD:
				addChinese();
				break;
				
			case Signal.COMMAND_CHINESE_EDITINFO:
				editChineseInfo();
				break;
				
			case Signal.COMMAND_CHINESE_DEL:
				delChinese();
				break;
				
			default:
				chinese_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editChineseInfo() throws IOException{
	  HttpSession session = chinese_request.getSession(true);
	  chinese_request.setCharacterEncoding("UTF-8");
	  String cId = chinese_request.getParameter("cid"),
	         title = new String(chinese_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         detail = new String(chinese_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8"),
	         path = new String(chinese_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(cId!=null && title!=null) {
		  Chinese chinese=new Chinese();
		  chinese.setID(Integer.parseInt(cId));
		  chinese = chinese.getChineseByChineseID();
		  if( chinese!=null ) {	
			  chinese.setTitle(title); 
			  chinese.setDetail(detail);
			  chinese.setPath(path);
			
			  try {
				  int result = chinese.modifyChinese();
				  if( result==1 ) {
					  session.setAttribute("message","修改簡介信息成功!");
					  chinese_response.sendRedirect("chinese.jsp");
					  } else {
						  session.setAttribute("message","修改簡介信息失败，请稍后再试!");
						  chinese_response.sendRedirect("edit_chinese.jsp?id="+cId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改簡介信息失败，请稍后再试!");
				  chinese_response.sendRedirect("edit_chinese.jsp?id="+cId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的Chinese,请确认簡介 ID后重新尝试!");
				  chinese_response.sendRedirect(chinese_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  chinese_response.sendRedirect(chinese_request.getHeader("referer"));	
				
			}			
		}

	public void addChinese() throws IOException
	{
		HttpSession session = chinese_request.getSession(true);

        String title = new String(chinese_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
               detail = new String(chinese_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8"),
               path = new String(chinese_request.getParameter("path").getBytes("ISO-8859-1"),"UTF-8");
			if(title!=null)
			{

			   	  Chinese chinese = new Chinese();
				  chinese.setTitle(title);
				  chinese.setDetail(detail);
				  chinese.setPath(path);

				try 
				{
					int result=chinese.addChinese();
					if (result == 1) 
					{
						session.setAttribute("message","添加簡介信息成功!");
						chinese_response.sendRedirect("chinese.jsp");
					  } else
					{
						session.setAttribute("message","添加簡介信息失败，请稍后再试!");
			    	    chinese_response.sendRedirect("add_chinese.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定簡介名称,操作不能进行!");
	    	    chinese_response.sendRedirect(chinese_request.getHeader("referer"));
			}			
		}
		
	
public void delChinese() throws IOException{
	HttpSession session = chinese_request.getSession(true);

		String cId = chinese_request.getParameter("cid");
		String msg="";
		if(cId!=null)
		{
			int chineseId=Integer.parseInt(cId);
			Chinese chinese=new Chinese();
			chinese.setID(chineseId);
			chinese = chinese.getChineseByChineseID();
			try
			{
				int result = chinese.deleteChinese();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除簡介信息成功!");
		    	    chinese_response.sendRedirect(chinese_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除簡介信息失败，请稍后再试!");
		    	    chinese_response.sendRedirect(chinese_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定簡介ID！";
			chinese_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

