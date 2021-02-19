package org.javamaster.b2c.thymeleaf;

import com.javamaster.b2c.cloud.test.common.util.ExceptionUtils;
import org.javamaster.b2c.thymeleaf.exception.MailException;
import org.javamaster.b2c.thymeleaf.model.Result;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.context.MessageSource;
import org.springframework.context.i18n.LocaleContextHolder;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.util.UriComponentsBuilder;

import javax.servlet.http.HttpServletRequest;
import java.net.URI;
import java.util.List;

/**
 * @author yudong
 */
@ControllerAdvice
public class AppControllerAdvice {

    public static final String JAVAMASTER_HOST = "javamasterHost";
    public static final String CURRENT_LOCALE = "currentLocale";
    public static final String SYSTEM_ERROR_CODE = "mail.sys.500";
    Logger logger = LoggerFactory.getLogger(getClass());

    @Autowired
    @Qualifier("messageSourceMessagesExceptions")
    private MessageSource messageSource;

    @ModelAttribute
    public void model(Model model, HttpServletRequest request, UriComponentsBuilder ucb) {
        URI uri = URI.create(request.getRequestURL().toString());
        String javamasterHost = uri.getScheme() + "://" + uri.getRawAuthority();
        model.addAttribute(JAVAMASTER_HOST, javamasterHost);
        model.addAttribute("javamasterHost2", ucb.build());
        model.addAttribute(CURRENT_LOCALE, LocaleContextHolder.getLocale());
    }

    @ResponseBody
    @ExceptionHandler(Exception.class)
    public Result<List<Object>> exceptonHandler(Exception e) {
        Result<List<Object>> result;
        if (e instanceof MailException) {
            logger.error("business error", e);
            MailException mailException = (MailException) e;
            result = new Result<>(false, mailException.getErrorCode(), mailException.getMessage());
            result.setData(ExceptionUtils.fetchThrowableInfo(e));
            return result;
        }
        logger.error("service error", e);
        result = new Result<>(false, SYSTEM_ERROR_CODE, messageSource.getMessage(SYSTEM_ERROR_CODE, null, LocaleContextHolder.getLocale()));
        result.setData(ExceptionUtils.fetchThrowableInfo(e));
        return result;
    }
}
