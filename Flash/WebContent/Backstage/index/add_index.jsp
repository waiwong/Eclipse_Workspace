<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chart1 Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">

function checkValidateForm() {
	   var regex1 = /^.*?\.(jpg|jpeg|bmp|gif|png|JPG|JPEG|PNG|GIF|BMP)$/;
	   var regex2 = /^.*?\.(swf|SWF)$/;
	   if(ValidateForm.title.value==""||ValidateForm.title.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("主题名称不能为空！");
		   ValidateForm.title.focus();
		   return false;
		   }
	   if(ValidateForm.type.value==""||ValidateForm.type.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("类型不能为空！");
		   return false;
		   }
	   if(ValidateForm.content.value==""||ValidateForm.content.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("主题介绍不能为空！");
		   return false;
		   }
	   if(!regex2.test(ValidateForm.swf.value)) {
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
<body id="index">

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
		
		
		<div id="center-column">
			<div class="top-bar">
				<h1>添加主题信息</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 添加主题信息</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="INDEXINFO">
                  <input type=hidden name=cmd value="addIndex"> 
                  
			   <table class="listing form" cellpadding="0" cellspacing="0">  
			                   
			                     
					<tr>
					  <th class="full" colspan="2">添加主题信息</th>
					</tr>
                     
					<tr>
						<td class="first" width="200px"><strong>【主題名称】</strong></td>
						<td class="last"><input type="text" name="title" size="30" 
						    maxlength="50"> </td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【类型】</strong></td>
						<td class="last"><input type="text" name="type" maxlength="50" 
						    size="30"></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><input type="file" name="photo" maxlength="50" 
						    size="30"></td>
				  </tr>
				  <tr class="bg">
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><input type="file" name="swf" maxlength="50" 
						    size="30"></td>
				  </tr>
				  <tr>
						<td class="first"><strong>【主題介紹】</strong></td>
						<td class="last"><textarea name="content" cols=60 rows=5>
						   </textarea></td>
				  </tr>
				  
				</table>
				
				<div class="select">
				   <input type="reset" value="Reset" style="height:22px">
				   <input type="submit" value="Add" style="height:22px">
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
