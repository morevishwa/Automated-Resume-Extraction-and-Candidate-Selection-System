<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Bean.UserBean,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Change Password</title>
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <link href="css/style.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" type="text/JavaScript" src="script/validate.js"></script>
  <SCRIPT type="text/javascript">
window.history.forward();
function noBack()
{
  window.history.forward();
}
</SCRIPT>
</head>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
<div class="main-out">
<div class="main">
<div class="page">
<div class="top">
<div class="header">
<div class="header-top">
<h1>Resume <span>Extractor</span></h1>

<%

System.out.println("Onrecruter");
String user=(String)session.getAttribute("currentSessionUser");
//response.addHeader("Refresh","2");
System.out.println("user:"+user);
	if(user==null)
	{
		response.sendRedirect("candidate.jsp");
	}
%>
</div>
<h3 >Welcome <%=user %></h3>
<h3 align="right"><a href="logout.jsp">Logout</a></h3>
<div class="header-img">
<h2>Find The Right Candidate</h2>
</div>
</div>
<div class="content">
<div class="content-left">
<a href="recruter.jsp">Go to home page</a>

<br></br>
<div class="welcome">
<form   style="border: 1" action="changeJobSeekerPassword" method = "post" onSubmit="return validatechange(this)">
							<TABLE WIDTH=549 BORDER=0 CELLPADDING=3 CELLSPACING=3 align="center">
							<tr>
								<td>User Name</td>
								<td><input type="text" name="user"></td>
							</tr>
							<tr>
								<td>Old Password</td>
								<td><input type="password" name="OldPassword"></td>
							</tr>
							<tr>
								<td>NewPassword</td>
								<td><input type="password" name="NewPassword"></td>
							</tr>
							<tr>
								<td>confirmPassword</td>
								<td><input type="password" name="confirmPassword"></td>
							</tr>
							<td><input type="submit" name="Submit" value="Save"></input></td>
							</tr>							
							</TABLE>
							</form>
</div>
</div>


</div>
</div>
<div class="bottom">
<p>Copyright &copy; 2018. Designed by Resume Extactors Group</p>

</div>
</div>
</div>
</div>


</body>
</html>
