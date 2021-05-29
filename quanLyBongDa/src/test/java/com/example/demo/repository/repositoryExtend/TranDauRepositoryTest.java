package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.HibernateSessionCommon;
import com.example.demo.repository.entityTable.*;
import org.hibernate.Session;
import org.hibernate.query.Query;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;
import java.text.ParseException;
import java.util.ArrayList;
import java.text.SimpleDateFormat;
import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

class TranDauRepositoryTest {

    private TranDauRepository tranDauRepositoryUnderTest;
    private Session session;

    @BeforeEach
    void setUp() {
        tranDauRepositoryUnderTest = new TranDauRepository();
        session = HibernateSessionCommon.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
    }

    @Test
    void testFindTranDauById() throws ParseException, CloneNotSupportedException {
        // Setup
        final TranDauEntity expectedResult;
        final TranDauEntity tranDauAdd = new TranDauEntity();
        TranDauEntity data = tranDauRepositoryUnderTest.findTranDauById(session, 1);
        String date = "2018-02-13 07:00";
        SimpleDateFormat formatter = new SimpleDateFormat("yyyy-MM-dd hh:ss");
        Date dateStr = formatter.parse(date);
        Timestamp timestamp = new Timestamp(dateStr.getTime());

        tranDauAdd.setThoiGianBatDau(timestamp);
        tranDauAdd.setSoKhanGiaDenSan(0);
        tranDauAdd.setThoiGianTranDau(0.0);
        tranDauAdd.setSoBanThangDoiNha(0);
        tranDauAdd.setSoBanThangDoiKhach(0);
        tranDauAdd.setTrangThai("trangThai");
        tranDauAdd.setSanBong(data.getSanBong());
        tranDauAdd.setVongDau(data.getVongDau());

        expectedResult = (TranDauEntity) tranDauAdd.clone();

        // Run the test
        session.persist(tranDauAdd);
        // tim tat ca tran dau
        String sql = "Select d from " + TranDauEntity.class.getName() + " d ";
        Query<TranDauEntity> query = session.createQuery(sql);
        ArrayList<TranDauEntity> tranDauEntityList = new ArrayList<>(query.getResultList());
        tranDauEntityList.sort((o1, o2) -> o1.getId() - o2.getId());

        int newId = tranDauEntityList.get(tranDauEntityList.size() - 1).getId();
        expectedResult.setId(newId);
        final TranDauEntity result = tranDauRepositoryUnderTest.findTranDauById(session, newId);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @AfterEach
    void rollback() {
        session.getTransaction().rollback();
    }
}
