package com.informatique.gov.judicialwarrant.rest.response;

import java.io.Serializable;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;


public class RestResponse implements Serializable{

	/**
	 * 
	 */
	private static final long serialVersionUID = 1986401251568932473L;
	
	public enum RestResponseStatusEnum{
		OK("OK"), ERROR("ERROR");
		
		private String statusStringValue;
		
		private RestResponseStatusEnum(String statusStringValue){
			this.statusStringValue = statusStringValue;
		}
		
		public String toString(){
			return statusStringValue;
		}
	}
	
	
	private final RestResponseStatusEnum status;
	
	private final Object body;
	
	
	public RestResponse(RestResponseStatusEnum status, Object body){
		this.status = status;
		this.body = body;
	}
	
	public RestResponse(Object body){
		this.status = RestResponseStatusEnum.OK;
		this.body = body;
	}
	
	public RestResponse(JudicialWarrantException ketabException){
		this.status = RestResponseStatusEnum.ERROR;
		this.body = ketabException;
	}


	public RestResponseStatusEnum getStatus() {
		return status;
	}


	public Object getBody() {
		return body ;
	}

}
