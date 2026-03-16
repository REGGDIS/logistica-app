package com.regdevs.logistica.exception;

import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.NoHandlerFoundException;

@ControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(Exception.class)
    public String handleException(Exception ex, Model model) throws Exception {
        if (ex instanceof org.springframework.security.access.AccessDeniedException) {
            throw ex;
        }
        model.addAttribute("errorMessage", ex.getMessage());
        model.addAttribute("errorTitle", "Error Interno del Servidor");
        return "error";
    }

    @ExceptionHandler(NoHandlerFoundException.class)
    public String handle404(NoHandlerFoundException ex, Model model) {
        model.addAttribute("errorTitle", "Página no Encontrada");
        model.addAttribute("errorMessage", "Lo sentimos, la página que buscas no existe.");
        return "error";
    }
}
