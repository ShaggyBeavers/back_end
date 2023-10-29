package com.lpnu.shaggybeavers.exception;

import org.jetbrains.annotations.NotNull;
import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.time.LocalDateTime;

@RestControllerAdvice
public class ControllerExceptionHandler {

    private static @NotNull CustomProblemDetail buildCustomProblemDetail(
            HttpStatus httpStatus, String message, String description)
    {
        CustomProblemDetail customProblemDetail = new CustomProblemDetail();
        customProblemDetail.setHttpStatus(httpStatus);
        customProblemDetail.setTimestamp(LocalDateTime.now());
        customProblemDetail.setMessage(message);
        customProblemDetail.setDescription(description);
        return customProblemDetail;
    }

    @ExceptionHandler(value = NotExistsObjectException.class)
    @ResponseStatus(value = HttpStatus.NOT_FOUND)
    public CustomProblemDetail notExistsObjectException(
            @NotNull NotExistsObjectException exception, @NotNull WebRequest webRequest
    ) {
        return ControllerExceptionHandler.buildCustomProblemDetail(
                HttpStatus.NOT_FOUND,
                exception.getMessage(),
                webRequest.getDescription(false)
        );
    }
}
