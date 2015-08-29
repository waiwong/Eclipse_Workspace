<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Feature_info" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature_info Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="feature">

<%
   String fId = request.getParameter("fid");
   Feature_info feature_info = new Feature_info();
   feature_info.setID(Integer.parseInt(fId));
   feature_info = feature_info.getFeature_infoByFeature_infoID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看详尽的Flash技术信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看详尽的Flash技术信息</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
				
					<tr>
						<th class="full" colspan="2"><%=feature_info.getTitle()%>的详细信息</th>
					</tr>
					<tr>
						<td class="first" width="120"><strong>【索 引 键】</strong></td>
						<td class="last"><%=feature_info.getNO()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【技术主题】</strong></td>
						<td class="last"><%=feature_info.getTitle()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【摘 要】</strong></td>
						<td class="last">
						   <%=feature_info.getSummary().replaceAll("\n","<br><br>")%>
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
