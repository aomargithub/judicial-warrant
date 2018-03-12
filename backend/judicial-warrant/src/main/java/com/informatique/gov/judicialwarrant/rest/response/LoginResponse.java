package com.informatique.gov.judicialwarrant.rest.response;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.rest.dto.UserDetailsDto;

import lombok.AllArgsConstructor;
import lombok.Data;


@Data
@AllArgsConstructor
public class LoginResponse  implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = -38541915313695856L;
	
	private UserDetailsDto userDetails ;

}
