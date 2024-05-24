package Upload;

/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Elexion
 */
/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */



/**
 *
 * @author Elexion
 */
import java.io.*;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.*;

import Login.ConnectionManager;

import com.lowagie.text.*;
import com.lowagie.text.pdf.*;

public class ConvertPDFToTEXT {
	static Connection con; 
	//static String path="D:\\Study\\project\\ResumeExtractor\\ResumeExtractor\\Resume\\";
    
      public static void pdfTotext(String fileName,String path, String jobseekerName, int jobSeeker_id) throws IOException {
      try {
      Document document = new Document();
      String test=null;
      StringReader stReader = null;
      document.open();
      PdfReader reader = new PdfReader(fileName);      
      int numberOfPages = reader.getNumberOfPages();
      
      String filenameExtension=fileName.substring( fileName.lastIndexOf("\\")+1);
      String filenm=filenameExtension.substring(0, filenameExtension.lastIndexOf("."));
           
      FileOutputStream fos=new FileOutputStream(path+filenm+".txt");
      for(int i=1;i<=numberOfPages;i++)
      {
    	  PdfDictionary dictionary = reader.getPageN(i);
    	  PRIndirectReference reference = (PRIndirectReference)
          dictionary.get(PdfName.CONTENTS);
          PRStream stream = (PRStream) PdfReader.getPdfObject(reference);
          byte[] bytes = PdfReader.getStreamBytes(stream);
          PRTokeniser tokenizer = new PRTokeniser(bytes);
          
          StringBuffer buffer = new StringBuffer();
          while (tokenizer.nextToken()) 
          {
        	  if (tokenizer.getTokenType() == PRTokeniser.TK_STRING) 
        	  {
        		  buffer.append(tokenizer.getStringValue());
        	  }
          }      
          test=buffer.toString();
          stReader = new StringReader(test);  
      
          int t;
          while((t=stReader.read())>0)
          fos.write(t); 
          
      }
      document.add(new Paragraph(".."));
      document.close();
      String fnm=path+filenm+".txt";
      String emailID=EmailExtractor.readFile(fnm);
      EmailClient.SendingEmail(emailID);
      try 
      {
    	  String description=new String();
  		  String name=new String();
  		
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
  		  int rcnt=stat.executeUpdate(query);

  		  if (rcnt>0)
  		  {
  			System.out.println("Record Added Successfully!!!");
  		  }
  		  else
  		  {
  			System.out.println("Record Addition FAILED!!!");
  		  }
  			//step-6:close the connection
  		  con.close();
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
        	System.out.println(e.getMessage());
     }
   }
 }


