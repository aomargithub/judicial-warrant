package com.informatique.gov.judicialwarrant.service.impl;

import java.util.Calendar;

import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.informatique.gov.judicialwarrant.domain.RequestSerial;
import com.informatique.gov.judicialwarrant.domain.RequestType;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.RequestSerialRepository;
import com.informatique.gov.judicialwarrant.service.RequestSerialService;
import lombok.AllArgsConstructor;
import lombok.Synchronized;

@Service
@AllArgsConstructor
public class RequestSerialServiceImpl implements RequestSerialService {
	private RequestSerialRepository requestSerialRepository;

	/**
	 * 
	 */
	private static final long serialVersionUID = 1L;

	@Override
	@Transactional(rollbackFor = Exception.class)
	@Synchronized
	public String getRequestSerial(RequestType requestType) throws JudicialWarrantException {
		// set Serial for Request

		String serial = null;
		try {
			int currentYear = Calendar.getInstance().get(Calendar.YEAR);
			RequestSerial requestSerial = requestSerialRepository.findByRequestTypeAndYear(requestType, currentYear);

			String serialPrefix = requestType.getRequestSerialPrefix();
			Integer lastNumber = 1;
			if (requestSerial != null && requestSerial.getYear() == currentYear) {
				lastNumber = requestSerial.getLastNumber() + 1;
				requestSerial.setLastNumber(lastNumber);
				requestSerialRepository.save(requestSerial);
			} else {
				RequestSerial newRequestSerial = new RequestSerial();
				newRequestSerial.setRequestType(requestType);
				newRequestSerial.setYear(currentYear);
				newRequestSerial.setLastNumber(1);
				requestSerialRepository.save(newRequestSerial);
			}
			serial = serialPrefix + currentYear + String.format("%05d", lastNumber);
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}

		return serial;
	}

}
