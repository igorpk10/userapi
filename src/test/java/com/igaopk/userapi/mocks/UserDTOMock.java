package com.igaopk.userapi.mocks;

import com.igaopk.userapi.users.dtos.UserDTO;

import java.util.Collections;

public class UserDTOMock {
    public static UserDTO createUserDTO() {
        return new UserDTO("Daniel Smith", "daniel", "daniel123", Collections.emptyList());
    }

    public static UserDTO createUserWithOtherUsernameDTO() {
        return new UserDTO("Daniel Smith", "san", "daniel123", Collections.emptyList());
    }
}
