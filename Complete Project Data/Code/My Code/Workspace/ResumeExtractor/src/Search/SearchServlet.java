package Search;

import java.io.DataInputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.ResultSetMetaData;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Set;

import javax.servlet.RequestDispatcher;
import javax.servlet.ServletConfig;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;
import javax.activation.MimetypesFileTypeMap;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.servlet.ServletOutputStream;
import javax.servlet.annotation.MultipartConfig;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.Part;

import Login.ConnectionManager;


@WebServlet(name = "SearchServlet",urlPatterns = {"/SearchServlet/*"})
@MultipartConfig

/**
 * Servlet implementation class SearchServlet
 */
public class SearchServlet extends HttpServlet {
	static Connection con=null;
	private static final long serialVersionUID = 1L;
	 private static final int BUFSIZE = 4096;
	   
       
    /**
     * @see HttpServlet#HttpServlet()
     */
    public SearchServlet() {
        super();
        // TODO Auto-generated constructor stub
    }

	/**
	 * @see Servlet#init(ServletConfig)
	 */
	public void init(ServletConfig config) throws ServletException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doGet(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		// TODO Auto-generated method stub
	}

	/**
	 * @see HttpServlet#doPost(HttpServletRequest request, HttpServletResponse response)
	 */
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO Auto-generated method stub
		
		 
		Map dbskill=new HashMap();
		String skilld[]=new String[20];
        String rank[]=new String[20];
        String keywords_input[]=new String[20];
        int cnt=0;
        int temp=0;
        String path = "";
       
		String[] Designation  = request.getParameterValues("Designation");
		String[] Skills  = request.getParameterValues("Skills");
		String[] Keywords  = request.getParameterValues("Keywords");
		String[] Location  = request.getParameterValues("Location");
		String Experience  =request.getParameter("Experience");
		
		System.out.println("Experience: "+Experience);
		
		int cnt1=0,cnt2=0,cnt3=0,cnt4=0,cnt5=0;
		int key=0;
		
		if(Designation!=null )
		{
		for(int i=0;i<Designation.length;i++)
		{
			keywords_input[key]=Designation[i];
			key++;
			//.add(Designation[i]);			
			 System.out.println("Designation:"+Designation[i]);
		}
		cnt1=Designation.length;
		}
		System.out.println("cnt1:"+cnt1);
		if(Skills!=null )
		{
		for(int i=0;i<Skills.length;i++)
		{
			keywords_input[key]=Skills[i];
			key++;			
			System.out.println("Skills:"+Skills[i]);
		}
		cnt2=Skills.length;
		}	 
		if(Keywords!=null )
		{
		for(int i=0;i<Keywords.length;i++)
		{
			keywords_input[key]=Keywords[i];
			key++;
			cnt3=i;
			 System.out.println("Keywords:"+Keywords[i]);
		}
		cnt3=Keywords.length;
		}
		
		if(Location!=null )
		{
		for(int i=0;i<Location.length;i++)
		{
			keywords_input[key]=Location[i];
			key++;
			cnt4=i;
			System.out.println("Location:"+Location[i]);
		}
		cnt4=Location.length;
		}
		try {
			path=Permutations.PermutationsCollection(keywords_input,key);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		try 
		{ 
			int[] jobseeker_id=new int[50];
			int[] keyword_id=new int[50];
			
			ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			System.out.println("cnt1: "+cnt1+" cnt2: "+cnt2+" cnt3: "+cnt3+" cnt4: "+cnt4);
			if(cnt1!=0 && cnt2!=0 && cnt3!=0 && cnt4!=0 && Experience!=null )
			{
			
			Statement stat=con.createStatement();
			String query="select distinct JobSeeker_id  from resume";
			ResultSet rs=stat.executeQuery(query);			
			int i=0;
			while(rs.next())
			{
				jobseeker_id[i]=rs.getInt(1);
				System.out.println("jobseeker_id[i]:"+jobseeker_id[i]);
				i++;
			}
			
			
			int k=0;
			
				Statement stat1=con.createStatement();
				String query1="select * from keywords";
				ResultSet rs1=stat1.executeQuery(query1);
				while(rs1.next())
				{					
					String desc=rs1.getString(3);
					for(int j=0;j<keywords_input.length;j++)
					{
						if(desc.equalsIgnoreCase(keywords_input[j]))
						{
							keyword_id[k]=rs1.getInt(1);
							System.out.println("keyword_id: "+keyword_id[k]);
							k++;
						}
					}
				}
				keyword_id[k]=113;
				System.out.println("keyword_id: "+keyword_id[k]);
				k++;
				
				int total_keyword=0;
				Statement stat11=con.createStatement();
				String query11="SELECT COUNT(*) FROM keywords";
				ResultSet rs11=stat11.executeQuery(query11);
				while(rs11.next())
				{	
					total_keyword=rs11.getInt(1);
				}
				
				
				for(int j=0;j<i;j++)
				{
					int m=0;
					cnt=0;
					int[] keyword_id1=new int[200];
					int[] Appreance=new int[200];
					Statement stat2=con.createStatement();
					String query2="select * from resume where JobSeeker_id="+jobseeker_id[j];
					ResultSet rs2=stat2.executeQuery(query2);
				
					//int m=0;
					while(rs2.next())
					{
						keyword_id1[m]=rs2.getInt(3);
						Appreance[m]=rs2.getInt(5);
						System.out.println("keyword_id1: "+keyword_id1[m]);
						m++;
					}
				
					int p_input=m/total_keyword;
					int[] probability=new int[50];
					int p=0;
					for(int j1=0;j1<k;j1++)
					{
						for(int j2=0;j2<m;j2++)
						{
							if(keyword_id[j1]==keyword_id1[j2])
							{
								if(keyword_id1[j2]==113)
								{
									Statement stat4=con.createStatement();
									String query4="select * from resume where Keyword_id=113 and JobSeeker_id="+jobseeker_id[j];
									ResultSet rs4=stat4.executeQuery(query4);
									if(rs4.next())
									{
										//int expr=Integer.parseInt(Experience);
										if(Experience.equals(rs4.getString(4)))
										{
											probability[p]=Appreance[j2]/m;
											p++;
											cnt++;
											//System.out.println("Experince match found!!!");
										}
										
									}									
								}
								else
								{
									probability[p]=Appreance[j2]/m;
									p++;
									cnt++;
								}
							}
						}
					}
					//System.out.println("cnt: "+cnt);
					//System.out.println("k: "+k);
					int per=0;
					int prob=0;
					for(int p1=0;p1< probability.length;p1++)
					{
						System.out.println("probability["+p1+"]: "+probability[p1]);
						prob=prob*probability[p1];
					}
					per=(cnt*100)/k;
					System.out.println("per: "+per);
					
					if(per > 30)
					{
						Statement stat3=con.createStatement();
						String query3="insert into matching_percent(JobSeeker_id,matching_percent) values('"+jobseeker_id[j]+"','"+per+"')";
						int rcnt=stat3.executeUpdate(query3);
					
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
				
				HttpSession session = request.getSession(true); 
				session.setAttribute("Designation",Designation);
				session.setAttribute("Skills",Skills);
				session.setAttribute("Keywords",Keywords);
				session.setAttribute("Location",Location);
				session.setAttribute("Experience",Experience);

				String user=(String)session.getAttribute("currentSessionUser");
		        System.out.println("-----------------------user name="+user);
		        
		        Statement st=con.createStatement(); 
				String queryfor="select email_id from resume_extraction.registration where name='"+user+"'";
				System.out.println(queryfor);
				ResultSet result=st.executeQuery(queryfor);
				String emailID=null;
			
				if(result.next())
				{
					emailID=result.getString("email_id");
					System.out.println("email===="+emailID);					
				}
				
				if(!(path.equals("")))
		        {
		        	MailSender.attachment(path, emailID);
		        }
				
				
				response.sendRedirect("searchResult.jsp");
			}
			else
			{
				response.sendRedirect("recruter.jsp");
			}
			
			
		}
		catch (SQLException ex) 
		{ 
			ex.printStackTrace(); 
		}
	}
}
