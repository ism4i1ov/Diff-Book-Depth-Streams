package com.example.diffbookdepthstreams.controller;

import com.example.diffbookdepthstreams.enums.BusinessStatus;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;

import java.io.Serializable;

@JsonIgnoreProperties(ignoreUnknown = true)
public class CommonResponse<T> implements Serializable {
    private Integer statusId;
    private String systemMessage;
    private String message;
    private Integer statusCode;
    private String statusType;
    private T body;

    public static <T> CommonResponse<T> response(T body, BusinessStatus businessStatus, String systemMessage) {
        return new CommonResponse<T>()
                .setBody(body)
                .setStatusId(businessStatus.getStatusId())
                .setSystemMessage(systemMessage)
                .setMessage(businessStatus.getMessage())
                .setStatusCode(businessStatus.getBusinessStatusType().getHttpStatus().value())
                .setStatusType(businessStatus.getBusinessStatusType().getStatusType());
    }

    public Integer getStatusId() {
        return statusId;
    }

    public CommonResponse<T> setStatusId(Integer statusId) {
        this.statusId = statusId;
        return this;
    }

    public String getSystemMessage() {
        return systemMessage;
    }

    public CommonResponse<T> setSystemMessage(String systemMessage) {
        this.systemMessage = systemMessage;
        return this;
    }

    public String getMessage() {
        return message;
    }

    public CommonResponse<T> setMessage(String message) {
        this.message = message;
        return this;
    }

    public Integer getStatusCode() {
        return statusCode;
    }

    public CommonResponse<T> setStatusCode(Integer statusCode) {
        this.statusCode = statusCode;
        return this;
    }

    public String getStatusType() {
        return statusType;
    }

    public CommonResponse<T> setStatusType(String statusType) {
        this.statusType = statusType;
        return this;
    }

    public T getBody() {
        return body;
    }

    public CommonResponse<T> setBody(T body) {
        this.body = body;
        return this;
    }
}
