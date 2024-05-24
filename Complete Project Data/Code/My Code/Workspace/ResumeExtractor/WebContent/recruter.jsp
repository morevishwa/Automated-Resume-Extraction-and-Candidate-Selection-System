<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1" import="Bean.UserBean,java.sql.*" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
  <title>Recruter</title>
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
session.setAttribute("currentSessionUser", user);
//response.addHeader("Refresh","2");
System.out.println("user:"+user);
	if(user==null)
	{
		response.sendRedirect("index.jsp");
	}
%>
</div>
<h3 >Welcome <%=user %></h3>
<h3 align="right"><a href="ChangePassword.jsp">Change Password &nbsp;<a href="logout.jsp">Logout</a></h3>
<div class="header-img">
<h2>Find The Right Candidate</h2>
</div>
</div>
<div class="content">
<div class="content-left">


<br></br>
<div class="welcome">
<form   style="border: 1" action="SearchServlet" enctype="multipart/form-data" method = "post" onSubmit="return validate(this)">

<TABLE WIDTH=549 BORDER=0 CELLPADDING=3 CELLSPACING=3 align="center">
<%
try
{
	Connection con;
	String url = "jdbc:mysql://br-cdbr-azure-south-a.cloudapp.net:3306/cdb_f394cf698f"; 
	// assuming "DataSource" is your DataSource name 
	Class.forName("com.mysql.jdbc.Driver"); 
	try 
	{ 
		//con=DriverManager.getConnection(url, "baf29fba0317d2","78416f21");
		con = DriverManager.getConnection("jdbc:mysql://localhost:3306/resume_extraction","root","root");
	Statement stat=con.createStatement();
	String query="SELECT * FROM keywords where Keyword_type='Designation'";
	ResultSet rs=stat.executeQuery(query);
	%>
	<tr>
    			<td>Designation</td>
    			<td><select STYLE="width: 200px" name="Designation" multiple="multiple">
    				<option value="">- Select -</option>
    			<%
					while(rs.next())
					{
					String designation=rs.getString(3);
				%>


  					<option value="<%= designation%>"><%= designation%></option>
  					<%} %>
  					</select>
				</td>
  			</tr>
  			<%
	Statement stat1=con.createStatement();
	String query1="SELECT * FROM keywords where Keyword_type='Skill'";
	ResultSet rs1=stat1.executeQuery(query1); %>
  			<tr>
    			<td>Skills</td>
    			<td><select STYLE="width: 200px" name="Skills" multiple="multiple">
  					<option value='Skills'>- Select -</option>
  					<%
					while(rs1.next())
					{
					String skill=rs1.getString(3);
				%>		<option value="<%=skill%>"><%=skill%></option>
				<%} %>
					</select>
				</td>
  				</tr>
  				<tr>
    				<td>Experience</td>
    				<td><select STYLE="width: 200px" name="Experience">
  						<option value="">- Select -</option>
						<option value="Fresher">Fresher</option>
					 	<option value="1">1</option>
					 	<option value="2">2</option>
					 	<option value="3">3</option>
					 	<option value="4">4</option>
					 	<option value="5">5</option>
					 	<option value="6">6</option>
					 	<option value="7">7</option>
						<option value="8">8</option>
					 	<option value="9">9</option>
					 	<option value="10">10</option>
					 	<option value="11">11</option>
					 	<option value="12">12</option>
					 	<option value="13">13</option>
					 	<option value="14">14</option>
					 	<option value="15">15</option>
					 	<option value="16">16</option>
					 	<option value="17">17</option>
					 	<option value="18">18</option>
					 	<option value="19">19</option>
					 	<option value="20">20</option>
						</select>&nbsp;&nbsp;&nbsp;year</td>

  </tr>
    <%
	Statement stat2=con.createStatement();
	String query2="SELECT * FROM keywords where Keyword_type='percentage criteria'";
	ResultSet rs2=stat2.executeQuery(query2); %>
  <tr>
    <td>Percentage Criteria</td>
    <td><select STYLE="width: 200px" name="Keywords" multiple="multiple">
  	<option  value='Keywords'>- Select -</option>
  	<%
					while(rs2.next())
					{
					String Keywords=rs2.getString(3);
					if(!Keywords.equals("Experience")){
				%>
  	<option value="<%=Keywords%>"><%=Keywords %></option>
  	<%}}%>
		</select>
	</td>
  </tr>
   <%
	Statement stat3=con.createStatement();
	String query3="SELECT * FROM keywords where Keyword_type='Location'";
	ResultSet rs3=stat3.executeQuery(query3); %>
  <tr>
    <td>Location</td>
    <td><select STYLE="width: 200px" name="Location" multiple="multiple">
    <option  value='Keywords'>- Select -</option>
    <%
					while(rs3.next())
					{
					String Location=rs3.getString(3);
				%>
  			<option value="<%=Location %>"><%=Location %> </option>
  			<%} %>
		</select>
	</td>
  </tr>

  <tr>
    <td></td>
    <td>
    <input type="submit" name="Submit" value="Search">
	</td>
  </tr>
  
</TABLE>

<%
	}
	catch (SQLException ex)
	{
		ex.printStackTrace();
	}
	}
	catch(ClassNotFoundException e)
	{
		System.out.println(e);
	}
%>
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