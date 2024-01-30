package com.fastFood.controllers;
import com.fastFood.dtos.ExceptionDTO;
import jakarta.persistence.EntityExistsException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class ExceptionController {

    @ExceptionHandler(EntityExistsException.class)
    public ResponseEntity<?> throwDuplicateClient(EntityExistsException exception) {

        var exceptionDTO = new ExceptionDTO("Cliente já cadastrado", "400");

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity<?> throwClientNotFound(EntityNotFoundException exception) {

        var exceptionDTO = new ExceptionDTO(exception.getMessage(), "404");

        return ResponseEntity.badRequest().body(exceptionDTO);
    }

    @ExceptionHandler(Exception.class)
    public ResponseEntity<?> throwGeneralException(Exception exception) {

        var exceptionDTO = new ExceptionDTO(exception.getMessage(), "500");

        return ResponseEntity.internalServerError().body(exceptionDTO);
    }
}
