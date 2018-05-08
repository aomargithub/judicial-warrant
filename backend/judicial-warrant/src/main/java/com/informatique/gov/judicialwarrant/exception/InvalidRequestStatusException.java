package com.informatique.gov.judicialwarrant.exception;


public class InvalidRequestStatusException extends JudicialWarrantException{


    private static final long serialVersionUID = -1062090233278945383L;

    public InvalidRequestStatusException(String description, String fixSuggestion) {
        super(JudicialWarrantExceptionEnum.INVALID_REQUEST_STATUS_EXCEPTION.getCode(), description, fixSuggestion);
    }


}
