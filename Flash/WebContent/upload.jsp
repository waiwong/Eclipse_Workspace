<%@ page language="java" contentType="text/html; charset=GBK"
    pageEncoding="GBK"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=GBK">
<title>Upload File</title>
<link rel="stylesheet" href="css/index.css" type="text/css"/>

<script language="javascript">

function checkValidateForm() {
	   var regex1 = /^.*?\.(jpg|jpeg|bmp|gif|png|JPG|JPEG|PNG|GIF|BMP)$/;
	   var regex2 = /^.*?\.(swf|SWF)$/;
	   if(ValidateForm.title.value==""||ValidateForm.title.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("��Ʒ���Ʋ���Ϊ�գ�");
		   ValidateForm.title.focus();
		   return false;
		   }
	   if(ValidateForm.author.value==""||ValidateForm.author.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("�������������ƣ�");
		   return false;
		   }
	   if(ValidateForm.level1.value=="��ѡ��"||ValidateForm.level1.value.replace(/(^\s*)|(\s*$)/g, "")=="��ѡ��") {
		   alert("��Ϊ����Ʒѡ��һ���������e��");
		   return false;
		   }
	   if(ValidateForm.level2.value==""||ValidateForm.level2.value.replace(/(^\s*)|(\s*$)/g, "")=="") {
		   alert("��Ϊ����Ʒ��������������e��");
		   return false;
		   } 
	   if(!regex2.test(ValidateForm.swf_file.value)) {
		   alert("��ѡ�� *.swf ���͵��ĵ���");
		   return false;
		   }
	   if(!regex1.test(ValidateForm.pic_file.value)) {
		   alert("��ѡ��ͼƬ���͵��ĵ���\n(*.jpg/*.jpeg/*.png/*.bmp/*.gif)");
		   return false;
		   } 
	   }


</script>

</head>

<jsp:include page="header.jsp" flush="true"/>
<body>

<div id="container">
  <div id="wrapper">
    <div id="content">
    <br>
    <h2>�ς��YԴ</h2>
      <form name="ValidateForm" method="post" action="fileUpload.jsp" 
            onSubmit="return checkValidateForm();" 
            ENCTYPE="multipart/form-data">                 
  
		<ul class="upload_bd_ul">
          <li>
                     
			<p class="upload_bd_ul_p1">��Ʒ���ƣ�</p>
			<p class="upload_bd_ul_p2">
			<input style="border-color: rgb(181, 184, 200);"
                   maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
                   onfocus="this.style.borderColor='#FFBA00'" 
                   class="text-input" type="text" name="title" maxlength="50"
				   size="30"></p>
		 </li>
		 
	   <li>
			<p class="upload_bd_ul_p1">�� �ߣ�</p>
			<p class="upload_bd_ul_p2">
			<input style="border-color: rgb(181, 184, 200);"  
                   maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
                   onfocus="this.style.borderColor='#FFBA00'" 
                   class="text-input" type="text" name="author" maxlength="50" 
				   size="30"></p>
	   </li>
				   
		 <li>
			<p class="upload_bd_ul_p1">�������</p>
			<p class="upload_bd_ul_p2">  
			<select style="width: 248px; margin:
                    3px 0pt 0pt;" name="level1">
              <option value="��ѡ��">��ѡ��</option>
              <option value="��һ������">��һ������</option>
              <option value="�ڶ�������">�ڶ�������</option>
              <option value="����������">����������</option>
              <option value="��������">��������</option>
            </select>
            </p>
         </li>
                    
		 <li>
			<p class="upload_bd_ul_p1">һ��������</p>
			<p class="upload_bd_ul_p2">
			<input style="border-color: rgb(181, 184, 200);" 
                   maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
                   onfocus="this.style.borderColor='#FFBA00'" 
                   class="text-input" type="text" name="level2" maxlength="50"
				   size="30"></p>
		</li>
		
		<li>
			<p class="upload_bd_ul_p1">����������</p>
			<p class="upload_bd_ul_p2">
			<input style="border-color: rgb(181, 184, 200);" 
                   maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
                   onfocus="this.style.borderColor='#FFBA00'" 
                   class="text-input" type="text" name="level3" maxlength="50"
				   size="30"></p>
		</li>
				   					
		<li>
		<p class="upload_bd_ul_p1">ͼƬ·����</p>
		<p class="upload_bd_ul_p2">
		<input style="border-color: rgb(181, 184, 200);" 
               maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
               onfocus="this.style.borderColor='#FFBA00'" 
               class="text-input" type="file" name="pic_file"></p>
		</li>
				   
		<li>
			<p class="upload_bd_ul_p1">SWF ·����</p>
			<p class="upload_bd_ul_p2">
			<input style="border-color: rgb(181, 184, 200);"
                   maxlength="30" onblur="this.style.borderColor='#B5B8C8'" 
                   onfocus="this.style.borderColor='#FFBA00'" 
                   class="text-input" type="file" name="swf_file"></p>
		</li>	


		<li> <p>
			<p class="upload_bd_ul_p1"></p>
			<input type="reset" value="Reset" style="height:22px">
			<input type="submit" value="Submit" style="height:22px">
		</li>
	 </ul>
	</form>

   </div>    
   
   <div id="sidebar">  
     <h2><img src="css/images/memo.gif"/>&nbsp; ��ܰ��ʾ </h2>
     <h3>1.���ϴ�( *.jpg/*.jpeg/*.png/*.bmp/*.gif ) ���͵�ͼƬ</h3>
     <h3>2.���ϴ�( *.swf ) ���͵� Flash ����</h3> 
   </div> 
   
    <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

</body>
</html>