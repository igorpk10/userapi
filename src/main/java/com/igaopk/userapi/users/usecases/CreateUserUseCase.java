package com.igaopk.userapi.users.usecases;

import com.igaopk.userapi.configurations.exceptions.UserPasswordConfirmationException;
import com.igaopk.userapi.users.dtos.CreateUserDTO;
import com.igaopk.userapi.users.dtos.UserCreatedDTO;
import org.springframework.stereotype.Component;

@Component
public interface CreateUserUseCase {
    public UserCreatedDTO create(CreateUserDTO userDTO) throws UserPasswordConfirmationException;

}