package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;


@Entity
@Table(name = "notification_channel")
@Data
@ToString(of = {"code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class NotificationChannel extends DomainEntity<Byte>{

	/**
	 * 
	 */
	private static final long serialVersionUID = -1786261377930171546L;
	
	@Id
	@Column(name = "id")
	private Byte id;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "is_active")
	private Boolean isActive;

	@NaturalId
	@Column(name = "code")
	private String code;
	
	@Embedded
	private CreateLog createLog;
}
