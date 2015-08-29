package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Chart2;

public class Chart2Servlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest chart2_request = null;

	private HttpServletResponse chart2_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		chart2_request = request;
		chart2_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_CHART2_ADD:
				addChart2();
				break;
				
			case Signal.COMMAND_CHART2_EDITINFO:
				editChart2Info();
				break;
				
			case Signal.COMMAND_CHART2_DEL:
				delChart2();
				break;
				
			default:
				chart2_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editChart2Info() throws IOException{
	  HttpSession session = chart2_request.getSession(true);
	  chart2_request.setCharacterEncoding("UTF-8");
	  String cId = chart2_request.getParameter("cid"),
	         region = new String(chart2_request.getParameter("region").getBytes("ISO-8859-1"), "UTF-8");
	  if(cId!=null && region!=null) {
		  Chart2 chart2=new Chart2();
		  chart2.setID(Integer.parseInt(cId));
		  chart2 = chart2.getChart2ByChart2ID();
		  if( chart2!=null ) {	
			  chart2.setRegion(region);
			  chart2.setFP10(chart2_request.getParameter("fp10"));
			  chart2.setFP9(chart2_request.getParameter("fp9"));
			  chart2.setFP8(chart2_request.getParameter("fp8"));
			
			  try {
				  int result = chart2.modifyChart2();
				  if( result==1 ) {
					  session.setAttribute("message","修改该地區的调查信息成功!");
					  chart2_response.sendRedirect("chart2.jsp");
					  } else {
						  session.setAttribute("message","修改该地區的调查信息失败，请稍后再试!");
						  chart2_response.sendRedirect("edit_chart2.jsp?cid="+cId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改该地區的调查信息失败，请稍后再试!");
				  chart2_response.sendRedirect("edit_chart2.jsp?cid="+cId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的地区,请确认地区ID后重新尝试!");
				  chart2_response.sendRedirect(chart2_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  chart2_response.sendRedirect(chart2_request.getHeader("referer"));	
				
			}			
		}

	public void addChart2() throws IOException
	{
		HttpSession session = chart2_request.getSession(true);

		String region = new String(chart2_request.getParameter("region").getBytes("ISO-8859-1"), "UTF-8");
			if(region!=null)
			{

				Chart2 chart2=new Chart2();
				chart2.setRegion(region);
				chart2.setFP10(chart2_request.getParameter("fp10"));
				chart2.setFP9(chart2_request.getParameter("fp9"));
			    chart2.setFP8(chart2_request.getParameter("fp8"));

				try 
				{
					int result=chart2.addChart2();
					if (result == 1) 
					{
						session.setAttribute("message","添加某地區的调查信息成功!");
						chart2_response.sendRedirect("chart2.jsp");
					  } else
					{
						session.setAttribute("message","添加某地區的调查信息失败，请稍后再试!");
			    	    chart2_response.sendRedirect("add_chart2.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定地区名称,操作不能进行!");
	    	    chart2_response.sendRedirect(chart2_request.getHeader("referer"));
			}			
		}
		
	
public void delChart2() throws IOException{
	HttpSession session = chart2_request.getSession(true);

		String cId = chart2_request.getParameter("cid");
		String msg="";
		if(cId!=null)
		{
			int chart2Id=Integer.parseInt(cId);
			Chart2 chart2=new Chart2();
			chart2.setID(chart2Id);
			chart2=chart2.getChart2ByChart2ID();
			try
			{
				int result = chart2.deleteChart2();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除该地區的调查信息成功!");
		    	    chart2_response.sendRedirect(chart2_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除该地區的调查信息失败，请稍后再试!");
		    	    chart2_response.sendRedirect(chart2_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定地区ID！";
			chart2_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

