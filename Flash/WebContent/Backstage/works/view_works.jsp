<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Works" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Works Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="works">

<%
   String wId = request.getParameter("wid");
   Works works = new Works();
   works.setID(Integer.parseInt(wId));
   works = works.getWorksByWorksID();
%>

<div id="main">
	<jsp:include page="../header/header.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>Flash资源管理</h3>
			<ul class="nav">
				<li><a href="works.jsp">资源概览</a></li>
				<li class="last"><a href="add_works.jsp">添加资源</a></li>
			</ul>
		</div>
		
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看资源信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看资源信息</div>
			</div><br />
			
           <jsp:include page="search_bar.jsp" flush="true"/>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
				
				
					<tr>
						<th class="full" colspan="2">
						          作品---<%=works.getTitle()%>的详细信息
						</th>
					</tr>
				   <tr>
						<td class="first"><strong>【作品名称】</strong></td>
						<td class="last"><%=works.getTitle()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【作 者】</strong></td>
						<td class="last"><%=works.getAuthor()%></td>
					</tr>
				   <tr>
						<td class="first"><strong>【一級索引】</strong></td>
						<td class="last"><%=works.getLevel1()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【二級索引】</strong></td>
						<td class="last"><%=works.getLevel2()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【三級索引】</strong></td>
						<td class="last"><%=works.getLevel3()%></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><%=works.getPhoto()%></td>
				   </tr>
					<tr>
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><%=works.getPath()%></td>
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
