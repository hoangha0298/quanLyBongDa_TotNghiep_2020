package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.entityTable.SuKienEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public class SuKienRepository extends com.example.demo.repository.Repository {

    public List<SuKienEntity> findAllSuKien(Session session) {
        String sql = "Select d from " + SuKienEntity.class.getName() + " d ";
        Query<SuKienEntity> query = session.createQuery(sql);
        return (List<SuKienEntity>) getResultList(query);
    }

    public SuKienEntity findSuKienById(Session session, int id) {
        String sql = "Select d from " + SuKienEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<SuKienEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return (SuKienEntity) getSingleResult(query);
    }

}
