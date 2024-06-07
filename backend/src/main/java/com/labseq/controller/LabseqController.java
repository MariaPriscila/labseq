package com.labseq.controller;

import com.labseq.config.HttpException;
import com.labseq.service.LabseqService;
import jakarta.inject.Inject;
import jakarta.ws.rs.GET;
import jakarta.ws.rs.Path;
import jakarta.ws.rs.Produces;
import jakarta.ws.rs.core.MediaType;
import jakarta.ws.rs.core.Response;

@Path("labseq")
public class LabseqController {
    @Inject
    LabseqService service;

    @GET
    @Path("/{n}")
    @Produces(MediaType.TEXT_PLAIN)
    public Long controller(long n) {
        if (n < 0) {
            throw new HttpException(Response.Status.BAD_REQUEST, "Number must be 0 or greater");
        }

        return service.calculateLabseq(n);
    }

}
