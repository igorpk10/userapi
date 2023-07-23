package com.igaopk.userapi.users.repositories;

import com.igaopk.userapi.mocks.UserMock;
import com.igaopk.userapi.users.entitys.User;
import jakarta.persistence.EntityManager;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.jdbc.AutoConfigureTestDatabase;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.dao.DataIntegrityViolationException;
import org.springframework.test.context.ActiveProfiles;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

@DataJpaTest
@AutoConfigureTestDatabase(replace = AutoConfigureTestDatabase.Replace.NONE)
@ActiveProfiles("test")
class UserRepositoryTest {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private EntityManager entityManager;

    @Test
    @DisplayName("Test if have users with the same user name")
    void verifyIfHave2UsersWithSameUserName() {
        //assert
        assertThrows(DataIntegrityViolationException.class, () -> {
            //arrange
            var user = UserMock.getUserMockWithoutCellphones();
            var user2 = UserMock.getUserMockWithoutCellphones();

            //act
            userRepository.save(user);
            userRepository.save(user2);
        });
    }

    @Test
    @DisplayName("Test to obtain a user from username")
    void  findByUserName(){
        //arrange
        var user = UserMock.getUserMockWithoutCellphones();

        //act
        userRepository.save(user);
        var response = (User) userRepository.findByUserName(user.getUsername());

        //assert
        assertEquals(user.getId(), response.getId());
    }
}