package com.informatique.gov.judicialwarrant.exception;

import org.apache.commons.lang3.exception.ExceptionUtils;

public class JudicialWarrantInternalException extends JudicialWarrantException{


    private static final long serialVersionUID = -1062090233278945383L;


    public JudicialWarrantInternalException(Throwable throwable) {
        super(JudicialWarrantExceptionEnum.INTERNAL_EXCEPTION.getCode(), JudicialWarrantExceptionEnum.INTERNAL_EXCEPTION.getDescription(), JudicialWarrantExceptionEnum.INTERNAL_EXCEPTION.getFixSuggestion(), throwable);
    }


    public String toString() {

        //TODO : need to be revisited, causes StackOverFlowError on some cases
        return new StringBuilder()
                .append("ErrorId : ").append(getErrorId()).append("-->>\n\r")
                .append("ErrorCode : ").append(getCode()).append("-->>\n\r")
                .append("CorrespondenceId : ").append(getRequestId()).append("-->>\n\r")
                .append("UserDomainName : ").append(getUserDomainName()).append("-->>\n\r")
                .append("StackTrace : ").append(ExceptionUtils.getStackTrace(this.getCause()))
                .toString();

    }


}
