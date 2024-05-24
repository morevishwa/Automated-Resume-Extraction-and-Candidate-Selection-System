<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Resume Extractor</title>
  <meta name="description" content=""></meta>
      <meta name="keywords" content=""></meta>
  <meta http-equiv="Content-Type"
        content="text/html; charset=iso-8859-1"></meta>
      <link href="css/style.css" rel="stylesheet" type="text/css"></link>
      <script language="JavaScript" type="text/JavaScript" src="script/validate.js"></script>
      <SCRIPT type="text/javascript">
window.history.forward();
function noBack()
{
  window.history.forward();
}
</SCRIPT>
</head>
<%
session.setAttribute("currentSessionUser",null);
session.invalidate();
//response.addHeader("Refresh","60"); 
%>
<body onload="noBack();"  onpageshow="if (event.persisted) noBack();" onunload="">
	<div class="main-out">
		<div class="main">
			<div class="page">
				<div class="top">
					<div class="header">
						<div class="header-top">
							<h1>Resume <span>Extractor</span></h1>
						</div>
						<div class="header-img">
						</div>
					</div>
					<div class="content">
						<div class="content-left">	
							<div class="img">
							<img src="images/img1.jpg" alt="" height="101"width="157">
						</div>
						<div class="welcome">
							<h1 class="title">Welcome to <span>Resume Extractor</span></h1>
								<p>
									We propose to build an Automated Resume Extraction and Classifier Service which will be built on Google's Cloud.
									Users can bulk upload resumes into the system.The system will automatically extract the resume content and store it  in a structure form within the Data Store.
									Classification algorithms will be run on the profiles to identify profile categories or classes.
									Users can upload specific requirements and identify probable profile matches</p>
						</div>
					</div>
					<div class="content-right"><br></br>
						<h3>
							<center>Please enter current username and password</center>
						</h3>
						<br></br>
						<form action="loginJobSeekerServlet" method ="post" name="login"  onsubmit="return loginvalidation()">
							<table>
								<tr>
									<td>EmailId</td>
									<td><input type = "text" name="username"></td>
								</tr>
								<tr>
									<td>password</td>
									<td><input type = "password" name ="password"></input></td>
								</tr>
								<tr>
									<td><td><input type="submit" name="Submit" value="Login"></input></td></td>
								</tr>
								<tr>
									<td><a href="JobSeekerRegistration.jsp">Sign Up</a></td>
									<td><a href="forgotjobseekerpassword.jsp">Forgot Password</a></td>
								</tr>							
							</table>
						<br>
						</form>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						<p>&nbsp;</p>
						
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
