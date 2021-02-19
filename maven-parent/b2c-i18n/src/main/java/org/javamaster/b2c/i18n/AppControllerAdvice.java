package org.javamaster.b2c.i18n;

import org.javamaster.b2c.i18n.exception.BusinessException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

/**
 * @author yudong
 */
@RestControllerAdvice
public class AppControllerAdvice {
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("messageSourceMessagesExceptions")
    private MessageSource messageSource;


    @ExceptionHandler(BusinessException.class)
    public String exceptonHandler(BusinessException e) {
        logger.error("business error", e);
        return messageSource.getMessage(e.getErrorKey(), e.getErrorParams(), LocaleContextHolder.getLocale());
    }


    @ExceptionHandler(Exception.class)
    public String exceptonHandler(Exception e) {
        logger.error("business error", e);
        return messageSource.getMessage("mail.sys.500", new Object[]{}, LocaleContextHolder.getLocale());
    }
}
