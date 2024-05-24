package Login;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.UserBean;

/**
 * Servlet implementation class loginServlet
 */
public class loginServlet extends HttpServlet 
{
	private static final long serialVersionUID = 1L;


	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException 
	{
		
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
		try 
		{ 
			UserBean user = new UserBean();
			
			String username=request.getParameter("username");
			user.setUserName(username); 
			user.setPassword(request.getParameter("password")); 
			user = UserDAO.login(user); 
			System.out.println("user:"+user.getUsername());
			if (user.isValid()) 
			{ 
				System.out.println("valid: "+user.isValid());
				
				HttpSession session = request.getSession(true);
				String name=user.getName();
				session.setAttribute("currentSessionUser",name);
				response.sendRedirect("recruter.jsp"); //logged-in page
			}
			else
			{
				response.sendRedirect("invalidRecruter.jsp"); //error page*/
			}
		} 
		catch (Throwable exception) 
		{ 
		    System.out.println(exception); 
		    exception.printStackTrace();
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
