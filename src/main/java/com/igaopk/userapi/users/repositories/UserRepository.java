package com.igaopk.userapi.users.repositories;

import com.igaopk.userapi.users.entitys.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface UserRepository extends JpaRepository<User, Integer> {
    UserDetails findByUserName(String userName);

    @Query("SELECT DISTINCT FROM User e ")
    List<User> retrieveAll();

}
