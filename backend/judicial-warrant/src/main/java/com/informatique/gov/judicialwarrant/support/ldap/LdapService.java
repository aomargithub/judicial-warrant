package com.informatique.gov.judicialwarrant.support.ldap;

import java.io.Serializable;

public interface LdapService extends Serializable{

	public boolean checkUserExists(String cn);
	public void addMemberToGroup(String groupName,  String user);
}
