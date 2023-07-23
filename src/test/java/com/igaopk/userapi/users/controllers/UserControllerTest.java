package com.igaopk.userapi.users.controllers;


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
    private JacksonTester<UserDTO> jacksonTesterJson;

    @Autowired
    private TokenService tokenService;

    @MockBean
    private UserRepository repository;

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
//
//    @Test
//    @DisplayName("Sucess when update User")
//    @WithMockUser
//    void updateUser() throws Exception {
//        var user = UserMock.getUserMockWithoutCellphonesAndOtherUserName();
//        repository.save(user);
//
//        var loginJwt = mockMvc.perform(post("/auth/login")
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
//        ).andReturn().getResponse().getHeader("Authentication");
//
//        var response = mockMvc.perform(put("/user/update")
//                .header("Authorization", loginJwt)
//                .contentType(MediaType.APPLICATION_JSON)
//                .content(jacksonTesterJson.write(UserDTOMock.createUserDTO()).getJson())
//        ).andReturn().getResponse();
//
//
//
//
//        assertThat(response.getStatus()).isEqualTo(HttpStatus.CREATED.value());
//
//        var expected = jacksonTesterJson.write(UserDTOMock.createUserWithOtherUsernameDTO());
//
//        assertThat(response.getContentAsString()).isEqualTo(expected);
//    }

}