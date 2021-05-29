package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.entityTable.TranDauEntity;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.stereotype.Repository;

@Repository
public class TranDauRepository extends com.example.demo.repository.Repository {

    public TranDauEntity findTranDauById(Session session, int id) {
        String sql = "Select d from " + TranDauEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<TranDauEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return (TranDauEntity) getSingleResult(query);
    }

}
