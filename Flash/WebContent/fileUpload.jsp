<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.util.FileUpload,
                 jsp.sqlServer.bean.Works" %>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">

<html>
<head>
<title>Get Uploaded Resources</title>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" href="css/index.css" type="text/css"/>
<link rel="stylesheet" href="css/flash.css" type="text/css"/>
</head>

<jsp:include page="header.jsp" flush="true"/>

<body>

<div id="container">
  <div id="wrapper">
    <div id="content">
      <h2>资源信息</h2>
      
<%
    String path = "C:\\Documents and Settings\\DiLLY\\workspace\\Flash\\WebContent\\Upload\\";
    FileUpload file = new FileUpload();
    file.setRequest(request);
    file.setUploadPath(path);
    file.process();
	String title = file.getParameter("title"),
	       level1 = file.getParameter("level1"),
	       level2 = file.getParameter("level2"),
	       level3 = file.getParameter("level3"),
	       author = file.getParameter("author"),
	       pic = null,
	       swf = null;
	String[] fileArr = file.getUpdFileNames();

    pic = "Upload/"+fileArr[0];
    swf = "Upload/"+fileArr[1];
 
	if(title!=null) {

		Works works = new Works();
		works.setTitle(title); 
		works.setLevel1(level1.trim());
		works.setLevel2(level2.trim());
		works.setLevel3(level3.trim());
	    works.setAuthor(author);
		works.setPhoto(pic);
	    works.setPath(swf);

		try  {
		  int result=works.addWorks();
		  if (result == 1) {

%>

       <div class="block">
         <div class="bot">  
         
              <div class="row-articles articles">
                <div class="cl">&nbsp;</div>
                  <div class="article">
                   <div class="cl">&nbsp;</div>
                       
                        <div class="cnt">
                          <h4>作品名称 : <%=title %></h4>
                          <p>作者 : <%=author %> </p> 
                          <p>作者类別 : <%=level1 %></p>   
                          <p>作品類型 : <%=level2 %> 
                                                                                           〖<%=level3 %>〗                                                           
                          </p>                          
                          <p>上传文件数目 : <%=fileArr.length %></p>
                          <p>上传后图片名称 : <%=fileArr[0] %></p>
                          <p>上传后SWF名称 : <%=fileArr[1] %></p>
                         </div>
                         
                      <div class="cl">&nbsp;</div>
                    </div>
                </div>  
            </div>
         </div>
         
    <%      } else {
                out.println( "<p>Upload Error!</p>" );
              }
		  } catch (Exception ex) {}
		} 
	  %>
	  
   </div>     
   
      <div id="sidebar">
       <h2><img src="css/images/memo.gif"/>&nbsp; 温馨提示 </h2>
       <h3>该資源已順利地添加到系统据庫中,現在可返回 Flash 资源庫页面查看!</h3>
       </div>
   
    <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

</body>
</html>
