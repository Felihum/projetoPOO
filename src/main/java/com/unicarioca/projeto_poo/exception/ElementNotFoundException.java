package com.unicarioca.projeto_poo.exception;

public class ElementNotFoundException extends RuntimeException{

    public ElementNotFoundException(){
        super("No such element found!");
    }

    public ElementNotFoundException(String message){
        super(message);
    }
}
