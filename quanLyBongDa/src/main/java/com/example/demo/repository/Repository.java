package com.example.demo.repository;

import org.hibernate.query.Query;

@org.springframework.stereotype.Repository
public abstract class Repository {

    public Object getSingleResult(Query query) {
        try {
            return query.getSingleResult();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

    public Object getResultList(Query query) {
        try {
            return query.getResultList();
        } catch (Exception e) {
            e.printStackTrace();
            return null;
        }
    }

}
