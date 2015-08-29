<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Feature,
                 java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Feature Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">
   function checkValidateForm() {
	   if(ValidateForm.title.value==""||ValidateForm.title.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("技術特色主题不能为空！");
		   ValidateForm.title.focus();
		   return false;
		   }
	   if(ValidateForm.type.value==""||ValidateForm.type.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("请输入主题类型！");
		   return false;
		   }
	   if(!regex2.test(ValidateForm.path.value)) {
		   alert("请选择 *.swf 类型的文档！");
		   return false;
		   }
	   if(!regex1.test(ValidateForm.photo.value)) {
		   alert("请选择图片类型的文档！\n(*.jpg/*.jpeg/*.png/*.bmp/*.gif)");
		   return false;
		   }
	   }
</script>

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
				<h1>修改Flash技术特色</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改Flash技术特色</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="FEATUREINFO?fid=<%=fId%>">
                  <input type=hidden name=cmd value="editFeatureInfo"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2">修改<%=feature.getTitle()%>此主题的信息</th>
					</tr>
                     
					<tr>
						<td class="first" width="172"><strong>【技术特色主题】</strong></td>
						<td class="last"><input type="text" name="title" size="30" 
						    value="<%=feature.getTitle()%>"> </td>
				   </tr>
				   <tr class="bg">
						<td class="first" width="172"><strong>【类 型】</strong></td>
						<td class="last"><input type="text" name="type" size="30" 
						    value="<%=feature.getType()%>"> </td>
				   </tr>
				  <tr>
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><input type="text" name="path" maxlength="50" 
						    size="30" value="<%=feature.getPath()%>"></td>
				  </tr>
				  <tr class="bg">
						<td class="first"><strong>【URL】</strong></td>
						<td class="last"><input type="text" name="url" maxlength="50" 
						    size="30" value="<%=feature.getURL()%>"></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><input type="text" name="photo" maxlength="50" 
						    size="30" value="<%=feature.getPhoto()%>"></td>
				  </tr>
				  <tr class="bg">
						<td class="first"><strong>【摘 要】</strong></td>
						<td class="last"><textarea name="summary" cols=60 rows=5>
						    <%=feature.getSummary()%></textarea>
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
