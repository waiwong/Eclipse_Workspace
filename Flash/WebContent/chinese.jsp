<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Chinese,
                 java.util.Vector,
                 java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Chinese Flash</title>
</head>

<jsp:include page="header.jsp" flush="true"/>
<script src="js/popup_layer.js" type="text/javascript"></script>

<script>

$(document).ready(function() {
	new PopupLayer({trigger:"#ele",popupBlk:"#blk",closeBtn:"#close",useOverlay:true,
		offsets:{
			x:-600,
			y:-60
		}
	});
});

</script>

<body>

  <div id="container">
    <div id="wrapper">
          <div id="content">
          
   <%
      int total = 0;
      ArrayList<String> title = new ArrayList<String> ();
      Chinese chinese = new Chinese();
      Vector chineseVector = new Vector();
      chineseVector=chinese.getAllChinese();
      
      if(chineseVector!=null) total = chineseVector.size();
      for(int j=0; j<total;j++) {
          Chinese tempChinese = (Chinese)chineseVector.get(j);
          title.add(tempChinese.getTitle());
    %>
      
      
           <h1><a name=<%=tempChinese.getTitle() %>> <%=tempChinese.getTitle() %></a> </h1>
           <img class="alignleft" src=<%= tempChinese.getPath() %> />
           <p> <%= tempChinese.getDetail().replaceAll("\n","<br/><br/>" ) %> </p>
           <a class="backToTop" href="#" >&nbsp;</a> 

      <%} %>
    </div>
    
       <div id="sidebar">
          <div id="block0">
            <h2><img src="css/images/catalog.gif"/>&nbsp;Catalog (目錄)</h2>
            <% for (int j=1; j<=total; j++) { %>
              <h3> <a href="#<%=title.get(j-1)%>"> 
              <%=title.get(j-1) %>
              </a> </h3>          
            <% }%>
          </div>    
      </div>
 
       
     <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

</body>
</html>