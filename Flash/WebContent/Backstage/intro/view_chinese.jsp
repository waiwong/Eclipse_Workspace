<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Chinese" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>ChineseFlash Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="intro">

<%
   String cId = request.getParameter("cid");
   Chinese chinese = new Chinese();
   chinese.setID(Integer.parseInt(cId));
   chinese = chinese.getChineseByChineseID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
       <jsp:include page="sidebar.jsp" flush="true"/>
		
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看闪客江湖资讯</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看闪客江湖资讯</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
				
				
					<tr>
						<th class="full" colspan="2"><%=chinese.getTitle()%>的详细信息</th>
					</tr>
					<tr>
						<td class="first" width="172"><strong>【简介名称】</strong></td>
						<td class="last"><%=chinese.getTitle()%></td>
				   </tr>
				   	<tr class="bg">
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><%=chinese.getPath()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【资讯內容】</strong></td>
						<td class="last">
						    <%=chinese.getDetail().replaceAll("\n","<br><br>")%>
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
