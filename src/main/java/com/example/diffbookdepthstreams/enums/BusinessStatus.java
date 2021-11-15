package com.example.diffbookdepthstreams.enums;

import org.springframework.http.HttpStatus;

public enum BusinessStatus {
    DEFAULT_SUCCESS(0, "Successfully processed!", BusinessStatusType.SUCCESS),
    INCORRECT_MILLISECOND(-2, "Input millisecond is incorrect, millisecond must be one of 100, 250 or 500. Send in path variable", BusinessStatusType.BAD_REQUEST),
    INTERNAL_ERROR(-1, "Error, please try again!", BusinessStatusType.ERROR);

    private final Integer statusId;
    private final String message;
    private final BusinessStatusType businessStatusType;

    BusinessStatus(Integer statusId, String message, BusinessStatusType businessStatusType) {
        this.statusId = statusId;
        this.message = message;
        this.businessStatusType = businessStatusType;
    }

    public Integer getStatusId() {
        return statusId;
    }

    public String getMessage() {
        return message;
    }

    public BusinessStatusType getBusinessStatusType() {
        return businessStatusType;
    }

    public enum BusinessStatusType {
        BAD_REQUEST("BAD REQUEST", HttpStatus.BAD_REQUEST),
        ERROR("INTERNAL ERROR", HttpStatus.INTERNAL_SERVER_ERROR),
        NOT_FOUND("NOT FOUND", HttpStatus.NOT_FOUND),
        SUCCESS("SUCCESS", HttpStatus.OK);

        private final String statusType;
        private final HttpStatus httpStatus;

        BusinessStatusType(String statusType, HttpStatus httpStatus) {
            this.statusType = statusType;
            this.httpStatus = httpStatus;
        }

        public String getStatusType() {
            return statusType;
        }

        public HttpStatus getHttpStatus() {
            return httpStatus;
        }
    }
}
