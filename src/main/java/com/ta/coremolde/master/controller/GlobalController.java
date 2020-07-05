package com.ta.coremolde.master.controller;

import com.ta.coremolde.master.model.response.Response;

public abstract class GlobalController {

    public <T> Response toResponse(T data) {
        return Response.builder()
                .code(200)
                .message("Success")
                .data(data)
                .build();
    }

}
