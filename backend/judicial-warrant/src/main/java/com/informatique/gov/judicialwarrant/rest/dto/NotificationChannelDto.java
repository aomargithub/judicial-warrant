package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Data
@ToString(callSuper = true)
@EqualsAndHashCode(callSuper = true)
public class NotificationChannelDto extends LookupDto<Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1953687407623218328L;
	
	private Byte id;
	private String englishName;
	private String arabicName;
}
