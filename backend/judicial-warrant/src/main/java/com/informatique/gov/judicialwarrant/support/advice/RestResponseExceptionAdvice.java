package com.informatique.gov.judicialwarrant.support.advice;


import java.io.Serializable;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantInternalException;
import com.informatique.gov.judicialwarrant.exception.PreConditionRequiredException;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotFoundException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.service.InternalErrorLogService;
import com.informatique.gov.judicialwarrant.support.dataenum.ExceptionClassNameEnum;

import lombok.AllArgsConstructor;



@ControllerAdvice
@AllArgsConstructor
public class RestResponseExceptionAdvice implements Serializable {

    /**
     *
     */
    private static final long serialVersionUID = 2715634386927438203L;
    private final static Logger logger = LogManager.getLogger(RestResponseExceptionAdvice.class);


    private InternalErrorLogService errorLogService;


    /*@ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody RestResponse handleValidationExcption(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        List<SakError> sakErrors = null;
        Object errorArgument = null;
        SakErrorEnum sakErrorEnum = null;
        SakError SakError = null;

        if (bindingResult.hasErrors()) {

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ObjectError> objectErrors = bindingResult.getGlobalErrors();
            sakErrors = new ArrayList<SakError>();


            for (FieldError fieldError : fieldErrors) {

                sakErrorEnum = SakErrorEnum.getByErrorCode(fieldError.getCode());

                errorArgument = fieldError.getArguments() != null && fieldError.getArguments().length > 0
                        ? fieldError.getArguments()[0] : null;

                if(sakErrorEnum.equals(SakErrorEnum.EXCEPTION_IN_VALIDATION)){
                    SakException sakException = (SakException) errorArgument;
                    sakException = errorLogService.log(sakException);
                    logger.error(errorArgument);
                }


                SakError = new SakError(fieldError.getRejectedValue(), sakErrorEnum, errorArgument);
                sakErrors.add(SakError);

            }

            for(ObjectError objectError : objectErrors){
                sakErrorEnum = SakErrorEnum.getByErrorCode(objectError.getCode());

                errorArgument = objectError.getArguments() != null && objectError.getArguments().length > 0
                        ? objectError.getArguments()[0] : null;

                if(sakErrorEnum.equals(SakErrorEnum.EXCEPTION_IN_VALIDATION)){
                    SakException sakException = (SakException) errorArgument;
                    sakException = errorLogService.log(sakException);
                    logger.error(errorArgument);
                }

                SakError = new SakError(objectError.getObjectName(), sakErrorEnum, errorArgument);
                sakErrors.add(SakError);
            }
        }

        return new RestResponse(sakErrors);
    }*/
    

    @ExceptionHandler(JudicialWarrantException.class)
    public ResponseEntity<?> handleSakException(JudicialWarrantException judicialWarrantException) {
    	
    	BodyBuilder bodyBuilder = null;
    	
    	String exceptionClassName = judicialWarrantException.getClass().getSimpleName();
    	
    	ExceptionClassNameEnum exceptionClassNameEnum= ExceptionClassNameEnum.valueOf(exceptionClassName);
    	
    	switch(exceptionClassNameEnum) {
    		case JudicialWarrantInternalException:
    			judicialWarrantException = errorLogService.log(judicialWarrantException);
                logger.error(judicialWarrantException);
            	bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
            	break;
    		
    		case ResourceNotModifiedException:
    			ResourceNotModifiedException entityNotModifiedException = (ResourceNotModifiedException)judicialWarrantException;
        		bodyBuilder = ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(entityNotModifiedException.getRealVersion().toString());
    			break;
    		case ResourceModifiedException:
    			ResourceModifiedException entityModifiedException = (ResourceModifiedException)judicialWarrantException;
        		bodyBuilder = ResponseEntity.status(HttpStatus.PRECONDITION_FAILED).eTag(entityModifiedException.getRealVersion().toString());
    			break;
    		case ResourceNotFoundException:
    			bodyBuilder = ResponseEntity.status(HttpStatus.NOT_FOUND);
    			break;
    		case PreConditionRequiredException:
    			bodyBuilder = ResponseEntity.status(HttpStatus.PRECONDITION_REQUIRED);
    			break;
    		default:
    			bodyBuilder = ResponseEntity.badRequest();
    			break;
    	}
        
        /*if(judicialWarrantException instanceof  JudicialWarrantInternalException) {
        	judicialWarrantException = errorLogService.log(judicialWarrantException);
            logger.error(judicialWarrantException);
        	bodyBuilder = ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR);
        }else {
        	if(judicialWarrantException instanceof EntityNotModifiedException) {
        		EntityNotModifiedException resourceNotModifiedException = (EntityNotModifiedException)judicialWarrantException;
        		bodyBuilder = ResponseEntity.status(HttpStatus.NOT_MODIFIED).eTag(resourceNotModifiedException.getRealVersion().toString());
        	}else {
        		
        		bodyBuilder = ResponseEntity.badRequest();
        	}
        }*/
        
        return bodyBuilder.body(judicialWarrantException);
    }

}
