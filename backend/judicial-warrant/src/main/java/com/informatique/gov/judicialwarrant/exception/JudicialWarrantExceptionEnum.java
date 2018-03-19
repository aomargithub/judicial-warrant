package com.informatique.gov.judicialwarrant.exception;

import lombok.Getter;

public enum JudicialWarrantExceptionEnum {


  INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "", ""),
  RESOURCE_NOT_MODIFIED_EXCEPTION("RESOURCE_NOT_MODIFIED_EXCEPTION", "", ""),
  RESOURCE_MODIFIED_EXCEPTION("RESOURCE_MODIFIED_EXCEPTION", "", ""),
  RESOURCE_NOT_FOUND_EXCEPTION("RESOURCE_NOT_FOUND_EXCEPTION", "", ""),
  PRE_CONDITION_REQUIRED("PRE_CONDITION_REQUIRED", "", "");

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
