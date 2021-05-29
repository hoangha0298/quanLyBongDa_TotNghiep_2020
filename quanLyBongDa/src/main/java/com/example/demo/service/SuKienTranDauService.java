package com.example.demo.service;

import com.example.demo.model.dto.ResponseDTO;
import com.example.demo.model.dto.SuKienTranDauDTO;
import com.example.demo.repository.entityTable.*;
import com.example.demo.repository.repositoryExtend.SuKienTranDauRepository;
import org.hibernate.Session;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class SuKienTranDauService {

    @Autowired
    private SuKienService suKienService;
    @Autowired
    private TranDauService tranDauService;
    @Autowired
    private CauThuDaService cauThuDaService;
    @Autowired
    private SuKienTranDauRepository suKienTranDauRepository;
    @Autowired
    private MessageService messageService;

    private String getMessage(String name) {
        return messageService.getMessage(name);
    }

    public ResponseDTO removeSuKienTranDau(Session session, SuKienTranDauDTO suKienTranDauDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        SuKienTranDauEntity suKienTranDau = suKienTranDauRepository.findSuKienTranDauById(session, suKienTranDauDTO.getId());
        if (suKienTranDau != null) {
            String strRemove = suKienTranDauRepository.removeSuKienTranDau(session, suKienTranDau);
            responseDTO.setCode(1);
            responseDTO.setMessage(getMessage("event_match.delete_success") + strRemove);

        } else {
            responseDTO.setCode(2);
            responseDTO.setMessage(getMessage("event_match.not_exist"));
        }
        return responseDTO;
    }

    public ResponseDTO addSuKienTranDau(Session session, SuKienTranDauDTO suKienTranDauDTO) {
        ResponseDTO responseDTO = new ResponseDTO();
        SuKienTranDauEntity suKienNew = createSuKienTranDauByDTO(session, suKienTranDauDTO);

        String error = validateSuKienTranDau(session, suKienNew);
        if (error == null) {
            int nextId = suKienTranDauRepository.getMaxId(session) + 1;
            suKienNew.setId(nextId);
            SuKienTranDauEntity raSan = createSuKienRaSan(session, suKienNew);

            suKienTranDauRepository.addSuKienTranDau(session, suKienNew);
            if (raSan != null) {
                suKienTranDauRepository.addSuKienTranDau(session, raSan);
            }

            responseDTO.setCode(1); // code = 1 la thanh cong
            responseDTO.setMessage(raSan == null ? getMessage("event_match.add_success") : getMessage("event_match.add_success_auto_add_child"));

        } else {
            responseDTO.setCode(2); // code = 2 la that bai
            responseDTO.setMessage(error);
        }

        return responseDTO;
    }

    // tham số có thể thừa bỏ đi, nhưng không thể thiếu
    public SuKienTranDauEntity createSuKienTranDauByDTO(Session session, SuKienTranDauDTO suKienTranDauDTO) {
        // tham số bắt buộc cần có
        SuKienTranDauEntity suKienTranDauEntity = new SuKienTranDauEntity();
        suKienTranDauEntity.setSuKien(suKienService.findSuKienById(session, suKienTranDauDTO.getIdSuKien()));
        suKienTranDauEntity.setTranDau(tranDauService.findTranDauById(session, suKienTranDauDTO.getIdTranDau()));
        suKienTranDauEntity.setThoiGianDienRa(suKienTranDauDTO.getThoiGianDienRa());
        // tham số có thể có tùy trường hợp
        suKienTranDauEntity.setCauThuDaByNguoiNhan(cauThuDaService.findCauThuDaById(session, suKienTranDauDTO.getIdCauThuNhan()));
        suKienTranDauEntity.setCauThuDaByNguoiTao(cauThuDaService.findCauThuDaById(session, suKienTranDauDTO.getIdCauThuTao()));
        suKienTranDauEntity.setSuKienNguyenNhan(suKienTranDauRepository.findSuKienTranDauById(session, suKienTranDauDTO.getIdSuKienNguyenNhan()));
        // có thể có hoặc k
        suKienTranDauEntity.setMoTa(suKienTranDauDTO.getMoTa());

        switch (suKienTranDauEntity.getSuKien().getKieu()) {
            // thẻ phạt : k có ng tạo, sk ng nhân , nếu là thẻ đỏ hoặc 2 thẻ vàng thì tự tạo sự kiện kết quả là ra sân
            case "thẻ": {
                suKienTranDauEntity.setCauThuDaByNguoiTao(null);
                suKienTranDauEntity.setSuKienNguyenNhan(null);
                break;
            }
            // k có ng nhân
            case "lỗi": {
                suKienTranDauEntity.setSuKienNguyenNhan(null);
                break;
            }
            // có người kiến tạo thì bỏ sk nt
            case "kiến tạo": {
                if (suKienTranDauEntity.getCauThuDaByNguoiTao() != null) {
                    suKienTranDauEntity.setSuKienNguyenNhan(null);
                }
                break;
            }
            // nếu là thẻ đỏ , 2 thẻ vàng thì tự sinh sk ra sân. Trường hợp thêm mới thì ng nhân thay ng phải là thẻ đỏ hoặc 2 thẻ vàng và k có ng ra chỉ có ng vào
            case "thay người": {
                if (suKienTranDauEntity.getCauThuDaByNguoiTao() != null)
                    suKienTranDauEntity.setSuKienNguyenNhan(null);
            }
        }
        return suKienTranDauEntity;
    }

    // sự kiện ra sân khi có 2 thẻ vàng hoặc 1 thẻ đỏ
    public SuKienTranDauEntity createSuKienRaSan(Session session, SuKienTranDauEntity suKienThe) { // suKienThe phải có id
        SuKienTranDauEntity raSan = null;
        SuKienEntity suKien = suKienThe.getSuKien();
        TranDauEntity tranDau = suKienThe.getTranDau();
        CauThuDaEntity nhanThe = suKienThe.getCauThuDaByNguoiNhan();
        int soTheVang = suKienTranDauRepository.yellowCardPlayerInMatch(session, tranDau, nhanThe).size();

        if (suKien.getTen().equals("thẻ đỏ") || (suKien.getTen().equals("thẻ vàng") && soTheVang == 1)) {
            raSan = new SuKienTranDauEntity();
            raSan.setId(suKienThe.getId() + 1);
            raSan.setTranDau(tranDau);
            raSan.setCauThuDaByNguoiTao(nhanThe);
            raSan.setSuKienNguyenNhan(suKienThe);
            raSan.setThoiGianDienRa(suKienThe.getThoiGianDienRa());
            raSan.setSuKien(suKienService.findSuKienById(session, 4)); // 4 la id ra san
        }
        return raSan;
    }

    // validate
    public String validateSuKienTranDau(Session session, SuKienTranDauEntity suKienTranDau) {
        Double phut = suKienTranDau.getThoiGianDienRa();
        SuKienTranDauEntity nguyenNhan = suKienTranDau.getSuKienNguyenNhan();

        if (suKienTranDau.getSuKien() == null) return getMessage("event.error");
        else if (suKienTranDau.getTranDau() == null) return getMessage("match.error");
        else if (phut == null) return getMessage("match.time_error");
        else if (phut < 0) return getMessage("match.time_less_than_zero");
        // su kien ng nhan phai dien ra truoc su kien nay
        else if (nguyenNhan != null && nguyenNhan.getThoiGianDienRa() > phut) {
            return getMessage("event_match.precedes_the_cause");
        }

        return validateTheoLoaiSuKien(session, suKienTranDau);
    }

    private String validateTheoLoaiSuKien(Session session, SuKienTranDauEntity suKienTranDau) {
        SuKienEntity suKien = suKienTranDau.getSuKien();

        // thẻ phạt : cần ng nhận thẻ
        switch (suKien.getKieu()) {
            case "thẻ": {
                if (suKienTranDau.getCauThuDaByNguoiNhan() == null)
                    return getMessage("player.get_card_error");
                else if (!cauThuDangDa(suKienTranDau.getCauThuDaByNguoiNhan()))
                    return getMessage("player.get_card_not_play");
                break;
            }
            // lỗi :cần cả ng phạm lỗi và người đá phạt
            case "lỗi": {
                String result = validateSuKienLoi(session, suKienTranDau);
                if (result != null) return result;
                break;
            }
            // kiến tạo có ng kiến tạo hoặc sự kiện ng nhân (1 trong 2) ưu tiên ng kiến tạo, (sự kiện ng nhân phải là lỗi phạt trực tiếp, góc, đền)
            // cần ng ghi bàn
            case "kiến tạo": {
                String result = validateSuKienKienTao(session, suKienTranDau);
                if (result != null) return result;
                break;
            }
            // cần ng ra hoặc ng vào (1 trong 2 hoặc cả 2) , sk ng nhân có thể có (phải là thẻ đỏ hoặc 2 thẻ vàng)
            case "thay người": {
                String result = validateSuKienThayNguoi(session, suKienTranDau);
                if (result != null) return result;
                break;
            }
        }
        return null;
    }

    private String validateSuKienLoi(Session session, SuKienTranDauEntity suKienTranDau) {
        if (suKienTranDau.getCauThuDaByNguoiNhan() == null)
            return getMessage("player.kick_error");
        else if (!cauThuDangDa(suKienTranDau.getCauThuDaByNguoiNhan()))
            return getMessage("player.kick_not_play");

        if (suKienTranDau.getCauThuDaByNguoiTao() == null)
            return getMessage("player.of_get_mistake_error");
        else if (!cauThuDangDa(suKienTranDau.getCauThuDaByNguoiTao()))
            return getMessage("player.of_get_mistake_not_play");

        if (soSanhCauThu(suKienTranDau) != 3)
            return getMessage("player.same_team");
        return null;
    }

    private String validateSuKienKienTao(Session session, SuKienTranDauEntity suKienTranDau) {
        SuKienTranDauEntity nguyenNhan = suKienTranDau.getSuKienNguyenNhan();
        CauThuDaEntity cauThuGhiBan = suKienTranDau.getCauThuDaByNguoiNhan();
        CauThuDaEntity cauThuKienTao = suKienTranDau.getCauThuDaByNguoiTao();

        if (cauThuGhiBan == null)
            return getMessage("player.scored_error");
        else if (!cauThuDangDa(cauThuGhiBan))
            return getMessage("player.scored_not_play");
            // có người kiến tạo
        else if (cauThuKienTao != null) {
            if (!cauThuDangDa(cauThuKienTao))
                return getMessage("player.assist_not_play");
            if (soSanhCauThu(suKienTranDau) != 2)
                return getMessage("player.different_team");
        }
        // k kiến tạo, k sk ng nhân
        else if (nguyenNhan == null) {
            return getMessage("player_and_event_parent.error_together");
        }
        // k kiến tạo, có sk ng nhân (sk ng nhân là 3 loại lỗi , người sút phạt của ng nhân và ng ghi bàn phải là 1
        else {
            String ten = nguyenNhan.getSuKien().getTen();
            if (!ten.equals("Lỗi phạt trực tiếp") && !ten.equals("Lỗi phạt đền") && !ten.equals("Lỗi phạt góc")) {
                return getMessage("event.different_type_event_parent_of_goal");
            }
            if (!cauThuGhiBan.equals(nguyenNhan.getCauThuDaByNguoiNhan())) {
                return getMessage("player.different");
            }
        }
        return null;
    }

    private String validateSuKienThayNguoi(Session session, SuKienTranDauEntity suKienTranDau) {
        SuKienTranDauEntity nguyenNhan = suKienTranDau.getSuKienNguyenNhan();
        CauThuDaEntity cauThuRaSan = suKienTranDau.getCauThuDaByNguoiTao();
        CauThuDaEntity cauThuVaoSan = suKienTranDau.getCauThuDaByNguoiNhan();

        // có cầu thủ ra sân (k có sk ng nhân)
        if (cauThuRaSan != null) {
            if (!cauThuDangDa(cauThuRaSan))
                return getMessage("player.out_pitch_not_play");
            // có cầu thủ vào sân
            if (cauThuVaoSan != null) {
                if (!cauThuVaoSan.getTrangThai().equals(getMessage("player.status.reserve"))) {
                    return getMessage("player.join_pitch_not_reserve");
                }
            }
        }
        // k có c thủ ra sân thì phải có cầu thủ vào sân , sk ng nhân có hoặc k
        else {
            if (cauThuVaoSan == null) {
                return getMessage("player.join_pitch_error");
            } else {
                if (!cauThuVaoSan.getTrangThai().equals(getMessage("player.status.reserve"))) {
                    return getMessage("player.join_pitch_not_reserve");
                }
                if (nguyenNhan != null) {
                    SuKienEntity suKienEntity = nguyenNhan.getSuKien();
                    if (!suKienEntity.getKieu().equals("thẻ")) {
                        return getMessage("event_match.parent_different_card");
                    } else if (suKienEntity.getTen().equals("thẻ vàng")) {
                        CauThuDaEntity nhanThe = nguyenNhan.getCauThuDaByNguoiNhan();
                        int soTheVang = suKienTranDauRepository.yellowCardPlayerInMatch(session, suKienTranDau.getTranDau(), nhanThe).size();
                        if (soTheVang != 2)
                            return getMessage("event_match.parent_different_tow_card_yellow");
                    }
                }
            }

        }

        return null;
    }

    // 1 trong 2 null trả 0, là 1 ng (chung id) trả về 1, nếu 2 cầu thủ cùng đội trả về 2, khác đội 3
    private int soSanhCauThu(SuKienTranDauEntity suKienTranDau) {
        CauThuDaEntity nhan = suKienTranDau.getCauThuDaByNguoiNhan();
        CauThuDaEntity tao = suKienTranDau.getCauThuDaByNguoiTao();

        if (nhan == null || tao == null) return 0;
        if (nhan.getId() == tao.getId()) return 1;
        if (nhan.getDoiDa().getId() == tao.getDoiDa().getId()) return 2;
        return 3;
    }

    private boolean cauThuDangDa(CauThuDaEntity cauThuDaEntity) {
        if (cauThuDaEntity.getTrangThai().equals(getMessage("player.status.play"))) return true;
        return false;
    }

}
