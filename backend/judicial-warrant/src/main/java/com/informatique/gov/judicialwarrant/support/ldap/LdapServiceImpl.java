package com.informatique.gov.judicialwarrant.support.ldap;

import java.util.List;

import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.stereotype.Service;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class LdapServiceImpl implements LdapService {

	/**
	 * 
	 */
	private static final long serialVersionUID = 8642791853481072647L;
	
	private LdapTemplate ldapTemplate;
	

	@Override
	public boolean checkUserExists(String cn) {
		
		return findPerson(cn) == null? false:true;
	}
	
	private Object findPerson(String cn) {
		String user = null;
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "person"));
		filter.and(new WhitespaceWildcardsFilter("cn", cn));
		List<Object> users = ldapTemplate.search("", filter.encode(), new AttributesMapper<Object>() {
			public Object mapFromAttributes(Attributes attrs) throws NamingException {
				return attrs.get("distinguishedName").get();
			}
		});
		
		if(!users.isEmpty()) {
			user = (String) users.get(0);
		}
		return user;
	}
	
	
	@Override
	 public void addMemberToGroup(String groupName, String user) {
		
	        Object personDn = findPerson(user);

	        DirContextOperations ctx = ldapTemplate.lookupContext("cn="+groupName);
	        ctx.addAttributeValue("member", personDn.toString());

	        ldapTemplate.modifyAttributes(ctx);
		
		
	        
	}
	
	

}
