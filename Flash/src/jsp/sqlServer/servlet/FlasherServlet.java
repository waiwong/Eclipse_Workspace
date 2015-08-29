package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Flasher;

public class FlasherServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest flasher_request = null;

	private HttpServletResponse flasher_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		flasher_request = request;
		flasher_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_FLASHER_ADD:
				addFlasher();
				break;
				
			case Signal.COMMAND_FLASHER_EDITINFO:
				editFlasherInfo();
				break;
				
			case Signal.COMMAND_FLASHER_DEL:
				delFlasher();
				break;
				
			default:
				flasher_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editFlasherInfo() throws IOException{
	  HttpSession session = flasher_request.getSession(true);
	  flasher_request.setCharacterEncoding("UTF-8");
	  String FId = flasher_request.getParameter("fid"),
	         nickname = new String(flasher_request.getParameter("nickname").getBytes("ISO-8859-1"), "UTF-8"),
	         truename = new String(flasher_request.getParameter("truename").getBytes("ISO-8859-1"), "UTF-8"),
	         kind = new String(flasher_request.getParameter("kind").getBytes("ISO-8859-1"), "UTF-8"),
	         photo = new String(flasher_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
	         first = new String(flasher_request.getParameter("first").getBytes("ISO-8859-1"), "UTF-8"),
	         classic = new String(flasher_request.getParameter("classic").getBytes("ISO-8859-1"), "UTF-8"),
	         detail = new String(flasher_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(FId!=null && truename!=null) {
		  Flasher flasher=new Flasher();
		  flasher.setID(Integer.parseInt(FId));
		  flasher = flasher.getFlasherByFlasherID();
		  if( flasher!=null ) {	
			  flasher.setNickname(nickname); 
			  flasher.setTruename(truename);
			  flasher.setKind(kind);
			  flasher.setPhoto(photo);
			  flasher.setFirst(first);
			  flasher.setClassic(classic);
			  flasher.setDetail(detail);
			
			  try {
				  int result = flasher.modifyFlasher();
				  if( result==1 ) {
					  session.setAttribute("message","修改閃客信息成功!");
					  flasher_response.sendRedirect("flasher.jsp");
					  } else {
						  session.setAttribute("message","修改閃客信息失败，请稍后再试!");
						  flasher_response.sendRedirect("edit_flasher.jsp?id="+FId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改閃客信息失败，请稍后再试!");
				  flasher_response.sendRedirect("edit_flasher.jsp?id="+FId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的閃客,请确认閃客 ID后重新尝试!");
				  flasher_response.sendRedirect(flasher_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  flasher_response.sendRedirect(flasher_request.getHeader("referer"));	
				
			}			
		}

	public void addFlasher() throws IOException
	{
		HttpSession session = flasher_request.getSession(true);

        String nickname = new String(flasher_request.getParameter("nickname").getBytes("ISO-8859-1"), "UTF-8"),
               truename = new String(flasher_request.getParameter("truename").getBytes("ISO-8859-1"), "UTF-8"),
               kind = new String(flasher_request.getParameter("kind").getBytes("ISO-8859-1"), "UTF-8"),
               photo = new String(flasher_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
               first = new String(flasher_request.getParameter("first").getBytes("ISO-8859-1"), "UTF-8"),
               classic = new String(flasher_request.getParameter("classic").getBytes("ISO-8859-1"), "UTF-8"),
               detail = new String(flasher_request.getParameter("detail").getBytes("ISO-8859-1"), "UTF-8");
			if(truename!=null)
			{

				Flasher flasher = new Flasher();
				flasher.setNickname(nickname); 
				flasher.setTruename(truename);
				flasher.setKind(kind);
				flasher.setPhoto(photo);
				flasher.setFirst(first);
			    flasher.setClassic(classic);
				flasher.setDetail(detail);

				try 
				{
					int result=flasher.addFlasher();
					if (result == 1) 
					{
						session.setAttribute("message","添加閃客信息成功!");
						flasher_response.sendRedirect("flasher.jsp");
					  } else
					{
						session.setAttribute("message","添加閃客信息失败，请稍后再试!");
			    	    flasher_response.sendRedirect("add_flasher.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定閃客名称,操作不能进行!");
	    	    flasher_response.sendRedirect(flasher_request.getHeader("referer"));
			}			
		}
		
	
public void delFlasher() throws IOException{
	HttpSession session = flasher_request.getSession(true);

		String FId = flasher_request.getParameter("fid");
		String msg="";
		if(FId!=null)
		{
			int flasherId=Integer.parseInt(FId);
			Flasher flasher=new Flasher();
			flasher.setID(flasherId);
			flasher = flasher.getFlasherByFlasherID();
			try
			{
				int result = flasher.deleteFlasher();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除閃客信息成功!");
		    	    flasher_response.sendRedirect(flasher_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除閃客信息失败，请稍后再试!");
		    	    flasher_response.sendRedirect(flasher_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定菜單ID！";
			flasher_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

