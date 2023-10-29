package com.lpnu.shaggybeavers.exception;

import lombok.Getter;
import lombok.Setter;
import org.springframework.http.ProblemDetail;

import java.util.List;

@Setter
@Getter
public class ExcpetionDetails extends ProblemDetail {

    private List<String> messageList;

}
