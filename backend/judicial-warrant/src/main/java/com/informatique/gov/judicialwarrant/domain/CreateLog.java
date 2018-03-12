package com.informatique.gov.judicialwarrant.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class CreateLog implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 7243886518617254306L;
	
	
	@Column(name = "create_by")
	private String createBy;
	
	@Column(name = "create_date")
	private Date createDate;
	
	

}
