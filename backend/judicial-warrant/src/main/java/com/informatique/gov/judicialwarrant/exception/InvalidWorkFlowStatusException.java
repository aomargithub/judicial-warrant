package com.informatique.gov.judicialwarrant.exception;


import lombok.Getter;

public class InvalidWorkFlowStatusException extends JudicialWarrantException{


    private static final long serialVersionUID = -1062090233278945383L;

    @Getter
    private String description;
    
    @Getter
    private String fixSuggestion;

    public InvalidWorkFlowStatusException(String description, String fixSuggestion) {
        super(JudicialWarrantExceptionEnum.INVALID_WORKFLOW_EXCEPTION.getCode(), description, fixSuggestion);
    }


}
