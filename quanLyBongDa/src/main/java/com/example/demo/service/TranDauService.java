package com.example.demo.service;

import com.example.demo.repository.entityTable.TranDauEntity;
import com.example.demo.repository.repositoryExtend.TranDauRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TranDauService {

    @Autowired
    private TranDauRepository tranDauRepository;

    public TranDauEntity findTranDauById(Session session, int id) {
        return tranDauRepository.findTranDauById(session, id);
    }

}
