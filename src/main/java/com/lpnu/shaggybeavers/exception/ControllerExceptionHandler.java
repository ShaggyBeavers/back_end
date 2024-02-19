package com.lpnu.shaggybeavers.exception;

import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.JwtException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Collections;
import java.util.List;

@RestControllerAdvice
public class ControllerExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = JwtException.class)
    public ResponseEntity<Object> handleJwtException(JwtException e) {
        return buildException((List.of(e.getMessage())), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = DuplicateException.class)
    public ResponseEntity<Object> handleDuplicateException(DuplicateException e) {
        return buildException((List.of(e.getMessage())), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = TokenException.class)
    public ResponseEntity<Object> handleTokenException(TokenException e) {
        return buildException((List.of(e.getMessage())), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NotEqualObjectsException.class)
    public ResponseEntity<Object> handleNotEqualObjectsException(NotEqualObjectsException e) {
        return buildException((List.of(e.getMessage())), HttpStatus.CONFLICT);
    }

    @ExceptionHandler(value = NotExistsObjectException.class)
    public ResponseEntity<Object> handleNotExistObjectException(NotExistsObjectException e) {
        return buildException(List.of(e.getMessage()),HttpStatus.CONFLICT);
    }

    @org.springframework.web.bind.annotation.ExceptionHandler(value = ExpiredJwtException.class)
    public ResponseEntity<?> handleExpiredJwtException(ExpiredJwtException e) {
        return buildException(Collections.singletonList(e.getMessage()),HttpStatus.UNAUTHORIZED);
    }

    @ExceptionHandler(value = FileException.class)
    public ResponseEntity<Object> handleFileException(FileException e) {
        return buildException(List.of(e.getMessage()), HttpStatus.CONFLICT);
    }

    private ResponseEntity<Object> buildException(List<String> message, HttpStatus httpStatus){
        var exceptionDetails = new ExcpetionDetails();


        exceptionDetails.setMessageList(message);
        exceptionDetails.setStatus(httpStatus);

        return new ResponseEntity<>(exceptionDetails, httpStatus);
    }

    @Override
    protected ResponseEntity<Object> handleExceptionInternal (Exception ex, Object body, HttpHeaders headers, HttpStatusCode statusCode, WebRequest request) {
        return buildException(List.of(ex.getMessage()),HttpStatus.valueOf(statusCode.value()));
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid (MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatusCode status, WebRequest request) {

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(FieldError::getDefaultMessage)
                .toList();

        return buildException(errors,HttpStatus.valueOf(status.value()));
    }
}
