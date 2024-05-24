package Login;

import java.text.*; 
import java.util.*;

import Bean.JobSeekerBean;
import Bean.JobSeekerResumeBean;

import java.sql.*; 
public class JobSeekerDAO 
{ 
	static Connection currentCon = null; 
	
	public static JobSeekerBean login(JobSeekerBean bean)
	{ 
		ResultSet rs = null;
		ResultSet rs1 = null;
		//preparing some objects for connection 
		Statement stmt = null; 
		Statement stmt2 = null; 
		String username = bean.getUsername(); 
		String password = bean.getPassword(); 
		String searchQuery = "select * from jobseeker_registration where email_id='" + username + "' AND password='" + password + "'"; 

		try 
		{ 
			//connect to DB 
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement(); 
			stmt2=currentCon.createStatement(); 
			rs = stmt.executeQuery(searchQuery); 
			boolean result = rs.next();
			
			// if user does not exist set the isValid variable to false 
			if (!result)
			{ 				
				//System.out.println("Sorry, you are not a registered user! Please sign up first");
				bean.setValid(false); 
			} 
			
			//if user exists set the isValid variable to true else 
			if (result) 
			{ 			
				String searchname = "select * from jobseeker_registration where email_id='" + username + "'"; 
				rs1 = stmt2.executeQuery(searchname); 
				if(rs1.next())
				{
					String name=rs1.getString("name");
					bean.setEmail_id(rs1.getString("email_id"));
					bean.setAddress(rs1.getString("address"));
					bean.setContact_number(rs1.getString("contact_number"));
					bean.setId(rs1.getInt("id"));
					
					System.out.println(name);
					bean.setName(name);
				}
				bean.setValid(true);
				
			} 
		} 
		catch (Exception ex) 
		{ 
			System.out.println("Log In failed: An Exception has occurred! " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		
		//some exception handling 
		finally 
		{ 
			if (rs != null) 
			{ 
				try 
				{ 
					rs.close(); 
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				rs = null; 
			}
			if (stmt != null) 
			{ 
				try { stmt.close(); } 
				catch (Exception e) {} 
				stmt = null; 
			} 
			if (currentCon != null) 
			{ 
				try { currentCon.close(); } 
				catch (Exception e) { } 
				currentCon = null; 
			} 
		} 
		return bean; 
	}	

	public static JobSeekerResumeBean getResume(JobSeekerBean bean)
	{
		ResultSet rs = null;
		JobSeekerResumeBean rbean = null;
		//preparing some objects for connection 
		Statement stmt = null; 
		int id = bean.getId();
		String searchQuery = "select * from jobseeker where JobSeeker_id=" +id+""; 

		try 
		{ 
			//connect to DB 
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement(); 
			rs = stmt.executeQuery(searchQuery); 
			boolean result = rs.next();
			
			if (result) 
			{ 		
				rbean = new JobSeekerResumeBean();
				rbean.setId(rs.getInt("id"));
				rbean.setResume_name(rs.getString("Resume_Name"));
				rbean.setResume_Location(rs.getString("Resume_Location"));
				rbean.setEmail_ID(rs.getString("Email_ID"));
				rbean.setName(rs.getString("name"));				
			} 
		} 
		catch (Exception ex) 
		{ 
			System.out.println("Log In failed: An Exception has occurred! " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		
		//some exception handling 
		finally 
		{ 
			if (rs != null) 
			{ 
				try 
				{ 
					rs.close(); 
				} 
				catch (Exception e) 
				{
					e.printStackTrace();
				}
				rs = null; 
			}
			if (stmt != null) 
			{ 
				try { stmt.close(); } 
				catch (Exception e) {} 
				stmt = null; 
			} 
			if (currentCon != null) 
			{ 
				try { currentCon.close(); } 
				catch (Exception e) { } 
				currentCon = null; 
			} 
		} 
		return rbean; 

	}

	public static boolean deleteResume(int id)
	{
		
		//preparing some objects for connection 
		Statement stmt = null;
		Statement stmt1 = null;
		String searchQuery = "delete from resume where JobSeeker_id=" +id+""; 

		try 
		{ 
			//connect to DB 
			currentCon = ConnectionManager.getConnection();
			stmt=currentCon.createStatement(); 
			int rs = stmt.executeUpdate(searchQuery); 
						
			String searchQuery1 = "delete from jobseeker where JobSeeker_id =" +id+""; 
			stmt1=currentCon.createStatement(); 
			int rs1 = stmt1.executeUpdate(searchQuery1); 
			return true;
		} 
		catch (Exception ex) 
		{ 
			System.out.println("Delete fail: An Exception has occurred! " + ex.getLocalizedMessage());
			ex.printStackTrace();
		}
		
		//some exception handling 
		finally 
		{ 
			
			if (stmt != null) 
			{ 
				try { stmt.close(); } 
				catch (Exception e) {} 
				stmt = null; 
			} 
			if (currentCon != null) 
			{ 
				try { currentCon.close(); } 
				catch (Exception e) { } 
				currentCon = null; 
			} 
		} 
		return false;
	}

}

