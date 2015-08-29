<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Skill" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Skill Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="skill">

<%
   String sId = request.getParameter("sid");
   Skill skill = new Skill();
   skill.setID(Integer.parseInt(sId));
   skill = skill.getSkillBySkillID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	
		<div id="left-column">
			<h3>Flash 实用技巧管理</h3>
			<ul class="nav">
				<li><a href="skill.jsp">实用技巧概览</a></li>
				<li class="last"><a href="add_skill.jsp">添加实用技巧</a></li>
			</ul>
		</div>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看Flash 技巧信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看Flash 技巧信息</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
								
					<tr>
						<th class="full" colspan="2">查看
						
						<%if(skill.getID()<4) {
							out.print(skill.getLabel());
						} else {
							out.print(skill.getsubLabel());
						}%>
						
						的详细信息</th>
					</tr>

					<tr class="bg">
						<td class="first" width="185"><strong>【索 引 鍵】</strong></td>
						<td class="last"><%=skill.getNO()%></td>
				   </tr>					
					<tr>
						<td class="first"><strong>【实用技巧主题】</strong></td>
						<td class="last"><%=skill.getLabel()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【技巧名称】</strong></td>
						<td class="last"><%=skill.getsubLabel()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【类 型】</strong></td>
						<td class="last"><%=skill.getType()%></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【教程內容】</strong></td>
						<td class="last"><%=skill.getSummary().replaceAll("\n","<br>")%></td>
					</tr>
					<tr>
						<td class="first"><strong>【教程中代碼】</strong></td>
						<td class="last"><%=skill.getCode().replaceAll("\n","<br>")%></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong></strong></td>
						<td class="last"><%=skill.getPath()%></td>
					</tr>
					
					
				</table>
              </div>
            </div>
            
       <jsp:include page="../messagebar.jsp" flush="true"/>
     </div>
   <div id="footer"></div>
</div>
</body>
</html>
