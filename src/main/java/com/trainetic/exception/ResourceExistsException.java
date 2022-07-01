package com.trainetic.exception;

import java.io.Serializable;

public class ResourceExistsException extends
        RuntimeException implements Serializable {

    private static final long serialVersionUID = 1L;

    public ResourceExistsException() {
    }

    public ResourceExistsException(String message) {
        super(message);
    }

    public ResourceExistsException(String message, Throwable cause) {
        super(message, cause);
    }

    public ResourceExistsException(Throwable cause) {
        super(cause);
    }

    public ResourceExistsException(String message, Throwable cause,
                                     boolean enableSuppression, boolean writableStackTrace) {
        super(message, cause, enableSuppression, writableStackTrace);
    }
}

