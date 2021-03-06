<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Feature" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="feature">

<%
   String fId = request.getParameter("fid");
   Feature feature = new Feature();
   feature.setID(Integer.parseInt(fId));
   feature = feature.getFeatureByFeatureID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看Flash技术特色</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看Flash技术特色</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
					
					<tr>
						<th class="full" colspan="2"><%=feature.getTitle()%>的详细信息</th>
					</tr>
					<tr>
						<td class="first" width="172"><strong>【技术特色主题】</strong></td>
						<td class="last"><%=feature.getTitle()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【类 型】</strong></td>
						<td class="last"><%=feature.getType()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><%=feature.getPath()%></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【URL】</strong></td>
						<td class="last"><%=feature.getURL()%></td>
					</tr>
					<tr>
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><%=feature.getPhoto()%></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【摘 要】</strong></td>
						<td class="last"><%=feature.getSummary()%></td>
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
