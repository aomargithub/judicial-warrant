package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.CapacityDelegation;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class CapacityDelegationWorkflowValidator {

	public static void validate(CapacityDelegation jwcdRequest, RequestInternalStatusEnum requiredInternalStatusEnum)
			throws JudicialWarrantException {
		try {
			String serial = jwcdRequest.getRequest().getSerial();
			if (jwcdRequest.getRequest().getCurrentInternalStatus() == null) {
				if (!requiredInternalStatusEnum.equals(RequestInternalStatusEnum.RECIEVED)) {
					throw new InvalidRequestStatusException(serial,
							RequestInternalStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode()));
				} else {
					return;
				}
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

	public static void validateForUpdate(CapacityDelegation jwcdRequest) throws JudicialWarrantException {
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
