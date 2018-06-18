package com.informatique.gov.judicialwarrant.support.utils;

import java.text.MessageFormat;

import javax.mail.internet.MimeMessage;

import org.springframework.context.MessageSource;
import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MailUtil {

	private JavaMailSender sender;
	private MessageSource messageSource;
	

	private void send(String body, String recipient, String subject) throws Exception {

		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(body, true);

		sender.send(mail);

	}

	public void sendAccountCreation(String loginName, String password, String recipient, String userEnglishName,
			String userArabicName) throws Exception {

		
		String body = messageSource.getMessage("mail.body", null, null);
		body = MessageFormat.format(body, loginName,userEnglishName,password,userArabicName,password);

		send(body, recipient, messageSource.getMessage("mail.subject", null, null));

	}
}
