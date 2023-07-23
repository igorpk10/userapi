package com.igaopk.userapi.configurations.exceptions;

public class UserNameDuplicateException extends RuntimeException {

    @Override
    public String getMessage() {
        return "Already exists this username";
    }
}
