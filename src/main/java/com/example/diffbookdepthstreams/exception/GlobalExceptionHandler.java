package com.example.diffbookdepthstreams.exception;

import com.example.diffbookdepthstreams.controller.CommonResponse;
import com.example.diffbookdepthstreams.entity.ErrorEntity;
import com.example.diffbookdepthstreams.service.functional.ErrorFunctionalService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class GlobalExceptionHandler extends ResponseEntityExceptionHandler {

    private final ErrorFunctionalService errorFunctionalService;

    public GlobalExceptionHandler(ErrorFunctionalService errorFunctionalService) {
        this.errorFunctionalService = errorFunctionalService;
    }

    @ExceptionHandler(BusinessException.class)
    public ResponseEntity<CommonResponse<Object>> businessException(BusinessException businessException) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorText(businessException.getBusinessStatus().getMessage());
        errorEntity.setErrorType(businessException.getBusinessStatus().getBusinessStatusType().getStatusType());
        errorEntity.setBody(businessException.getData());
        errorFunctionalService.loggingErrorDb(errorEntity);
        return ResponseEntity.status(businessException.getBusinessStatus().getBusinessStatusType().getHttpStatus())
                .body(CommonResponse.response(null, businessException.getBusinessStatus(), businessException.getSystemMessage()));
    }

    @ExceptionHandler(Exception.class)
    public void unknownException(Exception exception) {
        ErrorEntity errorEntity = new ErrorEntity();
        errorEntity.setErrorText(exception.getMessage());
        errorEntity.setErrorType("Unknown Error");
        errorFunctionalService.loggingErrorDb(errorEntity);
    }
}
