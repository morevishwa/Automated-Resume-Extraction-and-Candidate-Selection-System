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
public class ChangePassword extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public ChangePassword() 
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
				
				String oldPassword=request.getParameter("OldPassword");
				String newPassword=request.getParameter("NewPassword");
				String confirmPassword=request.getParameter("confirmPassword");
				String user=request.getParameter("user");
			
				try
				{
					if(user!=null && user.length()>0 && oldPassword!=null && oldPassword.length()>0 && confirmPassword!=null && confirmPassword.length()>0 && newPassword!=null && newPassword.length()>0) {
					ConnectionManager connectionManager=new ConnectionManager();	
					con=connectionManager.getConnection();
					Statement stmt1=con.createStatement();
					
					String query1="select * from recruter where Recruter_name='"+user+"'";
					ResultSet recordSet=stmt1.executeQuery(query1);
					String pass=null;
					int id;
					if(recordSet.next())
					{
						id=recordSet.getInt("Recruter_id");
						pass=recordSet.getString("Password");
						
						if(pass.equals(oldPassword))
						{
							Statement stat3=con.createStatement();
	                    	String query4="Update recruter set Password='"+newPassword+"' where Recruter_id="+id;
	                    	int recruterResult=stat3.executeUpdate(query4);
	            		
	            			Statement stat=con.createStatement();
	                    	String query="Update registration set password='"+newPassword+"', confirmpassword='"+newPassword+"' where registration_id="+id;
	                    	int registrationResult=stat.executeUpdate(query);
	                    	
	                    	con.close();
	                    	if(registrationResult>0 && recruterResult>0)
	    					{
	    						response.sendRedirect("Setpassword.jsp");
	    					}	
						}
						else
						{
							response.sendRedirect("SetErrorpassword.jsp");
						}
					}
				}else {
					response.sendRedirect("ChangePassword.jsp");
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
