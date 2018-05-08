package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.JwcdRequest;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class JwcdWorkflowValidator {

	public static void validate(JwcdRequest jwcdRequest, RequestInternalStatusEnum requiredInternalStatusEnum)
			throws InvalidRequestStatusException {
		if (jwcdRequest.getRequest().getCurrentInternalStatus() == null) {
			if (!requiredInternalStatusEnum.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException(
						"you can't change status until submit request and become recieve",
						"you can't change status until submit request and become recieve");
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
				throw new InvalidRequestStatusException("you only can change status to Incomplete or rejected or Inprogress",
						"you only can change status to Incomplete or rejected or Inprogress");
			}
			break;
		case INCOMPLETE:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.RECIEVED)) {
				throw new InvalidRequestStatusException("you only can change status to RECIEVED",
						"you only can change status to RECIEVED");
			}
			break;
		case REJECTED:
			throw new InvalidRequestStatusException("You can't do any thing after Reject Request",
					"You can't do any thing after Reject Request");
		case INPROGRESS:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REVIEW)) {
				throw new InvalidRequestStatusException("you can only send request to law affairs review",
						"you can only send request to law affairs review");
			}
			break;
		case JWCD_LAW_AFFAIRS_REVIEW:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_REJECTED)
					&& !requiredInternalStatus.equals(RequestInternalStatusEnum.JWCD_LAW_AFFAIRS_ACCEPTED)) {
				throw new InvalidRequestStatusException("you can only make Request law Affairs accept or reject",
						"you can only make Request law Affairs accept or reject");
			}
			break;
		case JWCD_LAW_AFFAIRS_ACCEPTED:
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.ISSUED)) {
				throw new InvalidRequestStatusException(
						"you can only Issued rhe request as the request is law affairs accept",
						"you can only Issued rhe request as the request is law affairs accept");
			}
			break;
		case JWCD_LAW_AFFAIRS_REJECTED:
			// TODO are here can return again to JWCD_LAW_AFFAIRS_REVIEW?
			if (!requiredInternalStatus.equals(RequestInternalStatusEnum.REJECTED)) {
				throw new InvalidRequestStatusException("you can only reject request as it is law affairs reject",
						"you can only reject request as it is law affairs reject");
			}
			break;
		case ISSUED:
			throw new InvalidRequestStatusException("you can't do any thing after request is issued",
					"you can't do any thing after request is issued");
		default:
			break;
		}
	}

	public static void validateForUpdate(JwcdRequest jwcdRequest) throws InvalidRequestStatusException {
		RequestStatusEnum currentStatus = RequestStatusEnum.getByCode(jwcdRequest.getRequest().getCurrentStatus().getCode());
		if (!currentStatus.equals(RequestStatusEnum.DRAFT) || !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
			throw new InvalidRequestStatusException("you can update request only if is draft or return incomplete",
					"you can update request only if is draft or return incomplete");
		} else {
		}
	}
}
