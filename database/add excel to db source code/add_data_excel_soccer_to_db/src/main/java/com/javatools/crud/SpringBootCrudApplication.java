package com.javatools.crud;

import com.javatools.crud.business.excel.SheetToObject;
import com.javatools.crud.business.excel.trandau.CauThuSheet;
import com.javatools.crud.business.excel.trandau.DoiBongSheet;
import com.javatools.crud.business.excel.trandau.GiaiDauSheet;
import com.javatools.crud.business.excel.trandau.TranDauSheet;
import com.javatools.crud.entitydatabase.*;
import com.javatools.crud.hibernate.DataUtils;
import com.javatools.crud.hibernate.HibernateUtils;
import com.javatools.crud.model.CauThu;
import com.javatools.crud.model.DoiBong;
import com.javatools.crud.model.GiaiDau;
import com.javatools.crud.model.TranDau;
import org.hibernate.Session;
import org.hibernate.SessionFactory;
import org.springframework.boot.CommandLineRunner;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.annotation.Bean;

import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.sql.Date;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.*;

@SpringBootApplication
public class SpringBootCrudApplication {

    public static void main(String[] args) {
        SpringApplication.run(SpringBootCrudApplication.class, args);
    }

    @Bean
    public CommandLineRunner runAfterStart() {
        return new CommandLineRunner() {
            @Override
            public void run(String... args) throws Exception {
                System.out.println("test");

                SessionFactory factory = HibernateUtils.getSessionFactory();
                Session session = factory.getCurrentSession();
                try {
                    session.getTransaction().begin();

//                    addDataCustom(session);
                    addExcelData(session);

//                    test(session);

                    System.out.println("- Calling commit...");
                    session.getTransaction().commit();
                    System.out.println("- Commit OK");
                } catch (Exception e) {
                    e.printStackTrace();
                    session.getTransaction().rollback();
                }

            }
        };
    }

    private void test(Session session) {
//        ArrayList<Object> giaiDaus = getAllRowOfExcel("D:\\data bong da 2\\england-premier-league-league-2018-to-2019-stats.xlsx", 0, GiaiDauSheet.class);
//        ArrayList<Object> doiBongs = getAllRowOfExcel("D:\\data bong da 2\\england-premier-league-teams-2018-to-2019-stats.xlsx", 0, DoiBongSheet.class);
//        ArrayList<Object> cauThus = getAllRowOfExcel("D:\\data bong da 2\\england-premier-league-players-2018-to-2019-stats.xlsx", 0, CauThuSheet.class);
//        ArrayList<Object> tranDaus = getAllRowOfExcel("D:\\data bong da 2\\england-premier-league-matches-2018-to-2019-stats.xlsx", 0, TranDauSheet.class);
//        HashMap<String, String> sanBong_DoiBong = createSanBongs_DoiBongs(tranDaus);

        List<DoiBongEntity> doiBongEntities = DataUtils.findAllDoiBong(session);
        for (DoiBongEntity db : doiBongEntities) {
            System.out.println("==================== " + db.getTen() + " ====================");
            System.out.println(db.getChiSoCauThus().size());
        }


    }

    public void addDataCustom(Session session) {

        // vị trí
        int id = 1;
        ViTriEntity viTriEntity = new ViTriEntity();
        viTriEntity.setId(id++);
        viTriEntity.setKieu("đội bóng");
        viTriEntity.setTen("đội khách");
        session.persist(viTriEntity);

        ViTriEntity viTriEntity1 = new ViTriEntity();
        viTriEntity1.setId(id++);
        viTriEntity1.setKieu("đội bóng");
        viTriEntity1.setTen("đội nhà");
        session.persist(viTriEntity1);

        ViTriEntity viTriEntity2 = new ViTriEntity();
        viTriEntity2.setId(id++);
        viTriEntity2.setKieu("trọng tài");
        viTriEntity2.setTen("trọng tài chính");
        session.persist(viTriEntity2);

        ViTriEntity viTriEntity3 = new ViTriEntity();
        viTriEntity3.setId(id++);
        viTriEntity3.setKieu("trọng tài");
        viTriEntity3.setTen("trọng tài biên");
        session.persist(viTriEntity3);

        ViTriEntity viTriEntity4 = new ViTriEntity();
        viTriEntity4.setId(id++);
        viTriEntity4.setKieu("trọng tài");
        viTriEntity4.setTen("trọng tài bàn");
        session.persist(viTriEntity4);

        ViTriEntity viTriEntity5 = new ViTriEntity();
        viTriEntity5.setId(id++);
        viTriEntity5.setKieu("cầu thủ");
        viTriEntity5.setTen("thủ môn");
        session.persist(viTriEntity5);

        ViTriEntity viTriEntity6 = new ViTriEntity();
        viTriEntity6.setId(id++);
        viTriEntity6.setKieu("cầu thủ");
        viTriEntity6.setTen("hậu vệ");
        session.persist(viTriEntity6);

        ViTriEntity viTriEntity7 = new ViTriEntity();
        viTriEntity7.setId(id++);
        viTriEntity7.setKieu("cầu thủ");
        viTriEntity7.setTen("tiền vệ");
        session.persist(viTriEntity7);

        ViTriEntity viTriEntity8 = new ViTriEntity();
        viTriEntity8.setId(id++);
        viTriEntity8.setKieu("cầu thủ");
        viTriEntity8.setTen("tiền đạo");
        session.persist(viTriEntity8);

        // Sự kiện
        int idSuKien = 1;
        SuKienEntity suKienEntity = new SuKienEntity();
        suKienEntity.setId(idSuKien++);
        suKienEntity.setKieu("thẻ");
        suKienEntity.setTen("thẻ vàng");
        session.persist(suKienEntity);

        SuKienEntity suKienEntity2 = new SuKienEntity();
        suKienEntity2.setId(idSuKien++);
        suKienEntity2.setKieu("thẻ");
        suKienEntity2.setTen("thẻ đỏ");
        session.persist(suKienEntity2);

        // không cân sự kiện bàn thắng, thay bằng sk kiến tạo , cột người nhận kiến tạo chính là người ghi bàn
        // trường hợp đá phạt tự ghi bàn thì ng kiến tạo = null
        SuKienEntity suKienEntity3 = new SuKienEntity();
        suKienEntity3.setId(idSuKien++);
        suKienEntity3.setKieu("kiến tạo");
        suKienEntity3.setTen("kiến tạo(ghi bàn)");
        session.persist(suKienEntity3);

        SuKienEntity suKienEntity5 = new SuKienEntity();
        suKienEntity5.setId(idSuKien++);
        suKienEntity5.setKieu("thay người");
        suKienEntity5.setTen("thay người");
        session.persist(suKienEntity5);

        // có thể comment tình huống lỗi
        SuKienEntity suKienEntity6 = new SuKienEntity();
        suKienEntity6.setId(idSuKien++);
        suKienEntity6.setKieu("lỗi");
        suKienEntity6.setTen("Lỗi phạt gián tiếp");
        session.persist(suKienEntity6);

        SuKienEntity suKienEntity7 = new SuKienEntity();
        suKienEntity7.setId(idSuKien++);
        suKienEntity7.setKieu("lỗi");
        suKienEntity7.setTen("Lỗi phạt trực tiếp");
        session.persist(suKienEntity7);

        SuKienEntity suKienEntity8 = new SuKienEntity();
        suKienEntity8.setId(idSuKien++);
        suKienEntity8.setKieu("lỗi");
        suKienEntity8.setTen("Lỗi phạt đền");
        session.persist(suKienEntity8);

        SuKienEntity suKienEntity9 = new SuKienEntity();
        suKienEntity9.setId(idSuKien++);
        suKienEntity9.setKieu("lỗi");
        suKienEntity9.setTen("Lỗi phạt góc");
        session.persist(suKienEntity9);

    }

    public ArrayList<Object> getAllRowOfExcel(String path, int sheet, Class sheetClass) {
        ArrayList<Object> datas = new ArrayList<>();
        try {
            InputStream excel = new FileInputStream(path);
            SheetToObject sheetToObject = (SheetToObject) sheetClass.getDeclaredConstructor(InputStream.class, Integer.class).newInstance(excel, 0);
            datas = sheetToObject.loadAllRows();
        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (FileNotFoundException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        } catch (InstantiationException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        }

        return datas;
    }

    public void hibernateAddEntity(List<Object> objectList, Session session) {
        for (Object o : objectList) {
            session.persist(o);
        }
    }

    public void addExcelData(Session session) {

        ArrayList<Object> giaiDaus = getAllRowOfExcel("D:\\data bong da 3\\england-premier-league-league-2018-to-2019-stats.xlsx", 0, GiaiDauSheet.class);
        ArrayList<Object> doiBongs = getAllRowOfExcel("D:\\data bong da 3\\england-premier-league-teams-2018-to-2019-stats.xlsx", 0, DoiBongSheet.class);
        ArrayList<Object> cauThus = getAllRowOfExcel("D:\\data bong da 3\\england-premier-league-players-2018-to-2019-stats.xlsx", 0, CauThuSheet.class);
        ArrayList<Object> tranDaus = getAllRowOfExcel("D:\\data bong da 3\\england-premier-league-matches-2018-to-2019-stats.xlsx", 0, TranDauSheet.class);
        HashMap<String, String> doiBongs_SanBongs = createSanBongs_DoiBongs(tranDaus);

//        hibernateAddEntity(createGiaiDaus(giaiDaus), session);
//        hibernateAddEntity(createVongDaus(session), session);
//        hibernateAddEntity(createSanBongs(doiBongs_SanBongs), session);
//        hibernateAddEntity(createDoiBongs(doiBongs, doiBongs_SanBongs, session), session);
//        hibernateAddEntity(createCauThus(cauThus, session), session);
//        hibernateAddEntity(createChiSoCauThus(cauThus, session), session);
//        hibernateAddEntity(createTrongTais(tranDaus), session);
//        hibernateAddEntity(createTranDaus(tranDaus, session), session);
//        hibernateAddEntity(createDoiDas(tranDaus, session), session);

        // phải add cauThuDa sau vì dùng tới tranDau lúc này chưa commit
        hibernateAddEntity(createCauThuDa(session), session);
    }

    public HashMap<String, String> createSanBongs_DoiBongs(List<Object> tranDaus) {
        HashMap<String, String> result = new HashMap<>();
        for (Object item : tranDaus) {
            TranDau value = (TranDau) item;
            result.put(value.getDoiNha(), value.getSanBong());
//            if (value.getSanBong().equals("Wembley Stadium (London)")) {
//                System.out.println("======================== " + value.getDoiNha() + " =========================");
//            }
        }
        return result;
    }

    public List<Object> createGiaiDaus(List<Object> giaiDaus) {
        ArrayList<Object> result = new ArrayList<>();
        int id = 1;
        for (Object item : giaiDaus) {
            GiaiDau value = (GiaiDau) item;
            GiaiDauEntity giaiDauEntity = new GiaiDauEntity();
            giaiDauEntity.setId(id++);
            giaiDauEntity.setTen(value.getTen());
            giaiDauEntity.setMuaGiai(value.getMuaGiai());
            giaiDauEntity.setSoDoiThamDu(value.getSoDoiThamDu());
            giaiDauEntity.setSoVongDau(value.getSoVongDau());
            result.add(giaiDauEntity);
        }
        return result;
    }

    public List<Object> createVongDaus(Session session) {
        ArrayList<Object> result = new ArrayList<>();
        GiaiDauEntity giaiDauEntity = DataUtils.findGiaiDau(session, 1);
//        int id = 1;
        for (int i = 0; i < giaiDauEntity.getSoVongDau(); i++) {
            VongDauEntity vongDauEntity = new VongDauEntity();
            vongDauEntity.setId(i + 1);
            vongDauEntity.setVong(i + 1);
            vongDauEntity.setGiaiDau(giaiDauEntity);
            result.add(vongDauEntity);
        }
        return result;
    }

    public List<Object> createSanBongs(HashMap<String, String> doiBongs_SanBongs) {
        List<Object> result = new ArrayList<>();
        List<String> sanBongs = new ArrayList<>(doiBongs_SanBongs.values());
        Collections.sort(sanBongs);

        int id = 1;
        for (String ten : sanBongs) {
            SanBongEntity sanBongEntity = new SanBongEntity();
            sanBongEntity.setId(id++);
            sanBongEntity.setTen(ten);
            sanBongEntity.setTinhTrang("tốt");
            sanBongEntity.setSucChua(100000);
            sanBongEntity.setDiaChi("England");
            sanBongEntity.setTrangThaiBanGhi("1");
            result.add(sanBongEntity);
        }

        return result;
    }

    public List<Object> createDoiBongs(List<Object> doiBongs, HashMap<String, String> doiBongs_SanBongs, Session session) {
        List<Object> result = new ArrayList<>();
        GiaiDauEntity giaiDauEntity = DataUtils.findGiaiDau(session, 1);
        int id = 1;
        for (Object item : doiBongs) {
            DoiBong value = (DoiBong) item;
            DoiBongEntity doiBongEntity = new DoiBongEntity();
            doiBongEntity.setId(id++);
            doiBongEntity.setLogo(value.getTen() + ".png");
            doiBongEntity.setTen(value.getTen());
            doiBongEntity.setTrangPhucTruyenThong(value.getTen() + "_ao.png");
            doiBongEntity.setQuocTich(value.getQuocTich());
            doiBongEntity.setDiaChi("England");

            doiBongEntity.setGiaiDau(giaiDauEntity);
            doiBongEntity.setSanBong(DataUtils.findSanBongByName(session, doiBongs_SanBongs.get(value.getTen())));
            doiBongEntity.setTrangThaiBanGhi("1");
            result.add(doiBongEntity);
        }
        return result;
    }

    public List createTrongTais(List<Object> tranDaus) {
        ArrayList<TrongTaiEntity> result = new ArrayList<>();
        ArrayList<String> tens = new ArrayList<>();

        for (Object item : tranDaus) {
            TranDau value = (TranDau) item;
            if (!tens.contains(value.getTrongTai())) tens.add(value.getTrongTai());
        }
        Collections.sort(tens);

        int id = 1;
        for (String ten : tens) {
            TrongTaiEntity trongTaiEntity = new TrongTaiEntity();
            trongTaiEntity.setId(id++);
            trongTaiEntity.setTen(ten);
            trongTaiEntity.setSoTranDaBat(200 + id * 3);
            trongTaiEntity.setSoTranBatChinh(trongTaiEntity.getSoTranDaBat() / 2);
            trongTaiEntity.setSoDienThoai("03545" + id * 7 + "23");
            trongTaiEntity.setTrangThaiBanGhi("1");

            result.add(trongTaiEntity);
        }
        return result;
    }

    public List createTranDaus(List<Object> tranDaus, Session session) {
        List<TranDauEntity> result = new ArrayList<>();
        int id = 1;
        for (Object item : tranDaus) {
            TranDau value = (TranDau) item;
            TranDauEntity tranDauEntity = new TranDauEntity();
            tranDauEntity.setId(id++);
            tranDauEntity.setThoiGianBatDau(value.getThoiGianBatDau());
            tranDauEntity.setSanBong(DataUtils.findSanBongByName(session, value.getSanBong()));
            tranDauEntity.setVongDau(DataUtils.findVongDau(session, value.getVongDau()));
            tranDauEntity.setSoKhanGiaDenSan(value.getSoNguoiDenSan());
            tranDauEntity.setThoiGianTranDau((double) (90 + (id % 10)));
//            tranDauEntity.setSoBanThangDoiNha(value.getBanThangDoiNha());
//            tranDauEntity.setSoBanThangDoiKhach(value.getBanThangDoiKhach());
            tranDauEntity.setSoBanThangDoiNha(0);
            tranDauEntity.setSoBanThangDoiKhach(0);
            tranDauEntity.setTrangThai("chưa bắt đầu");

            result.add(tranDauEntity);
        }
        return result;
    }

//    public List createTrongTaiTranDau(List<Object> tranDaus, Session session) {
//        List<DoiDaEntity> result = new ArrayList<>();
//        int idDoiDa = 1;
//
//        for (Object item : tranDaus) {
//            TranDau value = (TranDau) item;
//
//            result.add(doiKhach);
//        }
//        return result;
//    }

    public List createDoiDas(List<Object> tranDaus, Session session) {
        List<DoiDaEntity> result = new ArrayList<>();
        int idDoiDa = 1;

        for (Object item : tranDaus) {
            TranDau value = (TranDau) item;
            TranDauEntity tranDauEntity = DataUtils.findTranDauById(session, value.getId());

            DoiDaEntity doiNha = new DoiDaEntity();
            doiNha.setId(idDoiDa++);
            doiNha.setTranDau(tranDauEntity);
            doiNha.setDoiBong(DataUtils.findDoiBongByName(session, value.getDoiNha()));
            doiNha.setViTri(DataUtils.findViTriByKieuAndTen(session, "đội bóng", "đội nhà"));
            result.add(doiNha);

            DoiDaEntity doiKhach = new DoiDaEntity();
            doiKhach.setId(idDoiDa++);
            doiKhach.setTranDau(tranDauEntity);
            doiKhach.setDoiBong(DataUtils.findDoiBongByName(session, value.getDoiKhach()));
            doiKhach.setViTri(DataUtils.findViTriByKieuAndTen(session, "đội bóng", "đội khách"));
            result.add(doiKhach);
        }
        return result;
    }

    public List createCauThuDa(Session session) {
        List<CauThuDaEntity> result = new ArrayList<>();
        List<DoiDaEntity> doiDaEntities = DataUtils.findAllDoiDa(session);
        int idCauThuDa = 1;

        for (DoiDaEntity doiDaEntity : doiDaEntities) {
            // danh sach chi so cau thu cua doi bong
            List<ChiSoCauThuEntity> chiSoCauThuEntities = new ArrayList<>(doiDaEntity.getDoiBong().getChiSoCauThus());
            List<CauThuEntity> cauThuEntities = randomListCauThu(chiSoCauThuEntities);
            System.out.println(cauThuEntities.size());

            for (int i = 0; i < cauThuEntities.size(); i++) {
                CauThuEntity cauThuEntity = cauThuEntities.get(i);
                CauThuDaEntity cauThuDa = new CauThuDaEntity();
                cauThuDa.setId(idCauThuDa++);
                cauThuDa.setCauThu(cauThuEntity);
                cauThuDa.setDoiDa(doiDaEntity);
                // 11 cầu thủ đá chính
                if (i < 11)
                    cauThuDa.setTrangThai("đang đá");
                // 3 - 7 cầu thủ dự bị
                else
                    cauThuDa.setTrangThai("dự bị");
                cauThuDa.setPhutHieuLuc((double) 0);

                result.add(cauThuDa);
            }
        }
        return result;
    }

    // lấy random (11 + 3) đến (11 + 7) cau thu
    public List<CauThuEntity> randomListCauThu(List<ChiSoCauThuEntity> chiSoCauThuEntities) {
        List<CauThuEntity> result = new ArrayList<>();

        // random so luong cau thu 14 - 18
        int size = 9999999;
        while (size > chiSoCauThuEntities.size()) {
            size = new Random().nextInt(5) + 14;
        }

        // random danh sach cau thu
        while (result.size() <= size) {
            CauThuEntity random = chiSoCauThuEntities.get(new Random().nextInt(chiSoCauThuEntities.size())).getCauThu();
            if (!result.contains(random)) result.add(random);
        }

        return result;
    }

    public List createCauThus(List<Object> cauThus, Session session) {
        List<CauThuEntity> result = new ArrayList<>();
//        int idCauThu = 1;

        for (Object item : cauThus) {
            CauThu value = (CauThu) item;
            CauThuEntity cauThuEntity = new CauThuEntity();
            cauThuEntity.setId(value.getId());
            cauThuEntity.setGioiTinh(1); // 1 = nam
            cauThuEntity.setTen(value.getHoVaTen());
            cauThuEntity.setNgaySinh(new Date(value.getNgaySinh().getTime()));
            cauThuEntity.setQuocTich(value.getQuocTich());

            result.add(cauThuEntity);
        }
        return result;
    }

    public List createChiSoCauThus(List<Object> cauThus, Session session) {
        List<ChiSoCauThuEntity> result = new ArrayList<>();
        int idChiSoCauThu = 1;

        for (Object item : cauThus) {
            CauThu value = (CauThu) item;

            ChiSoCauThuEntity chiSoCauThuEntity = new ChiSoCauThuEntity();
            chiSoCauThuEntity.setId(idChiSoCauThu++);
            chiSoCauThuEntity.setCauThu(DataUtils.findCauThuById(session, value.getId()));
            chiSoCauThuEntity.setDoiBong(DataUtils.findDoiBongByName(session, value.getDoiBong()));
            chiSoCauThuEntity.setTinhTrangSucKhoe("tốt");
            chiSoCauThuEntity.setSoBanThangSanKhach(value.getSoBanSanKhach());
            chiSoCauThuEntity.setSoBanThangSanNha(value.getSoBanSanNha());
            chiSoCauThuEntity.setSoHoTroSanKhach(value.getHoTroSanKhach());
            chiSoCauThuEntity.setSoHoTroSanNha(value.getHoTroSanNha());
            chiSoCauThuEntity.setTrangThaiBanGhi("1"); // 1 = đang có hiệu lực

            String viTri = "";
            switch (value.getViTri()) {
                case "Goalkeeper":
                    viTri = "thủ môn";
                    break;
                case "Defender":
                    viTri = "hậu vệ";
                    break;
                case "Midfielder":
                    viTri = "tiền vệ";
                    break;
                case "Forward":
                    viTri = "tiền đạo";
                    break;
            }
            chiSoCauThuEntity.setViTriSoTruong(DataUtils.findViTriByKieuAndTen(session, "cầu thủ", viTri));
            chiSoCauThuEntity.setNgayTao(Date.valueOf("2018-02-05"));

            result.add(chiSoCauThuEntity);
        }
        return result;
    }

}
