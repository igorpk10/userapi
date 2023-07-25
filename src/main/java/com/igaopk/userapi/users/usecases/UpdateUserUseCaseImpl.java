package com.igaopk.userapi.users.usecases;

import com.igaopk.userapi.bases.BaseUserCredentials;
import com.igaopk.userapi.configurations.exceptions.UserUpdateException;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.dtos.UserUpdatedDTO;
import com.igaopk.userapi.users.mappers.CellPhoneMapper;

public class UpdateUserUseCaseImpl extends BaseUserCredentials implements UpdateUserUseCase {

    @Override
    public UserUpdatedDTO update(UserDTO userDTO, String credentials) throws UserUpdateException {
        var user = validateUserCredencials(userDTO.userName(), userDTO.password(), credentials);
        if (user != null) {
            user.update(userDTO);
            userRepository.save(user);
            return new UserUpdatedDTO(user);
        }

        throw new UserUpdateException();
    }
}