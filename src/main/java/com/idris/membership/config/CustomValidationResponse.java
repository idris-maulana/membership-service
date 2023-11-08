package com.idris.membership.config;

import com.idris.membership.common.CommonResponse;
import org.springframework.http.HttpStatus;
import org.springframework.validation.BindException;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;

import java.util.ArrayList;
import java.util.List;

@ControllerAdvice
public class CustomValidationResponse {

    @ExceptionHandler(BindException.class)
    @ResponseStatus(HttpStatus.OK)
    @ResponseBody
    public CommonResponse<?> handleException(BindException ex) {

        List<FieldError> errors = ex.getBindingResult().getFieldErrors();

        String message = errors.get(0).getDefaultMessage();
        String code = message.substring(message.indexOf("[") + 1, message.indexOf("]"));
        message = message.replace("["+code+"]", "");

        return new CommonResponse<>(Integer.valueOf(code), message.trim(), null);
    }
}
