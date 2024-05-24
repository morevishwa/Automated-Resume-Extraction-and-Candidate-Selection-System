package Login;

import java.io.IOException;
import javax.servlet.ServletConfig;
import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import Bean.JobSeekerBean;
import Bean.JobSeekerResumeBean;

/**
 * Servlet implementation class loginServlet
 */
public class loginJobSeekerServlet extends HttpServlet 
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
			JobSeekerBean user = new JobSeekerBean();
			
			String username=request.getParameter("username");
			user.setUsername(username); 
			user.setPassword(request.getParameter("password")); 
			user = JobSeekerDAO.login(user); 
			System.out.println("user:"+user.getUsername());
			if (user.isValid()) 
			{ 
				System.out.println("valid: "+user.isValid());
				
				HttpSession session = request.getSession(true);
				String name=user.getName();
				session.setAttribute("currentJobSeekarSessionUser",name);
				int jobSeeker_id=user.getId();
				session.setAttribute("currentJobSeekarId",jobSeeker_id);
				
				JobSeekerResumeBean bean = JobSeekerDAO.getResume(user);
				if (bean != null)
				{
					int id = bean.getId();
					String resumrName = bean.getResume_name();
					String loc = bean.getResume_Location();	
					
					session.setAttribute("currentResumeId",id);
					session.setAttribute("currentResumrName",resumrName);
					session.setAttribute("currentLoc",loc);
				}
				else
				{
					session.setAttribute("currentResumeId",0);
					session.setAttribute("currentLoc",null);
					session.setAttribute("currentResumrName",null);
				}
				response.sendRedirect("jobSeeker.jsp"); //logged-in page
							
			}
			else
			{
				response.sendRedirect("invalidcandidates.jsp"); //error page*/
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
