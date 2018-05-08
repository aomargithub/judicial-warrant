package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class JwcdWorkflowValidator {

	public static void validate(JwcdRequest jwcdRequest, RequestInternalStatusEnum requiredInternalStatusEnum)
			throws  InvalidRequestStatusException , JudicialWarrantInternalException{
		try {
			String serial = jwcdRequest.getRequest().getSerial();
		if (jwcdRequest.getRequest().getCurrentInternalStatus() == null) {
			if (!requiredInternalStatusEnum.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException(serial, RequestInternalStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode()));
			}
			else {
				return;
			}
		}
		RequestInternalStatusEnum currentInternalStatus = RequestInternalStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentInternalStatus().getCode());
		RequestInternalStatusEnum requiredInternalStatus = RequestInternalStatusEnum.getByCode(requiredInternalStatusEnum.getCode());
		switch (currentInternalStatus) {
		case RECIEVED:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.INCOMPLETE) && !requiredInternalStatus.equals(RequestInternalStatusEnum.REJECTED)
					&& !requiredInternalStatus.equals(RequestInternalStatusEnum.INPROGRESS)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case INCOMPLETE:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case REJECTED:
			throw new InvalidRequestStatusException(serial, currentInternalStatus);
		case INPROGRESS:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case JWCD_LAW_AFFAIRS_REVIEW:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED)
					&& !requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case JWCD_LAW_AFFAIRS_ACCEPTED:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.ISSUED)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case JWCD_LAW_AFFAIRS_REJECTED:
			// TODO are here can return again to JWCD_LAW_AFFAIRS_REVIEW?
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.REJECTED)) {
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
			break;
		case ISSUED:
			throw new InvalidRequestStatusException(serial, currentInternalStatus);
		default:
			break;
		}
		} 
		catch (JudicialWarrantException e) {
			throw e;
		}
	}

	public static void validateForUpdate(JwcdRequest jwcdRequest) throws InvalidRequestStatusException, JudicialWarrantInternalException {
		try {
			RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
			if (!currentStatus.equals(RequestStatusEnum.DRAFT) || !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
				String serial = jwcdRequest.getRequest().getSerial();
				throw new InvalidRequestStatusException(serial, currentStatus);
			}
		} 
		catch (JudicialWarrantException e) {
			throw e;
		}
	}
}
