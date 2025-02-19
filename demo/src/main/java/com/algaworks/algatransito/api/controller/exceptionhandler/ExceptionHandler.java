package com.algaworks.algatransito.api.controller.exceptionhandler;

import com.algaworks.algatransito.domain.model.exception.NegocioException;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@RestControllerAdvice
public class ExceptionHandler  extends ResponseEntityExceptionHandler {

//Poderia retornar no responseEntity um <void> e
    // então ele só me daria o erro 400, caso queira
    // retornar um texto coloco <string>
@org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());

    }


}
