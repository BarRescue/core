package com.trainetic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class ResourceExistsExceptionHandler implements ExceptionMapper<ResourceExistsException> {

    @Override
    public Response toResponse(ResourceExistsException e) {
        return Response.status(400, "Already exists.").build();
    }
}