<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>

<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<title>Opening Movie</title>
<style type="text/css">
	
	html {
		height: 100%;
		overflow: hidden;
		color:#CCCCCC;
	}
		
	#flashcontent {
		height: 100%;
	}

	body {
		height: 100%;
		margin: 0;
		padding: 0;
	}

</style>
</head>

<body>

     <object classid="clsid:D27CDB6E-AE6D-11cf-96B8-444553540000" width=100% height=100% id="FlashID">
        <param name="movie" value="swf/Other/Opening_Movie.swf" />
        <param name="quality" value="high" />
        <param name="wmode" value="opaque" />
        <param name="swfversion" value="9.0.45.0" />
 
      <!--[if !IE]>-->
      <object type="application/x-shockwave-flash" data="swf/Other/Opening_Movie.swf" width=100% height=100%>
        <param name="quality" value="high" />
        <param name="wmode" value="opaque" />
        <param name="swfversion" value="9.0.45.0" /> 
      </object>
     <!--<![endif]-->
    </object>
	
    	
</body>
</html>

