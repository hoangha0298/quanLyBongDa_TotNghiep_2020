package com.javatools.crud.hibernate;

import com.javatools.crud.entitydatabase.*;
import com.javatools.crud.model.TranDau;
import org.hibernate.Session;
import org.hibernate.query.Query;

import java.util.List;

public class DataUtils {



    public static VongDauEntity findVongDau(Session session, int id) {
        String sql = "Select d from " + VongDauEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<VongDauEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static CauThuEntity findCauThuById(Session session, int id) {
        String sql = "Select d from " + CauThuEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<CauThuEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static TrongTaiEntity findTrongTaiByName(Session session, String ten) {
        String sql = "Select d from " + TrongTaiEntity.class.getName() + " d "
                + " Where d.ten = :ten";
        Query<TrongTaiEntity> query = session.createQuery(sql);
        query.setParameter("ten", ten);
        return query.getSingleResult();
    }

    public static List<VongDauEntity> findAllVongDau(Session session) {
        String sql = "from " + VongDauEntity.class.getName();
        Query<VongDauEntity> query = session.createQuery(sql);
        return query.getResultList();
    }

    public static GiaiDauEntity findGiaiDau(Session session, int id) {
        String sql = "Select d from " + GiaiDauEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<GiaiDauEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static SanBongEntity findSanBongByName(Session session, String name) {
        String sql = "Select d from " + SanBongEntity.class.getName() + " d "
                + " Where d.ten = :ten";
        Query<SanBongEntity> query = session.createQuery(sql);
        query.setParameter("ten", name);
        System.out.println(name);
        return query.getSingleResult();
    }

    public static DoiBongEntity findDoiBongByName(Session session, String name) {
        String sql = "Select d from " + DoiBongEntity.class.getName() + " d "
                + " Where d.ten = :ten";
        Query<DoiBongEntity> query = session.createQuery(sql);
        query.setParameter("ten", name);
        return query.getSingleResult();
    }

    public static List<DoiBongEntity> findAllDoiBong(Session session) {
        String sql = "Select d from " + DoiBongEntity.class.getName() + " d ";
        Query<DoiBongEntity> query = session.createQuery(sql);
        return query.getResultList();
    }

    public static List<DoiDaEntity> findAllDoiDa(Session session) {
        String sql = "Select d from " + DoiDaEntity.class.getName() + " d ";
        Query<DoiDaEntity> query = session.createQuery(sql);
        return query.getResultList();
    }

    public static ViTriEntity findViTriByKieuAndTen(Session session,String type, String name) {
        String sql = "Select d from " + ViTriEntity.class.getName() + " d "
                + " Where d.kieu = :kieu and d.ten = :ten";
        Query<ViTriEntity> query = session.createQuery(sql);
        query.setParameter("ten", name);
        query.setParameter("kieu", type);
        return query.getSingleResult();
    }

    public static TranDauEntity findTranDauById(Session session, int id) {
        String sql = "Select d from " + TranDauEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<TranDauEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return query.getSingleResult();
    }

    public static List<TranDauEntity> findAllTranDau(Session session) {
        String sql = "Select d from " + TranDauEntity.class.getName() + " d ";
        Query<TranDauEntity> query = session.createQuery(sql);
        return query.getResultList();
    }
}