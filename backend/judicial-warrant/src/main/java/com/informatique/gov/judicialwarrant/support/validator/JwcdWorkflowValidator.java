package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class JwcdWorkflowValidator {

	public static void validate(JwcdRequest jwcdRequest, RequestInternalStatusEnum internalStatusEnum) throws InvalidRequestStatusException {
		RequestInternalStatusEnum currentInternalStatus = RequestInternalStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentInternalStatus().getCode());
		RequestInternalStatusEnum requiredInternalStatus = RequestInternalStatusEnum.getByCode(internalStatusEnum.getCode());
		RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
		switch (requiredInternalStatus) {
		case RECIEVED:
			if(!currentStatus.equals(RequestStatusEnum.DRAFT) && !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
				throw new InvalidRequestStatusException("", "");
			} 
		case INCOMPLETE:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException("", "");
			} 
		case REJECTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException("", "");
			} 
		case INPROGRESS:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_REVIEW:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.INPROGRESS)) {
				throw new InvalidRequestStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_ACCEPTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidRequestStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_REJECTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidRequestStatusException("", "");
			}
		case ISSUED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED)) {
				throw new InvalidRequestStatusException("", "");
			}
		default:
			break;
		}
	}
	
	public static void validateForUpdate(JwcdRequest jwcdRequest) throws InvalidRequestStatusException {
		RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
		if(!currentStatus.equals(RequestStatusEnum.DRAFT) || !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
			throw new InvalidRequestStatusException("", "");
		} else {
		}
	}
}
