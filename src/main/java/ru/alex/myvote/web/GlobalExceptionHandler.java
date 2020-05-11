package ru.alex.myvote.web;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.ModelAndView;
import org.springframework.web.servlet.NoHandlerFoundException;
import ru.alex.myvote.util.ValidationUtil;
import ru.alex.myvote.util.exception.ApplicationException;

import javax.servlet.http.HttpServletRequest;
import java.util.Map;

@ControllerAdvice
public class GlobalExceptionHandler {
    private static final Logger log = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    public GlobalExceptionHandler() {
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public ModelAndView wrongRequest(HttpServletRequest req, NoHandlerFoundException e) {
        return logAndGetExceptionView(req, e, false, e.getMessage());
    }

    @ExceptionHandler(ApplicationException.class)
    public ModelAndView applicationErrorHandler(HttpServletRequest req, ApplicationException appEx) {
        return logAndGetExceptionView(req, appEx, true, appEx.getArgs());
    }

    @ExceptionHandler(Exception.class)
    public ModelAndView defaultErrorHandler(HttpServletRequest req, Exception e) {
        log.error("Exception at request " + req.getRequestURL(), e);
        return logAndGetExceptionView(req, e, true, e.getMessage());
    }

    private ModelAndView logAndGetExceptionView(HttpServletRequest req, Exception e, boolean logException, String... msg) {
        Throwable rootCause = ValidationUtil.logAndGetRootCause(log, req, e, logException);

        return new ModelAndView("exception",
                Map.of("exception", rootCause, "message", msg != null ? msg : ValidationUtil.getMessage(rootCause)));
    }
}
