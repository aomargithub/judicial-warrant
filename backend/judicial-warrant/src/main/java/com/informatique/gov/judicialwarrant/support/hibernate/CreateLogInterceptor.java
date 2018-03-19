package com.informatique.gov.judicialwarrant.support.hibernate;

import java.io.Serializable;
import java.util.Date;

import org.hibernate.EmptyInterceptor;
import org.hibernate.type.Type;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CreateLog;
import com.informatique.gov.judicialwarrant.domain.CreationAuditable;
import com.informatique.gov.judicialwarrant.service.SecurityService;

@Component
public class CreateLogInterceptor extends EmptyInterceptor{

	/**
	 * 
	 */
	private static final long serialVersionUID = 5093121309231976940L;
	
	@Autowired
	private SecurityService securityService;
	
	
	@Override
	public boolean onSave(
			Object entity, 
			Serializable id, 
			Object[] state, 
			String[] propertyNames, 
			Type[] types) {
		try {
			if(entity instanceof CreationAuditable) {
				for(int i = 0; i < propertyNames.length; i++) {
					if("createLog".equals(propertyNames[i])) {
						state[i] = new CreateLog(securityService.getPrincipal(), new Date());
						return true;
					}
				}
			}
		}catch(Exception e) {
			return false;
		}
		
		return false;
	}
}
