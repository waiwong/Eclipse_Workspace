<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Menu" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="menu">

<%
   String mId = request.getParameter("mid");
   Menu menu = new Menu();
   menu.setID(Integer.parseInt(mId));
   menu = menu.getMenuByMenuID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>

	<div id="middle">
		<div id="left-column">
			<h3>菜单管理</h3>
			<ul class="nav">
				<li><a href="menu.jsp">菜单概览</a></li>
				<li class="last"><a href="add_menu.jsp">添加菜单</a></li>
			</ul>
		</div>
		<div id="center-column">
			<div class="top-bar">
				<h1>查看菜单信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看菜单信息</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
					<tr>
						<th class="full" colspan="2"><%=menu.getMenu()%>的详细信息</th>
					</tr>
					<tr>
						<td class="first" width="172"><strong>【菜单名称】</strong></td>
						<td class="last"><%=menu.getMenu()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【超连接】</strong></td>
						<td class="last"><%=menu.getURL()%></td>
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
