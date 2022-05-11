package com.stack.stack_over.Handlers;

import com.stack.stack_over.Exception.ErrorCodes;
import lombok.Builder;

import java.util.ArrayList;
import java.util.List;

@Builder
public class ErrorDto {

    private Integer httpId;
    private ErrorCodes errorCode;
    private String message;
    private List<String> errors =new ArrayList<>();

    public ErrorDto() {
    }

    public ErrorDto(Integer httpId, ErrorCodes errorCode, String message, List<String> errors) {
        this.httpId = httpId;
        this.errorCode = errorCode;
        this.message = message;
        this.errors = errors;
    }

    public Integer getHttpId() {
        return httpId;
    }

    public void setHttpId(Integer httpId) {
        this.httpId = httpId;
    }

    public ErrorCodes getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(ErrorCodes errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public List<String> getErrors() {
        return errors;
    }

    public void setErrors(List<String> errors) {
        this.errors = errors;
    }
}
