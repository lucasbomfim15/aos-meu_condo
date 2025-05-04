package com.aos.meu_condo.modules.user.exceptions;

public class UserFoundException extends RuntimeException{
    public UserFoundException(){
        super("User already exists:");
    }
}