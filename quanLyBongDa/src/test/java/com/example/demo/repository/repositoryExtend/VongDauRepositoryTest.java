package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.HibernateSessionCommon;
import com.example.demo.repository.entityTable.*;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;

class VongDauRepositoryTest {

    private VongDauRepository vongDauRepositoryUnderTest;
    private Session session;

    @BeforeEach
    void setUp() {
        vongDauRepositoryUnderTest = new VongDauRepository();
        session = HibernateSessionCommon.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
    }

    @Test
    void testFindAllVongDau() {
        // Setup
        final List<VongDauEntity> expectedResult = vongDauRepositoryUnderTest.findAllVongDau(session);
        final VongDauEntity vongDauAdd = new VongDauEntity();
        vongDauAdd.setId(0);
        vongDauAdd.setVong(0);
        vongDauAdd.setMoTa("moTa");
        vongDauAdd.setGiaiDau(expectedResult.get(0).getGiaiDau());

        // Run the test
        session.persist(vongDauAdd);
        final List<VongDauEntity> result = vongDauRepositoryUnderTest.findAllVongDau(session);
        result.sort((o1, o2) -> o1.getId() - o2.getId());

        int newId = result.get(result.size() - 1).getId();
        vongDauAdd.setId(newId);
        expectedResult.add(vongDauAdd);
        expectedResult.sort((o1, o2) -> o1.getId() - o2.getId());

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @AfterEach
    void rollback() {
        session.getTransaction().rollback();
    }
}
