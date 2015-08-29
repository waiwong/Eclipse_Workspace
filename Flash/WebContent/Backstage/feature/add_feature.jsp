<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Menu Manage System</title>
<link rel="stylesheet" href="../css/main.css" type="text/css" />

<script language="javascript">
   function checkValidateForm() {
	   var regex1 = /^.*?\.(jpg|jpeg|bmp|gif|png|JPG|JPEG|PNG|GIF|BMP)$/;
	   var regex2 = /^.*?\.(swf|SWF)$/;
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

<div id="main">
	<jsp:include page="../header/header_link.jsp" flush="true"/>
	<div id="middle">
	   <jsp:include page="sidebar.jsp" flush="true"/>
	   
		<div id="center-column">
			<div class="top-bar">
				<h1>添加技术特色</h1>
				<div class="breadcrumbs">当前位置&gt;&gt;
				  <a href="../homepage.jsp">主页面</a> / 添加技术特色</div>
			</div><br />
			
			<div class="select-bar"></div>
		  
			<div class="table">
				<img src="../css/images/bg-th-left.gif" width="8" height="7" alt="" class="left" />
				<img src="../css/images/bg-th-right.gif" width="7" height="7" alt="" class="right" />
				
				<form name="ValidateForm" onSubmit="return checkValidateForm();" 
				      method=post action="FEATUREINFO">
                  <input type=hidden name=cmd value="addFeature"> 
                  
				  <table class="listing form" cellpadding="0" cellspacing="0">    
			
				                  
					<tr>
					  <th class="full" colspan="2">添加Flash技术特色</th>
					</tr>
                     
					<tr>
						<td class="first" width="172"><strong>【技术特色主题】</strong></td>
						<td class="last"><input type="text" name="title" size="30"
						    maxlength="50"> </td>
				   </tr>
				   <tr class="bg">
						<td class="first"><strong>【类 型】</strong></td>
						<td class="last"><input type="text" name="type" maxlength="50" 
						    size="30"></td>
				   </tr>
				    <tr>
						<td class="first"><strong>【SWF 路徑】</strong></td>
						<td class="last"><input type="file" name="path" maxlength="50" 
						    size="30"></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【URL】</strong></td>
						<td class="last"><input type="text" name="url" maxlength="50" 
						    size="30"></td>
					</tr>
					<tr>
						<td class="first"><strong>【图片路徑】</strong></td>
						<td class="last"><input type="file" name="photo" maxlength="50" 
						    size="30"></td>
					</tr>
					<tr class="bg">
						<td class="first"><strong>【摘 要】</strong></td>
						<td class="last"><textarea name="summary" cols=60 rows=5>
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
