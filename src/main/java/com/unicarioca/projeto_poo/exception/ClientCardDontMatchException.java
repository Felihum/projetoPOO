package com.unicarioca.projeto_poo.exception;

public class ClientCardDontMatchException extends RuntimeException{

    public ClientCardDontMatchException(){
        super("This card is not registered with this client!");
    }

    public ClientCardDontMatchException(String message){
        super(message);
    }
}
