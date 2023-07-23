package com.igaopk.userapi.auth.dtos;

import jakarta.validation.constraints.NotBlank;

public record LoginDTO(
        @NotBlank
        String userName,
        @NotBlank
        String password
) {}
