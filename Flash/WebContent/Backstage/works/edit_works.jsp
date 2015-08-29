<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Works,
                 java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Works Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

function checkValidateForm() {
	
	   var regex1 = /^.*?\.(jpg|jpeg|bmp|gif|png|JPG|JPEG|PNG|GIF|BMP)$/;
	   var regex2 = /^.*?\.(swf|SWF)$/;
	   if(ValidateForm.title.value==""||ValidateForm.title.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("作品名称不能为空！");
		   ValidateForm.title.focus();
		   return false;
		   }
	   if(ValidateForm.author.value==""||ValidateForm.author.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("请输入作者名称！");
		   return false;
		   }
	   if(ValidateForm.level1.value=="请选择"||ValidateForm.level1.value.replace(/(^\s*)|(\s*$)/g, "")=="请选择") {
		   alert("请为此作品选择一级索引类別！");
		   return false;
		   }
	   if(ValidateForm.level2.value==""||ValidateForm.level2.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("请为此作品输入二级索引类別！");
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
<body id="works">

<%
   String wId = request.getParameter("wid");
   Works works = new Works();
   works.setID(Integer.parseInt(wId));
   works = works.getWorksByWorksID();
%>

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>

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
				<h1>修改资源信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改资源信息</div>
			</div><br />
			
		  <div class="select-bar"> </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="WORKSINFO?wid=<%=wId%>">
                  <input type=hidden name=cmd value="editWorksInfo"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">  
				  
				                    
					<tr>
					  <th class="full" colspan="2">
					                修改作品---<%=works.getTitle()%>的信息
					  </th>
					</tr>                  
					<tr>
						<td class="first"><strong>【作品名称】</strong></td>
						<td class="last"><input type="text" name="title" size="30" 
						    value="<%=works.getTitle()%>"></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【一級索引】</strong></td>
						<td class="last"> <select name="level1">
                           
                           <option value="第一代闪客" <%if(works.getLevel1().equals("第一代闪客画")){%> selected<%}%>>第一代闪客</option>
                           <option value="第二代闪客" <%if(works.getLevel1().equals("第二代闪客")){%> selected<%}%>>第二代闪客</option>
                           <option value="新生代闪客" <%if(works.getLevel1().equals("新生代闪客")){%> selected<%}%>>新生代闪客</option>
                           <option value="国外闪客" <%if(works.getLevel1().equals("国外闪客")){%> selected<%}%>>国外闪客</option>
                        </select></td>
					</tr>
				   <tr>
						<td class="first"><strong>【二級索引】</strong></td>
						<td class="last"><input type="text" name="level2" size="30" 
						    value="<%=works.getLevel2()%>"></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【三級索引】</strong></td>
						<td class="last"><input type="text" name="level3" size="30" 
						    value="<%=works.getLevel3()%>"></td>
				   </tr>
				   <tr>
						<td class="first"><strong>【作 者】</strong></td>
						<td class="last"><input type="text" name="author" maxlength="50" 
						    size="30" value="<%=works.getAuthor()%>"></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><input type="text" name="photo" size="30" 
						    value="<%=works.getPhoto()%>"></td>
				   </tr>
				   <tr>
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><input type="text" name="path" maxlength="50" 
						    size="30" value="<%=works.getPath()%>"></td>
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
