package com.unicarioca.projeto_poo.exception.client;

public class ClientEmailAlreadyExistsException extends RuntimeException{
    public ClientEmailAlreadyExistsException(){
        super("Email already exists!");
    }

    public ClientEmailAlreadyExistsException(String message){
        super(message);
    }
}
