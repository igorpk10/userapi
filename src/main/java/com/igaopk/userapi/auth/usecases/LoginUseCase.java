package com.igaopk.userapi.auth.usecases;

import com.igaopk.userapi.auth.dtos.LoginDTO;
import com.igaopk.userapi.configurations.exceptions.UserPasswordException;
import com.igaopk.userapi.configurations.security.JwtDataToken;
import org.springframework.stereotype.Component;

@Component
public interface LoginUseCase {

    public JwtDataToken login(LoginDTO userDTO) throws UserPasswordException;

}
