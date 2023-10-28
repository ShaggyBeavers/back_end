package com.lpnu.shaggybeavers.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;
import org.springframework.http.ProblemDetail;

import java.time.LocalDateTime;


@Setter
@Getter
public class CustomProblemDetail extends ProblemDetail {

    private HttpStatus httpStatus;
    private LocalDateTime timestamp;
    private String message;
    private String description;

}
