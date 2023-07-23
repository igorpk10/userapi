package com.igaopk.userapi.users.dtos;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotEmpty;

import java.util.List;

public record UserUpdatedDTO(
        @NotBlank
        String fullName,
        @NotBlank
        String userName,

        @NotEmpty
        List<String> cellPhones
) {
}
