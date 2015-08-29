<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="jsp.sqlServer.bean.Index,
                 java.util.Vector,
                 java.util.*" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flash Index</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<script src="js/popup_layer.js" type="text/javascript"></script>
<jsp:include page="header.jsp" flush="true"/>
<jsp:include page="forXML.jsp"/>

<body>


<div id="container">
  <div id="wrapper">
    <div id="content">
    
    
      <%
        int total = 0 , num = 0;
        ArrayList<String> title = new ArrayList<String> (),
                          content = new ArrayList<String> (),
                          swf = new ArrayList<String> ();
        Index index = new Index();
        Vector indexVector = new Vector();
        indexVector=index.getAllIndex();
        if(indexVector!=null) total = indexVector.size();
        for(int i=0; i<total;i++) {
          Index tempIndex = (Index)indexVector.get(i);
          if(tempIndex.getType().equals("index")) {
      %>
    
    
      <h1> <%= tempIndex.getTitle() %> </h1>
      <img class="alignleft" src = <%=tempIndex.getPhoto()%> />
      <p> <%= tempIndex.getContent().replaceAll("\n","<br/><br/>" ) %> </p>
      <a class="backToTop" href="#">&nbsp;</a> 
      
      <%
         } else if (tempIndex.getType().equals("chart")) {
        	 title.add( tempIndex.getTitle() );
        	 content.add( tempIndex.getContent() );
        	 swf.add( tempIndex.getSWF() );
        	 num++;
         }          
      }
      %> 
      
    </div>
      
    <div id="sidebar">
       <% for (int i=0;i<num ;i++) { %>
       <h2> <img src="css/images/flash.gif"/>&nbsp;<%= title.get(i) %> </h2>
       <p> <%= content.get(i).replaceAll("\n","<br>" ) %> </p> 
       <div id="ele" class="tigger"> <img src="css/images/view.png"/>
                                查看 Adobe Flash Player 图表
       </div>
       
       <div id="blk" class="block" style="display:none;">
            <div class="main">
                <h2>Adobe Flash Player 普及程度示意图</h2>
                <a href="javascript:void(0)" id="close" class="closeBtn"></a>
                <ul>
     
                    <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="550" height="640" id="FlashID">
                      <param name="movie" value=<%=swf.get(i) %> />
                      <param name="quality" value="high" />
                      <param name="wmode" value="opaque" />
                      <param name="swfversion" value="9.0.45.0" />
 
                    <!--[if !IE]>-->
                    <object type="application/x-shockwave-flash" data=<%=swf.get(i)%> width="550" height="640">
                      <param name="quality" value="high" />
                      <param name="wmode" value="opaque" />
                      <param name="swfversion" value="9.0.45.0" /> 
                    </object>
                    <!--<![endif]-->

                    </object>
                 </ul>
             </div>
          </div>
         <%} %>
         
    </div>     
      <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>


<script type="text/javascript">

$(document).ready(function() {

	    str = new PopupLayer({trigger:"#ele",popupBlk:"#blk",closeBtn:"#close",
	    	useOverlay:true,offsets:{
			x:-500,
			y:-400
		}
	});
});
</script>

</body>
</html>