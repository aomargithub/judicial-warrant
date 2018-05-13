package com.informatique.gov.judicialwarrant.utils;

import org.springframework.mail.SimpleMailMessage;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class MailUtil {


	private JavaMailSender sender;
	//Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public  void sendEmail(String messagePassword,String emailRecipient,String subject) throws Exception{
		
		        SimpleMailMessage mail = new SimpleMailMessage();
				mail.setTo(emailRecipient);
				mail.setSubject(subject);
				mail.setText(messagePassword);
				
			//	logger.info("Sending...");
				
				sender.send(mail);
				
			//	logger.info("Done!");
		
		    }
}
