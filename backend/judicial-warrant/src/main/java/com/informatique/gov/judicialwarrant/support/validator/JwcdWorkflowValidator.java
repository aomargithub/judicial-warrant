package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.exception.InvalidWorkFlowStatusException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class JwcdWorkflowValidator {

	public static void validate(JwcdRequest jwcdRequest, RequestInternalStatusEnum internalStatusEnum) throws InvalidWorkFlowStatusException {
		RequestInternalStatusEnum currentInternalStatus = RequestInternalStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentInternalStatus().getCode());
		RequestInternalStatusEnum requiredInternalStatus = RequestInternalStatusEnum.getByCode(internalStatusEnum.getCode());
		RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
		switch (requiredInternalStatus) {
		case RECIEVED:
			if(!currentStatus.equals(RequestStatusEnum.DRAFT) && !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
				throw new InvalidWorkFlowStatusException("", "");
			} 
		case INCOMPLETE:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidWorkFlowStatusException("", "");
			} 
		case REJECTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidWorkFlowStatusException("", "");
			} 
		case INPROGRESS:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidWorkFlowStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_REVIEW:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.INPROGRESS)) {
				throw new InvalidWorkFlowStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_ACCEPTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidWorkFlowStatusException("", "");
			}
		case JWCD_LAW_AFFAIRS_REJECTED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidWorkFlowStatusException("", "");
			}
		case ISSUED:
			if(!currentInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED)) {
				throw new InvalidWorkFlowStatusException("", "");
			}
		default:
			break;
		}
	}
	
	public static void validateForUpdate(JwcdRequest jwcdRequest) throws InvalidWorkFlowStatusException {
		RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
		if(!currentStatus.equals(RequestStatusEnum.DRAFT) || !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
			throw new InvalidWorkFlowStatusException("", "");
		} else {
		}
	}
}
