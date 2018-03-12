package com.informatique.gov.judicialwarrant.domain;

import java.io.Serializable;
import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Embeddable;

import lombok.Data;

@Embeddable
@Data
public class UpdateLog implements Serializable{
	/**
	 * 
	 */
	private static final long serialVersionUID = 7289512114211423564L;
	
	@Column(name = "update_by")
	private String updateBy;
	
	@Column(name = "update_date")
	private Date updateDate;

}
