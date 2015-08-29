package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Index;

public class IndexServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest index_request = null;

	private HttpServletResponse index_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		index_request = request;
		index_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_INDEX_ADD:
				addIndex();
				break;
				
			case Signal.COMMAND_INDEX_EDITINFO:
				editIndexInfo();
				break;
				
			case Signal.COMMAND_INDEX_DEL:
				delIndex();
				break;
				
			default:
				index_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editIndexInfo() throws IOException{
	  HttpSession session = index_request.getSession(true);
	  index_request.setCharacterEncoding("UTF-8");
	  String Id = index_request.getParameter("id"),
	         title = new String(index_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         type = new String(index_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
	         content = new String(index_request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8"),
	         photo = new String(index_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
	         swf = new String(index_request.getParameter("swf").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(Id!=null && title!=null) {
		  Index index=new Index();
		  index.setID(Integer.parseInt(Id));
		  index = index.getIndexByIndexID();
		  if( index!=null ) {	
			  index.setTitle(title);
			  index.setType(type);
			  index.setContent(content);
			  index.setPhoto(photo);
			  index.setSWF(swf);
			
			  try {
				  int result = index.modifyIndex();
				  if( result==1 ) {
					  session.setAttribute("message","修改主題信息成功!");
					  index_response.sendRedirect("index.jsp");
					  } else {
						  session.setAttribute("message","修改主題信息失败，请稍后再试!");
						  index_response.sendRedirect("edit_index.jsp?id="+Id);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改主題信息失败，请稍后再试!");
				  index_response.sendRedirect("edit_index.jsp?id="+Id);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的主題,请确认主題 ID后重新尝试!");
				  index_response.sendRedirect(index_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  index_response.sendRedirect(index_request.getHeader("referer"));	
				
			}			
		}

	public void addIndex() throws IOException
	{
		HttpSession session = index_request.getSession(true);

        String title = new String(index_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
               type = new String(index_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
               content = new String(index_request.getParameter("content").getBytes("ISO-8859-1"), "UTF-8"),
               photo = new String(index_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8"),
               swf = new String(index_request.getParameter("swf").getBytes("ISO-8859-1"), "UTF-8");
			if(title!=null)
			{

				Index index = new Index();
				index.setTitle(title);
				index.setType(type);
				index.setPhoto(photo);
				index.setSWF(swf);
				index.setContent(content);

				try 
				{
					int result=index.addIndex();
					if (result == 1) 
					{
						session.setAttribute("message","添加主題信息成功!");
						index_response.sendRedirect("index.jsp");
					  } else
					{
						session.setAttribute("message","添加主題信息失败，请稍后再试!");
			    	    index_response.sendRedirect("add_index.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定主題名称,操作不能进行!");
	    	    index_response.sendRedirect(index_request.getHeader("referer"));
			}			
		}
		
	
public void delIndex() throws IOException{
	HttpSession session = index_request.getSession(true);

		String Id = index_request.getParameter("id");
		String msg="";
		if(Id!=null)
		{
			int indexId=Integer.parseInt(Id);
			Index index=new Index();
			index.setID(indexId);
			index = index.getIndexByIndexID();
			try
			{
				int result = index.deleteIndex();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除该主題信息成功!");
		    	    index_response.sendRedirect(index_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除该主題信息失败，请稍后再试!");
		    	    index_response.sendRedirect(index_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定主題ID！";
			index_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

