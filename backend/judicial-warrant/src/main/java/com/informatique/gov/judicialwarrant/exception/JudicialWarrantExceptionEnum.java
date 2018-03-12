package com.informatique.gov.judicialwarrant.exception;

import lombok.Getter;

public enum JudicialWarrantExceptionEnum {


  INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "", "");

    @Getter
    private String code;
    @Getter
    private String description;
    @Getter
    private String fixSuggestion;

    private JudicialWarrantExceptionEnum(String code, String description, String fixSuggestion){
        this.code = code;
        this.description = description;
        this.fixSuggestion = fixSuggestion;
    }

}
