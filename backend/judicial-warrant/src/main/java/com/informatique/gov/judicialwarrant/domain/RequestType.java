package com.informatique.gov.judicialwarrant.domain;

import java.util.List;

import javax.persistence.Column;
import javax.persistence.Embedded;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.NaturalId;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

@Entity
@Table(name = "REQUEST_TYPE")
@Data
@ToString(of = {"id", "code", "arabicName", "englishName"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class RequestType extends DomainEntity<Byte> {
	
	/**
	 * 
	 */
	private static final long serialVersionUID = 8695777278368168503L;
	
	@Id
    @Column(name = "id")
	private Byte id;
	
	@Column(name = "ENGLISH_NAME")
	private String englishName;
	
	@Column(name = "ARABIC_NAME")
	private String arabicName;
	
	@Column(name = "IS_ACTIVE")
	private Boolean isActive;

	@NaturalId
	@Column(name = "code")
	private String code;
	
	@Embedded
	private CreateLog createLog;
	
	@Column(name = "REQUEST_SERIAL_PREFIX")
	private String requestSerialPrefix;
	
	@OneToMany(mappedBy = "requestType", fetch = FetchType.LAZY)
	private List<RequestSerial> requestSerials;
	
	@Column(name = "is_internal")
	private Boolean isInternal;
}
