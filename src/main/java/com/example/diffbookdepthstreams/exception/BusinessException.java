package com.example.diffbookdepthstreams.exception;


import com.example.diffbookdepthstreams.enums.BusinessStatus;

public class BusinessException extends RuntimeException {

    private final String systemMessage;
    private final BusinessStatus businessStatus;
    private String data;

    public BusinessException(String systemMessage, BusinessStatus businessStatus) {
        this.systemMessage = systemMessage;
        this.businessStatus = businessStatus;
    }

    public String getData() {
        return data;
    }

    public BusinessException setData(String data) {
        this.data = data;
        return this;
    }

    public BusinessStatus getBusinessStatus() {
        return businessStatus;
    }

    public static void throwException(BusinessStatus businessStatus, String systemMessage) {
        throw new BusinessException(systemMessage, businessStatus);
    }

    public String getSystemMessage() {
        return systemMessage;
    }
}
