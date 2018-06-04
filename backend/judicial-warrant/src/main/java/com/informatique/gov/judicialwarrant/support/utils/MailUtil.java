package com.informatique.gov.judicialwarrant.support.utils;

import javax.mail.internet.MimeMessage;

import org.springframework.mail.javamail.JavaMailSender;
import org.springframework.mail.javamail.MimeMessageHelper;
import org.springframework.stereotype.Component;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class MailUtil {

	private JavaMailSender  sender;

	private void send(String body, String recipient, String subject) throws Exception {

		  MimeMessage mail = sender.createMimeMessage();
          MimeMessageHelper helper = new MimeMessageHelper(mail, true);
          helper.setTo(recipient);
          helper.setSubject(subject);
          helper.setText(body,true);
		
		sender.send(mail);

	}

	public void sendAccountCreation(String loginName, String password, String recipient,String clientNameEn,String clientNameAr) throws Exception{
		String body = "<table style=\"width:100%\">\r\n" + "<caption><h2>" + loginName + "</h2></caption>\r\n"
				+ "  <tr>\r\n" + "    <th>Dear "+clientNameEn+" ,<br/>\r\n" + "    We create account in judicial Warrant<br/>\r\n"
				+ "    and your password is :" + password + "\r\n" + "    </th>\r\n"
				+ "    <th>"+clientNameAr+" عزيزى <br/> تم انشاء حساب فى الضبطية القضائية <br/>وكلمة السر :"+password+"</th> \r\n"
				+ "   \r\n" + "  </tr>\r\n" + " \r\n" + "</table>";
		send(body, recipient, "Creation Account in judical warrant");

	}
}
