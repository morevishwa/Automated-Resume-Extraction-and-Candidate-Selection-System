

<!DOCTYPE html PUBLIC "-//W3C//DTD XHTML 1.0 Transitional//EN" "http://www.w3.org/TR/xhtml1/DTD/xhtml1-transitional.dtd">

<%@ page  import="Search.JobSeeker" %>
<%@ page import="Search.ShortListMassage" %>
<%@ page  import="java.sql.*" %>
<jsp:useBean id="ob" class="Search.JobSeekerBean" />
<jsp:setProperty name="ob" property="name"/>
<jsp:setProperty name="ob" property="per"/>
<jsp:setProperty name="ob" property="path"/>


<html xmlns="http://www.w3.org/1999/xhtml">
<head>
  <title>Search Result</title>
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
<%
String user=(String)session.getAttribute("currentSessionUser");
	if(user==null)
	{
		response.sendRedirect("index.jsp");
	}
%>
<div class="main-out">
<div class="main">
<div class="page">
<div class="top">
<div class="header">
<div class="header-top">
<h1>Resume <span>Extractor</span></h1>

</div>
<h3>Welcome <%=user%></h3>
<h3 align="right"><a href="ChangePassword.jsp">Change Password &nbsp; </a><a href="logout.jsp">Logout</a></h3>
<div class="header-img">
<h2>Find The Right Candidate</h2>
</div>
</div>
<div class="content">
<div class="content-left">
<a href="recruter.jsp">Go to home page</a>

<br></br><br></br>
<TABLE WIDTH=0 BORDER=1 CELLPADDING=3 CELLSPACING=0 align="left">
	<tr>
		<td><h3>Name Of Resume</h3></td>
		<td><center><h3>Property Match Percentage</h3></center></td>
		<td><center><h3>Path</h3></center></td>
		<td><center><h3>Send Mail</h3></center></td>
	</tr>
	<%int i=0;

	Search.JobSeeker[] rs=ob.searchResult();
	int cnt=ob.getCount();
	int rCnt=ob.getResumeCount();
        System.out.println("matching_percent cnt:"+cnt);
	for(i=0;i<cnt;i++)
	  {
		//System.out.println("Name1: "+rs[i].name);
		//System.out.println("per1: "+rs[i].per);
		//System.out.println("path1: "+rs[i].path);
	  %>
		<tr>
			<td><%=rs[i].name %></td>
			<td><center><%=rs[i].per%>%</center></td>
			<% 
			session.setAttribute("currentSessionUser",user);	
			String resumePath=rs[i].path; 
			String filenameExtension=resumePath.substring( resumePath.lastIndexOf("\\")+1);
			System.out.println("resumePath = "+resumePath+"\n filenameExtension = "+filenameExtension);
			String btName = "Selected"+i;
			%>
			<td><a href="viewresume.jsp?RI=<%=rs[i].path%>"><%=filenameExtension%></a></td>
			 <td><a href="sendmail.jsp?email=<%=rs[i].name %>" target="_blank">Selected</a></td>	
			<!-- 
			<td>				
				<button id = "<%=rs[i].name %>" onclick="window.open('sendmail.jsp?email=<%=rs[i].name %>')">Try it</button>
			</td>
			 -->			
		</tr>
	<%
	} 	
	%>
	</TABLE>
	</br>
	</br></br>
	</br></br>
	</br><br><br><br>
		Total Matching Count <%= cnt%> Out of <%= rCnt%>
		</br></br></br>
</div>
<div class="content-right"><br></br>
<h3>Selected Search Criteria</h3>
<%String[] Designation=(String[])session.getAttribute("Designation");  %>
<ul>
  <li>Designation</li>
  <%for(int j=0;j<Designation.length;j++)
	  {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Designation[j]%><br>
  <%} %>
  <li>Skills</li>
   <%String[] Skills=(String[])session.getAttribute("Skills");
   for(int j=0;j<Skills.length;j++)
	  {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Skills[j]%><br>
  <%} %>
  <li>Experience</li>
  <%String Experience=(String)session.getAttribute("Experience");   %>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Experience%> years

  <li>Percentage Criteria</li>
  <%String[] Keywords=(String[])session.getAttribute("Keywords");
   for(int j=0;j<Keywords.length;j++)
	  {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Keywords[j]%><br>
  <%} %>
  <li>Location</li>
  <%String[] Location=(String[])session.getAttribute("Location");
   for(int j=0;j<Location.length;j++)
	  {%>
  &nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;&nbsp;<%=Location[j] %>
  <%} %>
</ul>
<%
try
{
	Connection con;
	String url = "jdbc:mysql://localhost:3306/resume_extraction";
	//Class.forName("com.mysql.jdbc.Driver");
	//String url = "jdbc:mysql://br-cdbr-azure-south-a.cloudapp.net:3306/cdb_f394cf698f"; 
	// assuming "DataSource" is your DataSource name 
	Class.forName("com.mysql.jdbc.Driver"); 
			try 
			{ 
				//con=DriverManager.getConnection(url, "baf29fba0317d2","78416f21");
			//con = DriverManager.getConnection("jdbc:mysql://instance15854.db.xeround.com:10654/reeume_extraction?" +
				//    "user=elexion&password=elexion");
			con=DriverManager.getConnection(url,"root","root");
			Statement stat=con.createStatement();
			String query="delete from matching_percent";
			int rcnt=stat.executeUpdate(query);
			if (rcnt==1)
			{
				System.out.println("Record deleted Successfully!!!");
			}
			else
			{
				System.out.println("Record deletion FAILED!!!");
			}
			
			con.close();
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
