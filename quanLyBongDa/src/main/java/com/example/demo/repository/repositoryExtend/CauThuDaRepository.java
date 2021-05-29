package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.entityTable.CauThuDaEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class CauThuDaRepository extends com.example.demo.repository.Repository {

    public CauThuDaEntity findCauThuDaById(Session session, int id) {
        String sql = "Select d from " + CauThuDaEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<CauThuDaEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return (CauThuDaEntity) getSingleResult(query);
    }

    public void update(Session session, CauThuDaEntity cauThuDaEntity) {
        session.persist(cauThuDaEntity);
    }

}
