package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "NATIONALITY")
@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class Nationality extends DomainEntity<Short> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695777278368168503L;
	
	@Id
    @Column(name = "id")
	private Short id;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@NaturalId
	@Column(name = "CODE")
	private String code;
	
	@Column(name = "ISO")
	private Long iso;
	
	
}
