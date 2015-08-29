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
				intro_response.sendRedirect("../error.jsp?msg='û��ָ����������'");
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
					  session.setAttribute("message","�޸ĺ�����Ϣ�ɹ�!");
					  intro_response.sendRedirect("intro.jsp");
					  } else {
						  session.setAttribute("message","�޸ĺ�����Ϣʧ�ܣ����Ժ�����!");
						  intro_response.sendRedirect("edit_intro.jsp?id="+Id);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","�޸ĺ�����Ϣʧ�ܣ����Ժ�����!");
				  intro_response.sendRedirect("edit_intro.jsp?id="+Id);
			    	}
			  } else {
				  session.setAttribute("message","�Ҳ���ָ����Intro,��ȷ�Ϻ��� ID�����³���!");
				  intro_response.sendRedirect(intro_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","û��ָ����Ҫ�Ĳ���,�������ܽ���!");
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
			if(title!=null)//�������Ʋ���Ϊ��
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
						session.setAttribute("message","��Ӻ�����Ϣ�ɹ�!");
						intro_response.sendRedirect("intro.jsp");
					  } else
					{
						session.setAttribute("message","��Ӻ�����Ϣʧ�ܣ����Ժ�����!");
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
				session.setAttribute("message","û��ָ����������,�������ܽ���!");
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
		    	    session.setAttribute("message","����ɾ��������Ϣ�ɹ�!");
		    	    intro_response.sendRedirect(intro_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","����ɾ��������Ϣʧ�ܣ����Ժ�����!");
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
			msg="û��ָ������ID��";
			intro_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

