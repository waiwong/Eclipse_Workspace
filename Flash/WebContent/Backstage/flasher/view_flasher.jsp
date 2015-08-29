<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Flasher" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="flasher">

<%
   String fId = request.getParameter("fid");
   Flasher flasher = new Flasher();
   flasher.setID(Integer.parseInt(fId));
   flasher = flasher.getFlasherByFlasherID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>闪客管理</h3>
			<ul class="nav">
				<li><a href="flasher.jsp">闪客概览</a></li>
				<li class="last"><a href="add_flasher.jsp">添加闪客信息</a></li>
			</ul>
		</div>
		
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看闪客信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看闪客信息</div>
			</div><br />
			
			<div class="select-bar">
			   <form action=search.jsp method=post name=myform onSubmit="return CheckForm();">
                 <input type=hidden name=search value="form1">
                   <select name=searchType>
                     <option value="none">请选择搜索标准</option>
                     <option value="男闪客">男闪客</option>
                     <option value="女闪客">女闪客</option>
                     <option value="闪客工作室">闪客工作室</option>
                   </select>&nbsp;
                   <input type=text size=30 name=keyword value="请输入作者名称">&nbsp;
                   <input type=submit value="Sumbit">
                </form>
		     </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
					<tr>
						<th class="full" colspan="2"><%=flasher.getTruename()%>的详细信息</th>
					</tr>
					
					
					<tr>
						<td class="first" width="200"><strong>【网 名】</strong></td>
						<td class="last"><%=flasher.getNickname()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【閃客/工作室名称】</strong></td>
						<td class="last"><%=flasher.getTruename()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【类 別】</strong></td>
						<td class="last"><%=flasher.getKind()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【第一个 Flash 作品】</strong></td>
						<td class="last"><%=flasher.getFirst()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【經典 Flash 作品】</strong></td>
						<td class="last"><%=flasher.getClassic()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><%=flasher.getPhoto()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【详细介绍】</strong></td>
						<td class="last">
						   <%=flasher.getDetail().replaceAll("\n","<br><br>")%>
						</td>
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
