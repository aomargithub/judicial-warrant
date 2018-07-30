package com.informatique.gov.judicialwarrant.support.security;

import org.springframework.security.core.GrantedAuthority;

import com.informatique.gov.judicialwarrant.support.dataenum.UserRoleEnum;

import lombok.AllArgsConstructor;
import lombok.EqualsAndHashCode;
import lombok.Getter;
import lombok.RequiredArgsConstructor;
import lombok.ToString;

@ToString(of = {"role"})
@EqualsAndHashCode(of = {"role"}, callSuper = false)
@RequiredArgsConstructor
@AllArgsConstructor
public class JudicialWarrantGrantedAuthority implements GrantedAuthority {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 667607029945851148L;
	private final String role;
	@Getter
	private String ldapSecurityGroup;
	
	public JudicialWarrantGrantedAuthority(UserRoleEnum userRoleEnum) {
		this.role = userRoleEnum.getCode();
	}


	@Override
	public String getAuthority() {
		return role;
	}
}
