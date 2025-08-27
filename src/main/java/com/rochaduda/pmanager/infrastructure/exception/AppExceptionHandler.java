package com.rochaduda.pmanager.infrastructure.exception;

import java.util.List;
import java.util.Objects;

import org.springframework.context.support.DefaultMessageSourceResolvable;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.context.request.ServletWebRequest;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.HandlerExceptionResolver;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice // Aviso ao spring que a classe é responsável por chamar
public class AppExceptionHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler(value = RequestException.class) //todas excecoes caem aqui
    public ResponseEntity<Object> handleRequestException(RequestException ex, WebRequest request){

      return handleException(ex, ex.getErrorCode(), ex.getMessage(), null, HttpStatus.BAD_REQUEST, request);
    }

    @ExceptionHandler(value = Exception.class) //todas excecoes caem aqui
    public ResponseEntity<Object> handleGenericException(Exception ex, WebRequest request){

      return handleException(ex, null, ex.getMessage(), null, HttpStatus.INTERNAL_SERVER_ERROR, request);
    }

    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
      MethodArgumentNotValidException ex,
      HttpHeaders headers, 
      HttpStatusCode status, 
      WebRequest request) {
        
        List<String> details = ex
          .getBindingResult()
          .getFieldErrors()
          .stream()
          .filter(Objects::nonNull)
          .map(DefaultMessageSourceResolvable::getDefaultMessage )
          .toList();

          return handleException(ex, "ValidationError", null, details, HttpStatus.BAD_REQUEST, request);
      
    }
    
    private ResponseEntity<Object> handleException(
        Exception ex,
        String errorCode,
        String message,
        List<String> details,
        HttpStatus status,
        WebRequest request){

        ServletWebRequest servletWebRequest = (ServletWebRequest) request;

        return handleExceptionInternal(
            ex, 
            RestError
                .builder()
                .errorCode(errorCode)
                .errorMesage(message)
                .details(details)
                .status(status.value())
                .path(servletWebRequest.getRequest().getRequestURI())
                .build(), 
            new HttpHeaders(), 
            status, 
            request);
    }
}
