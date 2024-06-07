package com.labseq.config;


import jakarta.ws.rs.core.Response;

import java.io.Serializable;

public class HttpException extends RuntimeException implements Serializable {
    private static final long serialVersionUID = 1L;
    private final Response.Status status;

    public HttpException(Response.Status status, String msg) {
        super(msg);
        this.status = status;
    }

    public HttpException(Response.Status status, String msg, Exception e) {
        super(msg, e);
        this.status = status;
    }

    public Response.Status getStatus() {
        return status;
    }
}
