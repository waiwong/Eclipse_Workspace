<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Flasher,
                 java.util.Vector" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

function checkValidateForm() {
	   var regex = /^.*?\.(jpg|jpeg|bmp|gif|png|JPG|JPEG|PNG|GIF|BMP)$/;
	   if(ValidateForm.truename.value==""||ValidateForm.truename.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("闪客/工作室名称不能为空！");
		   ValidateForm.truename.focus();
		   return false;
		   }
	   if(ValidateForm.kind.value=="请选择"||ValidateForm.kind.value.replace(/(^\s*)|(\s*$)/g, "")=="请选择") {
		   alert("类別不能为空！");
		   return false;
		   }
	   if(ValidateForm.classic.value==""||ValidateForm.classic.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("經典 Flash 作品不能为空！");
		   return false;
		   } 
	   if(!regex.test(ValidateForm.photo.value)) {
		   alert("请选择图片类型的文档！\n(*.jpg/*.jpeg/*.png/*.bmp/*.gif)");
		   return false;
		   }   	   
	   }

</script>

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
				<h1>修改闪客信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 修改闪客信息</div>
			</div><br />
			
		  <div class="select-bar"> </div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="FLASHERINFO?fid=<%=fId%>">
                  <input type=hidden name=cmd value="editFlasherInfo"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">                    
					<tr>
					  <th class="full" colspan="2">修改<%=flasher.getTruename()%>的信息</th>
					</tr>
                    
                     
					<tr>
						<td class="first"><strong>【网名】</strong></td>
						<td class="last"><input type="text" name="nickname" size="30" 
						    value="<%=flasher.getNickname()%>"></td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【閃客/工作室名称】</strong></td>
						<td class="last"><input type="text" name="truename" maxlength="50" 
						    size="30" value="<%=flasher.getTruename()%>">
						    &nbsp;<font color=red size=-1>*</font></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【类別】</strong></td>
						<td class="last"><select name="kind">
                           <option value="请选择" <%if(flasher.getKind().equals("请选择")){%> selected<%}%>>请选择</option>
                           <option value="男闪客" <%if(flasher.getKind().equals("男闪客")){%> selected<%}%>>男閃客</option>
                           <option value="女闪客" <%if(flasher.getKind().equals("女闪客")){%> selected<%}%>>女閃客</option>
                           <option value="闪客工作室" <%if(flasher.getKind().equals("闪客工作室")){%> selected<%}%>>閃客工作室</option>
                        </select></td>
				  </tr>
				  <tr class="bg">
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><input type="text" name="photo" maxlength="50" 
						    size="30" value="<%=flasher.getPhoto()%>"></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【第一個 Flash 作品】</strong></td>
						<td class="last"><input type="text" name="first" maxlength="50" 
						    size="30" value="<%=flasher.getFirst()%>"></td>
				  </tr>
				  <tr class="bg">
						<td class="first"><strong>【經典 Flash 作品】</strong></td>
						<td class="last"><input type="text" name="classic" maxlength="50" 
						    size="30" value="<%=flasher.getClassic()%>"></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【详细介绍】</strong></td>
						<td class="last"><textarea name="detail" cols=60 rows=5>
						    <%=flasher.getDetail()%></textarea>
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
