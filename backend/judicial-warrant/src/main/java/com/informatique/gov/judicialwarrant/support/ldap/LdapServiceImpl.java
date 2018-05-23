package com.informatique.gov.judicialwarrant.support.ldap;

import java.util.List;

import javax.naming.Name;
import javax.naming.NamingException;
import javax.naming.directory.Attributes;
import javax.naming.ldap.LdapName;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ldap.core.AttributesMapper;
import org.springframework.ldap.core.DirContextOperations;
import org.springframework.ldap.core.LdapTemplate;
import org.springframework.ldap.core.support.AbstractContextMapper;
import org.springframework.ldap.core.support.BaseLdapNameAware;
import org.springframework.ldap.filter.AndFilter;
import org.springframework.ldap.filter.EqualsFilter;
import org.springframework.ldap.filter.WhitespaceWildcardsFilter;
import org.springframework.ldap.support.LdapNameBuilder;
import org.springframework.stereotype.Service;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.rest.dto.UserDto;

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
	
	private Object findGroup(String cn) {
		String group = null;
		AndFilter filter = new AndFilter();
		filter.and(new EqualsFilter("objectclass", "group"));
		filter.and(new WhitespaceWildcardsFilter("cn", cn));
		List<Object> groups = ldapTemplate.search("", filter.encode(), new AttributesMapper<Object>() {
			public Object mapFromAttributes(Attributes attrs) throws NamingException {
				return attrs.get("distinguishedName").get();
			}
		});
		
		if(!groups.isEmpty()) {
			group = (String) groups.get(0);
		}
		return group;
	}
	
	@Override
	 public void addMemberToGroup(String groupName, UserDto dto) {
		
	        Object groupDn = buildGroupDn(groupName);
	        Name personDn = buildUserDn(dto);

	        DirContextOperations ctx = ldapTemplate.lookupContext(groupDn.toString());
	        ctx.addAttributeValue("member", personDn);

	        ldapTemplate.modifyAttributes(ctx);
		
		
	    }
	 
	 
	 private Name buildGroupDn(String groupName) {
	        return LdapNameBuilder.newInstance()
//	        		.add("cn", "local")
//	        		.add("cn", "oman")
//	                .add("ou", "jw")
	                .add("cn", groupName)
	                .build();
	    }
	 
	 private Name buildUserDn(UserDto dto) {
	        return LdapNameBuilder.newInstance()
//	        		.add("cn", "local")
//	        		.add("cn", "oman")
//	                .add("ou", "jw")
	                .add("cn", dto.getLoginName())
	                .build();
	    }

	
	
	

}
