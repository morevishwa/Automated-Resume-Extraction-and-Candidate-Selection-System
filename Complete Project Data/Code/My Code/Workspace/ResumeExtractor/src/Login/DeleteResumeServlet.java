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
public class DeleteResumeServlet extends HttpServlet 
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
			HttpSession session = request.getSession(true);
			int id = (Integer) session.getAttribute("currentResumeId");
			String resumrName = (String)session.getAttribute("currentResumrName");
			String name = (String) session.getAttribute("currentJobSeekarSessionUser");
			int jobSeeker_id = (Integer) session.getAttribute("currentJobSeekarId");
			
			if (id > 0) 
			{ 
				JobSeekerDAO.deleteResume(id);
			}
			
			response.sendRedirect("jobSeeker.jsp");
			
			session.setAttribute("currentJobSeekarSessionUser",name);
			session.setAttribute("currentJobSeekarId",jobSeeker_id);
			
			session.setAttribute("currentResumeId",0);
			session.setAttribute("currentLoc",null);
			session.setAttribute("currentResumrName",null);
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
