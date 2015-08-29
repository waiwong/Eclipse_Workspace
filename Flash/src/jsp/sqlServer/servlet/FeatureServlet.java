package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Feature;

public class FeatureServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest feature_request = null;

	private HttpServletResponse feature_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		feature_request = request;
		feature_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_FEATURE_ADD:
				addFeature();
				break;
				
			case Signal.COMMAND_FEATURE_EDITINFO:
				editFeatureInfo();
				break;
				
			case Signal.COMMAND_FEATURE_DEL:
				delFeature();
				break;
				
			default:
				feature_response.sendRedirect("../error.jsp?msg='没有指定操作类型'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editFeatureInfo() throws IOException{
	  HttpSession session = feature_request.getSession(true);
	  feature_request.setCharacterEncoding("UTF-8");
	  String fId = feature_request.getParameter("fid"),
	         title = new String(feature_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         type = new String(feature_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
	         summary = new String(feature_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8"),
	         path = new String(feature_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8"),
	         url = new String(feature_request.getParameter("url").getBytes("ISO-8859-1"), "UTF-8"),
	         photo = new String(feature_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(fId!=null && title!=null) {
		  Feature feature=new Feature();
		  feature.setID(Integer.parseInt(fId));
		  feature = feature.getFeatureByFeatureID();
		  if( feature!=null ) {	
			  feature.setTitle(title);
			  feature.setType(type);
			  feature.setSummary(summary);
			  feature.setPath(path);
			  feature.setURL(url);
			  feature.setURL(photo);
			
			  try {
				  int result = feature.modifyFeature();
				  if( result==1 ) {
					  session.setAttribute("message","修改主题信息成功!");
					  feature_response.sendRedirect("feature.jsp");
					  } else {
						  session.setAttribute("message","修改主题信息失败，请稍后再试!");
						  feature_response.sendRedirect("edit_feature.jsp?fid="+fId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","修改主题信息失败，请稍后再试!");
				  feature_response.sendRedirect("edit_feature.jsp?fid="+fId);
			    	}
			  } else {
				  session.setAttribute("message","找不到指定的主题,请确认主题ID后重新尝试!");
				  feature_response.sendRedirect(feature_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","没有指定必要的参数,操作不能进行!");
			  feature_response.sendRedirect(feature_request.getHeader("referer"));	
				
			}			
		}

	public void addFeature() throws IOException
	{
		HttpSession session = feature_request.getSession(true);

		String title = new String(feature_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
               type = new String(feature_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
               summary = new String(feature_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8"),
               path = new String(feature_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8"),
               url = new String(feature_request.getParameter("url").getBytes("ISO-8859-1"), "UTF-8"),
		       photo = new String(feature_request.getParameter("photo").getBytes("ISO-8859-1"), "UTF-8");
		
		if(title!=null)//主题名称不能为空
			{

				Feature feature = new Feature();
				feature.setTitle(title);
				feature.setType(type);
				feature.setSummary(summary);
			    feature.setPath(path);
				feature.setURL(url);
				feature.setPhoto(photo);

				try 
				{
					int result=feature.addFeature();
					if (result == 1) 
					{
						session.setAttribute("message","添加主题成功!");
						feature_response.sendRedirect("feature.jsp");
					  } else
					{
						session.setAttribute("message","添加主题失败，请稍后再试!");
			    	    feature_response.sendRedirect("add_feature.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","没有指定主题名称,操作不能进行!");
	    	    feature_response.sendRedirect(feature_request.getHeader("referer"));
			}			
		}
		
	
public void delFeature() throws IOException{
	HttpSession session = feature_request.getSession(true);

		String fId = feature_request.getParameter("fid");
		String msg="";
		if(fId!=null)
		{
			int featureId=Integer.parseInt(fId);
			Feature feature=new Feature();
			feature.setID(featureId);
			feature = feature.getFeatureByFeatureID();
			try
			{
				int result = feature.deleteFeature();
				if(result==1)
				{
		    	    session.setAttribute("message","彻底删除主题成功!");
		    	    feature_response.sendRedirect(feature_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","彻底删除主题失败，请稍后再试!");
		    	    feature_response.sendRedirect(feature_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="没有指定主题ID！";
			feature_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

