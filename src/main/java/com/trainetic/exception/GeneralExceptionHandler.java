package com.trainetic.exception;

import javax.ws.rs.core.Response;
import javax.ws.rs.ext.ExceptionMapper;
import javax.ws.rs.ext.Provider;

@Provider
public class GeneralExceptionHandler implements ExceptionMapper<GeneralException> {

    @Override
    public Response toResponse(GeneralException e) {
        return Response.status(400, e.getMessage()).build();
    }

}
