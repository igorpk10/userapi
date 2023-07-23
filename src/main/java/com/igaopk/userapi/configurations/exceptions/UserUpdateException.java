package com.igaopk.userapi.configurations.exceptions;

public class UserUpdateException extends Exception{

    @Override
    public String getMessage() {
        return "User update error";
    }
}
