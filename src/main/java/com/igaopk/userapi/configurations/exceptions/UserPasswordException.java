package com.igaopk.userapi.configurations.exceptions;

public class UserPasswordException extends Exception {

    @Override
    public String getMessage() {
        return "Wrong password";
    }
}
