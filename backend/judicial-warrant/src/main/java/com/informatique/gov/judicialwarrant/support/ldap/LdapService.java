package com.informatique.gov.judicialwarrant.support.ldap;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.rest.dto.UserDto;

public interface LdapService extends Serializable{

	public boolean checkUserExists(String cn);
	public void addMemberToGroup(String groupName, UserDto dto);
}
