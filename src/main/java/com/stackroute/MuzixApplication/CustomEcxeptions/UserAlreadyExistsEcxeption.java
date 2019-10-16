package com.stackroute.MuzixApplication.CustomEcxeptions;

public class UserAlreadyExistsEcxeption extends Exception
{
    String message;

    public UserAlreadyExistsEcxeption() {
    }

    public UserAlreadyExistsEcxeption(String message) {
        super(message);
        this.message = message;
    }

}
