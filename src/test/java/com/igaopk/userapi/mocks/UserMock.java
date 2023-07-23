package com.igaopk.userapi.mocks;

import com.igaopk.userapi.users.entitys.User;

import java.util.Collections;

public class UserMock {
    public static User getUserMockWithoutCellphones(){
        return new User("Daniel Smith", "daniel", "daniel123", Collections.emptyList());
    }

    public static User getUserMockWithoutCellphonesAndOtherUserName(){
        return new User("Daniel Smith", "san", "daniel123", Collections.emptyList());
    }
}