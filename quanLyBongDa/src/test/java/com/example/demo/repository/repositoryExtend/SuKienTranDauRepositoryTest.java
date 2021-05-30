package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.HibernateSessionCommon;
import com.example.demo.repository.entityTable.*;
import com.example.demo.service.MessageService;
import com.example.demo.service.SuKienService;
import org.hibernate.Session;
import org.junit.jupiter.api.AfterEach;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.junit.jupiter.SpringExtension;

import java.util.ArrayList;
import java.util.List;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.eq;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;

@ExtendWith(SpringExtension.class)
class SuKienTranDauRepositoryTest {

    private final int suKienTranDauIdExist = 7;

    @Mock
    private CauThuDaRepository mockCauThuDaRepository;
    @Mock
    private MessageService mockMessageService;
    @Mock
    private SuKienService mockSuKienService;

    @InjectMocks
    private SuKienTranDauRepository suKienTranDauRepositoryUnderTest;

    private Session session;

    @BeforeEach
    void setUp() {
        initMocks(this);
        session = HibernateSessionCommon.getSessionFactory().getCurrentSession();
        session.getTransaction().begin();
    }

    @Test // id đúng tìm thấy
    void testFindSuKienTranDauById1() {
        // Setup
        final SuKienTranDauEntity data = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, suKienTranDauIdExist);
        final SuKienTranDauEntity expectedResult = new SuKienTranDauEntity();
        expectedResult.setThoiGianDienRa(0.0);
        expectedResult.setMoTa("moTa");
        expectedResult.setSuKienNguyenNhan(data);
        expectedResult.setSuKien(data.getSuKien());
        expectedResult.setCauThuDaByNguoiTao(data.getCauThuDaByNguoiTao());
        expectedResult.setCauThuDaByNguoiNhan(data.getCauThuDaByNguoiNhan());
        expectedResult.setTranDau(data.getTranDau());

        session.persist(expectedResult);

        // Run the test
        int id = suKienTranDauRepositoryUnderTest.getMaxId(session);
        final SuKienTranDauEntity result = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, id);
        expectedResult.setId(id);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test // id không tồn tại , trả về null
    void testFindSuKienTranDauById2() {
        // Setup
        final SuKienTranDauEntity data = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, suKienTranDauIdExist);
        final SuKienTranDauEntity suKienTranDauAdd = new SuKienTranDauEntity();
        suKienTranDauAdd.setThoiGianDienRa(0.0);
        suKienTranDauAdd.setMoTa("moTa");
        suKienTranDauAdd.setSuKienNguyenNhan(data);
        suKienTranDauAdd.setSuKien(data.getSuKien());
        suKienTranDauAdd.setCauThuDaByNguoiTao(data.getCauThuDaByNguoiTao());
        suKienTranDauAdd.setCauThuDaByNguoiNhan(data.getCauThuDaByNguoiNhan());
        suKienTranDauAdd.setTranDau(data.getTranDau());

        session.persist(suKienTranDauAdd);

        // Run the test
        int id = suKienTranDauRepositoryUnderTest.getMaxId(session);
        final SuKienTranDauEntity result = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, id + 1);

        // Verify the results
        assertThat(result).isEqualTo(null);
    }

//    @Test // thêm sự kiện không phải thay người
//    void testAddSuKienTranDau1() throws CloneNotSupportedException {
//        // Setup
//        final SuKienTranDauEntity data = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, suKienTranDauIdExist);
//        final SuKienTranDauEntity suKienTranDauAdd = new SuKienTranDauEntity();
//        suKienTranDauAdd.setThoiGianDienRa(0.0);
//        suKienTranDauAdd.setMoTa("moTa");
//        suKienTranDauAdd.setTranDau(data.getTranDau());
//
//        when(mockMessageService.getMessage("event.type.card.yellow")).thenReturn("1");
//        int idTheVang = Integer.parseInt(mockMessageService.getMessage("event.type.card.yellow"));
//        suKienTranDauAdd.setSuKien(mockSuKienService.findSuKienById(session, idTheVang));
//        int idCauThuDa = 101;
//        suKienTranDauAdd.setCauThuDaByNguoiNhan(mockCauThuDaRepository.findCauThuDaById(session, idCauThuDa));
//
//        // Run the test
//        suKienTranDauRepositoryUnderTest.addSuKienTranDau(session, suKienTranDauAdd);
//        int id = suKienTranDauRepositoryUnderTest.getMaxId(session);
//        SuKienTranDauEntity expectedResult = (SuKienTranDauEntity) suKienTranDauAdd.clone();
//        expectedResult.setId(id);
//        SuKienTranDauEntity result = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, id);
//
//        // Verify the results
//        assertThat(result).isEqualTo(expectedResult);
//    }

//    @Test
//    void testRemoveSuKienTranDau() {
//        // Setup
//        final SuKienTranDauEntity suKienTranDauRemove = new SuKienTranDauEntity();
//        suKienTranDauRemove.setId(0);
//        suKienTranDauRemove.setThoiGianDienRa(0.0);
//        suKienTranDauRemove.setMoTa("moTa");
//
//        when(mockMessageService.getMessage("msgCode")).thenReturn("result");
//
//        // Run the test
//        final String result = suKienTranDauRepositoryUnderTest.removeSuKienTranDau(session, suKienTranDauRemove);
//
//        // Verify the results
//        assertThat(result).isEqualTo("result");
//        verify(mockCauThuDaRepository).update(any(Session.class), eq(new CauThuDaEntity()));
//    }

    @Test
    void testYellowCardPlayerInMatch() {
        // Setup
        final TranDauEntity tranDau = new TranDauEntity();
        final CauThuDaEntity cauThuDa = new CauThuDaEntity();
        final List<SuKienTranDauEntity> expectedResult = new ArrayList<>();


        // Run the test
        final List<SuKienTranDauEntity> result = suKienTranDauRepositoryUnderTest.yellowCardPlayerInMatch(session, tranDau, cauThuDa);

        // Verify the results
        assertThat(result).isEqualTo(expectedResult);
    }

    @Test
    void testGetMaxId() {
        // Setup
        final int nextId = suKienTranDauRepositoryUnderTest.getMaxId(session) + 1;
        final SuKienTranDauEntity data = suKienTranDauRepositoryUnderTest.findSuKienTranDauById(session, suKienTranDauIdExist);
        final SuKienTranDauEntity suKienTranDauAdd = new SuKienTranDauEntity();

        suKienTranDauAdd.setId(nextId);
        suKienTranDauAdd.setThoiGianDienRa(0.0);
        suKienTranDauAdd.setMoTa("moTa");
        suKienTranDauAdd.setSuKienNguyenNhan(data);
        suKienTranDauAdd.setSuKien(data.getSuKien());
        suKienTranDauAdd.setCauThuDaByNguoiTao(data.getCauThuDaByNguoiTao());
        suKienTranDauAdd.setCauThuDaByNguoiNhan(data.getCauThuDaByNguoiNhan());
        suKienTranDauAdd.setTranDau(data.getTranDau());

        session.persist(suKienTranDauAdd);

        // Run the test
        final int result = suKienTranDauRepositoryUnderTest.getMaxId(session);

        // Verify the results
        assertThat(result).isEqualTo(nextId);
    }

    @AfterEach
    void rollback() {
        session.getTransaction().rollback();
    }

}
