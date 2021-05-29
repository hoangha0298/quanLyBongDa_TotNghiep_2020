package com.example.demo.service;

import com.example.demo.repository.entityTable.VongDauEntity;
import com.example.demo.repository.repositoryExtend.VongDauRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class VongDauService {

    @Autowired
    private VongDauRepository vongDauRepository;

    public List<VongDauEntity> findAllVongDau(Session session) {
        return vongDauRepository.findAllVongDau(session);
    }

}
