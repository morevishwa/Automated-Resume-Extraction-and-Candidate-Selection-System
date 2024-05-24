package Upload;
import java.sql.*; 
import java.io.*;
import java.util.*;

import javax.servlet.http.*;

import org.apache.commons.fileupload.*;
import org.apache.commons.fileupload.disk.*;
import org.apache.commons.fileupload.servlet.*;
import org.apache.commons.io.output.*;

import Login.ConnectionManager;

import java.io.IOException;

import javax.servlet.GenericServlet;
import javax.servlet.ServletContext;
import javax.servlet.ServletException;
import javax.swing.JOptionPane;

/**
 * Servlet implementation class UploadServlet
 */
public class UploadServlet extends HttpServlet {
	private static final long serialVersionUID = 1L;
	private GenericServlet pageContext;
	static Connection con; 
	//static String path="D:\\Study\\project\\ResumeExtractor\\ResumeExtractor\\Resume\\";
    /**
     * @see HttpServlet#HttpServlet()
     */
    public UploadServlet() {
        super();
        // TODO Auto-generated constructor stub
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
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException 
	{
		// TODO Auto-generated method stub
		
		File file ;
		int maxFileSize = 5000 * 1024;
		int maxMemSize = 5000 * 1024;
		   
		   
		//ServletContext context = pageContext.getServletContext();
		String filePath = getServletContext().getInitParameter("file-upload");
		// Verify the content type
		String contentType = request.getContentType();
		 
		if ((contentType.indexOf("multipart/form-data") >= 0)) 
		{
		      DiskFileItemFactory factory = new DiskFileItemFactory();
		      // maximum size that will be stored in memory
		      factory.setSizeThreshold(maxMemSize);
		      // Location to save data that is larger than maxMemSize.
		      factory.setRepository(new File(filePath));
		      // Create a new file upload handler
		      ServletFileUpload upload = new ServletFileUpload(factory);
		      // maximum file size to be uploaded.
		      upload.setSizeMax( maxFileSize );
		      try{ 
		         // Parse the request to get file items.
		         List fileItems = upload.parseRequest(request);
		         // Process the uploaded file items
		         Iterator i = fileItems.iterator();
		         while ( i.hasNext () ) 
		         {
		            FileItem fi = (FileItem)i.next();
		            
		            if ( !fi.isFormField () )	
		            {
		            	
		            	// Get the uploaded file parameters
		            	String fieldName = fi.getFieldName();
		            	System.out.println("field name:"+fieldName);
		            	String fileName = fi.getName();
		            	System.out.println("file name:"+fileName);
		            	
		            		//boolean isInMemory = fi.isInMemory();
		            		//long sizeInBytes = fi.getSize();
		            		// Write the file	
		            	if(fileName.equals(""))
		            	{
		            		response.sendRedirect("UploadError.jsp");
		            		break;
		            	}
		            	else
		            	{
		            		String fileNm=filePath +fileName.substring(fileName.lastIndexOf("\\")+1);
		            		File file1=new File(fileNm);
		            		String extension=null;
		            		int dotPos = fileNm.lastIndexOf(".");
	            			extension = fileNm.substring(dotPos);
		            		if(file1.exists())
		            		{
		            			response.sendRedirect("UploadError.jsp");
		            		}
		            		else if(extension.equals(".docx")||extension.equals(".doc")||extension.equals(".pdf")||extension.equals(".txt"))
		            		{
		            			HttpSession session = request.getSession(true);
		        				String jobseekerName= (String)session.getAttribute("currentJobSeekarSessionUser");
		        				int jobSeeker_id= (Integer)session.getAttribute("currentJobSeekarId");
		        				
		            			if( fileName.lastIndexOf("\\") >= 0 )
		            			{
		            				file = new File(fileNm);
		            				System.out.println("fileNm:"+fileNm);
		            				System.out.print("Filepath:"+filePath+"\nFileNAme:"+fileName+"\nfile:"+file);
		            			}
		            			else
		            			{
		            				file = new File( filePath + fileName.substring(fileName.lastIndexOf("\\")+1)) ;		            		
		            			}
		            			fi.write( file ) ;
		            			//String filenameExtension=fileName.substring( fileName.lastIndexOf("\\"));
		            			
		            			dotPos = fileNm.lastIndexOf(".");
		            			extension = fileNm.substring(dotPos);
		            			if(extension.equals(".docx"))
		            			{
		            				TextUtils.docxTotxt( filePath+fileName.substring(fileName.lastIndexOf("\\")+1), filePath, jobseekerName, jobSeeker_id);
		            			}
		            		
		            			else if(extension.equals(".doc"))
		            			{
		            				ReadDocFileFromJava.docTotxt(filePath+fileName.substring(fileName.lastIndexOf("\\")+1),filePath, jobseekerName, jobSeeker_id);
		            			}
		            			else if(extension.equals(".pdf"))
		            			{
		            				ConvertPDFToTEXT.pdfTotext(filePath+fileName.substring(fileName.lastIndexOf("\\")+1),filePath, jobseekerName, jobSeeker_id);
		            			}
		            			
		            			
		        				
		            			if(extension.equals(".txt"))
		            			{
		            				try
		            				{
		            					String emailID=EmailExtractor.readFile(filePath+fileName.substring(fileName.lastIndexOf("\\")+1));
		            					EmailClient.SendingEmail(emailID);
		            					try 
		            					{ 
		            						ConnectionManager connectionManager=new ConnectionManager();	
		            						con=connectionManager.getConnection();
		            						String filenameExtension=fileName.substring( fileName.lastIndexOf("\\")+1);
		            						String filenm=filenameExtension.substring(0, filenameExtension.lastIndexOf("."));
		            						
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
		            						
		            						String query="insert into Jobseeker(id,JobSeeker_id,Name,Resume_name,Resume_Location,Email_ID) values("+ id1 +","+jobSeeker_id+",'"+jobseekerName+"','"+filenameExtension+"','"+filePath+fileName+"','"+emailID+"')";
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
		            				}
		            				catch(Exception e) 
		            				{ 
		            					System.out.println(e); 
		            				} 
		            				searchfile.searchKeyword(filePath+fileName,fileName,filePath);
		            			}
		            			System.out.println("Uploaded Filename: "+filePath+fileName.substring( fileName.lastIndexOf("\\")+1));
		            			String fnm=filePath+fileName;
		            			response.sendRedirect("UploadSuccess.jsp");
		            			//out.println("<Script>");
		            			//searchfile.searchKeyword(filePath+fileName,fileName);
		            		}
		            		else
		            		{
		            			response.sendRedirect("UploadFileError.jsp");
		            		}
		            	}
		            }
		         }
		      }		         		         		      
		      catch(Exception ex) 
		      {
		    	  ex.printStackTrace();
		          System.out.println(ex);
		      }
		}
	}
}
		      
			
		
	


