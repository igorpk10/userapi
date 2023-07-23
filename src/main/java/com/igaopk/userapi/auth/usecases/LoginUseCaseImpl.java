package com.igaopk.userapi.auth.usecases;

import com.igaopk.userapi.auth.dtos.LoginDTO;
import com.igaopk.userapi.configurations.exceptions.UserPasswordException;
import com.igaopk.userapi.configurations.security.JwtDataToken;
import com.igaopk.userapi.configurations.security.services.TokenService;
import com.igaopk.userapi.users.entitys.User;
import com.igaopk.userapi.users.services.UserService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.authentication.UsernamePasswordAuthenticationToken;
import org.springframework.security.crypto.password.PasswordEncoder;

public class LoginUseCaseImpl implements LoginUseCase {

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private TokenService tokenService;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private UserService service;

    @Override
    public JwtDataToken login(LoginDTO userDTO) throws UserPasswordException {
        try {
            var user = service.findByUserName(userDTO.userName());

            if (passwordEncoder.matches(userDTO.password(), user.getPassword())) {
                var authToken = new UsernamePasswordAuthenticationToken(userDTO.userName(), userDTO.password());
                var authentication = manager.authenticate(authToken);
                var jwtToken = tokenService.generateToken((User) authentication.getPrincipal());
                return new JwtDataToken(jwtToken);
            }

            throw new UserPasswordException();
        }catch (Exception ex){
            throw new UserPasswordException();
        }
    }
}
