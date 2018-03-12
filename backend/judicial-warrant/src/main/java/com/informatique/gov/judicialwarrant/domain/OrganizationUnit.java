package com.informatique.gov.judicialwarrant.domain;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;

import org.springframework.data.annotation.Version;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "ORGANIZATION_UNIT")
@Data
@ToString(of = {"id", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"arabicName", "englishName"}, callSuper = false)
public class OrganizationUnit extends DomainEntity<Short> {

	/**
	 * 
	 */
	private static final long serialVersionUID = 4750673549591061137L;
	
	@Id
    @SequenceGenerator(name = "OrganizationUnitSequence", sequenceName = "organization_unit_seq", allocationSize = 1)
	@GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "OrganizationUnitSequence")
    @Column(name = "id")
    private Short id;
	
	@Version
	@Column(name="orm_version")
	private Short ormVersion;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "is_active")
	private Boolean isActive;
	
	@Embedded
	private CreateLog createLog;
	
	@Embedded
	private UpdateLog updateLog;
	
	@Column(name = "LIST_ORDER")
	private Short listOrder;
}
