package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

public interface UserPasswordService extends Serializable{
	
	public String generateRandomUserPassword();
	public void sendUserPasswordToEmail(String messagePassword,String emailRecipient,String subject);

}
