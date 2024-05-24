package Upload;

import org.apache.poi.poifs.filesystem.*;

import java.sql.*; 

import org.apache.poi.hpsf.DocumentSummaryInformation;
import org.apache.poi.hwpf.*;
import org.apache.poi.hwpf.extractor.*;
import org.apache.poi.hwpf.usermodel.HeaderStories;

import Login.ConnectionManager;

import java.io.*;

import javax.swing.text.Document;

public class ReadDocFileFromJava 
{
	static Connection con; 
	//static String path="D:\\Study\\project\\ResumeExtractor\\ResumeExtractor\\Resume\\";
    
	public static void docTotxt(String fileName,String path, String jobseekerName , int jobSeeker_id) 
    {
    	readMyDocument(fileName,path, jobseekerName, jobSeeker_id);    
    }
    public static void readMyDocument(String fileName,String path, String jobseekerName, int jobSeeker_id)
    {
        POIFSFileSystem fs = null;
        try {
            fs = new POIFSFileSystem(new FileInputStream(fileName));
            HWPFDocument doc = new HWPFDocument(fs);        
           readParagraphs(doc,fileName,path);
            int pageNumber=1;
            readDocumentSummary(doc);
            String filenameExtension=fileName.substring(fileName.lastIndexOf("\\")+1);
            String filenm=filenameExtension.substring(0, filenameExtension.lastIndexOf("."));            
            String fnm=path+filenm+".txt";
            String emailID=EmailExtractor.readFile(fnm);
            EmailClient.SendingEmail(emailID);
            try{
            	String name=null;
            	ConnectionManager connectionManager=new ConnectionManager();	
				con=connectionManager.getConnection();
				

				Statement stmt1=con.createStatement();
				
				String query1="select MAX(id) from Jobseeker";
				ResultSet recordSet=stmt1.executeQuery(query1);
				int id1=0;
				if(recordSet.next())
				{
					id1=recordSet.getInt(1);
				}
				id1=id1+1;
				System.out.println(id1);	
				
				
				Statement stat=con.createStatement();    			
    			String query="insert into Jobseeker(id,JobSeeker_id,Name,Resume_name,Resume_Location,Email_ID) values("+id1+","+jobSeeker_id+",'"+jobseekerName+"','"+filenameExtension+"','"+fileName+"','"+emailID+"')";
    			System.out.println("query "+ query);
    			int rcnt=stat.executeUpdate(query);    			
    			if (rcnt>0)
    			{
    				System.out.println("Record Added Successfully!!!");
    			}
    			else
    			{
    				System.out.println("Record Addition FAILED!!!");
    			}    			    			
    			//con.close();    			
    		} 
    		catch (SQLException ex) 
    		{ 
    			ex.printStackTrace(); 
    		}
    		finally
    		{
    			con.close();
    		}
            searchfile.searchKeyword(fnm,filenameExtension,path);            
        } 
        catch (Exception e) 
        {
        	e.printStackTrace();
        }
    }

    public static void readParagraphs(HWPFDocument doc,String fileName,String path) {
    	try
    	{
        WordExtractor we = new WordExtractor(doc);

        /**Get the total number of paragraphs**/
        String str=we.getText();
        System.out.println("the doc contains"+str);
        String[] paragraphs = we.getParagraphText();
 
        try
        {
        	String filenameExtension=fileName.substring( fileName.lastIndexOf("\\"));
            String filenm=filenameExtension.substring(0, filenameExtension.lastIndexOf("."));
                            
        	FileWriter fstream = new FileWriter(path+filenm+".txt");
        	  BufferedWriter out = new BufferedWriter(fstream);        	  
        	  //Close the output stream        	  
        	  for (int i = 0; i < paragraphs.length; i++) {        	      
        		  out.write(paragraphs[i].toString());
        	            System.out.println(paragraphs[i].toString());
        	        }
        	  out.close();
        }
        catch (Exception e){//Catch exception if any
        	  System.err.println("Error: " + e.getMessage());
        	  }
        
    	}
        catch(Exception e)
        {
        	
        }
        
    }

    public static void readHeader(HWPFDocument doc, int pageNumber){
    	
        HeaderStories headerStore = new HeaderStories( doc);
        String header = headerStore.getHeader(pageNumber);
     //  System.out.println("Header Is: "+header);

    }

    public static void readFooter(HWPFDocument doc, int pageNumber){
  HeaderStories headerStore = new HeaderStories( doc);
  
      String footer = headerStore.getFooter(pageNumber);
    //    System.out.println("Footer Is: "+footer);

    }

    public static void readDocumentSummary(HWPFDocument doc) 
    {
        DocumentSummaryInformation summaryInfo=doc.getDocumentSummaryInformation();
        String category = summaryInfo.getCategory();
        String company = summaryInfo.getCompany();
        int lineCount=summaryInfo.getLineCount();
        int sectionCount=summaryInfo.getSectionCount();
        int slideCount=summaryInfo.getSlideCount();
    }
}
