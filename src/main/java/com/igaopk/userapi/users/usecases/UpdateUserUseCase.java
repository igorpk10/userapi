package com.igaopk.userapi.users.usecases;

import com.igaopk.userapi.configurations.exceptions.UserUpdateException;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.dtos.UserUpdatedDTO;
import org.springframework.stereotype.Component;

@Component
public interface UpdateUserUseCase {

    public UserUpdatedDTO update(UserDTO userDTO, String credentials) throws UserUpdateException;

}
