package Upload;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.List;
import java.util.Scanner;

import javax.xml.crypto.Data;

import Bean.EmailClass;
import EmailSender.SendEmail;
import open.source.email.service.soa.service.EmailService;
import open.source.email.service.soa.service.SendEmail.Arg0;
import open.source.email.service.soa.service.SendEmailResponse.Return;
import open.source.email.service.soa.service.SendEmailResponse.Return.ErrorList;
import open.source.email.service.soa.service.SendEmailResponse.Return.ErrorList.Error;
import open.source.email.service.soa.service.ServiceProviderService;

public class EmailClient 
{
	static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String strDate;
	
	public static String SendingEmail(String emailId) 
	{
	
		String msg="Thank you for uploading your Resume";
		
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
		/*
		ServiceProviderService providerService = new ServiceProviderService();		
		EmailService emailservice = providerService.getServiceProviderPort();
		
		Arg0 request = new Arg0();
 		System.out.println("Email :-"+emailId);
		request.setEmailTo(emailId);
		request.setEmailMessage("Thank you for uploading your Resume");
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
		*/
	}
}
