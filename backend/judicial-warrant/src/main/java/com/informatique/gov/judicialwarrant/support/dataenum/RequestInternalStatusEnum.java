package com.informatique.gov.judicialwarrant.support.dataenum;

import lombok.Getter;

public enum RequestInternalStatusEnum {
	
	RECIEVED("RECIEVED", RequestStatusEnum.SUBMITED),
	INCOMPLETE("INCOMPLETE", RequestStatusEnum.INCOMPLETE),
	REJECTED("REJECTED", RequestStatusEnum.REJECTED),
	INPROGRESS("INPROGRESS", RequestStatusEnum.INPROGRESS),
	JWCD_LAW_AFFAIRS_REVIEW("JWCD_LAW_AFFAIRS_REVIEW", RequestStatusEnum.INPROGRESS),
	JWCD_LAW_AFFAIRS_REJECTED("JWCD_LAW_AFFAIRS_REJECTED", RequestStatusEnum.INPROGRESS),
	JWCD_LAW_AFFAIRS_ACCEPTED("JWCD_LAW_AFFAIRS_ACCEPTED", RequestStatusEnum.INPROGRESS),
	ISSUED("ISSUED", RequestStatusEnum.ISSUED);
	
	
	
	
	@Getter
    private String code;
	
	@Getter
	private RequestStatusEnum requestStatusEnum;

	
	private RequestInternalStatusEnum(String code, RequestStatusEnum requestStatusEnum) {
		this.code = code;
		this.requestStatusEnum = requestStatusEnum;
	}
	
	
	public static RequestInternalStatusEnum getByCode(String code) {
		for(RequestInternalStatusEnum internalStatusEnum : RequestInternalStatusEnum.values()) {
			if(internalStatusEnum.code.equals(code)) {
				return internalStatusEnum;
			}
		}
		return null;
	}
	
	
	
}