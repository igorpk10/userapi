package com.igaopk.userapi.auth.controllers;

import com.igaopk.userapi.auth.dtos.LoginDTO;
import com.igaopk.userapi.auth.usecases.LoginUseCase;
import com.igaopk.userapi.configurations.exceptions.UserPasswordException;
import com.igaopk.userapi.configurations.security.JwtDataToken;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/auth")
public class AuthController {
    @Autowired
    private LoginUseCase loginUseCase;

    @PostMapping(path = "/login")
    public ResponseEntity<JwtDataToken> login(@RequestBody @Valid LoginDTO userDTO) throws UserPasswordException {
        var response = loginUseCase.login(userDTO);
        return ResponseEntity.status(HttpStatus.OK).body(response);
    }
}
