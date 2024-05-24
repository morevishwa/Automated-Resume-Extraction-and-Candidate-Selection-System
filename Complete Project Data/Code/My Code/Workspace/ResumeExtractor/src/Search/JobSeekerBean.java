package Search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.Statement;

import Login.ConnectionManager;

public class JobSeekerBean 
{
	private int per;
	private String name;
	private String path;
	private String email; 
	
	public String getName() 
	{ 
		//System.out.println("getName invoked!");
		return this.name; 
	} 
	
	public void setName(String name) 
	{ 
		this.name = name; 
		//System.out.println("setName invoked!");
	} 
	
	public int getPer() 
	{ 
		//System.out.println("getPer invoked!");
		return this.per;		
	} 
	
	public void setPer(int per) 
	{ 
		this.per = per;
		//System.out.println("setPer invoked!");
	} 
			
	public String getPath() 
	{ 
		
		//System.out.println("getPath invoked!");
		return this.path; 
	} 
	
	public void setPath(String path) 
	{ 
		this.path = path; 
		//System.out.println("setPath invoked!");
	} 
	
	public String getEmail() 
	{ 
		
		//System.out.println("getEmail invoked!");
		return this.email; 
	} 
	
	public void setEmail(String email) 
	{ 
		this.email = email; 
		//System.out.println("setEmail invoked!");
	} 
		                      
	public JobSeeker[] searchResult()
	{
		Connection con=null;
		Search.JobSeeker[] result=new JobSeeker[100];
		int cnt=0;
		
		try
		{
			ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			Statement stat=con.createStatement();
			String query="SELECT * FROM matching_percent ORDER BY matching_percent DESC";
			ResultSet rs=stat.executeQuery(query);						
			while(rs.next())
			{
				int id=rs.getInt(2);
				int per=rs.getInt(3);
				Statement stat1=con.createStatement();
				String query1="select * from jobseeker where JobSeeker_id="+id;
				ResultSet rs1=stat1.executeQuery(query1);
				while(rs1.next())
				{	
					String name1=rs1.getString("Resume_Name");
					String path1=rs1.getString("Resume_Location");	
					String email1=rs1.getString("Email_ID");
					result[cnt]=new JobSeeker(name1,per,path1,email1);					
					cnt++;
				}							
			}	
			
			/*for(int l=0;l<cnt;l++)
			{
				System.out.println("name:"+result[l].name);
				System.out.println("per:"+result[l].per);
				System.out.println("path:"+result[l].path);
				System.out.println("path:"+result[l].email);
				
			}*/
			
			con.close();
		}
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
		}
		return (result);
	}
	
	
	public int getCount()
	{
		Connection con;
		int cnt=0;
		try
		{
			ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			Statement stat=con.createStatement();
			String query="SELECT * FROM matching_percent";
			ResultSet rs=stat.executeQuery(query);	
			while(rs.next())
				cnt++;			
			con.close();
		}
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
		}
		return cnt;
	}
	
	public int getResumeCount()
	{
		Connection con;
		int cnt=0;
		try
		{
			ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			Statement stat=con.createStatement();
			String query="SELECT * FROM jobseeker";
			ResultSet rs=stat.executeQuery(query);	
			while(rs.next())
				cnt++;			
			con.close();
		}
		catch (Exception ex) 
		{ 
			ex.printStackTrace(); 
		}
		return cnt;
	}
}
