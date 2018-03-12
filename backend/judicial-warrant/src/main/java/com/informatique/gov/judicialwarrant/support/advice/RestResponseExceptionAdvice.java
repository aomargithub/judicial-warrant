package com.informatique.gov.judicialwarrant.support.advice;


import lombok.AllArgsConstructor;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import com.informatique.gov.judicialwarrant.exception.JudicialWarrantException;
import com.informatique.gov.judicialwarrant.rest.response.RestResponse;
import com.informatique.gov.judicialwarrant.service.InternalErrorLogService;

import java.io.Serializable;



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
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public @ResponseBody RestResponse handleSakException(JudicialWarrantException ketabException) {
        ketabException = errorLogService.log(ketabException);

        logger.error(ketabException);
        return new RestResponse(ketabException);
    }

}
