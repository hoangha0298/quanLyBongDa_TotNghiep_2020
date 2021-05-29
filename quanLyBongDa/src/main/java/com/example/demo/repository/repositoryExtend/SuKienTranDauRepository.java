package com.example.demo.repository.repositoryExtend;

import com.example.demo.repository.entityTable.*;
import com.example.demo.service.MessageService;
import org.hibernate.Criteria;
import org.hibernate.Session;
import org.hibernate.criterion.Projections;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

import java.util.ArrayList;
import java.util.List;

@Repository
public class SuKienTranDauRepository extends com.example.demo.repository.Repository {

    @Autowired
    private CauThuDaRepository cauThuDaRepository;
    @Autowired
    private MessageService messageService;

    private String getMessage(String name) {
        return messageService.getMessage(name);
    }

    public SuKienTranDauEntity findSuKienTranDauById(Session session, int id) {
        String sql = "Select d from " + SuKienTranDauEntity.class.getName() + " d "
                + " Where d.id = :id";
        Query<SuKienTranDauEntity> query = session.createQuery(sql);
        query.setParameter("id", id);
        return (SuKienTranDauEntity) getSingleResult(query);
    }

    // nếu sự kiện là thay người (rời sân, vào sân) thì cập nhật TRANG_THAI của CAU_THU_DA
    public void addSuKienTranDau(Session session, SuKienTranDauEntity suKienTranDauEntity) {
        if (suKienTranDauEntity.getSuKien().getKieu().equals("thay người")) {
            CauThuDaEntity cauThuRa = suKienTranDauEntity.getCauThuDaByNguoiTao();
            CauThuDaEntity cauThuVao = suKienTranDauEntity.getCauThuDaByNguoiNhan();

            if (cauThuRa != null) {
                cauThuRa.setTrangThai(getMessage("player.status.out_pitch"));
                cauThuDaRepository.update(session, cauThuRa);
            }
            if (cauThuVao != null) {
                cauThuVao.setTrangThai(getMessage("player.status.play"));
                cauThuDaRepository.update(session, cauThuVao);
            }
        }

        session.persist(suKienTranDauEntity);
    }

    // xóa sự kiện truyền vào và tất cả sự kiện kết quả ; nếu có sự kiện ng nhân là thẻ vàng, đỏ thì xóa cả sk ng nhân
    // trường hợp đặc biết nếu sk xóa là thẻ vàng và k có sk kết quả kt xem cầu thủ nhận có mấy thẻ vàng , nếu 2 xóa cả sự kiện ra sân (sự kiện kết quả của thẻ vàng còn lại)
    public String removeSuKienTranDau(Session session, SuKienTranDauEntity suKienTranDauRemove) {
        String result = " xóa : ";
        SuKienTranDauEntity suKienNguyenNhan = suKienTranDauRemove.getSuKienNguyenNhan();

        // nếu sk xóa là thẻ vàng (chắc chắn k có sk nguyên nhân)
        if (suKienTranDauRemove.getSuKien().getTen().equals("thẻ vàng")) {
            List<SuKienTranDauEntity> listSuKienTheVang = yellowCardPlayerInMatch(session, suKienTranDauRemove.getTranDau(), suKienTranDauRemove.getCauThuDaByNguoiNhan());
            if (listSuKienTheVang.size() == 2) {
                result += removeAllSuKienKetQua(session, listSuKienTheVang.get(0));
                result += removeAllSuKienKetQua(session, listSuKienTheVang.get(1));
            }
            result += removeAllSuKienAndKetQua(session, suKienTranDauRemove);
        }
        // sk có ng nhân là thẻ thì bắt đầu xóa (lấy gốc) từ sự kiện ng nhân
        else if (suKienNguyenNhan != null && suKienNguyenNhan.getSuKien().getKieu().equals("thẻ")) {
            result += removeAllSuKienAndKetQua(session, suKienNguyenNhan);
        }
        // xóa sk bình thường
        else {
            result += removeAllSuKienAndKetQua(session, suKienTranDauRemove);
        }
        return result;
    }

    // xóa sự kiện truyền vào và tất cả sự kiện kết quả con, cháu , ....
    private String removeAllSuKienAndKetQua(Session session, SuKienTranDauEntity suKienTranDauRemove) {
        String result = "";
        List<SuKienTranDauEntity> listSuKienKetQua = new ArrayList<>(suKienTranDauRemove.getSuKienKetQuas());

        for (SuKienTranDauEntity suKienKetQua : listSuKienKetQua) {
            if (suKienKetQua.getSuKienKetQuas().size() == 0) {
                result += delete(session, suKienKetQua);
            } else {
                result += removeAllSuKienAndKetQua(session, suKienKetQua);
            }
        }
        result += delete(session, suKienTranDauRemove);
        return result;
    }

    // xóa tất cả sự kiện kết quả con, cháu , .... k xóa sk truyền vào
    private String removeAllSuKienKetQua(Session session, SuKienTranDauEntity suKienTranDau) {
        String result = "";
        List<SuKienTranDauEntity> listSuKienKetQua = new ArrayList<>(suKienTranDau.getSuKienKetQuas());

        for (SuKienTranDauEntity suKienKetQua : listSuKienKetQua) {
            if (suKienKetQua.getSuKienKetQuas().size() == 0) {
                result += delete(session, suKienKetQua);
            } else {
                result += removeAllSuKienAndKetQua(session, suKienKetQua);
            }
        }
        return result;
    }

    // nếu sự kiện xóa là ra sân thì cầu thủ ra cập nhật thành đang đá, cầu thủ vào cầu thủ vào cập nhật thành dự bị
    private String delete(Session session, SuKienTranDauEntity suKienTranDauRemove) {
        if (suKienTranDauRemove.getSuKien().getKieu().equals("thay người")) {
            CauThuDaEntity cauThuRa = suKienTranDauRemove.getCauThuDaByNguoiTao();
            CauThuDaEntity cauThuVao = suKienTranDauRemove.getCauThuDaByNguoiNhan();

            if (cauThuRa != null) {
                cauThuRa.setTrangThai(getMessage("player.status.play"));
                cauThuDaRepository.update(session, cauThuRa);
            }
            if (cauThuVao != null) {
                cauThuVao.setTrangThai(getMessage("player.status.reserve"));
                cauThuDaRepository.update(session, cauThuVao);
            }
        }

        session.delete(suKienTranDauRemove);
        return suKienTranDauRemove.getId() + "/" + suKienTranDauRemove.getSuKien().getTen() + ", ";
    }

    public List<SuKienTranDauEntity> yellowCardPlayerInMatch(Session session, TranDauEntity tranDau, CauThuDaEntity cauThuDa) {
        String sql = "Select d from " + SuKienTranDauEntity.class.getName() + " d "
                + " Where d.tranDau = :tranDau and d.cauThuDaByNguoiNhan = :cauThu and d.suKien.ten = 'thẻ vàng'";
        Query<SuKienTranDauEntity> query = session.createQuery(sql);
        query.setParameter("tranDau", tranDau);
        query.setParameter("cauThu", cauThuDa);
        List<SuKienTranDauEntity> result = (List<SuKienTranDauEntity>) getResultList(query);
        if (result == null) return new ArrayList<>();
        return result;
    }

    public int getMaxId(Session session) {
        Criteria criteria = session
                .createCriteria(SuKienTranDauEntity.class)
                .setProjection(Projections.max("id"));
        Integer maxId = (Integer) criteria.uniqueResult();
        if (maxId == null) return 0;
        return maxId;
    }

}
