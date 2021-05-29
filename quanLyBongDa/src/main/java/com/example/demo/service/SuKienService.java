package com.example.demo.service;

import com.example.demo.repository.entityTable.SuKienEntity;
import com.example.demo.repository.repositoryExtend.SuKienRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SuKienService {

    @Autowired
    private SuKienRepository suKienRepository;

    public List<SuKienEntity> findAllSuKien(Session session) {
        return suKienRepository.findAllSuKien(session);
    }

    public SuKienEntity findSuKienById(Session session, int id) {
        return suKienRepository.findSuKienById(session, id);
    }

}
