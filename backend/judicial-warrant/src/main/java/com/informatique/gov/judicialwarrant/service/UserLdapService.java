package com.informatique.gov.judicialwarrant.service;

import java.io.Serializable;

public interface UserLdapService extends Serializable{

	public boolean checkUserExists(String cn);
}
