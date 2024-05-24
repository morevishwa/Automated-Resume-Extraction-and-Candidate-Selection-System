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
public class JobSeeker_Registration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public JobSeeker_Registration() 
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
				//UserBean user = new UserBean();
				String name=request.getParameter("name");
				String address=request.getParameter("address");
				String contactNumber=request.getParameter("contactnumber");
				String emailId=request.getParameter("emailid");
				String password=request.getParameter("password");
				String confirmpassword=request.getParameter("confirmpassword");
			
				try
				{
					ConnectionManager connectionManager=new ConnectionManager();	
					con=connectionManager.getConnection();
					Statement stmt1=con.createStatement();
					
					String query1="select MAX(id) from jobseeker_registration";
					ResultSet recordSet=stmt1.executeQuery(query1);
					int id1=0;
					if(recordSet.next())
					{
						id1=recordSet.getInt(1);
					}
					id1=id1+1;
					System.out.println(id1);
					Statement stat=con.createStatement();
					String query="insert into jobseeker_registration(id,name,address,contact_number,email_id,password,confirmpassword)value("+id1+",'"+name+"','"+address+"',"+contactNumber+",'"+emailId+"','"+password+"','"+confirmpassword+"')";					
					int registrationResult=stat.executeUpdate(query);
						
					// need to check whether connection is alive or not.. 
					con.close();
					System.out.println(registrationResult);
					if(registrationResult>0)
					{
						response.sendRedirect("candidates.jsp");
					}	
					else
					{
						// redirect to the error page
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
