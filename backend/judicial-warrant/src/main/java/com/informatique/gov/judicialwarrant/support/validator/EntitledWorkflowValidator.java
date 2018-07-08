package com.informatique.gov.judicialwarrant.support.validator;

import java.io.Serializable;

import org.springframework.stereotype.Component;

import com.informatique.gov.judicialwarrant.domain.Entitled;
import com.informatique.gov.judicialwarrant.domain.EntitledRegistration;
import com.informatique.gov.judicialwarrant.domain.RequestInternalStatus;
import com.informatique.gov.judicialwarrant.exception.InvalidEntitledStatusException;
import com.informatique.gov.judicialwarrant.exception.InvalidRequestStatusException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.persistence.repository.EntitledRegistrationRepository;
import com.informatique.gov.judicialwarrant.support.dataenum.EntitledStatusEnum;
import com.informatique.gov.judicialwarrant.support.dataenum.RequestInternalStatusEnum;

import lombok.AllArgsConstructor;

@Component
@AllArgsConstructor
public class EntitledWorkflowValidator implements Serializable {

	private static final long serialVersionUID = -3293773899770316425L;
	
	private EntitledRegistrationRepository entitledRegistrationRepository;

	public void validate(Entitled entitled, EntitledStatusEnum requiredEntitledStatusEnum)
			throws JudicialWarrantException {
		try {
			if (entitled.getCurrentStatus() == null) {
				if (requiredEntitledStatusEnum.getCode().equals(EntitledStatusEnum.SUBMITED.getCode())) {
					return;
				} else {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
			}
			EntitledStatusEnum currentEntitledStatusEnum = EntitledStatusEnum
					.getByCode(entitled.getCurrentStatus().getCode());
			switch (currentEntitledStatusEnum) {
			case DRAFT:
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.SUBMITED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
			case INCOMPLETE:
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.SUBMITED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
				
			case INPROGRESS:
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.REJECTED)
						&& !requiredEntitledStatusEnum.equals(EntitledStatusEnum.ACCEPTED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;	
			case SUBMITED:
				checkEntitledRegistrationInProgress(entitled);
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.INCOMPLETE)
						&& !requiredEntitledStatusEnum.equals(EntitledStatusEnum.INPROGRESS)
						&& !requiredEntitledStatusEnum.equals(EntitledStatusEnum.REJECTED)
						&& !requiredEntitledStatusEnum.equals(EntitledStatusEnum.ACCEPTED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
			case REJECTED:
				throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
			case ACCEPTED:
				checkEntitledRegistrationInProgress(entitled);
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.TRAINNING)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
			case TRAINNING:
				checkEntitledRegistrationInProgress(entitled);
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.PASSED)
						&& !requiredEntitledStatusEnum.equals(EntitledStatusEnum.FAILED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
			case PASSED:
				checkEntitledRegistrationInProgress(entitled);
				if (!requiredEntitledStatusEnum.equals(EntitledStatusEnum.CARD_RECIEVED)) {
					throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
				}
				break;
			case FAILED:
				throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
			case CARD_RECIEVED:
				throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);

			default:
				throw new InvalidEntitledStatusException(entitled.getId(), requiredEntitledStatusEnum);
			}
		} catch (JudicialWarrantException e) {
			throw e;
		} catch (Exception e) {
			throw new JudicialWarrantInternalException(e);
		}
	}

	public void checkEntitledRegistrationInProgress(Entitled entitled) throws JudicialWarrantException {
		EntitledRegistration entitledRegistration = entitledRegistrationRepository.findByRequestSerial(entitled.getEntitledRegistration().getRequest().getSerial());
		RequestInternalStatus requestInternalStatus = entitledRegistration.getRequest().getCurrentInternalStatus();
		if (!requestInternalStatus.getCode().equals(RequestInternalStatusEnum.INPROGRESS.getCode()) 
				&& !requestInternalStatus.getCode().equals(RequestInternalStatusEnum.INCOMPLETE.getCode())
				&& !requestInternalStatus.getCode().equals(RequestInternalStatusEnum.REJECTED.getCode())) {
			throw new InvalidRequestStatusException(entitled.getEntitledRegistration().getRequest().getSerial(),
					RequestInternalStatusEnum.getByCode(requestInternalStatus.getCode()));
		}
	}

}
