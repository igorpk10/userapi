package com.igaopk.userapi.users.controllers;

import com.igaopk.userapi.configurations.exceptions.UserPasswordConfirmationException;
import com.igaopk.userapi.configurations.exceptions.UserUpdateException;
import com.igaopk.userapi.configurations.security.services.TokenService;
import com.igaopk.userapi.users.dtos.CreateUserDTO;
import com.igaopk.userapi.users.dtos.UserCreatedDTO;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.dtos.UserUpdatedDTO;
import com.igaopk.userapi.users.mappers.CellPhoneMapper;
import com.igaopk.userapi.users.services.UserService;
import com.igaopk.userapi.users.usecases.CreateUserUseCase;
import com.igaopk.userapi.users.usecases.UpdateUserUseCase;
import io.swagger.v3.oas.annotations.security.SecurityRequirement;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/user")
@SecurityRequirement(name = "bearer-key")
public class UserController {

    @Autowired
    private CreateUserUseCase createUserUseCase;

    @Autowired
    private UpdateUserUseCase updateUser;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private UserService userService;

    @GetMapping()
    public ResponseEntity<List<UserDTO>> get() {
        var users = userService.findAll();
        var userDtoList = users.stream().map(user -> {
            var cellphones = CellPhoneMapper.parseCellPhoneToList(user.getCellPhones());
            return new UserDTO(user.getFullName(), user.getUsername(), user.getPassword(), user.getPassword(), cellphones);
        }).toList();

        return ResponseEntity.status(HttpStatus.OK).body(userDtoList);
    }

    @PostMapping("/create")
    public ResponseEntity<UserCreatedDTO> create(@RequestBody @Valid CreateUserDTO userDTO) throws UserPasswordConfirmationException {
        var user = createUserUseCase.create(userDTO);
        return ResponseEntity.status(HttpStatus.CREATED).body(user);
    }

    @PutMapping("/update")
    public ResponseEntity<UserUpdatedDTO> updatePassword(@RequestBody @Valid UserDTO userDTO,
                                                         @RequestHeader("Authorization") String authorization) throws UserUpdateException {
        var user = updateUser.update(userDTO, authorization);
        return ResponseEntity.status(HttpStatus.OK).body(user);
    }
}