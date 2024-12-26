package com.sermaluc.retotecnico.exception;

import com.sermaluc.retotecnico.util.Constants;
import com.sermaluc.retotecnico.dto.ExceptionMessage;
import io.jsonwebtoken.ExpiredJwtException;
import io.jsonwebtoken.security.SignatureException;
import org.hibernate.exception.ConstraintViolationException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.security.authentication.AccountStatusException;
import org.springframework.security.authentication.BadCredentialsException;
import org.springframework.validation.BindException;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;

import java.nio.file.AccessDeniedException;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.stream.Collectors;

@RestControllerAdvice
public class GlobalExceptionHandler {

    private static final Logger logger = LoggerFactory.getLogger(GlobalExceptionHandler.class);

    @ExceptionHandler(BindException.class)
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex) {
        Map<String, List<String>> body = new HashMap<>();

        List<String> errors = ex.getBindingResult()
                .getFieldErrors()
                .stream()
                .map(DefaultMessageSourceResolvable::getDefaultMessage)
                .collect(Collectors.toList());
        body.put("message", errors);
        return new ResponseEntity<>(body, HttpStatus.BAD_REQUEST);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<ExceptionMessage> handleSecurityException(Exception ex) {
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        logger.error("Exceptions: {}", ex.getMessage(), ex);
         if (ex instanceof DataIntegrityViolationException) {
            ConstraintViolationException exDetail =
                    (ConstraintViolationException) ex.getCause();
            String errorMessage = (Objects.equals(exDetail.getConstraintName(),
                    Constants.UNIQUE_INDEX_EMAIL_USER)) ?
                    " The email already has registered" : "Data integrity violation exception";
            exceptionMessage.setMessage(errorMessage);
            return new ResponseEntity<>(exceptionMessage, HttpStatus.NOT_ACCEPTABLE);
        }
        if (ex instanceof BadCredentialsException) {
            exceptionMessage.setMessage("The username or password is incorrect");
            return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(401));
        }
        if (ex instanceof AccountStatusException) {
            exceptionMessage.setMessage("The account is locked");
            return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(403));
        }
        if (ex instanceof AccessDeniedException) {
            exceptionMessage.setMessage("You are not authorized to access this resource");
            return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(403));
        }
        if (ex instanceof SignatureException) {
            exceptionMessage.setMessage("The JWT signature is invalid");
            return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(403));
        }
        if (ex instanceof ExpiredJwtException) {
            exceptionMessage.setMessage("The JWT token has expired");
            return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(403));
        }
        exceptionMessage.setMessage("Unknown internal server error.");
        return new ResponseEntity<>(exceptionMessage, HttpStatusCode.valueOf(500));
    }


    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<ExceptionMessage> handleIllegalArgumentException(IllegalArgumentException ex, WebRequest request) {
        logger.error("Invalid argument: {}, Request Details: {}", ex.getMessage(), request.getDescription(false), ex);
        ExceptionMessage exceptionMessage = new ExceptionMessage();
        exceptionMessage.setMessage("Invalid argument");
        return new ResponseEntity<>(exceptionMessage, HttpStatus.BAD_REQUEST);
    }

}
