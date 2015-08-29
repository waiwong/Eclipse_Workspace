package jsp.sqlServer.servlet;
import java.io.IOException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import jsp.sqlServer.util.Signal;
import jsp.sqlServer.bean.Skill;

public class SkillServlet extends HttpServlet {

	private static final long serialVersionUID = 878216958170698920L;

	final static boolean VERBOSE = true;

	private HttpServletRequest skill_request = null;

	private HttpServletResponse skill_response = null;

	public void service(HttpServletRequest request, HttpServletResponse response) {
		skill_request = request;
		skill_response = response;
		String command = request.getParameter("cmd");
		try {
			switch (Signal.getCommand(command)) {

			case Signal.COMMAND_SKILL_ADD:
				addSkill();
				break;
				
			case Signal.COMMAND_SKILL_EDITINFO:
				editSkillInfo();
				break;
				
			case Signal.COMMAND_SKILL_DEL:
				delSkill();
				break;
				
			default:
				skill_response.sendRedirect("../error.jsp?msg='û��ָ����������'");
				break;
			}
		} catch (Exception ex) {
			ex.printStackTrace();
		}
	}
		
	public void editSkillInfo() throws IOException{
	  HttpSession session = skill_request.getSession(true);
	  skill_request.setCharacterEncoding("UTF-8");
	  String sId = skill_request.getParameter("sid"),
	         no = skill_request.getParameter("no"),
	         label = new String(skill_request.getParameter("label").getBytes("ISO-8859-1"), "UTF-8"),
	         sublabel = new String(skill_request.getParameter("sublabel").getBytes("ISO-8859-1"), "UTF-8"),
	         type = new String(skill_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
	         summary = new String(skill_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8"),
	         code = new String(skill_request.getParameter("code").getBytes("ISO-8859-1"), "UTF-8"),
	         path = new String(skill_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
	  
	  if(sId!=null && label!=null) {
		  Skill skill = new Skill();
		  skill.setID(Integer.parseInt(sId));
		  skill = skill.getSkillBySkillID();
		  if( skill!=null ) {	
			  skill.setNO(Integer.parseInt(no));
			  skill.setLabel(label);
			  skill.setsubLabel(sublabel);
			  skill.setType(type);
			  skill.setSummary(summary);
			  skill.setCode(code);
			  skill.setPath(path);
			
			  try {
				  int result = skill.modifySkill();
				  if( result==1 ) {
					  session.setAttribute("message","�޸Ľ̳���Ϣ�ɹ�!");
					  skill_response.sendRedirect("skill.jsp");
					  } else {
						  session.setAttribute("message","�޸Ľ̳���Ϣʧ�ܣ����Ժ�����!");
						  skill_response.sendRedirect("edit_skill.jsp?sid="+sId);
						  }
				  }
			  catch(Exception e) {
				  session.setAttribute("message","�޸Ľ̳���Ϣʧ�ܣ����Ժ�����!");
				  skill_response.sendRedirect("edit_skill.jsp?sid="+sId);
			    	}
			  } else {
				  session.setAttribute("message","�Ҳ���ָ���Ľ̳�,��ȷ�Ͻ̳�ID�����³���!");
				  skill_response.sendRedirect(skill_request.getHeader("referer"));	
				}
				
		  } else {
			  session.setAttribute("message","û��ָ����Ҫ�Ĳ���,�������ܽ���!");
			  skill_response.sendRedirect(skill_request.getHeader("referer"));	
				
			}			
		}

	public void addSkill() throws IOException
	{
		HttpSession session = skill_request.getSession(true);

		String no = skill_request.getParameter("no"),
               label = new String(skill_request.getParameter("label").getBytes("ISO-8859-1"), "UTF-8"),
               sublabel = new String(skill_request.getParameter("sublabel").getBytes("ISO-8859-1"), "UTF-8"),
               type = new String(skill_request.getParameter("type").getBytes("ISO-8859-1"), "UTF-8"),
               summary = new String(skill_request.getParameter("summary").getBytes("ISO-8859-1"), "UTF-8"),
               code = new String(skill_request.getParameter("code").getBytes("ISO-8859-1"), "UTF-8"),
               path = new String(skill_request.getParameter("path").getBytes("ISO-8859-1"), "UTF-8");
 
			if(label!=null)//�ˆ����Ʋ���Ϊ��
			{

				Skill skill=new Skill();
				skill.setNO(Integer.parseInt(no));
				skill.setLabel(label);
				skill.setsubLabel(sublabel);
			    skill.setType(type);
				skill.setSummary(summary);
				skill.setCode(code);
				skill.setPath(path);

				try 
				{
					int result = skill.addSkill();
					if (result == 1) 
					{
						session.setAttribute("message","��ӽ̳���Ϣ�ɹ�!");
						skill_response.sendRedirect("skill.jsp");
					  } else
					{
						session.setAttribute("message","��ӽ̳���Ϣʧ�ܣ����Ժ�����!");
			    	    skill_response.sendRedirect("add_skill.jsp");
					}
				}
				catch (Exception ex) 
				{
					ex.printStackTrace();
				}
			}
			else
			{
				session.setAttribute("message","û��ָ���̳�����,�������ܽ���!");
	    	    skill_response.sendRedirect(skill_request.getHeader("referer"));
			}			
		}
		
	
public void delSkill() throws IOException{
	HttpSession session = skill_request.getSession(true);

		String sId = skill_request.getParameter("sid");
		String msg="";
		if(sId!=null)
		{
			int skillId=Integer.parseInt(sId);
			Skill skill=new Skill();
			skill.setID(skillId);
			skill=skill.getSkillBySkillID();
			try
			{
				int result = skill.deleteSkill();
				if(result==1)
				{
		    	    session.setAttribute("message","����ɾ���̳���Ϣ�ɹ�!");
		    	    skill_response.sendRedirect(skill_request.getHeader("referer"));
				}
				else
				{
					session.setAttribute("message","����ɾ���̳���Ϣʧ�ܣ����Ժ�����!");
		    	    skill_response.sendRedirect(skill_request.getHeader("referer"));
				}
			}
			catch (Exception ex) 
			{
				ex.printStackTrace();
			}
		}
		else
		{
			msg="û��ָ���̳�ID��";
			skill_response.sendRedirect("../error.jsp?msg="+msg);
		}
	}
}

