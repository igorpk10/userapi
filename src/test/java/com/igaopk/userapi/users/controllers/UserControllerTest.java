package com.igaopk.userapi.users.controllers;


import com.igaopk.userapi.auth.dtos.LoginDTO;
import com.igaopk.userapi.configurations.security.SecurityFilter;
import com.igaopk.userapi.configurations.security.services.TokenService;
import com.igaopk.userapi.mocks.UserDTOMock;
import com.igaopk.userapi.mocks.UserMock;
import com.igaopk.userapi.users.dtos.UserDTO;
import com.igaopk.userapi.users.repositories.UserRepository;
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
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.security.test.context.support.WithMockUser;
import org.springframework.test.web.servlet.MockMvc;


import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.put;

@SpringBootTest
@AutoConfigureMockMvc
@AutoConfigureJsonTesters
class UserControllerTest {

    @Autowired
    private MockMvc mockMvc;

    @Autowired
    private JacksonTester<Object> jacksonTesterJson;

    @Autowired
    private TokenService tokenService;

    @MockBean
    private UserRepository repository;

    @Autowired
    private PasswordEncoder passwordEncoder;

    @Test
    @DisplayName("Error on create user")
    @WithMockUser
    void createUserError() throws Exception {
        var response = mockMvc.perform(post("/user/create")).andReturn().getResponse();
        assertThat(response.getStatus()).isEqualTo(HttpStatus.BAD_REQUEST.value());
    }

    @Test
    @DisplayName("Success on create user")
    @WithMockUser
    void createUser() throws Exception {
        when(repository.save(any()))
                .thenReturn(UserMock.getUserMockWithoutCellphonesAndOtherUserName());

        var response = mockMvc.perform(post("/user/create")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
    }

    @Test
    @DisplayName("Sucess when update User")
    @WithMockUser
    void userUpdate() throws Exception {
        var user = UserMock.getUserMockWithoutCellphones();
        user.setPassword(passwordEncoder.encode(user.getPassword()));
        when(repository.findByUserName(any())).thenReturn(user);

        var loginResponse = mockMvc.perform(post("/auth/login")
                .contentType(MediaType.APPLICATION_JSON)
                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
        ).andReturn().getResponse();

        var token = loginResponse.getContentAsString().substring(13, 156);

        var response = mockMvc.perform(put("/user/update")
                .contentType(MediaType.APPLICATION_JSON)
                .header("Authorization", token)
                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
        ).andReturn().getResponse();

        assertThat(response.getStatus()).isEqualTo(HttpStatus.OK.value());
    }
}