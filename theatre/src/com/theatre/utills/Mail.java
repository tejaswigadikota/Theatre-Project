package com.theatre.utills;

import java.util.Properties;
import javax.mail.Message;
import javax.mail.MessagingException;
import javax.mail.PasswordAuthentication;
import javax.mail.Session;
import javax.mail.Transport;
import javax.mail.internet.InternetAddress;
import javax.mail.internet.MimeMessage;
 
public class Mail {
	
	private String toAddress;
	private String subjectMessage;
	private String messageBody;
	
	public void to(String t){toAddress=t;}
	public void subject(String s){subjectMessage = s;}
	public void message(String m){messageBody = m;}
	
	public void send() throws Exception{
		if(toAddress==null || subjectMessage==null || messageBody==null)
			throw new Exception("Insufficient details");
		
		Properties props = new Properties();
		props.put("mail.smtp.host", "smtp.gmail.com");
		props.put("mail.smtp.socketFactory.port", "465");
		props.put("mail.smtp.socketFactory.class", "javax.net.ssl.SSLSocketFactory");
		props.put("mail.smtp.auth", "true");
		props.put("mail.smtp.port", "465");
 
		Session session = Session.getDefaultInstance(props,
			new javax.mail.Authenticator() {
				protected PasswordAuthentication getPasswordAuthentication() {
					return new PasswordAuthentication("theatre.javaproject@gmail.com","theatre123");
				}
			});
 
		try {
 
			Message message = new MimeMessage(session);
			message.setFrom(new InternetAddress("theatre.javaproject@gmail.com"));
			
			message.setRecipients(Message.RecipientType.TO,	InternetAddress.parse(toAddress));
			message.setSubject(subjectMessage);
			message.setText(messageBody);
 
			Transport.send(message);
 
 
		} catch (MessagingException e) {
			throw new RuntimeException(e);
		}
	}
}