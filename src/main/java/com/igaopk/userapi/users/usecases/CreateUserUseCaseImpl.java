package com.igaopk.userapi.users.usecases;

import com.igaopk.userapi.bases.BaseUserCredentials;
import com.igaopk.userapi.configurations.exceptions.UserPasswordConfirmationException;
import com.igaopk.userapi.users.dtos.CreateUserDTO;
import com.igaopk.userapi.users.dtos.UserCreatedDTO;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.entitys.CellPhone;
import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.mappers.CellPhoneMapper;
import com.igaopk.userapi.users.services.CellPhoneService;
import com.igaopk.userapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.List;

public class CreateUserUseCaseImpl extends BaseUserCredentials implements CreateUserUseCase {

    @Autowired
    private UserService userService;

    @Autowired
    private CellPhoneService cellPhoneService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Override
    public UserCreatedDTO create(CreateUserDTO userDTO) throws UserPasswordConfirmationException {
        if (userDTO.password().equals(userDTO.passwordConfirmation())) {
            var userEntity = new User(userDTO);

            persistUser(userEntity);
            persistCellPhones(userDTO.cellPhones(), userEntity);

            return new UserCreatedDTO(userEntity.getFullName(), userEntity.getUsername());
        }

        throw new UserPasswordConfirmationException();
    }

    private void persistUser(User user){
        user.encryptPassword(passwordEncoder);
        userService.save(user);
    }

    private void persistCellPhones(List<String> cellphones, User user){
        var cellphonesList = CellPhoneMapper.parseCellPhoneToObject(cellphones, user);
        cellPhoneService.save(cellphonesList);
    }


}
