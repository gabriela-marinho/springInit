package com.algaworks.algatransito.domain.model.exception;

public class NegocioException  extends RuntimeException {

    //RuntimeException exceção que nao precisa tratar


    public NegocioException(String message) {
        super(message);
    }
}
