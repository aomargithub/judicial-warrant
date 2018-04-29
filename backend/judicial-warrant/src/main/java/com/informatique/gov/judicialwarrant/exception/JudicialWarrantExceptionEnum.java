package com.informatique.gov.judicialwarrant.exception;


import lombok.Getter;

public enum JudicialWarrantExceptionEnum {


  INTERNAL_EXCEPTION("INTERNAL_EXCEPTION", "", ""),
  RESOURCE_NOT_MODIFIED_EXCEPTION("RESOURCE_NOT_MODIFIED_EXCEPTION", "", ""),
  RESOURCE_MODIFIED_EXCEPTION("RESOURCE_MODIFIED_EXCEPTION", "", ""),
  RESOURCE_NOT_FOUND_EXCEPTION("RESOURCE_NOT_FOUND_EXCEPTION", "", ""),
  PRE_CONDITION_REQUIRED("PRE_CONDITION_REQUIRED", "", ""),
  
  // Validation for custom Validation and validation annotation
  // TODO edit in exception description
  EXCEPTION_IN_VALIDATION("EXCEPTION_IN_VALIDATION", "Internal exception occured while validating", "check logs for the exception"),
  NOTBLANK("NotBlank", "Target value is Blank", "check logs for the exception"),
  NOTNULL("NotNull", "Target value is Null", "check logs for the exception"),
  MIN("Min", "Target value exceeds Min", "check logs for the exception"),
  MAX("Max", "Target value exceeds Max", "check logs for the exception"),
  NOTEMPTY("NotEmpty", "Target value is Empty", "check logs for the exception"),
  SIZE("Size", "Target value is size", "check logs for the exception"),
  EMAIL("Email", "Target value is email", "check logs for the exception"),
  DATE_PAST("Past", "Target value is Past", "check logs for the exception"),
  DATE_PASTORPRESENT("PastOrPresent", "Target value is Past Or present", "check logs for the exception"),
  DATE_FUTURE("Future", "Target value is Future", "check logs for the exception"),
  DATE_FUTUREORPRESENT("FutureOrPresent", "Target value is Future or present", "check logs for the exception"),
  ASSERT_FALSE("AssertFalse", "Target value is Assert False", "check logs for the exception"),
  ASSERT_TRUE("AssertTrue", "Target value is Assert True", "check logs for the exception"),
  DECIMAL_MAX("DecimalMax", "Target value is Decimal Max", "check logs for the exception"),
  DECIMAL_MIN("DecimalMin", "Target value is Decimal Min", "check logs for the exception"),
  POSITIVEORZERO("PositiveOrZero", "Target value is PositiveOrZero", "check logs for the exception"),
  NEGATIVEORZERO("NegativeOrZero", "Target value is PositiveOrZero", "check logs for the exception"),
  POSITIVE("Positive", "Target value is Positive", "check logs for the exception"),
  NEGATIVE("Negative", "Target value is Negative", "check logs for the exception"),
  PATTERN("Pattern", "Target value is Pattern", "check logs for the exception"),
  NULL("Null", "Target value is null", "check logs for the exception");

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
