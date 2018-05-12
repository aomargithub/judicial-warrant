package com.informatique.gov.judicialwarrant.service.impl;

import org.springframework.stereotype.Service;

import com.informatique.gov.judicialwarrant.service.UserPasswordService;
import com.informatique.gov.judicialwarrant.utils.MailUtil;
import com.informatique.gov.judicialwarrant.utils.RandomPasswordUtil;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserPasswordServiceImpl implements UserPasswordService {

	/**
	 * 
	 */
	private static final long serialVersionUID = -8025857261443977389L;
	private MailUtil mail;
	  


	@Override
	public String generateRandomUserPassword() {
		// TODO Auto-generated method stub
		
		return RandomPasswordUtil.generate(5);
	}

	@Override
	public void sendUserPasswordToEmail(String messagePassword,String emailRecipient,String subject) {
		// TODO Auto-generated method stub
		try {
			mail.sendEmail(messagePassword, emailRecipient, subject);
		} catch (Exception e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		
	}

}
