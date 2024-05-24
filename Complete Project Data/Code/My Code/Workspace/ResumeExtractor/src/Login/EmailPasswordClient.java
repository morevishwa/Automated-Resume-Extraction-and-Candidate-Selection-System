package Login;

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

public class EmailPasswordClient 
{
	static SimpleDateFormat sdfDate = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
	static String strDate;
	
	public static void SendingEmail(String emailId,String password) 
	{
		String msg="Your Password is:-"+password;
		
		EmailClass email = new EmailClass();
		email.setToEmail(emailId);
		email.setSubject("Resume Extracion Group :- Password");
		email.setMsg(msg);
		email.setError("");
		
		EmailClass returnEmail = SendEmail.SendMailTLS(email);
		String returnmsg = returnEmail.getError();
		
		if(returnmsg == "")
		{
			System.out.println(" mail send sucessfully ");
		}
		else
		{
			System.out.println(returnmsg);
		}
	}
}
