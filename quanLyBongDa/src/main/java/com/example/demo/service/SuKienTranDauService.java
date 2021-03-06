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

    // tham s??? c?? th??? th???a b??? ??i, nh??ng kh??ng th??? thi???u
    public SuKienTranDauEntity createSuKienTranDauByDTO(Session session, SuKienTranDauDTO suKienTranDauDTO) {
        // tham s??? b???t bu???c c???n c??
        SuKienTranDauEntity suKienTranDauEntity = new SuKienTranDauEntity();
        suKienTranDauEntity.setSuKien(suKienService.findSuKienById(session, suKienTranDauDTO.getIdSuKien()));
        suKienTranDauEntity.setTranDau(tranDauService.findTranDauById(session, suKienTranDauDTO.getIdTranDau()));
        suKienTranDauEntity.setThoiGianDienRa(suKienTranDauDTO.getThoiGianDienRa());
        // tham s??? c?? th??? c?? t??y tr?????ng h???p
        suKienTranDauEntity.setCauThuDaByNguoiNhan(cauThuDaService.findCauThuDaById(session, suKienTranDauDTO.getIdCauThuNhan()));
        suKienTranDauEntity.setCauThuDaByNguoiTao(cauThuDaService.findCauThuDaById(session, suKienTranDauDTO.getIdCauThuTao()));
        suKienTranDauEntity.setSuKienNguyenNhan(suKienTranDauRepository.findSuKienTranDauById(session, suKienTranDauDTO.getIdSuKienNguyenNhan()));
        // c?? th??? c?? ho???c k
        suKienTranDauEntity.setMoTa(suKienTranDauDTO.getMoTa());

        switch (suKienTranDauEntity.getSuKien().getKieu()) {
            // th??? ph???t : k c?? ng t???o, sk ng nh??n , n???u l?? th??? ????? ho???c 2 th??? v??ng th?? t??? t???o s??? ki???n k???t qu??? l?? ra s??n
            case "th???": {
                suKienTranDauEntity.setCauThuDaByNguoiTao(null);
                suKienTranDauEntity.setSuKienNguyenNhan(null);
                break;
            }
            // k c?? ng nh??n
            case "l???i": {
                suKienTranDauEntity.setSuKienNguyenNhan(null);
                break;
            }
            // c?? ng?????i ki???n t???o th?? b??? sk nt
            case "ki???n t???o": {
                if (suKienTranDauEntity.getCauThuDaByNguoiTao() != null) {
                    suKienTranDauEntity.setSuKienNguyenNhan(null);
                }
                break;
            }
            // n???u l?? th??? ????? , 2 th??? v??ng th?? t??? sinh sk ra s??n. Tr?????ng h???p th??m m???i th?? ng nh??n thay ng ph???i l?? th??? ????? ho???c 2 th??? v??ng v?? k c?? ng ra ch??? c?? ng v??o
            case "thay ng?????i": {
                if (suKienTranDauEntity.getCauThuDaByNguoiTao() != null)
                    suKienTranDauEntity.setSuKienNguyenNhan(null);
            }
        }
        return suKienTranDauEntity;
    }

    // s??? ki???n ra s??n khi c?? 2 th??? v??ng ho???c 1 th??? ?????
    public SuKienTranDauEntity createSuKienRaSan(Session session, SuKienTranDauEntity suKienThe) { // suKienThe ph???i c?? id
        SuKienTranDauEntity raSan = null;
        SuKienEntity suKien = suKienThe.getSuKien();
        TranDauEntity tranDau = suKienThe.getTranDau();
        CauThuDaEntity nhanThe = suKienThe.getCauThuDaByNguoiNhan();
        int soTheVang = suKienTranDauRepository.yellowCardPlayerInMatch(session, tranDau, nhanThe).size();

        if (suKien.getTen().equals("th??? ?????") || (suKien.getTen().equals("th??? v??ng") && soTheVang == 1)) {
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

        // th??? ph???t : c???n ng nh???n th???
        switch (suKien.getKieu()) {
            case "th???": {
                if (suKienTranDau.getCauThuDaByNguoiNhan() == null)
                    return getMessage("player.get_card_error");
                else if (!cauThuDangDa(suKienTranDau.getCauThuDaByNguoiNhan()))
                    return getMessage("player.get_card_not_play");
                break;
            }
            // l???i :c???n c??? ng ph???m l???i v?? ng?????i ???? ph???t
            case "l???i": {
                String result = validateSuKienLoi(session, suKienTranDau);
                if (result != null) return result;
                break;
            }
            // ki???n t???o c?? ng ki???n t???o ho???c s??? ki???n ng nh??n (1 trong 2) ??u ti??n ng ki???n t???o, (s??? ki???n ng nh??n ph???i l?? l???i ph???t tr???c ti???p, g??c, ?????n)
            // c???n ng ghi b??n
            case "ki???n t???o": {
                String result = validateSuKienKienTao(session, suKienTranDau);
                if (result != null) return result;
                break;
            }
            // c???n ng ra ho???c ng v??o (1 trong 2 ho???c c??? 2) , sk ng nh??n c?? th??? c?? (ph???i l?? th??? ????? ho???c 2 th??? v??ng)
            case "thay ng?????i": {
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
            // c?? ng?????i ki???n t???o
        else if (cauThuKienTao != null) {
            if (!cauThuDangDa(cauThuKienTao))
                return getMessage("player.assist_not_play");
            if (soSanhCauThu(suKienTranDau) != 2)
                return getMessage("player.different_team");
        }
        // k ki???n t???o, k sk ng nh??n
        else if (nguyenNhan == null) {
            return getMessage("player_and_event_parent.error_together");
        }
        // k ki???n t???o, c?? sk ng nh??n (sk ng nh??n l?? 3 lo???i l???i , ng?????i s??t ph???t c???a ng nh??n v?? ng ghi b??n ph???i l?? 1
        else {
            String ten = nguyenNhan.getSuKien().getTen();
            if (!ten.equals("L???i ph???t tr???c ti???p") && !ten.equals("L???i ph???t ?????n") && !ten.equals("L???i ph???t g??c")) {
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

        // c?? c???u th??? ra s??n (k c?? sk ng nh??n)
        if (cauThuRaSan != null) {
            if (!cauThuDangDa(cauThuRaSan))
                return getMessage("player.out_pitch_not_play");
            // c?? c???u th??? v??o s??n
            if (cauThuVaoSan != null) {
                if (!cauThuVaoSan.getTrangThai().equals(getMessage("player.status.reserve"))) {
                    return getMessage("player.join_pitch_not_reserve");
                }
            }
        }
        // k c?? c th??? ra s??n th?? ph???i c?? c???u th??? v??o s??n , sk ng nh??n c?? ho???c k
        else {
            if (cauThuVaoSan == null) {
                return getMessage("player.join_pitch_error");
            } else {
                if (!cauThuVaoSan.getTrangThai().equals(getMessage("player.status.reserve"))) {
                    return getMessage("player.join_pitch_not_reserve");
                }
                if (nguyenNhan != null) {
                    SuKienEntity suKienEntity = nguyenNhan.getSuKien();
                    if (!suKienEntity.getKieu().equals("th???")) {
                        return getMessage("event_match.parent_different_card");
                    } else if (suKienEntity.getTen().equals("th??? v??ng")) {
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

    // 1 trong 2 null tr??? 0, l?? 1 ng (chung id) tr??? v??? 1, n???u 2 c???u th??? c??ng ?????i tr??? v??? 2, kh??c ?????i 3
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
