package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Feature_info;

public class Feature_infoServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest feature_info_request = null;

	private HttpServletResponse feature_info_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		feature_info_request = request;
		feature_info_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_FEATURE_info_ADD:
				addFeature_info();
				break;
				
			case Signal.COMMAND_FEATURE_info_EDITINFO:
				editFeature_infoInfo();
				break;
				
			case Signal.COMMAND_FEATURE_info_DEL:
				delFeature_info();
				break;
				
			default:
				feature_info_response.sendRedirect("../error.jsp?msg='û��ָ����������'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editFeature_infoInfo() throws IOException{
	  HttpSession session = feature_info_request.getSession(true);
	  feature_info_request.setCharacterEncoding("UTF-8");
	  String fId = feature_info_request.getParameter("fid"),
	         index = feature_info_request.getParameter("index"),
	         title = new String(feature_info_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
	         summary = new String(feature_info_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8");
	 
	  if(fId!=null && title!=null) {
		  Feature_info feature_info=new Feature_info();
		  feature_info.setID(Integer.parseInt(fId));
		  feature_info = feature_info.getFeature_infoByFeature_infoID();
		  if( feature_info!=null ) {	
			  feature_info.setNO(Integer.parseInt(index));
			  feature_info.setTitle(title);
			  feature_info.setSummary(summary);
			
			  try {
				  int result = feature_info.modifyFeature_info();
				  if( result==1 ) {
					  session.setAttribute("message","�޸ļ�����ɫ�ɹ�!");
					  feature_info_response.sendRedirect("feature_info.jsp");
					  } else {
						  session.setAttribute("message","�޸ļ�����ɫʧ�ܣ����Ժ�����!");
						  feature_info_response.sendRedirect("edit_feature_info.jsp?fid="+fId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","�޸ļ�����ɫʧ�ܣ����Ժ�����!");
				  feature_info_response.sendRedirect("edit_feature_info.jsp?fid="+fId);
			    	}
			  } else {
				  session.setAttribute("message","�Ҳ���ָ��������,��ȷ������ID�����³���!");
				  feature_info_response.sendRedirect(feature_info_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","û��ָ����Ҫ�Ĳ���,�������ܽ���!");
			  feature_info_response.sendRedirect(feature_info_request.getHeader("referer"));	
				
			}			
		}

	public void addFeature_info() throws IOException
	{
		HttpSession session = feature_info_request.getSession(true);

		String  index = feature_info_request.getParameter("index"),
                title = new String(feature_info_request.getParameter("title").getBytes("ISO-8859-1"), "UTF-8"),
                summary = new String(feature_info_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8");
		
			if(title!=null)
			{

				Feature_info feature_info=new Feature_info();
			    feature_info.setNO(Integer.parseInt(index));
				feature_info.setTitle(title);
				feature_info.setSummary(summary);

				try 
				{
					int result=feature_info.addFeature_info();
					if (result == 1) 
					{
						session.setAttribute("message","��Ӽ�����ɫ�ɹ�!");
						feature_info_response.sendRedirect("feature_info.jsp");
					  } else
					{
						session.setAttribute("message","��Ӽ�����ɫʧ�ܣ����Ժ�����!");
			    	    feature_info_response.sendRedirect("add_feature_info.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","û��ָ����������,�������ܽ���!");
	    	    feature_info_response.sendRedirect(feature_info_request.getHeader("referer"));
			}			
		}
		
	
public void delFeature_info() throws IOException{
	HttpSession session = feature_info_request.getSession(true);

		String fId = feature_info_request.getParameter("fid");
		String msg="";
		if(fId!=null)
		{
			int feature_infoId=Integer.parseInt(fId);
			Feature_info feature_info = new Feature_info();
			feature_info.setID(feature_infoId);
			feature_info=feature_info.getFeature_infoByFeature_infoID();
			try
			{
				int result = feature_info.deleteFeature_info();
				if(result==1)
				{
		    	    session.setAttribute("message","����ɾ��������ɫ�ɹ�!");
		    	    feature_info_response.sendRedirect(feature_info_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","����ɾ��������ɫʧ�ܣ����Ժ�����!");
		    	    feature_info_response.sendRedirect(feature_info_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="û��ָ������ID��";
			feature_info_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

