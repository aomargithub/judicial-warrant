package com.informatique.gov.judicialwarrant.support.modelmpper;


import java.io.Serializable;
import java.util.List;
import java.util.Set;

import com.informatique.gov.judicialwarrant.domain.DomainModel;
import com.informatique.gov.judicialwarrant.rest.dto.UserModel;


public interface ModelMapper<E extends DomainModel<ID>, D extends UserModel<ID>, ID extends Serializable> extends Serializable{
	
	
	D toDto(E entity);
	List<D> toDto(List<E> entities);
	Set<D> toDto(Set<E> entities);
	
	E toEntity(D dto);
	List<E> toEntity(List<D> dtos);
	Set<E> toEntity(Set<D> dtos);

	
	E toNewEntity(D dto);
	List<E> toNewEntity(List<D> dtos);
	Set<E> toNewEntity(Set<D> dtos);
}
