package com.igaopk.userapi.configurations.exceptions;

import jakarta.servlet.ServletException;

public class UserNotFoundException extends ServletException {

    @Override
    public String getMessage() {
        return "User not found";
    }
}
