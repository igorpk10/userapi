package com.igaopk.userapi.configurations.exceptions;

public class UserPasswordConfirmationException extends Exception {

    @Override
    public String getMessage() {
        return "Password mismatches";
    }
}
