package com.igaopk.userapi.users.dtos;

import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.mappers.CellPhoneMapper;
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

    public UserUpdatedDTO(User user) {
        this(user.getFullName(), user.getUsername(), CellPhoneMapper.parseCellPhoneToList(user.getCellPhones()));
    }
}
