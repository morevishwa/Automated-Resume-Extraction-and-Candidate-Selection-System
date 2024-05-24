<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<%@ page import="Search.ShortListMassage" %>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Send Email</title>

</head>
<body>

<%	
	String email=request.getParameter("email");
   	String user=(String)session.getAttribute("currentSessionUser");
   	String msg = ShortListMassage.filename(email,user);
   	System.out.println("end of the method");
 %>
 <%=msg %>
 <script type="text/javascript">
 alert(msg);
 </script>
 </form>
</body>
</html>