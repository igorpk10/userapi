package com.igaopk.userapi.users.services;

import com.igaopk.userapi.users.entitys.CellPhone;
import com.igaopk.userapi.users.repositories.CellPhoneRepository;
import jakarta.transaction.Transactional;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

public class CellPhoneServiceImpl implements CellPhoneService {

    @Autowired
    private CellPhoneRepository repository;

    @Override
    @Transactional
    public List<CellPhone> save(List<CellPhone> cellPhone) {
        return repository.saveAll(cellPhone);
    }
}
