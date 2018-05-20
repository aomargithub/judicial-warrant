package com.informatique.gov.judicialwarrant.support.validator;

import com.informatique.gov.judicialwarrant.domain.ERRequest;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestStatusEnum;

public class ErWorkflowValidator {

	public static void validate(ERRequest erRequest, RequestInternalStatusEnum requiredInternalStatusEnum)
			throws JudicialWarrantException {
		try {
			String serial = erRequest.getRequest().getSerial();
			if (erRequest.getRequest().getCurrentInternalStatus() == null) {
				if (!requiredInternalStatusEnum.equals(RequestInternalStatusEnum.RECIEVED)) {
					throw new InvalidRequestStatusException(serial,
							RequestInternalStatusEnum.getByCode(erRequest.getRequest().getCurrentStatus().getCode()));
				} else {
					return;
				}
			}
			RequestInternalStatusEnum currentInternalStatus = RequestInternalStatusEnum
					.getByCode(erRequest.getRequest().getCurrentInternalStatus().getCode());
			RequestInternalStatusEnum requiredInternalStatus = RequestInternalStatusEnum
					.getByCode(requiredInternalStatusEnum.getCode());
			switch (currentInternalStatus) {
			case RECIEVED:
				if (!requiredInternalStatus.equals(RequestInternalStatusEnum.INCOMPLETE)
						&& !requiredInternalStatus.equals(RequestInternalStatusEnum.REJECTED)
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
				if (!requiredInternalStatus.equals(RequestInternalStatusEnum.REJECTED)
						&& !requiredInternalStatus.equals(RequestInternalStatusEnum.CAPACITY_DELEGATION_ISSUED)
						&& !requiredInternalStatus.equals(RequestInternalStatusEnum.INPROGRESS)) {
					throw new InvalidRequestStatusException(serial, currentInternalStatus);
				}
				break;
			case CAPACITY_DELEGATION_ISSUED:
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			default:
				throw new InvalidRequestStatusException(serial, currentInternalStatus);
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

	public static void validateForUpdate(ERRequest erRequest) throws JudicialWarrantException {
		try {
			RequestStatusEnum currentStatus = RequestStatusEnum
					.getByCode(erRequest.getRequest().getCurrentStatus().getCode());
			if (!currentStatus.equals(RequestStatusEnum.DRAFT) && !currentStatus.equals(RequestStatusEnum.INCOMPLETE)) {
				String serial = erRequest.getRequest().getSerial();
				throw new InvalidRequestStatusException(serial, currentStatus);
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}
}
