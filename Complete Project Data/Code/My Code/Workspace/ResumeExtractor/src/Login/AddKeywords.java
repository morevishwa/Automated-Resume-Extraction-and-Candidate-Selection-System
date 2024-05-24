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
public class AddKeywords extends HttpServlet 
{
	private static final long serialVersionUID = 1L;
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public AddKeywords() 
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
				String keyword_type= request.getParameter("keyword_type");
				String keyword_desc=request.getParameter("keyword_desc");
				try
				{
					if(keyword_desc!=null && keyword_desc.length()>0)
					{
						ConnectionManager connectionManager=new ConnectionManager();	
						con=connectionManager.getConnection();
						Statement stmt1=con.createStatement();
					
						String query1="select MAX(Keyword_id) from keywords";
						ResultSet recordSet=stmt1.executeQuery(query1);
						int id1=0;
						if(recordSet.next())
						{
							id1=recordSet.getInt(1);
						}
						id1=id1+1;
						System.out.println(id1);
						Statement stat=con.createStatement();
						String query="insert into keywords(Keyword_id,Keyword_type,Keyword_desc)value("+id1+",'"+keyword_type+"','"+keyword_desc+"')";					
						int registrationResult=stat.executeUpdate(query);
							
						// need to check whether connection is alive or not.. 
						con.close();
						if(registrationResult>0)
						{
							response.sendRedirect("Admin.jsp");
						}		
					}
					else
					{
						response.sendRedirect("Addkeywords.jsp");	// redirect to the error page
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

