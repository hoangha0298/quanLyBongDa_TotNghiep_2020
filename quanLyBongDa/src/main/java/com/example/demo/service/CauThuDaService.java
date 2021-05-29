package com.example.demo.service;

import com.example.demo.repository.entityTable.CauThuDaEntity;
import com.example.demo.repository.repositoryExtend.CauThuDaRepository;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class CauThuDaService {

    @Autowired
    private CauThuDaRepository cauThuDaRepository;

    public CauThuDaEntity findCauThuDaById(Session session, int id) {
        return cauThuDaRepository.findCauThuDaById(session, id);
    }

    public void update(Session session, CauThuDaEntity cauThuDaEntity) {
        cauThuDaRepository.update(session, cauThuDaEntity);
    }

}
