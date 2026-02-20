package com.example.login.demo.exception;

import jakarta.servlet.http.HttpServletRequest;
import org.springframework.boot.context.config.ConfigDataResourceNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import java.sql.SQLIntegrityConstraintViolationException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.Map;

@RestControllerAdvice
public class GlobalExceptionHandler {

    @ExceptionHandler(ResourceNotFoundException.class)
    public ResponseEntity<Map<String,Object>> handleResourceNotFoundException(ResourceNotFoundException ex, HttpServletRequest request)
    {
        return
                buildResponse(ex.getMessage(), HttpStatus.NOT_FOUND,request.getRequestURI());
    }
    @ExceptionHandler(IllegalArgumentException.class)
    public ResponseEntity<Map<String,Object>> handleIllegalArgumentException(IllegalArgumentException ex,HttpServletRequest request)
    {
        return buildResponse(ex.getMessage(),HttpStatus.BAD_REQUEST,request.getRequestURI());
    }
    @ExceptionHandler(MethodArgumentNotValidException.class)
    public ResponseEntity<Map<String,Object>> handleMethodArgumentNotValidException(MethodArgumentNotValidException ex,HttpServletRequest request)
    {
        Map<String,String> filederrors=new HashMap<>();
        ex.getBindingResult().getFieldErrors().forEach(error -> filederrors.put(error.getField(), error.getDefaultMessage()));
        Map<String,Object> response=new HashMap();
        response.put("timestamp",LocalDateTime.now());
        response.put("status",HttpStatus.BAD_REQUEST.value());
        response.put("error","validation failed");
        response.put("message","one or more fileds are invalid.");
        response.put("path",request.getRequestURI());
        response.put("filederrors",filederrors);
        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }
    @ExceptionHandler(SQLIntegrityConstraintViolationException.class)
    public ResponseEntity<Map<String,Object>> handleSQLIntegrityConstraintViolationException  (SQLIntegrityConstraintViolationException ex,HttpServletRequest request)
    {

        Map<String,Object> response=new HashMap();
        response.put("timestamp",LocalDateTime.now());
        response.put("status",HttpStatus.BAD_REQUEST.value());
        response.put("error",ex.getMessage());

        response.put("path",request.getRequestURI());

        return new ResponseEntity<>(response,HttpStatus.BAD_REQUEST);

    }

    private ResponseEntity<Map<String,Object>> buildResponse(String message,HttpStatus status,String path)
    {
        Map<String,Object> response=new HashMap();
        response.put("time", LocalDateTime.now());
        response.put("status",status.value());
        response.put("message",message);
        response.put("path",path);
        return new ResponseEntity<>(response,status);

    }
}
