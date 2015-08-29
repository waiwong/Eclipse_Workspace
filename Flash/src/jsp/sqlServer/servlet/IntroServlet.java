package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Intro;

public class IntroServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest intro_request = null;

	private HttpServletResponse intro_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		intro_request = request;
		intro_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_INTRO_ADD:
				addIntro();
				break;
				
			case Signal.COMMAND_INTRO_EDITINFO:
				editIntroInfo();
				break;
				
			case Signal.COMMAND_INTRO_DEL:
				delIntro();
				break;
				
			default:
				intro_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editIntroInfo() throws IOException{
	  HttpSession session = intro_request.getSession(true);
	  intro_request.setCharacterEncoding("UTF-8");
	  String Id = intro_request.getParameter("id"),
	         title = new String(intro_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         type = new String(intro_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
	         detail = new String(intro_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8"),
	         path = new String(intro_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(Id!=null && title!=null) {
		  Intro intro=new Intro();
		  intro.setID(Integer.parseInt(Id));
		  intro = intro.getIntroByIntroID();
		  if( intro!=null ) {	
			  intro.setTitle(title); 
			  intro.setType(type);
			  intro.setDetail(detail);
			  intro.setPath(path);
			
			  try {
				  int result = intro.modifyIntro();
				  if( result==1 ) {
					  session.setAttribute("message","修改簡介信息成功!");
					  intro_response.sendRedirect("intro.jsp");
					  } else {
						  session.setAttribute("message","修改簡介信息失败，请稍后再试!");
						  intro_response.sendRedirect("edit_intro.jsp?id="+Id);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改簡介信息失败，请稍后再试!");
				  intro_response.sendRedirect("edit_intro.jsp?id="+Id);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的Intro,请确认簡介 ID后重新尝试!");
				  intro_response.sendRedirect(intro_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  intro_response.sendRedirect(intro_request.getHeader("referer"));	
				
			}			
		}

	public void addIntro() throws IOException
	{
		HttpSession session = intro_request.getSession(true);

        String title = new String(intro_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
               type = new String(intro_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
               detail = new String(intro_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8"),
               path = new String(intro_request.getParameter("path").getBytes("ISO-8859-1"),"UTF-8");
			if(title!=null)//簡介名称不能为空
			{

			   	  Intro intro = new Intro();
				  intro.setTitle(title);
				  intro.setType(type);
				  intro.setDetail(detail);
				  intro.setPath(path);

				try 
				{
					int result=intro.addIntro();
					if (result == 1) 
					{
						session.setAttribute("message","添加簡介信息成功!");
						intro_response.sendRedirect("intro.jsp");
					  } else
					{
						session.setAttribute("message","添加簡介信息失败，请稍后再试!");
			    	    intro_response.sendRedirect("add_intro.jsp");
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
	    	    intro_response.sendRedirect(intro_request.getHeader("referer"));
			}			
		}
		
	
public void delIntro() throws IOException{
	HttpSession session = intro_request.getSession(true);

		String Id = intro_request.getParameter("id");
		String msg="";
		if(Id!=null)
		{
			int introId=Integer.parseInt(Id);
			Intro intro=new Intro();
			intro.setID(introId);
			intro = intro.getIntroByIntroID();
			try
			{
				int result = intro.deleteIntro();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除簡介信息成功!");
		    	    intro_response.sendRedirect(intro_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除簡介信息失败，请稍后再试!");
		    	    intro_response.sendRedirect(intro_request.getHeader("referer"));
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
			intro_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

