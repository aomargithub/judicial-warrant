package com.informatique.gov.judicialwarrant.support.utils;

import org.springframework.mail.MailSender;
import org.springframework.mail.SimpleMailMessage;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;
@Component
@AllArgsConstructor
public class MailUtil {


	private MailSender  sender;
	//Logger logger = LoggerFactory.getLogger(this.getClass());
	
	public  void send(String body,String recipient,String subject) throws Exception{
		
		        SimpleMailMessage mail = new SimpleMailMessage();
		        mail.setFrom("attabdullah@informatique-eg.com");
				mail.setTo(recipient);
				mail.setSubject(subject);
				mail.setText(body);
				
			//	logger.info("Sending...");
				
				sender.send(mail);
				
			//	logger.info("Done!");
		
		    }
}
