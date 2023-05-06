package com.revature.ECommerce.Exception;

public class InvalidCredential extends RuntimeException{
    public InvalidCredential(String message){
        super(message);
    }
}
