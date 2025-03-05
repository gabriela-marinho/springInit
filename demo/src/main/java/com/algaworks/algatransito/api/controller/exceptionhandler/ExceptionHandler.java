package com.algaworks.algatransito.api.controller.exceptionhandler;

import com.algaworks.algatransito.domain.model.exception.NegocioException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ProblemDetail;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.FieldError;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.net.URI;
import java.util.stream.Collectors;

@RestControllerAdvice
public class ExceptionHandler  extends ResponseEntityExceptionHandler {

    //ESSE METODO É DO  ResponseEntityExceptionHandler
    // EU INSERI ELE AQUI E O MESMO PASSOU A SOBREESCREVER O Q ExceptionHandler EXTENDIA
    @Override
    protected ResponseEntity<Object> handleMethodArgumentNotValid(
            MethodArgumentNotValidException ex,
            HttpHeaders headers,
            HttpStatusCode status,
            WebRequest request) {

        ProblemDetail problemDetail = ProblemDetail.forStatus(status);
        problemDetail.setTitle(" um ou  mais campos estao invalidos");
        problemDetail.setType(URI.create("https://algatransito.erro"));

          ex.getBindingResult().getAllErrors().stream().collect(Collectors.toMap(objectError -> ))

//                  .forEach(objectError ->
//                System.out.println(((FieldError) objectError).getField() + " " +  objectError.getDefaultMessage()));

        return super.handleExceptionInternal(ex,problemDetail, headers, status, request);
    }



    //Poderia retornar no responseEntity um <void> e
    // então ele só me daria o erro 400, caso queira
    // retornar um texto coloco <string>
@org.springframework.web.bind.annotation.ExceptionHandler(NegocioException.class)
    public ResponseEntity<String> capturar(NegocioException e) {
        //return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(e.getMessage());
        return ResponseEntity.badRequest().body(e.getMessage());

    }


}
