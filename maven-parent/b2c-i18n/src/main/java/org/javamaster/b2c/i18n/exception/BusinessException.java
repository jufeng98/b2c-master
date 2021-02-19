package org.javamaster.b2c.i18n.exception;

import org.springframework.http.HttpStatus;
import org.springframework.web.bind.annotation.ResponseStatus;

/**
 * @author yudong
 */
@ResponseStatus(HttpStatus.UNAVAILABLE_FOR_LEGAL_REASONS)
public class BusinessException extends RuntimeException {
    private static final long serialVersionUID = -2291594001935827990L;

    private final String errorKey;
    private final Object[] errorParams;


    public BusinessException(String errorKey, Object... errorParams) {
        this.errorKey = errorKey;
        this.errorParams = errorParams;
    }

    public String getErrorKey() {
        return errorKey;
    }

    public Object[] getErrorParams() {
        return errorParams;
    }
}
