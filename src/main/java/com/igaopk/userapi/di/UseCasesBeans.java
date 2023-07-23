package com.igaopk.userapi.di;

import com.igaopk.userapi.auth.usecases.LoginUseCase;
import com.igaopk.userapi.auth.usecases.LoginUseCaseImpl;
import com.igaopk.userapi.users.usecases.*;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class UseCasesBeans {

    @Bean
    public LoginUseCase injectLoginUseCase() {
        return new LoginUseCaseImpl();
    }

    @Bean
    public CreateUserUseCase injectCreateUserUseCase() {
        return new CreateUserUseCaseImpl();
    }

    @Bean
    public UpdateUserUseCase injectUpdateUserUseCase() {return new UpdateUserUseCaseImpl();}
}
