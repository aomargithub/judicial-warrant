package com.informatique.gov.judicialwarrant.service.impl;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.User;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.UserRepository;
import com.informatique.gov.judicialwarrant.service.InternalUserService;

import lombok.AllArgsConstructor;

@Service
@AllArgsConstructor
public class UserServiceImpl implements InternalUserService{

	/**
	 * 
	 */
	private static final long serialVersionUID = 6811481132650963567L;
	
	private UserRepository userRepository;

	
	@Override
	@Transactional(rollbackFor = Exception.class, readOnly = true)
	public User getByLoginName(String loginName) throws JudicialWarrantException {
		User user = null;
		try {
			
			user = userRepository.findByLoginNameIgnoreCase(loginName);
			
		}catch(Exception e){
            throw new JudicialWarrantInternalException(e);
        }
		return user;		
	}

}
