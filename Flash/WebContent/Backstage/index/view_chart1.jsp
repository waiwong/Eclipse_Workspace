<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="java.util.Vector,
                 jsp.sqlServer.bean.Chart1" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart1 Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />
</head>
<body id="index">

<%
   String cId = request.getParameter("cid");
   Chart1 chart1 = new Chart1();
   chart1.setID(Integer.parseInt(cId));
   chart1 = chart1.getChart1ByChart1ID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		<div id="center-column">
			<div class="top-bar">
				<h1>查看線狀图信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 查看線狀图信息</div>
			</div><br />
			
			<div class="select-bar"> </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				<table class="listing form" cellpadding="0" cellspacing="0">
					<tr>
						<th class="full" colspan="2"><%=chart1.getName()%>的详细信息</th>
					</tr>
					<tr>
						<td class="first"><strong>【多媒体播放器名称】</strong></td>
						<td class="last"><%=chart1.getName()%></td>
				   </tr>
					<tr class="bg">
						<td class="first"><strong>【在市場上所占百分比】</strong></td>
						<td class="last"><%=chart1.getPercent()%></td>
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
