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
@Table(name = "ENTITLED_STATUS")
@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class EntitledStatus extends DomainEntity<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 4507299751782117054L;

	@Id
    @Column(name = "id")
    private Short id;
	
	@NaturalId
	@Column(name = "code")
	private String code;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Embedded
	private CreateLog createLog;
}
