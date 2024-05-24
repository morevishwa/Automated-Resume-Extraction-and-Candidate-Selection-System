package Search;

import java.io.IOException;
import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;
import java.util.Set;

import org.xmind.core.Core;
import org.xmind.core.CoreException;
import org.xmind.core.ISheet;
import org.xmind.core.ITopic;
import org.xmind.core.IWorkbook;
import org.xmind.core.IWorkbookBuilder;

import Login.ConnectionManager;

public class ConbinationListOFResume {

	static Connection con=null;
	
	public static String searchReume(ArrayList<ArrayList<String>> combinationStringSet,String keywords_input[]) throws Exception
	{
		int[] jobseeker_id=new int[50];
		int[] keyword_id=new int[50];
		
		HashMap<String,ArrayList<String>> diagramlist=new HashMap<String, ArrayList<String>>();
		HashMap<Integer, ArrayList<Integer>> maching = new HashMap<Integer, ArrayList<Integer>>();
		HashMap<Integer, String> resume = new HashMap<Integer, String>();
		HashMap<String, Integer>  keywords= new HashMap<String, Integer>();

		ConnectionManager connectionManager=new ConnectionManager();	
		con=connectionManager.getConnection();
						
		Statement stat=con.createStatement();
		String query="select distinct JobSeeker_id  from resume";
		ResultSet rs=stat.executeQuery(query);			
		int jobseekercont=0;
		while(rs.next())
		{
			jobseeker_id[jobseekercont]=rs.getInt(1);
			resume.put(rs.getInt(1), "no");
			jobseekercont++;
		}		

		int keywordcount=0;
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
					keywords.put(desc, rs1.getInt(1));
					System.out.println("string="+desc+" id="+rs1.getInt(1));
				}
			}
		}
			
	
		for(int j=0;j<jobseekercont;j++)
		{
			ArrayList<Integer> key=new ArrayList<Integer>();
			int m=0;
			int cnt=0;
			int[] keyword_id1=new int[200];
			int[] Appreance=new int[200];
			Statement stat2=con.createStatement();
			String query2="select * from resume where JobSeeker_id="+jobseeker_id[j];
			ResultSet rs2=stat2.executeQuery(query2);
					
			//int m=0;
			while(rs2.next())
			{
				key.add(rs2.getInt(3));
			}
			System.out.println("resume="+jobseeker_id[j]+" key="+key);
			maching.put(jobseeker_id[j], key);
		}
		
		for(int index1=0;index1<combinationStringSet.size();index1++)
		{
			ArrayList<String> keywordList=combinationStringSet.get(index1);			
			ArrayList<String> resumenms=new ArrayList<String>();
			int count=0;
			Set set=maching.entrySet();
			Iterator itr=set.iterator();
			
			while(itr.hasNext())
			{
				Map.Entry me=(Entry) itr.next();
				ArrayList<Integer> key=(ArrayList<Integer>) me.getValue();
				count=0;
				for(int index=0;index<key.size();index++)
				{
					int keyid=key.get(index);
					
					for(int i=0;i<keywordList.size();i++)
					{
						String keywordnm=keywordList.get(i);
						int no=keywords.get(keywordnm);
						if(keyid==no)
						{
							System.out.println("Id matching="+keyid+" "+no);
							count++;
						}
					}
				}
				
				if(count==keywordList.size())
				{
					int reno=(Integer)me.getKey();
					String id=resume.get(reno);
					if(id.equals("no"))
					{
						
						Statement stat2=con.createStatement();
						String query2="select * from Jobseeker where JobSeeker_id="+reno;
						ResultSet rs2=stat2.executeQuery(query2);
						
						if(rs2.next())
						{
							String resumenm=rs2.getString("name");
							System.out.println("Resume name="+resumenm+" added");
							resumenms.add(resumenm);
							resume.put(reno, "yes");
						}
					}
				}
			}
			
			if(resumenms.size()>0)
			{
				String name="";
				for(int i=0;i<keywordList.size();i++)
				{
					String keywordnm=keywordList.get(i);
					name=name+keywordnm+",";
				}
				System.out.println("combo="+name+" list"+resumenms);
				diagramlist.put(name, resumenms);				
			}
		}
		String path=diagram(diagramlist);
		return path;
	}

	private static String diagram(HashMap<String, ArrayList<String>> diagramlist) throws IOException, CoreException 
	{
	
		SimpleDateFormat dateFormat = new SimpleDateFormat("dd-MM-yyyy HH:mm:ss");
		   
		   //get current date time with Calendar()
		   Calendar cal = Calendar.getInstance();
		   String dateStr=dateFormat.format(cal.getTime());
		   dateStr = dateStr.replaceAll(" ", "_");
		   dateStr = dateStr.replaceAll(":", "_");
		   dateStr = dateStr.replaceFirst("-", "_");
		   
		   String filename="ResumeDiagram_"+dateStr+".xmind";
		   System.out.println("file name="+filename);
		IWorkbookBuilder workbookBuilder = Core.getWorkbookBuilder();
		String path = "C:/"+filename;
		IWorkbook workbook = workbookBuilder.createWorkbook(path);
		ISheet primarySheet = workbook.getPrimarySheet();
		
		// root
		ITopic rootTopic = primarySheet.getRootTopic();
		rootTopic.setTitleText("Resume Extration");
		
		Set set=diagramlist.entrySet();
		Iterator itr=set.iterator();
		
		while(itr.hasNext())
		{
			Map.Entry me=(Entry) itr.next();
			String name=(String) me.getKey();
			ArrayList<String> resumes=(ArrayList<String>) me.getValue();
			
			ITopic createTopic = workbook.createTopic();		
			createTopic.setTitleText(name);
			
			for(int i=0;i<resumes.size();i++)
			{
				String resumename=resumes.get(i);
			
				ITopic subTopic = workbook.createTopic();		
				subTopic.setTitleText(resumename);
				
				createTopic.add(subTopic, 0, ITopic.ATTACHED);
			}
			//createTopic.setFolded(true);
			rootTopic.add(createTopic, 0, ITopic.ATTACHED);
		}
		// save sucessfully
		workbook.save();
		System.out.println(" work book is sucessfully generated.. ");
		return path;
	}
}
