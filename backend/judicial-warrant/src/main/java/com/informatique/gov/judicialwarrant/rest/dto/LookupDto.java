package com.informatique.gov.judicialwarrant.rest.dto;

import lombok.Data;
import lombok.EqualsAndHashCode;
import lombok.ToString;

import java.io.Serializable;

@Data
@ToString
@EqualsAndHashCode(of = {"code"})
public abstract class LookupDto<T extends Serializable> implements UserModel<T>{
    private static final long serialVersionUID = 1749941026273969916L;
    private Boolean isActive;
    private String code;
}
