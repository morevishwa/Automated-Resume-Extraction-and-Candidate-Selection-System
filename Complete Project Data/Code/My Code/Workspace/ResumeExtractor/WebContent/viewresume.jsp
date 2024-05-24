<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page  import="java.io.FileInputStream" %>
<%@ page  import="java.io.FileOutputStream" %>
<%@ page  import="java.io.BufferedInputStream"  %>
<%@ page  import="java.io.File"  %>
<%@ page import="Search.ShortListMassage" %>
<%@ page import="java.io.IOException" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Insert title here</title>

</head>
<body>

<%
	BufferedInputStream buf=null;
	out.clear();
try{		
	String filepath=request.getParameter("RI");
	System.out.println("filepath:"+filepath);
   	String filename=filepath.substring( filepath.lastIndexOf("\\")+1);   
   	System.out.println("filename: "+filename);

   	String user=(String)session.getAttribute("currentSessionUser");
   	//ShortListMassage.filename(filename,user);
   	// myOut=null;
   	//FileOutputStream myout=null; 

   	File myfile = new File(filepath);
     
     //set response headers
     response.setContentType("APPLICATION/DOWNLOAD");
    
    // ServletOutputStream myOut =;
 	 
     response.setHeader("Content-Disposition","attachment; filename="+ filename  );

     response.setContentLength( (int) myfile.length( ) );
     
     FileInputStream input = new FileInputStream(myfile);
     
     buf = new BufferedInputStream(input);
     int readBytes = 0;
 
     //read from the file; write to the ServletOutputStream
     while((readBytes = buf.read( )) != -1)
     {
    	 response.getWriter().write(readBytes);
     }
    	 //System.out.println("buf: "+readBytes);
      // myOut.write(readBytes);
} catch (IOException ioe){
     
        throw new ServletException(ioe.getMessage( ));
         
     } finally {
         
     //close the input/output streams
         
          if (buf != null)
          buf.close( );
         
     }

 %>
 
 </form>
</body>
</html>