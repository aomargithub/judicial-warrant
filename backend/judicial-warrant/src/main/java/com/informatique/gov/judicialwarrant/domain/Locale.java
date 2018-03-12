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
@Table(name = "locale")
@Data
@ToString(of = {"code", "name"})
@EqualsAndHashCode(of = {"code"}, callSuper = false)
public class Locale extends DomainEntity<Byte> {

    private static final long serialVersionUID = -1431820909224829837L;
    @Id
    @Column(name = "id")
    private Byte id;
    
    @Column(name = "NAME")
    private String name;
    
    @Column(name = "is_active")
	private Boolean isActive;

	@NaturalId
	@Column(name = "code")
	private String code;
	
	@Embedded
	private CreateLog createLog;
}
