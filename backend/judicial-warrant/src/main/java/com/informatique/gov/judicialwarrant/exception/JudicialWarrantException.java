package com.informatique.gov.judicialwarrant.exception;

import com.fasterxml.jackson.databind.annotation.JsonSerialize;
import com.informatique.gov.judicialwarrant.support.jackson.JudicialWarrantExceptionSerializer;
import lombok.Getter;
import lombok.Setter;

import java.beans.ConstructorProperties;
import java.io.Serializable;

@JsonSerialize(using = JudicialWarrantExceptionSerializer.class)
public class JudicialWarrantException extends Exception implements Serializable {

    private static final long serialVersionUID = 3429261224926462433L;
    @Getter
    private final String code;
    @Getter
    private final String description;
    @Getter
    private final String fixSuggestion;
    @Getter
    @Setter
    private Long errorId;
    @Getter
    @Setter
    private String userDomainName;
    @Getter
    @Setter
    private Long requestId;

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
