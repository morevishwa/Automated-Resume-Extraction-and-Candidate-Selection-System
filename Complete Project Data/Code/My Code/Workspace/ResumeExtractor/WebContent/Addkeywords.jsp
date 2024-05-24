<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Bean.UserBean,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Keywords</title>
  <meta name="description" content="">
  <meta name="keywords" content="">
  <meta http-equiv="Content-Type"
 content="text/html; charset=iso-8859-1">
  <link href="css/style.css" rel="stylesheet" type="text/css">
  <script language="JavaScript" type="text/JavaScript" src="script/validate.js"></script>
  <!-- Load jQuery from Google's CDN -->
   <!-- Load jQuery UI CSS  -->
    <link rel="stylesheet" href="http://code.jquery.com/ui/1.10.3/themes/smoothness/jquery-ui.css" />
    
    <!-- Load jQuery JS -->
    <script src="http://code.jquery.com/jquery-1.9.1.js"></script>
    <!-- Load jQuery UI Main JS  -->
    <script src="http://code.jquery.com/ui/1.10.3/jquery-ui.js"></script>
    
    <!-- Load SCRIPT.JS which will create datepicker for input field  -->
    <script src="script/script.js"></script>
    
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
</div>
<h3 >Welcome </h3>
<div class="header-img">
<h2>Find The Right Candidate</h2>
</div>
</div>
<div class="content">
<div class="content-left">


<br></br>
<div class="img"><a href="Admin.jsp">Go to home page</a></div>
<div class="welcome">
<form   style="border: 1" action="AddKeywords" method = "post" onSubmit="return validateKeyword(this)">
							<TABLE WIDTH=549 BORDER=0 CELLPADDING=3 CELLSPACING=3 align="center">
							<tr>
								<td>keyword Type</td>
								<td>
									<select name="keyword_type">
  										<option value="Designation" selected="selected">Designation</option>
  										<option value="Skill">Skill</option>
  										<option value="Location">Location</option>
  									</select> 
								</td>
							</tr>
							<tr>
								<td>Keyword Desc.</td>
								<td><input type="text" name="keyword_desc"></td>
							</tr>
							<td></td>
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
