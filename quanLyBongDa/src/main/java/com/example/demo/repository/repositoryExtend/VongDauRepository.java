package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.entityTable.VongDauEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class VongDauRepository extends com.example.demo.repository.Repository{

    public List<VongDauEntity> findAllVongDau(Session session) {
        String sql = "from " + VongDauEntity.class.getName();
        Query<VongDauEntity> query = session.createQuery(sql);
        return (List<VongDauEntity>) getResultList(query);
    }
}
