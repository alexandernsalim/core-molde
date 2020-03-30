package com.ta.coremolde.util;

import com.ta.coremolde.model.exception.MoldeException;
import com.ta.coremolde.model.response.Response;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(value = MoldeException.class)
    public Response moldeException(MoldeException e) {
        return Response.builder()
                .code(e.getCode())
                .message(e.getMessage())
                .build();
    }

}
