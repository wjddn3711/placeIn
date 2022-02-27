package com.lee.placein.controller.error;

import com.lee.placein.constant.ErrorCode;
import com.lee.placein.dto.ApiErrorResponse;
import com.lee.placein.exception.GeneralException;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.servlet.ModelAndView;

import javax.servlet.http.HttpServletResponse;
import java.util.Map;

@ControllerAdvice
public class BaseExceptionHandler {

    @ExceptionHandler
    public ModelAndView general(GeneralException e, HttpServletResponse response){
        ErrorCode errorCode = e.getErrorCode();
        HttpStatus status = errorCode.isClientSideError() ?
                HttpStatus.BAD_REQUEST :
                HttpStatus.INTERNAL_SERVER_ERROR;

        return new ModelAndView(("error"),
                Map.of(
                        "statusCode",status.value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage(e)
                ),
                status);
    }

    @ExceptionHandler
    public ModelAndView exception(Exception e){
        ErrorCode errorCode = ErrorCode.INTERNAL_ERROR;
        HttpStatus status = HttpStatus.INTERNAL_SERVER_ERROR;

        return new ModelAndView(("error"),
                Map.of(
                        "statusCode",status.value(),
                        "errorCode", errorCode,
                        "message", errorCode.getMessage(e)
                ),
                status);
    }

}
