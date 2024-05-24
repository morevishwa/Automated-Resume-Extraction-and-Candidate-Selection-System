<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">
<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Job Seeker</title>
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta http-equiv="Content-Type" content="text/html; charset=iso-8859-1">
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
String user=(String)session.getAttribute("currentJobSeekarSessionUser");
int jobSeeker_id = (Integer)session.getAttribute("currentJobSeekarId");
int id = (Integer)session.getAttribute("currentResumeId");
String resumrName=(String)session.getAttribute("currentResumrName");
String loc=(String)session.getAttribute("currentLoc");


session.setAttribute("currentJobSeekarId",jobSeeker_id);
session.setAttribute("currentJobSeekarSessionUser", user);
session.setAttribute("currentResumeId",id);
session.setAttribute("currentResumrName",resumrName);
session.setAttribute("currentLoc",loc);
//response.addHeader("Refresh","2");
System.out.println("Jobseeker:"+user);
	if(user==null)
	{
		response.sendRedirect("candidates.jsp");
	}
%>
</div>
<h3 >Welcome <%=user %></h3>
<h3 align="right"><a href="ChangeJobSeekerPassword.jsp">Change Password &nbsp;<a href="logoutjob.jsp">Logout</a></h3>
<div class="header-img">
<h2>For The Job Seekers</h2>
</div>
</div>
<div class="content">
<div class="content-left">

<!-- <div class="img"><a href="candidates.jsp">Go to home page</a></div> -->

<div class="welcome">

<%
if (id > 0)
{
%>
	<TABLE WIDTH=0 BORDER=1 CELLPADDING=3 CELLSPACING=0 align="left">
		<tr>
			<td><h3>Name Of Resume</h3></td>
			<td><center><h3>Path</h3></center></td>
			<td><center><h3>Action</h3></center></td>
		</tr>
		<tr>
			<td><%=resumrName %></td>
			<% 
				String filenameExtension = loc.substring(loc.lastIndexOf("\\")+1);
	        %>
			<td><a href="viewresume.jsp?RI=<%=loc%>"><%=filenameExtension%></a></td>
			<td>
				<form name="Delete" action="DeleteResumeServlet" method="post">
					<input type="submit" value="Delete" />
				</form>
			</td>
		</tr>	
	</TABLE>
<%
}
else
{
%>
	<br></br><br></br><h2>Upload The Resume:</h2><br>
	<h3>Select a file to upload:</h3>
	<form name="upload" action="UploadServlet" method="post" enctype="multipart/form-data" onsubmit="return validateForm()">
		<input type="file" name="file" size="50" /></br>
		<br>
			Your Resume Name must be in "firstname_lastname" format<br>
			for example, Abhishek_kumar.docx
		<br/>
		<br><input type="submit" value="Upload File" />
	</form>
<%
}
%>
</center>
    <br></br>
    <br></br>
    <br></br>
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
