package com.informatique.gov.judicialwarrant.support.advice;


import java.io.Serializable;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.ResponseEntity.BodyBuilder;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.exception.JudicialWarrantExceptionEnum;
import com.informatique.gov.judicialwarrant.exception.ResourceModifiedException;
import com.informatique.gov.judicialwarrant.exception.ResourceNotModifiedException;
import com.informatique.gov.judicialwarrant.exception.JudicialwarrantError;
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


    @ExceptionHandler(MethodArgumentNotValidException.class)
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody ResponseEntity<List<JudicialwarrantError>> handleValidationExcption(MethodArgumentNotValidException exception) {

        BindingResult bindingResult = exception.getBindingResult();
        ResponseEntity<List<JudicialwarrantError>> response = null;
        List<JudicialwarrantError> exceptionErrors = null;
        Object errorArgument = null;
        JudicialWarrantExceptionEnum judicialWarrantExceptionEnum = null;
        JudicialwarrantError judicialwarrantError = null;

        if (bindingResult.hasErrors()) {

            List<FieldError> fieldErrors = bindingResult.getFieldErrors();
            List<ObjectError> objectErrors = bindingResult.getGlobalErrors();
            exceptionErrors = new ArrayList<JudicialwarrantError>();


            for (FieldError fieldError : fieldErrors) {

            	judicialWarrantExceptionEnum = JudicialWarrantExceptionEnum.getByCode(fieldError.getCode());

                errorArgument = fieldError.getArguments() != null && fieldError.getArguments().length > 0
                        ? fieldError.getArguments()[0] : null;

                if(judicialWarrantExceptionEnum.equals(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION)){
                	JudicialWarrantException judicialWarrantException = (JudicialWarrantException) errorArgument;
                	judicialWarrantException = errorLogService.log(judicialWarrantException);
                    logger.error(errorArgument);
                }


                judicialwarrantError = new JudicialwarrantError(fieldError.getRejectedValue(), judicialWarrantExceptionEnum, errorArgument);
                exceptionErrors.add(judicialwarrantError);

            }

            for(ObjectError objectError : objectErrors){
            	judicialWarrantExceptionEnum = JudicialWarrantExceptionEnum.getByCode(objectError.getCode());

                errorArgument = objectError.getArguments() != null && objectError.getArguments().length > 0
                        ? objectError.getArguments()[0] : null;

                if(judicialWarrantExceptionEnum.equals(JudicialWarrantExceptionEnum.EXCEPTION_IN_VALIDATION)){
                	JudicialWarrantException judicialWarrantException = (JudicialWarrantException) errorArgument;
                	judicialWarrantException = errorLogService.log(judicialWarrantException);
                    logger.error(errorArgument);
                }

                judicialwarrantError = new JudicialwarrantError(objectError.getObjectName(), judicialWarrantExceptionEnum, errorArgument);
                exceptionErrors.add(judicialwarrantError);
            }
        }
        response = ResponseEntity.ok(exceptionErrors);
        return response;
    }
    

    @ExceptionHandler(JudicialWarrantException.class)
    public ResponseEntity<?> handleSakException(JudicialWarrantException judicialWarrantException) {
    	
    	BodyBuilder bodyBuilder = null;
    	
    	String exceptionClassName = judicialWarrantException.getClass().getSimpleName();
    	
    	ExceptionClassNameEnum exceptionClassNameEnum = ExceptionClassNameEnum.valueOf(exceptionClassName);
    	
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
