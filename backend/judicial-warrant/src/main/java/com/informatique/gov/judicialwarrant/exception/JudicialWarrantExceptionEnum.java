package com.informatique.gov.judicialwarrant.exception;


import lombok.Getter;

public enum JudicialWarrantExceptionEnum {


  INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "", ""),
  RESOURCE_NOT_MODIFIED_EXCEPTION("RESOURCE_NOT_MODIFIED_EXCEPTION", "", ""),
  RESOURCE_MODIFIED_EXCEPTION("RESOURCE_MODIFIED_EXCEPTION", "The resource you are trying to update is already updated in another context", "refetch the data"),
  SINGLE_RESOURCE_MODIFIED_EXCEPTION("SINGLE_RESOURCE_MODIFIED_EXCEPTION", "The resource you are trying to update: %s with id : %s is already updated in another context real version is %s but expected is %s", "refetch the data"),
  RESOURCE_NOT_FOUND_EXCEPTION("RESOURCE_NOT_FOUND_EXCEPTION", "The resource you are trying to update is not found", "double check data provided"),
  SINGLE_RESOURCE_NOT_FOUND_EXCEPTION("SINGLE_RESOURCE_NOT_FOUND_EXCEPTION", "The resource you are trying to update :%s with id %s is not found", "double check data provided"),
  PRE_CONDITION_REQUIRED("PRE_CONDITION_REQUIRED", "The etag header must have a the value of version of the targeted resource", "double check data provided"),
  SINGLE_RESOURCE_VERSION_NOT_PROVIDED_EXCEPTION("SINGLE_RESOURCE_VERSION_NOT_PROVIDED_EXCEPTION", "The version of %s with id %s must be provided", "double check data provided"),
  INVALID_REQUEST_STATUS_EXCEPTION("INVALID_REQUEST_STATUS_EXCEPTION", "The target status : %s is invalid, request serial : %s", "double check data provided"),
  
  // Validation for custom Validation and validation annotation
  // TODO edit in exception description
  EXCEPTION_IN_VALIDATION("EXCEPTION_IN_VALIDATION", "Internal exception occured while validating", "check logs for the exception"),
  NOTBLANK("NotBlank", "Target value is Blank", "check logs for the exception"),
  NOTNULL("NotNull", "Target value is Null", "check logs for the exception"),
  MIN("Min", "Target value exceeds Min", "check logs for the exception"),
  MAX("Max", "Target value exceeds Max", "check logs for the exception"),
  NOTEMPTY("NotEmpty", "Target value is Empty", "check logs for the exception"),
  SIZE("Size", "Target value is exceeds size", "check logs for the exception"),
  EMAIL("Email", "Target value is not match email pattern", "check logs for the exception"),
  DATE_PAST("Past", "Target value is not Past Date", "check logs for the exception"),
  DATE_PASTORPRESENT("PastOrPresent", "Target value is not Past Or present Date", "check logs for the exception"),
  DATE_FUTURE("Future", "Target value is not Future Date", "check logs for the exception"),
  DATE_FUTUREORPRESENT("FutureOrPresent", "Target value is not Future or present Date", "check logs for the exception"),
  ASSERT_FALSE("AssertFalse", "Target value is not Assert False", "check logs for the exception"),
  ASSERT_TRUE("AssertTrue", "Target value is Assert True", "check logs for the exception"),
  DECIMAL_MAX("DecimalMax", "Target value is exceeds Decimal Max", "check logs for the exception"),
  DECIMAL_MIN("DecimalMin", "Target value is exceeds Decimal Min", "check logs for the exception"),
  POSITIVEORZERO("PositiveOrZero", "Target value is not Positive Or Zero", "check logs for the exception"),
  NEGATIVEORZERO("NegativeOrZero", "Target value is not Positive Or Zero", "check logs for the exception"),
  POSITIVE("Positive", "Target value is not Positive", "check logs for the exception"),
  NEGATIVE("Negative", "Target value is not Negative", "check logs for the exception"),
  PATTERN("Pattern", "Target value is not match Pattern", "check logs for the exception"),
  NULL("Null", "Target value is not null", "check logs for the exception"),
  
  
  
  
  
  //============user errors
  	USER_NULL ("USER_NULL", "Target value is null", "Double check data"),
  	USER_EMAIL_ADDRESS_ALEARDY_EXISTS ("USER_EMAIL_ADDRESS_ALEARDY_EXISTS", "Target value is aleardy exists", "Double check data"),
    USER_CIVIL_ID_EXIST(" USER_CIVIL_ID_EXIST", "Target value is aleardy exists", "Double check data"),
  	USER_ORGANIZATION_UNIT_ID_NULL("USER_ORGANIZATION_UNIT_ID_NULL", "Target value is null", "Double check data"),
  	USER_ROLE_ID_NULL("USER_ROLE_ID_NULL", "Target value is null", "Double check data"),
    USER_LOGIN_NAME_EXISTS("USER_LOGIN_NAME_EXISTS", "Target value is aleardy exists", "Double check data"),
    USER_LOGIN_NAME_NOT_FOUND_LDAP("USER_LOGIN_NAME_NOT_FOUND_LDAP", "Target value is not exists ldap", "Double check data"),
	MOBILE_NUMBER1_EXIST("MOBILE_NUMBER1_EXIST", "Target value is aleardy exists", "Double check data");
	//==========end of user errors

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

    public static JudicialWarrantExceptionEnum getByCode(String code) {
		for (JudicialWarrantExceptionEnum value : JudicialWarrantExceptionEnum.values()) {
			if (value.getCode().equals(code)) {
				return value;
			}
		}
		throw new IllegalArgumentException("Unknown errorCode : " + code);
	}

    
}
