package org.ClientService.entities;
import java.io.IOException;

import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.Properties;
import java.util.Random;

import javax.activation.DataHandler;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
import javax.mail.util.ByteArrayDataSource;


public class SendEmail {
	static String emailFrom="charleswilkenson13@gmail.com";
	 final static String username="wilki";
	 final static String password="";
  
     
	 public static  boolean sendEmail(String passwordUser,String emailTo){
		   String sujet="Mot de passe";  
		   String message="Voici votre mot de passe tachez de le garder secret   ";

			Properties props = new Properties();
		
			    props.put("mail.transport.protocol", "smtp"); 
			    props.put("mail.debug", "true");
			    props.put("mail.smtp.starttls.enable", "true");
			    props.put("mail.smtp.socketFactory.port","465");
			    props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");    
			    props.put("mail.smtp.auth", "true");    
			    props.put("mail.smtp.host", "smtp.gmail.com");
			    props.put("mail.smtp.port", "465");
			    props.put("mail.smtp.timeout", "10000");    
			    props.put("mail.smtp.ssl.checkserveridentity", "false");
			    props.put("mail.smtp.ssl.trust", "*");
			    props.put("mail.smtp.connectiontimeout", "10000");    
			    props.put("mail.smtp.debug", "true");
			    props.put("mail.smtp.socketFactory.fallback", "false");


		 	Session session=Session.getInstance(props,new javax.mail.Authenticator(){
		 	protected PasswordAuthentication getPasswordAuthentication(){
		 		return new PasswordAuthentication(emailFrom,password);
		 	}
		 		
		 	});

		 	try {
		 		Message mailMessage=new MimeMessage(session);
		 		mailMessage.setFrom(new InternetAddress(emailFrom));
		 		mailMessage.setRecipients(Message.RecipientType.TO, InternetAddress.parse(emailTo));
		 	
		 mailMessage.setSubject(sujet);
		 mailMessage.setText(message+passwordUser);
		 Transport.send(mailMessage);
		 System.out.println("Email envoye avec succes");
		 	} catch (MessagingException e) {

		 		 System.out.println("Unable to send mail"+e.getMessage());
		 		throw new RuntimeException(e);
		 		
		 	}

			
			return true;
		 }
	 
	
}

