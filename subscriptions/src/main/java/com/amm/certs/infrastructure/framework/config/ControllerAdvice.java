package com.amm.certs.infrastructure.framework.config;

import com.amm.certs.domain.certification.exception.CertificationNotFound;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpError;
import com.amm.certs.infrastructure.framework.common.http.dto.HttpResponse;
import java.util.List;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.BindingResult;
import org.springframework.validation.FieldError;
import org.springframework.web.HttpMediaTypeNotSupportedException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.MissingServletRequestParameterException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.client.HttpServerErrorException;

@RestControllerAdvice
class ControllerAdvice {
    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponse> handleDomainException(MissingServletRequestParameterException ex) {
        return new ResponseEntity(
                HttpResponse.ofError(new HttpError(HttpStatus.BAD_REQUEST.value(), ex.getMessage())),
                HttpStatus.BAD_REQUEST
        );
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.NOT_FOUND)
    public ResponseEntity<HttpResponse> handleDomainException(CertificationNotFound ex) {
        return new ResponseEntity(
                HttpResponse.ofError(new HttpError(HttpStatus.NOT_FOUND.value(), ex.getMessage())),
                HttpStatus.NOT_FOUND
        );
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.UNSUPPORTED_MEDIA_TYPE)
    public ResponseEntity<HttpResponse> handleDomainException(HttpMediaTypeNotSupportedException ex) {
        return new ResponseEntity(
                HttpResponse.ofError(new HttpError(HttpStatus.UNSUPPORTED_MEDIA_TYPE.value(), ex.getMessage())),
                HttpStatus.UNSUPPORTED_MEDIA_TYPE
        );
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.INTERNAL_SERVER_ERROR)
    public ResponseEntity<HttpResponse> handleDomainException(HttpServerErrorException.InternalServerError ex) {
        return new ResponseEntity(
                HttpResponse.ofError(new HttpError(HttpStatus.INTERNAL_SERVER_ERROR.value(), ex.getMessage())),
                HttpStatus.INTERNAL_SERVER_ERROR
        );
    }

    @ExceptionHandler()
    @ResponseStatus(HttpStatus.BAD_REQUEST)
    public ResponseEntity<HttpResponse> handleDomainException(MethodArgumentNotValidException ex) {
        BindingResult bindingResult = ex.getBindingResult();
        List<FieldError> fieldErrors = bindingResult.getFieldErrors();

        StringBuilder errorMessage = new StringBuilder();
        for (FieldError fieldError : fieldErrors) {
            errorMessage.append(fieldError.getField())
                    .append(": ")
                    .append(fieldError.getDefaultMessage())
                    .append("; ");
        }
        return new ResponseEntity(
                HttpResponse.ofError(new HttpError(HttpStatus.BAD_REQUEST.value(), errorMessage.toString())),
                HttpStatus.BAD_REQUEST
        );
    }
}

