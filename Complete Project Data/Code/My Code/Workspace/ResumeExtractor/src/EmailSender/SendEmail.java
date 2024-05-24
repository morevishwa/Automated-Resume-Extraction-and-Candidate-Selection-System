package EmailSender;

import java.util.Properties;

import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;

import Constants.Comman;
import Bean.EmailClass;


public class SendEmail 
{
	public static EmailClass SendMailTLS (EmailClass email) 
	{
		Properties props = new Properties();
		props.put("mail.smtp.auth", Comman.mail_smtp_auth_TLS);
		props.put("mail.smtp.starttls.enable", Comman.mail_smtp_starttls_enable);
		props.put("mail.smtp.host", Comman.mail_smtp_host_TLS);
		props.put("mail.smtp.port", Comman.mail_smtp_port_TLS);

		Session session = Session.getInstance
			(
				props, new javax.mail.Authenticator() 
				{
					protected PasswordAuthentication getPasswordAuthentication() 
					{
						return new PasswordAuthentication(Comman.emailId, Comman.emailPassword);
					}
				}
			);

		try 
		{
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress(Comman.emailId));
			message.setRecipients(Message.RecipientType.TO, InternetAddress.parse(email.getToEmail()));
			message.setSubject(email.getSubject());
			message.setText(email.getMsg());

			Transport.send(message);
			email.setError("");
			System.out.println("Done");
			
		} 
		catch (MessagingException e) 
		{
			System.out.println(e.getMessage());
			email.setError(e.getMessage());
			//throw new RuntimeException(e);
		}
		return email;
	}
	
}
