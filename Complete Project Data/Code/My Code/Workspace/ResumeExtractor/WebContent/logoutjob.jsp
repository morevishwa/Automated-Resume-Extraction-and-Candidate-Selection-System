

<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
   "http://www.w3.org/TR/html4/loose.dtd">

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <%
        session.setAttribute("currentJobSeekarId",0);
        session.setAttribute("currentJobSeekarSessionUser", null);
        session.setAttribute("currentResumeId",0);
        session.setAttribute("currentResumrName",null);
        session.setAttribute("currentLoc",null);
        //response.addHeader("Refresh","2");
        System.out.println("Jobseeker:"+null);
session.invalidate();
response.sendRedirect("candidates.jsp");
%>
    </body>
</html>
