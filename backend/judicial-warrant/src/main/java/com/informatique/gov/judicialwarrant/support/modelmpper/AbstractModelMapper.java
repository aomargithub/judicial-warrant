package com.informatique.gov.judicialwarrant.support.modelmpper;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import javax.persistence.EntityManagerFactory;
import javax.persistence.PersistenceUnit;
import javax.persistence.PersistenceUnitUtil;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.hibernate.proxy.HibernateProxy;

import com.informatique.gov.judicialwarrant.domain.DomainModel;
import com.informatique.gov.judicialwarrant.rest.dto.UserModel;

public abstract class AbstractModelMapper<E extends DomainModel<ID>, D extends UserModel<ID>, ID extends Serializable> implements ModelMapper<E, D, ID>{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5171542953876089724L;
	private final static Logger logger = LogManager.getLogger(AbstractModelMapper.class);
	
	
	@PersistenceUnit
	private EntityManagerFactory entityMangerFactory;
	
	protected abstract E toEntity(D dto, boolean nullId);
	
	public boolean isConvertable(Collection<?> collection){
		return collection != null && !collection.isEmpty();
	}
	
	public boolean isConvertable(D dto){
		return dto != null;
	}
	
	public boolean isConvertable(E entity){
		if (entity != null) {
			if(HibernateProxy.class.isInstance(entity)){
				HibernateProxy proxy = HibernateProxy.class.cast(entity);				
				if (!proxy.getHibernateLazyInitializer().isUninitialized()) {
					return true;
				}
			}else{
				return true;
			}
		}
		return false;
	}
	
	protected  boolean isConvertable(Object entity, String attributeName){
		PersistenceUnitUtil unitUtil = entityMangerFactory.getPersistenceUnitUtil();
		return unitUtil.isLoaded(entity, attributeName);
	}

	@Override
	public List<D> toDto(List<E> entities){
		List<D> dtos = null;

		if(isConvertable(entities)){
			dtos = new ArrayList<D>();

			for(E e : entities){
				dtos.add(toDto(e));
			}
		}
		return dtos;
	}

	@Override
	public Set<D> toDto(Set<E> entities){
		Set<D> dtos = null;

		if(isConvertable(entities)){
			dtos = new HashSet<D>();

			for(E e : entities){
				dtos.add(toDto(e));
			}
		}
		return dtos;
	}



	@Override
	public E toEntity(D dto){
		return toEntity(dto, false);
	}

	@Override
	public List<E> toEntity(List<D> dtos){
		List<E> entities = null;

		if(isConvertable(dtos)){
			entities = new ArrayList<E>();

			for(D dto : dtos){
				entities.add(toEntity(dto));
			}
		}

		return entities;
	}

	@Override
	public Set<E> toEntity(Set<D> dtos){
		Set<E> entities = null;

		if(isConvertable(dtos)){
			entities = new HashSet<E>();

			for(D dto : dtos){
				entities.add(toEntity(dto));
			}
		}

		return entities;
	}

	@Override
	public E toNewEntity(D dto){
		return toEntity(dto, true);
	}

	@Override
	public List<E> toNewEntity(List<D> dtos){
		List<E> entities = null;

		if(isConvertable(dtos)){
			entities = new ArrayList<E>();

			for(D dto : dtos){
				entities.add(toNewEntity(dto));
			}
		}
		
		return entities;
	}
	
	@Override
	public Set<E> toNewEntity(Set<D> dtos){
		Set<E> entities = null;
		
		if(isConvertable(dtos)){
			entities = new HashSet<E>();
			
			for(D dto : dtos){
				entities.add(toNewEntity(dto));
			}
		}
		
		return entities;
	}
}
