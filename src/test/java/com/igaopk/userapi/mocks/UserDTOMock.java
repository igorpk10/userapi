package com.igaopk.userapi.mocks;

import com.igaopk.userapi.auth.dtos.LoginDTO;
import com.igaopk.userapi.users.dtos.UserDTO;
import org.springframework.security.crypto.password.PasswordEncoder;

import java.util.Collections;
import java.util.List;

public class UserDTOMock {
    public static UserDTO createUserDTO() {
        return new UserDTO("Daniel Smith", "daniel", "daniel123", "daniel123", List.of("34999999999"));
    }

    public static UserDTO createUserWithOtherUsernameDTO() {
        return new UserDTO("Daniel Smith", "yugi", "yugi123", "daniel123", Collections.emptyList());
    }

    public static LoginDTO createUserForLogin(){
        return new LoginDTO("daniel", "daniel123");
    }
}
