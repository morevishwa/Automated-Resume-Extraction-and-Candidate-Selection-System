package Search;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;

import Bean.EmailClass;
import EmailSender.SendEmail;
import Login.ConnectionManager;
import Login.EmailPasswordClient;
import open.source.email.service.soa.service.EmailService;
import open.source.email.service.soa.service.SendEmail.Arg0;
import open.source.email.service.soa.service.SendEmailResponse.Return;
import open.source.email.service.soa.service.SendEmailResponse.Return.ErrorList;
import open.source.email.service.soa.service.SendEmailResponse.Return.ErrorList.Error;
import open.source.email.service.soa.service.ServiceProviderService;

public class ShortListMassage 
{
	static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String strDate;
	
	public static String filename(String name,String user)
	{
		String msg = "";
		Connection con=null;
		try
		{
			ConnectionManager connectionManager=new ConnectionManager();	
			con=connectionManager.getConnection();
			Statement stmt1=con.createStatement();
			
			String query1="select * from Jobseeker where Resume_Name='"+name+"'";
			ResultSet recordSet=stmt1.executeQuery(query1);
			String mail=null;
			if(recordSet !=null)
			{
				if(recordSet.next())
				{
					mail=recordSet.getString("Email_ID");
			
					String query2="select * from registration where name='"+user+"'";
					ResultSet recordSet1=stmt1.executeQuery(query2);
					if(recordSet1.next())
					{
						String company=recordSet1.getString("companyname");
						msg = SendingEmail(mail,company);
					}
				}
			}
										
		}
		catch (SQLException ex) 
		{ 
			ex.printStackTrace(); 
			msg = ex.getMessage();
		}
		return msg;
	}
	
	public static String SendingEmail(String emailId,String company) 
	{
		String msg="Your resume has been ShortListed for "+company;
		
		EmailClass email = new EmailClass();
		email.setToEmail(emailId);
		email.setSubject(msg);
		email.setMsg(msg);
		email.setError("");
		
		EmailClass returnEmail = SendEmail.SendMailTLS(email);
		String returnmsg = returnEmail.getError();
		
		if(returnmsg == "")
		{
			System.out.println(" mail send sucessfully ");
			return "Mail send sucessfully";
		}
		else
		{
			System.out.println(returnmsg);
			return returnmsg;
		}
	}

	/*
	public static void SendingEmail(String emailId,String company) 
	{
		ServiceProviderService providerService = new ServiceProviderService();		
		EmailService emailservice = providerService.getServiceProviderPort();
		
		Arg0 request = new Arg0();
 		System.out.println("Email :-"+emailId);
		request.setEmailTo(emailId);
		String msg="Your resume has been ShortListed for "+company;
		request.setEmailMessage(msg);
		request.setEmailSubject("Resume Extracion Group");
		Return response = emailservice.sendEmail(request);
		String responseStatus = (String) response.getStatus();
		if(responseStatus.equals("scuess"))
		{
			System.out.println(" mail send sucessfully ");
		}
		else
		{
			ErrorList errorList = response.getErrorList();
			List<Error> errorCollection = errorList.getError();
		
			for( Error error : errorCollection)
			{
				System.out.println(error.getType() + " : " + error.getMessageCode() + " : " + error.getMessage());
			}
		}
	}

*/
}
