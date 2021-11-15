package com.example.diffbookdepthstreams.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Table;

@Entity
@Table(name = "error_log")
public class ErrorEntity extends BasedEntity{

    @Column(name = "error_text")
    private String errorText;
    @Column(name = "error_type")
    private String errorType;
    @Column(name = "body")
    private String body;

    public String getErrorText() {
        return errorText;
    }

    public ErrorEntity setErrorText(String errorText) {
        this.errorText = errorText;
        return this;
    }

    public String getErrorType() {
        return errorType;
    }

    public ErrorEntity setErrorType(String errorType) {
        this.errorType = errorType;
        return this;
    }

    public String getBody() {
        return body;
    }

    public ErrorEntity setBody(String body) {
        this.body = body;
        return this;
    }
}
