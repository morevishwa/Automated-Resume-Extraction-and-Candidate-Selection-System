package Upload;

import java.io.*;
import java.sql.*;

import Login.ConnectionManager;

public class searchfile 
{
	static Connection con; 
	//static String path="D:\\Study\\project\\ResumeExtractor\\ResumeExtractor\\Resume\\";
	
    public static void searchKeyword(String filename,String filenmwithExtension,String path) 
    {
        try 
        {
        	ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			Statement stat=con.createStatement();
        	String query="select * from keywords";
        	ResultSet rs=stat.executeQuery(query);
        	while(rs.next())
        	{
        		BufferedReader bf = new BufferedReader(new FileReader(filename));        				
        		int linecount = 0;
        		String line;
        		String description=new String();        				
        		while (( line = bf.readLine()) != null)
        		{// Increment the count and find the index of the word
        			linecount++;    	                    
    	            String keyword_desc=rs.getString("keyword_desc");
    	            keyword_desc=keyword_desc.toLowerCase();
    	            line=line.toLowerCase();
    	            int indexfound = line.indexOf(keyword_desc);
    	            //boolean flag=keyword_desc.equalsIgnoreCase("keyword_desc");    	                    
    	            boolean flag=line.contains(keyword_desc);
    	                  
    	            // If greater than -1, means we found the word
    	            if (flag==true ) 
    	            {
    	              	 if(keyword_desc.equals("Experience"))
    	    	         {
    	              		 String part1=line.substring(indexfound);
    	    	             String part2=line.substring(0);
    	    	             int num1=0,num2=0;
    		                 String s1=null;
    		                 String s2=null;
    	    	             char[] c = part1.toCharArray(); 
    	    	             for (int n=0 ; n< part1.length() ; n++)
    	    	             {
    	    	            	 if (Character.isDigit(c[n]))
    	    	                 {
    	    	            		 char c1 = part1.charAt(n);
    	    	                    //System.out.println("Character value is " + c);
    	    	                    s1 = Character.toString(c1);
    	    	                    num1=Integer.parseInt(s1);
    	    	                     //System.out.println("num1:"+num1);
    	    	                 }    	                    	 
    	    	             }
    	    	                    	
    	    	             char[] c2 = part2.toCharArray(); 
    	    	             for (int n=0 ; n< part2.length() ; n++)
    	    	             {   	                    	  	
    	    	             	if (Character.isDigit(c2[n]))
    	    	             	{
    	    	             		//String s=new String(c[n]);
    	    	             		char c3 = part2.charAt(n);
    	    	                    //System.out.println("Character value is " + c);
    	    	                    s2 = Character.toString(c3);
    	    	                    num2=Integer.parseInt(s2);
    	    	                    //System.out.println("num2:"+num2);
    	    	                  }    	                    	 
    	    	               	}
    	    	                    	
    	    	                if(num2!=0)
    	    	                {
    	    	                	description=s2;
    	    	                }
    	    	                else
    	    	                {
    	    	                	description=s1;    	                    		
    	    	                }
    	    	              }
    	                    	
    	                    	//System.out.println("Line:"+line);
    	                	    Statement stat1=con.createStatement();
    	            			//step-4: Execute the Query using the Statement Object
    	            			String query2="select JobSeeker_id from jobseeker where Resume_Name='"+filenmwithExtension+"'";
    	            			ResultSet rs1=stat1.executeQuery(query2);
    	            			//step-5:Process the Result
    	            			rs1.next();
    	            			//System.out.print(" "+rs.getInt(1));
    	                    	int JobSeeker_id=rs1.getInt(1);
    	                    	int Keyword_id=rs.getInt(1);
    	                    	
    	                    	Statement stmt=con.createStatement();
    	                    	String query3="select * from resume where JobSeeker_id="+JobSeeker_id+" And Keyword_id="+Keyword_id;
    	                    	ResultSet rs3=stmt.executeQuery(query3);
    	                    	if(rs3.next())
    	                    	{
    	                    		int appearence=rs3.getInt(5);
    	            				appearence=appearence+1;
    	            				Statement stat3=con.createStatement();
        	                    	String query4="Update resume set Appearence="+appearence+" where JobSeeker_id="+JobSeeker_id+" And Keyword_id="+Keyword_id;
        	            			stat3.executeUpdate(query4);
        	            		/*	if (rcnt>0)
        	            			{
        	            				System.out.println("Record Added Successfully!!!");
        	            			}
        	            			else
        	            			{
        	            				System.out.println("Record Addition FAILED!!!");
        	            			}*/
    	                    	}    	                    	
    	                    	else
    	                    	{
    	                    	Statement stat2=con.createStatement();
    	                    	String query1="insert into resume(JobSeeker_id,Keyword_id,Description,Appearence) values('"+JobSeeker_id+"','"+Keyword_id+"','"+description+"',1)";
    	            			int rcnt=stat2.executeUpdate(query1);
    	            			if (rcnt>0)
    	            			{
    	            				System.out.println("Record Added Successfully!!!");
    	            			}
    	            			else
    	            			{
    	            				System.out.println("Record Addition FAILED!!!");
    	            			}
    	                    	}
    	            			    	                    
    	                    }
    	            }
    	           // Close the file after done searching
    	            bf.close();
    				    			 
    			}
    			//step-6:close the connection
        			
    			con.close();
    			
    			
    		} 
    		catch (SQLException ex) 
    		{ 
    			ex.printStackTrace(); 
    		}
            catch (IOException e) 
            {
            	System.out.println("IO Error Occurred: " + e.toString());
            }
    }
}
