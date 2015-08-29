<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Feature_info,
                 java.util.Vector" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature_info Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">
   function checkValidateForm() {
	   if(ValidateForm.title.value==""||ValidateForm.title.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("Flash技术主题不能为空！");
		   ValidateForm.title.focus();
		   return false;
		   }
	   if(ValidateForm.index.value==""||ValidateForm.index.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("请输入索引键！");
		   return false;
		   }
	   }
</script>

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
				<h1>修改Flash细節技术的信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改Flash细節技术的信息</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="FEATURE_infoINFO?fid=<%=fId%>">
                  <input type=hidden name=cmd value="editFeature_infoInfo"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2">修改<%=feature_info.getTitle()%>的技术信息</th>
					</tr>
                     
                    <tr>
						<td class="first" width="120"><strong>【索 引 键】</strong></td>
						<td class="last"><input type="text" name="index" size="30" 
						    value="<%=feature_info.getNO()%>"> </td>
				   </tr> 
					<tr class="bg">
						<td class="first"><strong>【技术主题】</strong></td>
						<td class="last"><input type="text" name="title" size="30" 
						    value="<%=feature_info.getTitle()%>"> </td>
				   </tr>
				  <tr>
						<td class="first"><strong>【摘 要】</strong></td>
						<td class="last"><textarea name="summary" cols=60 rows=5>
						    <%=feature_info.getSummary()%></textarea>
						</td>
				  </tr>
				  
				</table>
				
				<div class="select">
				   <input type="reset" value="Reset" style="height:22px">
				   <input type="submit" value="Modify" style="height:22px">
				</div>
			  </form>
		   </div>			  				
        </div>

       <jsp:include page="../messagebar.jsp" flush="true"/>
     </div>
   <div id="footer"></div>
</div>
</body>
</html>
