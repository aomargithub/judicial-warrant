package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum UserRoleEnum {
	ADMIN("ROLE_ADMIN"), OFFICER("ROLE_OFFICER"), MINISTER("ROLE_MINISTER"), TRAINING_INSTITUTE("ROLE_TRAINING_INSTITUTE"), LAW_AFFAIRS("ROLE_LAW_AFFAIRS"), USER("ROLE_USER");
	
	@Getter
	private String code;
	
	private UserRoleEnum (String code) {
		this.code = code;
	}
}
