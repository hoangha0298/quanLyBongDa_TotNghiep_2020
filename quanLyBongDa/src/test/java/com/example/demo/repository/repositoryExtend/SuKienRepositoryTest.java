package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.HibernateSessionCommon;
import com.example.demo.repository.entityTable.*;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class SuKienRepositoryTest {

    private SuKienRepository suKienRepositoryUnderTest;
    private Session session;

    @BeforeEach
    void setUp() {
        suKienRepositoryUnderTest = new SuKienRepository();
        session = HibernateSessionCommon.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
    }

    @Test
    void testFindAllSuKien() {
        // Setup
        final List<SuKienEntity> expectedResult = suKienRepositoryUnderTest.findAllSuKien(session);
        final SuKienEntity suKienAdd = new SuKienEntity();
        suKienAdd.setKieu("kieu");
        suKienAdd.setTen("ten");
        suKienAdd.setMoTa("moTa");

        // Run the test
        session.persist(suKienAdd);
        final List<SuKienEntity> result = suKienRepositoryUnderTest.findAllSuKien(session);
        result.sort((o1, o2) -> o1.getId() - o2.getId());

        // set id mới tự sinh từ db cho su kien moi them
        int newId = result.get(result.size() - 1).getId();
        suKienAdd.setId(newId);
        expectedResult.add(suKienAdd);
        expectedResult.sort((o1, o2) -> o1.getId() - o2.getId());

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    // tìm id đúng
    @Test
    void testFindSuKienById1() throws CloneNotSupportedException {
        // Setup
        final SuKienEntity suKienAdd = new SuKienEntity();
        suKienAdd.setKieu("kieu");
        suKienAdd.setTen("ten");
        suKienAdd.setMoTa("moTa");

        // Run the test
        session.persist(suKienAdd);

        final List<SuKienEntity> allSuKien = suKienRepositoryUnderTest.findAllSuKien(session);
        allSuKien.sort((o1, o2) -> o1.getId() - o2.getId());
        // get id mới tự sinh từ db cho su kien moi them
        int newId = allSuKien.get(allSuKien.size() - 1).getId();

        final SuKienEntity expectedResult = (SuKienEntity) suKienAdd.clone();
        expectedResult.setId(newId);
        SuKienEntity result = suKienRepositoryUnderTest.findSuKienById(session, newId);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    // tìm id không tồn tại
    @Test
    void testFindSuKienById2() {
        // Setup
        final List<SuKienEntity> allSuKien = suKienRepositoryUnderTest.findAllSuKien(session);
        allSuKien.sort((o1, o2) -> o1.getId() - o2.getId());
        // get id lớn nhất
        int newId = allSuKien.get(allSuKien.size() - 1).getId();

        // Run the test
        SuKienEntity result = suKienRepositoryUnderTest.findSuKienById(session, newId + 1);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    // tìm id 0
    @Test
    void testFindSuKienById3() {
        // Run the test
        SuKienEntity result = suKienRepositoryUnderTest.findSuKienById(session, 0);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    // tìm id âm
    @Test
    void testFindSuKienById4() {
        // Setup

        // Run the test
        SuKienEntity result = suKienRepositoryUnderTest.findSuKienById(session, -1);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

    @AfterEach
    void rollback() {
        session.getTransaction().rollback();
    }

}
