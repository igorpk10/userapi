package com.igaopk.userapi.di;

import com.igaopk.userapi.users.services.CellPhoneService;
import com.igaopk.userapi.users.services.CellPhoneServiceImpl;
import com.igaopk.userapi.users.services.UserService;
import com.igaopk.userapi.users.services.UserServiceImpl;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class ServiceBeans {

    @Bean
    public UserService injectUserService() {
        return new UserServiceImpl();
    }

    @Bean
    public CellPhoneService injectCellPhone() {
        return new CellPhoneServiceImpl();
    }
}
