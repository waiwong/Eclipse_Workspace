<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Intro,
                 java.util.Vector,
                 java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flash Introduction</title>
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
       <div id="Directory">
          <div id="content">
          
      <%
      int total = 0 ,
          i = 1,
          count = 0, 
          num = 0;
      ArrayList<String> title = new ArrayList<String> (),
                        stitle = new ArrayList<String> (),
                        sdetail = new ArrayList<String> (),
                        spath = new ArrayList<String> ();
      Intro intro = new Intro();
      Vector introVector = new Vector();
      introVector=intro.getAllIntro();
      if(introVector!=null) total = introVector.size();
      for(int j=0; j<total;j++) {
          Intro tempIntro = (Intro)introVector.get(j);
          if(tempIntro.getType().equals("Text")) {
        	  title.add( tempIntro.getTitle() );
      %>
      
      
          <div id="<%="block" + String.valueOf(i) %>" >
            <h1><a name=<%=tempIntro.getTitle() %>> <%=tempIntro.getTitle() %></a> </h1>
            <img class="alignleft" src=<%= tempIntro.getPath() %> />
            <p> <%= tempIntro.getDetail().replaceAll("\n","<br/><br/>" ) %> </p>
            <a class="backToTop" href="#" >&nbsp;</a> 
          </div>  
    
      <%
          i++; count++;
          } else if ( tempIntro.getType().equals("swf") ) {
         	 stitle.add( tempIntro.getTitle() );
        	 sdetail.add( tempIntro.getDetail() );
        	 spath.add( tempIntro.getPath() );
        	 num++;        	  
          }
        }
      %>
      
    </div>
    
       <div id="sidebar">
          <div id="block0">
            <h2><img src="css/images/catalog.gif"/>&nbsp;Catalog (目錄)</h2>
            <% for (int j=1; j<=count; j++) { %>
              <h3> <a href="#<%=title.get(j-1)%>"> 
              <%=title.get(j-1) %>
              </a> </h3>          
            <% }%>
          </div>
          
          <br>
          <% for (int j=0; j<num; j++) { %>
            <h2><img src="css/images/flash.gif"/>&nbsp;<%=stitle.get(j) %></h2>
            <p><%=sdetail.get(j) %></p>

            <div class="list">         
              <div id="ele" class="tigger">查看Flash 各版本的啓动画面...</div>
              <div id="blk" class="blk" style="display:none;">
              <div class="main">
                <h2>Flash Historical Version</h2>
                  <a href="javascript:void(0)" 
                     id="close" class="closeBtn"> </a>
                   <ul>
                   
                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="700" height="300" id="FlashID">
                      <param name="movie" value=<%=spath.get(j) %> />
                      <param name="quality" value="high" />
                      <param name="wmode" value="opaque" />
                      <param name="swfversion" value="9.0.45.0" />
 
                    <!--[if !IE]>-->
                    <object type="application/x-shockwave-flash" data=<%=spath.get(j) %> width="700" height="300">
                      <param name="quality" value="high" />
                      <param name="wmode" value="opaque" />
                      <param name="swfversion" value="9.0.45.0" /> 
                    </object>
                    <!--<![endif]-->
                    </object>
                                                                
                  </ul>
               </div>
            </div>
          </div>
        <%} %>
        
        
      </div>            
     </div> 
     <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

</body>
</html>