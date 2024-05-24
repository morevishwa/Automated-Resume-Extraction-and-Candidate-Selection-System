package Login;

import java.text.*; 
import java.util.*;

import Bean.UserBean;

import java.sql.*; 
public class UserDAO 
{ 
	static Connection currentCon = null; 
	static ResultSet rs = null;
	static ResultSet rs1 = null;
	public static UserBean login(UserBean bean)
	{ 
		
		//preparing some objects for connection 
		Statement stmt = null; 
		Statement stmt2 = null; 
		String username = bean.getUsername(); 
		String password = bean.getPassword(); 
		String searchQuery = "select * from recruter where Recruter_Name='" + username + "' AND Password='" + password + "'"; 
		// "System.out.println" prints in the console; Normally used to trace the process 
		//System.out.println("Your user name is " + username); 
		//System.out.println("Your password is " + password); 
		//System.out.println("Query: "+searchQuery); 
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
				String searchname = "select name from registration where email_id='" + username + "'"; 
				rs1 = stmt2.executeQuery(searchname); 
				if(rs1.next())
				{
					String name=rs1.getString("name");
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

	
}

