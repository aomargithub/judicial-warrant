package com.informatique.gov.judicialwarrant.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.informatique.gov.judicialwarrant.support.jackson.JudicialWarrantExceptionSerializer;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@Getter
@JsonSerialize(using = JudicialWarrantExceptionSerializer.class)
public class JudicialWarrantException extends Exception implements Serializable {

    private static final long serialVersionUID = 3429261224926462433L;
   
    private final String code;
    private final String description;
    private final String fixSuggestion;
    @Setter
    private Long errorId;
    @Setter
    private String userDomainName;
    @Setter
    private Long requestId;
    
    public JudicialWarrantException(JudicialWarrantExceptionEnum judicialWarrantExceptionEnum) {
        this.code = judicialWarrantExceptionEnum.getCode();
        this.description = judicialWarrantExceptionEnum.getDescription();
        this.fixSuggestion = judicialWarrantExceptionEnum.getFixSuggestion();
    }

    @ConstructorProperties({"code", "description", "fixSuggestion"})
    public JudicialWarrantException(String code, String description, String fixSuggestion) {
        this.code = code;
        this.description = description;
        this.fixSuggestion = fixSuggestion;
    }


    public JudicialWarrantException(String code, String description, String fixSuggestion, Throwable cause) {
        super(cause);
        this.code = code;
        this.description = description;
        this.fixSuggestion = fixSuggestion;
    }

    public String toString() {

        //TODO : need to be revisited, causes StackOverFlowError on some cases
        return new StringBuilder()
                .append("ErrorId : ").append(getErrorId()).append("-->>\n\r")
                .append("ErrorCode : ").append(getCode()).append("-->>\n\r")
                .append("requestId : ").append(getRequestId()).append("-->>\n\r")
                .append("UserDomainName : ").append(getUserDomainName()).append("-->>\n\r")
                .append("Description : ").append(this.getDescription()).toString();
    }
}
