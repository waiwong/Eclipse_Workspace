<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<%@ page import="java.io.*,
                 java.sql.*,
                 java.util.*,
                 org.jdom.*,
                 org.jdom.output.*"%>
                 
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta content="text/html; charset=UTF-8" http-equiv="content-type">
<title>JSP for XML</title>
<%@ include file = "link_db.jsp"  %>   
</head>
<body>
<%
   Connection conn; 
   ResultSet rs; 
   conn = getConnection(); 
   
   String path = "C:\\Documents and Settings\\DiLLY\\workspace\\Flash\\WebContent\\xml\\",
          sql1 = "select Distinct NO from t_skill Where Type = 'detail'",
          sql2 = "select * from t_chart1", 
          sql3 = "select * from t_chart2",
          sql4 = "select Title,Summary,SWF,URL from t_feature Where Type = 'RollBar'", 
          sql5 = "select * from t_version",
          sql6 = "select ID,Nickname from t_flasher Where Type = '男闪客'",
          sql7 = "select ID,Nickname from t_flasher Where Type = '女闪客'",
          sql8 = "select ID,Truename from t_flasher Where Type = '闪客工作室'",
          sql9 = "select Distinct Level1 from t_works";
   
   int num1, num2, num3, num4, num5, num6, num7;
   ResultSetMetaData rsmd1, rsmd2, rsmd3, rsmd4, rsmd5, rsmd6, rsmd7;
   Document document1, document2, document3, document4, document5, document6, document7;
   XMLOutputter outp1, outp2, outp3, outp4, outp5, outp6, outp7;
   Element element1, element2, element3, element4, element5, element6, element7;
   

    /******************************* skill.xml *******************************/
	int total = 0, num = 0;
	ArrayList<String> sublabel = new ArrayList<String>();
	ArrayList<Integer> id = new ArrayList<Integer>();
	String label ="";
	rs = executeQuery(conn,sql1);

	FileOutputStream fo = new FileOutputStream(path+"skill.xml");
    PrintStream so = new PrintStream(fo);
    so.println("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
    so.println("<categories>");
    
    while(rs.next()) {
    	total = rs.getRow();
    }

    
    for (int i=1; i<=total; i++) {
        String temp = "select ID,Label,subLabel from t_skill Where NO = "+i;
        rs = executeQuery(conn,temp);
        sublabel.clear();
        id.clear();
        while (rs.next()) {
    	  label=rs.getString("Label");
    	  sublabel.add(rs.getString("subLabel"));
    	  id.add(rs.getInt("ID"));
    	  num = rs.getRow();
        }

        so.println("<category name=\""+label+"\">");
        for (int j=0; j<num;j++) {
    	  
    try {
          byte[] temp_label = sublabel.get(j).getBytes("GB2312");
          String sub = new String(temp_label);
          so.println("<item>");
          so.println("<title>"+sub+"</title>");
          so.println("<url>skill_detail.jsp?sid="+id.get(j)+"</url>");
          so.println("</item>");
       } catch(Exception e) {
           out.println(e.toString());
           }

       } so.println("</category>");
       }
    so.println("</categories>");
    so.close();
    

    
    /******************************* Combobox.xml *******************************/
	int count1 = 0, count2 = 0, count3 = 0;
	ArrayList<String> level1 = new ArrayList<String>(),
	                  level2 = new ArrayList<String>(),
	                  level3 = new ArrayList<String>();

	rs = executeQuery(conn,sql9);

	FileOutputStream fos = new FileOutputStream(path+"Combobox.xml");
    PrintStream ps = new PrintStream(fos);
    ps.println("<?xml version=\"1.0\" encoding=\"GB2312\" ?>");
    ps.println("<items>");

    try {
      while(rs.next()) {
    	 level1.add( rs.getString("Level1") );  
    	 count1 = rs.getRow();
     }

    
    for (int i=0; i<count1; i++) {
        byte[] temp_level1 = level1.get(i).getBytes("GB2312");
        String sub1 = new String(temp_level1);
        int flag = i+1;
        ps.println("<sub index=\""+flag+". "+sub1+"\">");

        String temp_box1 = "select Distinct Level2 from t_works where Level1='"+level1.get(i)+"'";
        rs = executeQuery(conn,temp_box1);
        level2.clear();
        while (rs.next()) {
        	level2.add( rs.getString("Level2") );  
        	count2 = rs.getRow();
        }

        for (int j=0; j<count2; j++) {
            byte[] temp_level2 = level2.get(j).getBytes("GB2312");
            String sub2 = new String(temp_level2);
            int flag1 = j+1;
            ps.println("<sub2 index=\""+flag1+". "+sub2+"\">");
            
            String temp_box2 = "select Distinct Level3 from t_works where Level1='"+level1.get(i)+"'" 
                               +"and Level2='"+level2.get(j)+"'";
            rs = executeQuery(conn,temp_box2);
            level3.clear();
            while (rs.next()) {
            	count3 = rs.getRow();
            	level3.add( rs.getString("Level3") );  
            } 
            for (int k=0; k<count3; k++) {
            	int flag2 = k+1;
                byte[] temp_level3 = level3.get(k).getBytes("GB2312");
                String sub3 = new String(temp_level3);
                ps.println("<item index=\""+flag2+". "+sub3+"\"/>");
            }
            
            ps.println("</sub2>");
        }ps.println("</sub>");
    } 
       } catch(Exception e) {
           out.println(e.toString());
           }
 
    ps.println("</items>");
    ps.close();    
    
    
    
    /******************************* chart1.xml *******************************/    
   
   rs = executeQuery(conn,sql2);

   document1 = new Document(new Element("list"));//創建文檔
   rsmd1 = rs.getMetaData(); //獲取字段名
   num1 = rsmd1.getColumnCount(); //獲取字段數

   while(rs.next()){ //將查詢結果取出
     element1 = new Element("Product"); //創建元素 生成JDOM樹
     document1.getRootElement().addContent(element1);
     for (int i=1; i<=num1; i++) { 
    	String date = new String(rs.getString(i)); //代碼轉換
        Element element1_1 = new Element(rsmd1.getColumnName(i)).setText(date);
        element1.addContent(element1_1);
        }
    }

    outp1 = new XMLOutputter();
    outp1.output(document1, new FileOutputStream(path+"chart1.xml")); //輸出XML文檔
    
    
 
    
    /******************************* chart2.xml *******************************/    

    rs = executeQuery(conn,sql3);

    document2 = new Document(new Element("list"));
    rsmd2 = rs.getMetaData();
    num2 = rsmd2.getColumnCount(); 

    while(rs.next()){ 
      element2 = new Element("Version"); 
      document2.getRootElement().addContent(element2);
      for (int i=1; i<=num2; i++) { 
     	String date = new String(rs.getString(i)); 
         Element element2_2 = new Element(rsmd2.getColumnName(i)).setText(date);
         element2.addContent(element2_2);
         }
     }

     outp2 = new XMLOutputter();
     outp2.output(document2, new FileOutputStream(path+"chart2.xml")); 
    
    
    
     /******************************* feature.xml *******************************/    

     rs = executeQuery(conn,sql4);

     document3 = new Document(new Element("list"));
     rsmd3 = rs.getMetaData();
     num3 = rsmd3.getColumnCount(); 

     while(rs.next()){ 
       element3 = new Element("feature"); 
       document3.getRootElement().addContent(element3);
       for (int i=1; i<=num3; i++) { 
      	String date = new String(rs.getString(i)); 
          Element element3_3 = new Element(rsmd3.getColumnName(i)).setText(date);
          element3.addContent(element3_3);
          }
      }

      outp3 = new XMLOutputter();
      outp3.output(document3, new FileOutputStream(path+"feature.xml"));    
    

      
      /******************************* version.xml *******************************/    

      rs = executeQuery(conn,sql5);

      document4 = new Document(new Element("list"));
      rsmd4 = rs.getMetaData();
      num4 = rsmd4.getColumnCount(); 

      while(rs.next()){ 
        element4 = new Element("Software"); 
        document4.getRootElement().addContent(element4);
        for (int i=1; i<=num4; i++) { 
       	String date = new String(rs.getString(i)); 
           Element element4_4 = new Element(rsmd4.getColumnName(i)).setText(date);
           element4.addContent(element4_4);
           }
       }

       outp4 = new XMLOutputter();
       outp4.output(document4, new FileOutputStream(path+"version.xml"));     
      
      
      
       /******************************* men.xml *******************************/    

       rs = executeQuery(conn,sql6);

       document5 = new Document(new Element("list"));
       rsmd5 = rs.getMetaData();
       num5 = rsmd5.getColumnCount(); 

       while(rs.next()){ 
         element5 = new Element("Man"); 
         document5.getRootElement().addContent(element5);
         for (int i=1; i<=num5; i++) { 
        	String date = new String(rs.getString(i)); 
            Element element5_5 = new Element(rsmd5.getColumnName(i)).setText(date);
            element5.addContent(element5_5);
            }
        }

        outp5 = new XMLOutputter();
        outp5.output(document5, new FileOutputStream(path+"men.xml"));  
        
        
        
        /******************************* women.xml *******************************/    

        rs = executeQuery(conn,sql7);

        document6 = new Document(new Element("list"));
        rsmd6 = rs.getMetaData();
        num6 = rsmd6.getColumnCount(); 

        while(rs.next()){ 
          element6 = new Element("Woman"); 
          document6.getRootElement().addContent(element6);
          for (int i=1; i<=num6; i++) { 
         	String date = new String(rs.getString(i)); 
             Element element6_6 = new Element(rsmd6.getColumnName(i)).setText(date);
             element6.addContent(element6_6);
             }
         }

         outp6 = new XMLOutputter();
         outp6.output(document6, new FileOutputStream(path+"women.xml"));
         
         
         
         
         /******************************* team.xml *******************************/    

         rs = executeQuery(conn,sql8);

         document7 = new Document(new Element("list"));
         rsmd7 = rs.getMetaData();
         num7 = rsmd7.getColumnCount(); 

         while(rs.next()){ 
           element7 = new Element("Team"); 
           document7.getRootElement().addContent(element7);
           for (int i=1; i<=num7; i++) { 
          	String date = new String(rs.getString(i)); 
              Element element7_7 = new Element(rsmd7.getColumnName(i)).setText(date);
              element7.addContent(element7_7);
              }
          }

          outp7 = new XMLOutputter();
          outp7.output(document7, new FileOutputStream(path+"team.xml"));
       
    rs.close();
    conn.close();
%>

</body>
</html> 