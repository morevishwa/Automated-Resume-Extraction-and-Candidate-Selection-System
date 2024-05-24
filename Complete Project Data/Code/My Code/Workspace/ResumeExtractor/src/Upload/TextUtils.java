package Upload;

import java.io.BufferedReader;
import java.io.FileOutputStream;
import java.io.FileReader;
import java.io.IOException;
import java.io.OutputStreamWriter;
import java.io.PrintStream;
import java.io.Writer;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import javax.xml.bind.JAXBContext;
import javax.xml.bind.JAXBElement;
import javax.xml.bind.Marshaller;
import javax.xml.namespace.QName;

import org.apache.log4j.Logger;
import org.docx4j.jaxb.Context;
import org.docx4j.jaxb.NamespacePrefixMapperUtils;
import org.docx4j.openpackaging.packages.WordprocessingMLPackage;
import org.docx4j.openpackaging.parts.WordprocessingML.MainDocumentPart;
import org.xml.sax.SAXException;
import org.xml.sax.helpers.DefaultHandler;

import Login.ConnectionManager;

public class TextUtils {
	static Connection con; 
	//static String path="D:\\Study\\project\\ResumeExtractor\\ResumeExtractor\\Resume\\";
    
	private static Logger log = Logger.getLogger(TextUtils.class);	
	
	public static void extractText(Object o, Writer w) throws Exception 
	{
		extractText(o, w, Context.jc);
	}
	
	public static void extractText(Object o, Writer w, JAXBContext jc) throws Exception 
	{		
		Marshaller marshaller=jc.createMarshaller();
		NamespacePrefixMapperUtils.setProperty(marshaller, 
		NamespacePrefixMapperUtils.getPrefixMapper());
		marshaller.marshal(o, new TextExtractor(w));
	}

	public static void extractText(Object o, Writer w, JAXBContext jc,String uri, String local, Class declaredType) throws Exception 
	{		
		Marshaller marshaller=jc.createMarshaller();
		NamespacePrefixMapperUtils.setProperty(marshaller, 
		NamespacePrefixMapperUtils.getPrefixMapper());
		marshaller.marshal(new JAXBElement(new QName(uri,local), declaredType, o ),new TextExtractor(w));		
	}

	static class TextExtractor extends DefaultHandler 
	{
		  private Writer out;		  
		  public TextExtractor(Writer out) 
		  {
		    this.out = out;   
		  }
		  public void characters(char[] text, int start, int length)throws SAXException 
		  {		     
		    try 
		    {
		      out.write(text, start, length); 
		    }
		    catch (IOException e) 
		    {
		      throw new SAXException(e);   
		    }		   
		  }  		    
		} // end TextExtractor	
	
	public static void docxTotxt(String fileName,String path, String jobseekerName, int jobSeeker_id) throws Exception 
	{
		String inputfilepath =fileName;;
		System.out.println("fileName");		
		WordprocessingMLPackage wordMLPackage = WordprocessingMLPackage.load(new java.io.File(inputfilepath));
		MainDocumentPart documentPart = wordMLPackage.getMainDocumentPart();				
		org.docx4j.wml.Document wmlDocumentEl = (org.docx4j.wml.Document)documentPart.getJaxbElement();				
		String filenameExtension=fileName.substring(fileName.lastIndexOf("\\")+1);
        String filenm=filenameExtension.substring(0, filenameExtension.lastIndexOf("."));        
        String fnm=path+filenm+".txt";	             
		PrintStream out1 = new PrintStream(new FileOutputStream(fnm));
		System.setOut(out1);
		Writer out = new OutputStreamWriter(System.out);
		extractText(wmlDocumentEl, out);
		out.close();
		out1.close();
		 String emailID=EmailExtractor.readFile(fnm);
		 emailID=emailID.substring(0,emailID.indexOf(".com")+4);
		 System.out.println("emailID:"+emailID);
		 EmailClient.SendingEmail(emailID);
       try
       {
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
  				int rcnt=stat.executeUpdate(query);			
  				if (rcnt>0)
  				{
  					System.out.println("Record Added Successfully!!!");
  				}
  				else
  				{
  					System.out.println("Record Addition FAILED!!!");
  				}
  				
  						
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
	
}


