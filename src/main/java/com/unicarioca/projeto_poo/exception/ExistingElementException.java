package com.unicarioca.projeto_poo.exception;

public class ExistingElementException extends RuntimeException{
    public ExistingElementException(){
        super("This element already exists!");
    }

    public ExistingElementException(String message){
        super(message);
    }

}
