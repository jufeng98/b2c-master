package org.javamaster.b2c.thymeleaf.exception;

import org.javamaster.b2c.thymeleaf.utils.SpringUtils;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;

/**
 * @author yudong
 */
public class MailException extends RuntimeException {
    private static final long serialVersionUID = -2291594001935827990L;

    private final String errorCode;
    private final String[] errorParams;

    public MailException(String errorCode) {
        this.errorCode = errorCode;
        this.errorParams = new String[0];
    }

    public MailException(String errorCode, String... errorParams) {
        this.errorCode = errorCode;
        this.errorParams = errorParams;
    }

    @Override
    public String getMessage() {
        MessageSource messageSource = (MessageSource) SpringUtils.getBean("messageSourceMessagesExceptions");
        return messageSource.getMessage(errorCode, errorParams, LocaleContextHolder.getLocale());
    }

    public String getErrorCode() {
        return errorCode;
    }
}
