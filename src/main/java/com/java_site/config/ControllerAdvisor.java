package com.java_site.config;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.http.HttpStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

/**
 * Глобальний обробник контролерів для обробки винятків у всьому додатку.
 */
@ControllerAdvice
public class ControllerAdvisor extends ResponseEntityExceptionHandler {

    @ExceptionHandler(IllegalArgumentException.class)
    public String handleIllegalArgumentException(IllegalArgumentException ex, Model model) {
        setErrorAttributes(ex, model, "Недопустиме значення");
        return "error";
    }

    @ExceptionHandler(NullPointerException.class)
    public String handleNullPointerException(NullPointerException ex, Model model) {
        setErrorAttributes(ex, model, "Значення не може бути порожнім");
        return "error";
    }

    private void setErrorAttributes(Exception ex, Model model, String defaultMessage) {
        model.addAttribute("errorMessage", ex.getMessage() != null ? ex.getMessage() : defaultMessage);
    }

    @ExceptionHandler(Exception.class)
    @ResponseStatus(value = HttpStatus.INTERNAL_SERVER_ERROR)
    public void handleException(Exception ex, Model model) {
        setErrorAttributes(ex, model, ex.getMessage());
    }



}
