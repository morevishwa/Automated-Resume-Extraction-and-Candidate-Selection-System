package Login;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import Search.JobSeeker;

/**
 * Servlet implementation class Registration
 */
public class ForgotJobSeekerPassword extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ForgotJobSeekerPassword() 
    {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request , response);
	}
	
	private void processRequest(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException
	{
		Connection con=null;
		try { 			
				
				String user=request.getParameter("Email_id");
			
				try
				{
					if(user !=null && user.length()>0)
					{
						ConnectionManager connectionManager=new ConnectionManager();	
						con=connectionManager.getConnection();
						Statement stmt1=con.createStatement();
					
						String query1="select * from jobseeker_registration where email_id='"+user+"'";
						ResultSet recordSet=stmt1.executeQuery(query1);
						String pass=null;
						if(recordSet !=null)
						{
							if(recordSet.next())
							{
								pass=recordSet.getString("Password");
								
								EmailPasswordClient.SendingEmail(user, pass);
							
								response.sendRedirect("setJobforgotpassword.jsp");
							}
						}
						else
						{
							response.sendRedirect("SetJobErrorforgotpassword.jsp");
						}
					}
					else
					{
						response.sendRedirect("forgotjobseekerpassword.jsp");
					}
				}
				catch (SQLException ex) 
				{ 
					ex.printStackTrace(); 
				}				
			}
			
		catch (Throwable theException) 
		{ 
			System.out.println(theException); 
			theException.printStackTrace();
		}
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		processRequest(request , response);
	}
}
