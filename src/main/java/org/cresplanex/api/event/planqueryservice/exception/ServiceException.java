package org.cresplanex.api.event.planqueryservice.exception;

import build.buf.gen.plan.v1.PlanServiceErrorCode;

public abstract class ServiceException extends RuntimeException {
    public ServiceException(String message) {
        super(message);
    }

    public ServiceException(String message, Throwable cause) {
        super(message, cause);
    }

    abstract public PlanServiceErrorCode getServiceErrorCode();
    abstract public String getErrorCaption();
}
