package com.igaopk.userapi.users.repositories;

import com.igaopk.userapi.users.entitys.CellPhone;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface CellPhoneRepository extends JpaRepository<CellPhone, Integer> {


}
