package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Menu;

public class MenuServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest menu_request = null;

	private HttpServletResponse menu_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		menu_request = request;
		menu_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_MENU_ADD:
				addMenu();
				break;
				
			case Signal.COMMAND_MENU_EDITINFO:
				editMenuInfo();
				break;
				
			case Signal.COMMAND_MENU_DEL:
				delMenu();
				break;
				
			default:
				menu_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editMenuInfo() throws IOException{
	  HttpSession session = menu_request.getSession(true);
	  menu_request.setCharacterEncoding("UTF-8");
	  String mId = menu_request.getParameter("mid"),
	         name = new String(menu_request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
	  if(mId!=null && name!=null) {
		  Menu menu=new Menu();
		  menu.setID(Integer.parseInt(mId));
		  menu = menu.getMenuByMenuID();
		  if( menu!=null ) {	
			  menu.setMenu(name);
			  menu.setURL(menu_request.getParameter("url"));
			
			  try {
				  int result = menu.modifyMenu();
				  if( result==1 ) {
					  session.setAttribute("message","修改菜單信息成功!");
					  menu_response.sendRedirect("menu.jsp");
					  } else {
						  session.setAttribute("message","修改菜單信息失败，请稍后再试!");
						  menu_response.sendRedirect("edit_menu.jsp?mid="+mId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改菜單信息失败，请稍后再试!");
				  menu_response.sendRedirect("edit_menu.jsp?mid="+mId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的菜單,请确认菜單ID后重新尝试!");
				  menu_response.sendRedirect(menu_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  menu_response.sendRedirect(menu_request.getHeader("referer"));	
				
			}			
		}

	public void addMenu() throws IOException
	{
		HttpSession session = menu_request.getSession(true);

		String name = new String(menu_request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			if(name!=null)//菜單名称不能为空
			{

				Menu menu=new Menu();
				menu.setURL(menu_request.getParameter("url"));
				menu.setMenu(name);

				try 
				{
					int result=menu.addMenu();
					if (result == 1) 
					{
						session.setAttribute("message","添加菜單成功!");
						menu_response.sendRedirect("menu.jsp");
					  } else
					{
						session.setAttribute("message","添加菜單失败，请稍后再试!");
			    	    menu_response.sendRedirect("add_menu.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定菜單名称,操作不能进行!");
	    	    menu_response.sendRedirect(menu_request.getHeader("referer"));
			}			
		}
		
	
public void delMenu() throws IOException{
	HttpSession session = menu_request.getSession(true);

		String mId = menu_request.getParameter("mid");
		String msg="";
		if(mId!=null)
		{
			int menuId=Integer.parseInt(mId);
			Menu menu=new Menu();
			menu.setID(menuId);
			menu=menu.getMenuByMenuID();
			try
			{
				int result = menu.deleteMenu();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除菜單成功!");
		    	    menu_response.sendRedirect(menu_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除菜單失败，请稍后再试!");
		    	    menu_response.sendRedirect(menu_request.getHeader("referer"));
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
			menu_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

