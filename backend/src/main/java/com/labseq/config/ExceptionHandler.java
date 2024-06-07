package com.labseq.config;


import jakarta.ws.rs.core.Response;
import jakarta.ws.rs.ext.ExceptionMapper;
import jakarta.ws.rs.ext.Provider;

@Provider
public class ExceptionHandler implements ExceptionMapper<HttpException> {
    @Override
    public Response toResponse(HttpException exception) {
        return Response.status(exception.getStatus())
                .entity(exception.getMessage()).build();
    }
}
