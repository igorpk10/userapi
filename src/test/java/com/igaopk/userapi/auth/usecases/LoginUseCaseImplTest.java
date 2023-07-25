package com.igaopk.userapi.auth.usecases;

import com.igaopk.userapi.configurations.security.JwtDataToken;
import com.igaopk.userapi.configurations.security.services.TokenService;
import com.igaopk.userapi.mocks.UserDTOMock;
import com.igaopk.userapi.mocks.UserMock;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.repositories.UserRepository;
import com.igaopk.userapi.users.services.UserService;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.json.AutoConfigureJsonTesters;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.json.JacksonTester;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.security.authentication.AuthenticationManager;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.test.web.servlet.MockMvc;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class LoginUseCaseImplTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<UserDTO> jacksonTesterJson;

    @Autowired
    private AuthenticationManager manager;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Autowired
    private LoginUseCase loginUseCase;

    @Autowired
    private UserService service;

    @MockBean
    private UserRepository repository;

    @Autowired
    private TokenService tokenService;

    @Test
    @DisplayName("Login error")
    void successGenerateToken() throws Exception {
        var user = UserMock.getUserMockWithoutCellphones();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        when(repository.findByUserName(any())).thenReturn(user);

        var response = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }

    @Test
    @DisplayName("Login error")
    void errorGenerateTokenPassworsMissmath() throws Exception {
        var user = UserMock.getUserMockWithoutCellphones();
        when(repository.findByUserName(any())).thenReturn(user);

        var response = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.UNAUTHORIZED.value());
    }

}