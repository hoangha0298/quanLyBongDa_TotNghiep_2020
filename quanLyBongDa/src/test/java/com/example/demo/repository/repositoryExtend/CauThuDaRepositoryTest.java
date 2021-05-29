package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.HibernateSessionCommon;
import com.example.demo.repository.entityTable.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Date;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.Arrays;

import static org.assertj.core.api.Assertions.assertThat;

class CauThuDaRepositoryTest {

    private CauThuDaRepository cauThuDaRepositoryUnderTest;
    private Session session;

    @BeforeEach
    void setUp() {
        cauThuDaRepositoryUnderTest = new CauThuDaRepository();
        session = HibernateSessionCommon.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
    }

    // tìm đúng id
    @Test
    void testFindCauThuDaById1() throws CloneNotSupportedException {
        // Setup
        final CauThuDaEntity expectedResult;
        final CauThuDaEntity cauThuDaAdd = new CauThuDaEntity();
        CauThuDaEntity data = cauThuDaRepositoryUnderTest.findCauThuDaById(session, 1);

        cauThuDaAdd.setPhutHieuLuc(0.0);
        cauThuDaAdd.setTrangThai("trangThai");
        cauThuDaAdd.setDoiDa(data.getDoiDa());
        cauThuDaAdd.setViTri(data.getViTri());
        cauThuDaAdd.setCauThu(data.getCauThu());

        expectedResult = (CauThuDaEntity) cauThuDaAdd.clone();

        // Run the test
        session.persist(cauThuDaAdd);
        // tim tat ca cau thu da
        String sql = "Select d from " + CauThuDaEntity.class.getName() + " d ";
        Query<CauThuDaEntity> query = session.createQuery(sql);
        ArrayList<CauThuDaEntity> cauThuDaEntities = new ArrayList<>(query.getResultList());
        cauThuDaEntities.sort((o1, o2) -> o1.getId() - o2.getId());

        int newId = cauThuDaEntities.get(cauThuDaEntities.size() - 1).getId();
        expectedResult.setId(newId);
        final CauThuDaEntity result = cauThuDaRepositoryUnderTest.findCauThuDaById(session, newId);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    // tìm sai id
    @Test
    void testFindCauThuDaById2() throws CloneNotSupportedException {
        // Run the test
        // tim tat ca cau thu da
        String sql = "Select d from " + CauThuDaEntity.class.getName() + " d ";
        Query<CauThuDaEntity> query = session.createQuery(sql);
        ArrayList<CauThuDaEntity> cauThuDaEntities = new ArrayList<>(query.getResultList());
        cauThuDaEntities.sort((o1, o2) -> o1.getId() - o2.getId());

        int idMax = cauThuDaEntities.get(cauThuDaEntities.size() - 1).getId();
        final CauThuDaEntity result = cauThuDaRepositoryUnderTest.findCauThuDaById(session, idMax + 1);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    // tìm id < 0
    @Test
    void testFindCauThuDaById3() throws CloneNotSupportedException {
        // Run the test
        final CauThuDaEntity result = cauThuDaRepositoryUnderTest.findCauThuDaById(session, -1);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    // update thành công
    @Test
    void testUpdate1() throws CloneNotSupportedException {
        // Setup
        final CauThuDaEntity expectedResult  = cauThuDaRepositoryUnderTest.findCauThuDaById(session, 1);
        expectedResult.setTrangThai("trang thai update");
        expectedResult.setPhutHieuLuc(12);

        // Run the test
        cauThuDaRepositoryUnderTest.update(session, expectedResult);
        final CauThuDaEntity result = cauThuDaRepositoryUnderTest.findCauThuDaById(session, 1);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    // update thất bại
    @Test
    void testUpdate2() throws CloneNotSupportedException {
        // Setup
        final CauThuDaEntity data  = cauThuDaRepositoryUnderTest.findCauThuDaById(session, 1);
        data.setTrangThai("trang thai update");
        data.setPhutHieuLuc(12);
        data.setDoiDa(null);
        CauThuDaEntity expectedResult = null; // bằng null vì update trước đó thất bại nên sau đó get data luôn tạo ra exception

        // Run the test
        cauThuDaRepositoryUnderTest.update(session, data);
        final CauThuDaEntity result = cauThuDaRepositoryUnderTest.findCauThuDaById(session, 1);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @AfterEach
    void rollback() {
        session.getTransaction().rollback();
    }

}
