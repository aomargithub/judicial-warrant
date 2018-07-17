package com.informatique.gov.judicialwarrant.support.utils;

import java.io.File;
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
	

	private void send(String body, String recipient, String subject, File file) throws Exception {

		MimeMessage mail = sender.createMimeMessage();
		MimeMessageHelper helper = new MimeMessageHelper(mail, true);
		helper.setTo(recipient);
		helper.setSubject(subject);
		helper.setText(body, true);
		if (file != null) {
			helper.addAttachment(file.getName(), file);
		}
		sender.send(mail);

	}

	public void sendAccountCreation(String loginName, String password, String recipient, String userEnglishName,
			String userArabicName) throws Exception {

		
		String body = messageSource.getMessage("mail.password.body", null, null);
		body = MessageFormat.format(body, loginName,userEnglishName,password,userArabicName,password);

		send(body, recipient, messageSource.getMessage("mail.password.subject", null, null), null);

	}
	
	public void sendEntitledsCheck(String recipient, File file) throws Exception {

		String body = messageSource.getMessage("mail.law_Affairs.body", null, null);
		send(body, recipient, messageSource.getMessage("mail.law_Affairs.subject", null, null), file);

	}
}
