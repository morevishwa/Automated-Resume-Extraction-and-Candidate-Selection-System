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
public class Registration extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public Registration() 
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
				//int contactNumber=Integer.parseInt(mobno); 
				String emailId=request.getParameter("emailid");
				String password=request.getParameter("password");
				String confirmpassword=request.getParameter("confirmpassword");
				String birthdate=request.getParameter("birthdate");
				String gender=request.getParameter("gender");
				String companyname=request.getParameter("companyname");
			
				try
				{
					ConnectionManager connectionManager=new ConnectionManager();	
					con=connectionManager.getConnection();
					Statement stmt1=con.createStatement();
					
					String query1="select MAX(Recruter_Id) from recruter";
					ResultSet recordSet=stmt1.executeQuery(query1);
					int id1=0;
					if(recordSet.next())
					{
						id1=recordSet.getInt(1);
					}
					id1=id1+1;
					System.out.println(id1);
					Statement stat=con.createStatement();
					String query="insert into registration(registration_id,name,address,contact_number,email_id,password,confirmpassword,Birthdate,gender,companyname)value("+id1+",'"+name+"','"+address+"',"+contactNumber+",'"+emailId+"','"+password+"','"+confirmpassword+"','"+birthdate+"','"+gender+"','"+companyname+"')";					
					int registrationResult=stat.executeUpdate(query);
						
					// execute validation need to be done.. after execute update 
					Statement stat2=con.createStatement();
					String query2="insert into recruter(Recruter_id,Recruter_name,Password)value("+id1+",'"+emailId+"','"+password+"')";					
					int recruterResult=stat2.executeUpdate(query2);
						
					// need to check whether connection is alive or not.. 
					con.close();
					if(registrationResult>0 && recruterResult>0)
					{
						response.sendRedirect("Admin.jsp");
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
