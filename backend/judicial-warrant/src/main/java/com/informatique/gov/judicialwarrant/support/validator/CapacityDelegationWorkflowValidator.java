package com.informatique.gov.judicialwarrant.support.validator;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class CapacityDelegationWorkflowValidator {

	private CapacityDelegationAttachmentsValidator capacityDelegationAttachmentsValidator;
	
	public void validate(CapacityDelegation capacityDelegation, RequestInternalStatusEnum requiredInternalStatusEnum)
			throws JudicialWarrantException {
		try {
			String serial = capacityDelegation.getRequest().getSerial();
			if (capacityDelegation.getRequest().getCurrentInternalStatus() == null) {
				if (!requiredInternalStatusEnum.equals(RequestInternalStatusEnum.RECIEVED)) {
					throw new InvalidRequestStatusException(serial,
							RequestInternalStatusEnum.getByCode(capacityDelegation.getRequest().getCurrentStatus().getCode()));
				} else {
					capacityDelegationAttachmentsValidator.validate(capacityDelegation);
					return;
				}
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

	public void validateForUpdate(CapacityDelegation jwcdRequest) throws JudicialWarrantException {
		try {
			RequestStatusEnum currentStatus = RequestStatusEnum
					.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
			if (!currentStatus.equals(RequestStatusEnum.DRAFT)) {
				String serial = jwcdRequest.getRequest().getSerial();
				throw new InvalidRequestStatusException(serial, currentStatus);
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}
}
