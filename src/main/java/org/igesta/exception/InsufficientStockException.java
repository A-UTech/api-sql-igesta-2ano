package org.igesta.exception;

public class InsufficientStockException extends RuntimeException {
    public InsufficientStockException(String message){
        super(message);
    }
    public InsufficientStockException(){
        super("Estoque insuficiente");
    }
}

