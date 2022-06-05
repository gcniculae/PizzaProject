package com.pizza.entity;

public class ErrorDto {

    private String errorCode;
    private String message;
    private Integer status;

    public ErrorDto() {
    }

    public ErrorDto(String errorCode, String message, Integer status) {
        this.errorCode = errorCode;
        this.message = message;
        this.status = status;
    }

    public String getErrorCode() {
        return errorCode;
    }

    public void setErrorCode(String errorCode) {
        this.errorCode = errorCode;
    }

    public String getMessage() {
        return message;
    }

    public void setMessage(String message) {
        this.message = message;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }
}
