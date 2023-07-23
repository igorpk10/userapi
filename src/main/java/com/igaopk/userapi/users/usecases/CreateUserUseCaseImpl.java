package com.igaopk.userapi.users.usecases;

import com.igaopk.userapi.bases.BaseUserCredentials;
import com.igaopk.userapi.configurations.exceptions.UserPasswordConfirmationException;
import com.igaopk.userapi.users.dtos.CreateUserDTO;
import com.igaopk.userapi.users.dtos.UserCreatedDTO;
import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

public class CreateUserUseCaseImpl extends BaseUserCredentials implements CreateUserUseCase {

    @Autowired
    private UserService repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCreatedDTO create(CreateUserDTO userDTO) throws UserPasswordConfirmationException {
        if(userDTO.password().equals(userDTO.passwordConfirmation())){
            var userEntity = new User(userDTO);
            userEntity.encryptPassword(passwordEncoder);

            var response = repository.save(userEntity);
            return new UserCreatedDTO(response.getFullName(), response.getUsername());
        }

        throw new UserPasswordConfirmationException();
    }
}
