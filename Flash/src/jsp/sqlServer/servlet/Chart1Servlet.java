package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Chart1;

public class Chart1Servlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest chart1_request = null;

	private HttpServletResponse chart1_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		chart1_request = request;
		chart1_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_CHART1_ADD:
				addChart1();
				break;
				
			case Signal.COMMAND_CHART1_EDITINFO:
				editChart1Info();
				break;
				
			case Signal.COMMAND_CHART1_DEL:
				delChart1();
				break;
				
			default:
				chart1_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editChart1Info() throws IOException{
	  HttpSession session = chart1_request.getSession(true);
	  chart1_request.setCharacterEncoding("UTF-8");
	  String cId = chart1_request.getParameter("cid"),
	         name = new String(chart1_request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
	  if(cId!=null && name!=null) {
		  Chart1 chart1=new Chart1();
		  chart1.setID(Integer.parseInt(cId));
		  chart1 = chart1.getChart1ByChart1ID();
		  if( chart1!=null ) {	
			  chart1.setName(name);
			  chart1.setPercent(chart1_request.getParameter("percent"));
			
			  try {
				  int result = chart1.modifyChart1();
				  if( result==1 ) {
					  session.setAttribute("message","修改信息成功!");
					  chart1_response.sendRedirect("chart1.jsp");
					  } else {
						  session.setAttribute("message","修改信息失败，请稍后再试!");
						  chart1_response.sendRedirect("edit_chart1.jsp?cid="+cId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改信息失败，请稍后再试!");
				  chart1_response.sendRedirect("edit_chart1.jsp?cid="+cId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的信息,请确认ID后重新尝试!");
				  chart1_response.sendRedirect(chart1_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  chart1_response.sendRedirect(chart1_request.getHeader("referer"));	
				
			}			
		}

	public void addChart1() throws IOException
	{
		HttpSession session = chart1_request.getSession(true);

		String name = new String(chart1_request.getParameter("name").getBytes("ISO-8859-1"), "UTF-8");
			if(name!=null)
			{

				Chart1 chart1=new Chart1();
				chart1.setPercent(chart1_request.getParameter("percent"));
				chart1.setName(name);

				try 
				{
					int result=chart1.addChart1();
					if (result == 1) 
					{
						session.setAttribute("message","添加信息成功!");
						chart1_response.sendRedirect("chart1.jsp");
					  } else
					{
						session.setAttribute("message","添加信息失败，请稍后再试!");
			    	    chart1_response.sendRedirect("add_chart1.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定多媒体播放器名称,操作不能进行!");
	    	    chart1_response.sendRedirect(chart1_request.getHeader("referer"));
			}			
		}
		
	
public void delChart1() throws IOException{
	HttpSession session = chart1_request.getSession(true);

		String cId = chart1_request.getParameter("cid");
		String msg="";
		if(cId!=null)
		{
			int chart1Id=Integer.parseInt(cId);
			Chart1 chart1=new Chart1();
			chart1.setID(chart1Id);
			chart1=chart1.getChart1ByChart1ID();
			try
			{
				int result = chart1.deleteChart1();
				if(result==1)
				{
		    	    session.setAttribute("message","该栏目彻底删除成功!");
		    	    chart1_response.sendRedirect(chart1_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","该栏目彻底删除失败，请稍后再试!");
		    	    chart1_response.sendRedirect(chart1_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定ID！";
			chart1_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

