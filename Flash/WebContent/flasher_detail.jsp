<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.util.*,
                 jsp.sqlServer.bean.Flasher,
                 jsp.sqlServer.bean.Works,
                 java.util.Vector" %>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Flasher Detail</title>
</head>

<link rel="stylesheet" href="css/index.css" type="text/css"/>
<link rel="stylesheet" href="css/flash.css" type="text/css"/>
<script src="js/SpryTab.js" type="text/javascript"></script>
<jsp:include page="header.jsp" flush="true"/>

<body>

<div id="container">
  <div id="wrapper">
    <div id="content">
    
      <%
         String kind = null,
                swf = null,
                author = null;
         int total, all_w = 0;
         ArrayList<String> photo = new ArrayList<String>(),
                           title = new ArrayList<String>(),
                           path = new ArrayList<String>();
         String str = request.getParameter("id");
         int parm = Integer.parseInt(str);
 
         Flasher flasher = new Flasher();
         Vector flasherVector = new Vector();
         flasherVector = flasher.getAllFlasher();
         parm -= 1;
         Flasher tempFlasher = (Flasher)flasherVector.get(parm);
         if(tempFlasher.getKind().equals("闪客工作室")) 
           author =  tempFlasher.getTruename();
         else author = tempFlasher.getNickname();
           
         Works works = new Works();
         Vector worksVector = new Vector();
         worksVector = works.getAllWorks();
         if(worksVector!=null) all_w = worksVector.size();
         total = 0;
         for(int i=0; i<all_w; i++) {
           Works tempWorks = (Works)worksVector.get(i);
           if(tempWorks.getAuthor().equals(author)) {
          	   total++;
          	   photo.add (tempWorks.getPhoto());
          	   title.add (tempWorks.getTitle());
          	   path.add (tempWorks.getPath()); 
           }
         }
                 
           if(tempFlasher.getKind().equals("闪客工作室")) {
             kind = tempFlasher.getKind();
             out.print("<h1>" +tempFlasher.getTruename()+ "の 詳細介紹</h1>");
      %>
    
              
        	<div class="feat_prod_box_details">            
            	<div class="prod_img"><img src=<%=tempFlasher.getPhoto() %> alt="" title="" border="0" />
            </div>
            
            <div class="prod_det_box">
                <div class="box_top"></div>
                <div class="box_center">
                    <div class="prod_title"> <strong>
                                                                工作室名稱 :<%=tempFlasher.getTruename() %></strong>
                    </div>
                    <p class="details">
                                                                類 型 :<%=tempFlasher.getKind() %> <br>
                                                                經典Flash作品 :<%=tempFlasher.getClassic() %>  <br><br>
                                                                工作室簡介/自述  : <br>
                        <%=tempFlasher.getDetail().replaceAll("\n","<br><br>") %>                         
                    </p>

                    <div class="clear"></div>
                    </div>      
                      <div class="box_bottom"></div>
                </div>    
            <div class="clear"></div>
            </div>	  
            
                        
            <div id="demo" class="demolayout">
            
                <ul id="demo-nav" class="demolayout">
                <li><a class="active" href="#tab1">經典 Flash 作品</a></li>
                </ul>
                
                <div class="tabs-container">
                   <div style="display: block;" class="tab" id="tab1">
                   
                    <%for (int j=0; j<total; j++) { %>   
                                            
                       <div id=<%="ele" + String.valueOf(j) %> class="tigger">
                         <div class="group"> 
                           <a href="view_resource.jsp?path=<%=path.get(j) %>">
                           <img src=<%=photo.get(j)  %> alt="<%=title.get(j) %>"
                                title="<%=title.get(j) %>"/></a>
                         </div> 
                       </div>
                                  
                     <% } %>
                     
                     <div class="clear"></div>
                   </div>	
                 </div>
                 
            </div>

          
         <% 
            } else  { 
            	kind = tempFlasher.getKind();
            	out.print("<h1>" +tempFlasher.getNickname()+ "の 詳細介紹</h1>");
         %>
         
            <div class="feat_prod_box_details">            
            	<div class="prod_img"><img src=<%=tempFlasher.getPhoto() %> 
            	     alt="" title="" border="0" /> </div>
            
                <div class="prod_det_box">
                   <div class="box_top"> </div>
                   <div class="box_center">
                      <div class="prod_title"> <strong>
                                                                   網 名 :<%=tempFlasher.getNickname() %></strong>
                      </div>
                      <p class="details">
                                                                真實姓名 : <%=tempFlasher.getTruename() %> <br>
                                                                類 型 : <%=tempFlasher.getKind() %> <br>
                                                                經典Flash作品 : <%=tempFlasher.getFirst() %>  <br>
                                                                經典Flash作品 : <%=tempFlasher.getClassic() %>  <br>                        
                      </p>

                      <div class="clear"></div>
                  </div>      
                  <div class="box_bottom"></div>
              </div>    
              <div class="clear"></div>
           </div>	  
            
                       
           <div id="demo" class="demolayout">
                <ul id="demo-nav" class="demolayout">
                  <li><a class="active" href="#tab1">详细介绍</a></li>
                  <li><a class="" href="#tab2">經典 Flash 作品</a></li>
                </ul>
                
                <div class="tabs-container">
                    <div style="display: block;" class="tab" id="tab1">
                         <p>
                           <%=tempFlasher.getDetail().replaceAll("\n","<br><br>") %>
                         </p>                        
                    </div>	
                    
                    <div style="display: block;" class="tab" id="tab2">
                                        
                    <%for (int j=0; j<total; j++) { %>   
                                            
                       <div id=<%="ele" + String.valueOf(j) %> class="tigger">
                         <div class="group"> 
                           <a href="view_resource.jsp?path=<%=path.get(j) %>">
                           <img src=<%=photo.get(j)  %> alt="<%=title.get(j) %>"
                                title="<%=title.get(j) %>"/></a>
                         </div> 
                       </div>
                                  
                     <% } %>

                   </div>
                 </div>
              </div>
         

        <% } %>        

    <a class="backToTop" href="#">&nbsp;</a> 
    </div>

     
    <div id="sidebar">   
    
   <% 
     if(kind.equals("男闪客")) { 
       swf = "men.swf";
     }
     else if (kind.equals("女闪客")) {
	   swf = "women.swf";
     }
     else {
	   swf = "team.swf";
     }
   %>


       <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width="250" height="330" id="FlashID">
         <param name="movie" value=<%=swf %> />
         <param name="quality" value="high" />
         <param name="wmode" value="transparent" />
         <param name="swfversion" value="9.0.45.0" />
 
       <!--[if !IE]>-->
       <object type="application/x-shockwave-flash" data=<%=swf%> width="250" height="330">
         <param name="quality" value="high" />
         <param name="wmode" value="transparent" />
         <param name="swfversion" value="9.0.45.0" /> 
       </object>
       <!--<![endif]-->
       </object>
    
    </div>
      
      <jsp:include page="footer.jsp" flush="true"/>
  </div>
</div>

<script type="text/javascript">

var tabber1 = new SpryTab({
id: 'demo'
});

</script>

</body>
</html>