package com.informatique.gov.judicialwarrant.support.validator;
import static java.lang.annotation.ElementType.*;
import static java.lang.annotation.RetentionPolicy.*;
 
import java.lang.annotation.Documented;
import java.lang.annotation.Retention;
import java.lang.annotation.Target;
 
import javax.validation.Constraint;
import javax.validation.Payload;


@Target({ METHOD, FIELD, ANNOTATION_TYPE })
@Retention(RUNTIME)
@Constraint(validatedBy = CapacityDelegationChangeStatusRequestValid.class)
@Documented
public @interface CapacityDelegationChangeStatusRequestV {
	   String message() default "Attachment does,t match";
	 
	    Class<?>[] groups() default {};
	 
	    Class<? extends Payload>[] payload() default {};
}
