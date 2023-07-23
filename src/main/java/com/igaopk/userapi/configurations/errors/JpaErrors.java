package com.igaopk.userapi.configurations.errors;

import com.igaopk.userapi.configurations.exceptions.UserNameDuplicateException;
import com.igaopk.userapi.configurations.exceptions.UserPasswordException;
import com.igaopk.userapi.configurations.exceptions.UserUpdateException;
import jakarta.persistence.EntityNotFoundException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
public class JpaErrors {

    @ExceptionHandler(EntityNotFoundException.class)
    public ResponseEntity entityNotFound() {
        return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
    }

    @ExceptionHandler(UserNameDuplicateException.class)
    public ResponseEntity entityDataIntegrity(UserNameDuplicateException userNameDuplicateException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userNameDuplicateException.getMessage());
    }

    @ExceptionHandler(UserPasswordException.class)
    public ResponseEntity entityNotAuthorizedByPassword(UserPasswordException userPasswordErrorException) {
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userPasswordErrorException.getMessage());
    }

    @ExceptionHandler(UserUpdateException.class)
    public ResponseEntity entityUserPasswordUpdateError(UserUpdateException userPasswordUpdateErrorException){
        return ResponseEntity.status(HttpStatus.UNAUTHORIZED).body(userPasswordUpdateErrorException.getMessage());
    }
}