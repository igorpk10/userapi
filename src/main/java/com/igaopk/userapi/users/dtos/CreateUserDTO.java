package com.igaopk.userapi.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record CreateUserDTO(
        @NotBlank
        String fullName,
        @NotBlank
        String userName,
        @NotBlank
        String password,
        @NotBlank
        String passwordConfirmation,
        @NotEmpty
        List<String> cellPhones
) { }
