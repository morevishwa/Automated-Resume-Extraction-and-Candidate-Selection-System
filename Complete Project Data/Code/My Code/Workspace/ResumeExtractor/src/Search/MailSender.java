package Search;

import java.io.File;
import java.util.Properties;

import javax.activation.*;
import javax.mail.*;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeBodyPart;
import javax.mail.internet.MimeMessage;
import javax.mail.internet.MimeMultipart;

public class MailSender {

public static void attachment(String path, String toMailID ) {

    final String username = "sangrammore11@gmail.com";
    final String password = "itsend2013";

    Properties props = new Properties();
    props.put("mail.smtp.auth", true);
    props.put("mail.smtp.starttls.enable", "true");
    props.put("mail.smtp.host", "smtp.gmail.com");
    props.put("mail.smtp.port", "587");
    props.setProperty("mail.smtp.user", username);
    props.setProperty("mail.smtp.password", password);
    props.setProperty("mail.smtp.auth", "true");      


    Session session = Session.getInstance(props,
            new javax.mail.Authenticator() {
                protected PasswordAuthentication getPasswordAuthentication() {
                    return new PasswordAuthentication(username, password);
                }
            });

    try {

        Message message = new MimeMessage(session);
        message.setFrom(new InternetAddress("sangrammore11@gmail.com"));
        message.setRecipients(Message.RecipientType.TO,
                InternetAddress.parse(toMailID));
        message.setSubject("Resume Extraction Diagram");
        message.setText("PFA");

        MimeBodyPart messageBodyPart = new MimeBodyPart();

        Multipart multipart = new MimeMultipart();

        messageBodyPart = new MimeBodyPart();
        File attachment=new File(path);
        String file = attachment.getAbsolutePath();
        String fileName = attachment.getName();
        DataSource source = new FileDataSource(file);
        messageBodyPart.setDataHandler(new DataHandler(source));
        messageBodyPart.setFileName(fileName);
        multipart.addBodyPart(messageBodyPart);

        message.setContent(multipart);

        System.out.println("Sending");

        Transport.send(message);

        System.out.println("Done");

    } catch (MessagingException e) {
        e.printStackTrace();
    }
  }
}
