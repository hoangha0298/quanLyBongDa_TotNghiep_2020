<%@ page import="java.util.List" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="java.util.Collections" %>
<%@ page import="java.util.Comparator" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.example.demo.repository.entityTable.*" %>
<%@ page import="com.example.demo.service.TranDauService" %>
<%@ page import="com.example.demo.service.SuKienService" %>
<%@ page import="com.example.demo.repository.repositoryExtend.CauThuDaRepository" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Trận đấu</title>
    <link rel="stylesheet" href="/css/common.css">
    <link rel="stylesheet" href="/css/trandau.css">
</head>
<body>

<%!
    // lấy danh sách cầu thủ đang đá và dự bị, bỏ qua cầu thủ ra sân. sắp xếp theo trạng thái và tên
    public void getDanhSachCauThu(List<CauThuDaEntity> cauThuDaEntities) {
        cauThuDaEntities.removeIf(item -> item.getTrangThai().equals("ra sân"));
        cauThuDaEntities.sort((CauThuDaEntity o1, CauThuDaEntity o2) -> {
            int compare = o2.getTrangThai().compareTo(o1.getTrangThai());
            if (compare == 0) compare = o1.getCauThu().getTen().compareTo(o2.getCauThu().getTen());
            return compare;
        });
    }

    // lấy class theo trạng thái của cầu thủ
    public String getClassOfStatus(CauThuDaEntity cauThuDaEntity) {
        switch (cauThuDaEntity.getTrangThai()) {
            case "đang đá": {
                return "playing_soccer";
            }
            case "dự bị":{
                return "prepared";
            }
        }
        return "";
    }

//    public String getOptionsByCauThuDas (List<CauThuDaEntity> cauThuDaEntities, String classDefault) {
//        String options = "";
//        ViTriEntity viTriEntity = cauThuDaEntities.get(0).getDoiDa().getViTri();
//        for (CauThuDaEntity cauThuDa : cauThuDaEntities) {
//            options += "<option value=\"" + cauThuDa.getId() + "\"" +
//                    " class=\"player-get-home " + getClassOfStatus(cauThuDa) + "\">" +
//                    viTriEntity.getTen() + " : " +
//                    cauThuDa.getCauThu().getTen() + ", " +
//                    cauThuDa.getTrangThai() + "</option>";
//        }
//    }
%>

<%
    TranDauService tranDauService = (TranDauService) request.getAttribute("tranDauService");
    SuKienService suKienService = (SuKienService) request.getAttribute("suKienService");
    Session DBsession = (Session) request.getAttribute("session");
    int id = Integer.parseInt(request.getParameter("id"));
    TranDauEntity tranDau = tranDauService.findTranDauById(DBsession, id);
    List<DoiDaEntity> doiDas = new ArrayList<>(tranDau.getDoiDas());
    List<SuKienTranDauEntity> suKienTranDaus = new ArrayList<>(tranDau.getSuKienTranDaus());

    Collections.sort(suKienTranDaus, new Comparator<SuKienTranDauEntity>() {
        @Override
        public int compare(SuKienTranDauEntity o1, SuKienTranDauEntity o2) {
            return (int) (o1.getThoiGianDienRa().doubleValue() - o2.getThoiGianDienRa().doubleValue());
        }
    });
    DoiDaEntity doiKhach;
    DoiDaEntity doiNha;
%>
<%
    if (doiDas.get(0).getViTri().getTen().equals("đội khách")) {
        doiKhach = doiDas.get(0);
        doiNha = doiDas.get(1);
    } else {
        doiKhach = doiDas.get(1);
        doiNha = doiDas.get(0);
    }
    List<CauThuDaEntity> cauThuDaDoiKhachs = new ArrayList<>(doiKhach.getCauThuDas());
    List<CauThuDaEntity> cauThuDaDoiNhas = new ArrayList<>(doiNha.getCauThuDas());

    getDanhSachCauThu(cauThuDaDoiKhachs);
    getDanhSachCauThu(cauThuDaDoiNhas);

    List<SuKienEntity> suKiens = suKienService.findAllSuKien(DBsession);
%>
<div class="header" id="match" value="<%=tranDau.getId()%>">
    <p><%=doiKhach.getDoiBong().getTen()%> - <%=doiNha.getDoiBong().getTen()%>
    </p>
</div>

<div class="information">
    <div class="title">
        <div role="link">
            <span style="color: #3F1052">Ngoại hạng anh</span>
            <span style="color: black"> · </span>
            <span><%=new SimpleDateFormat("h:mm").format(tranDau.getThoiGianBatDau())%></span>
            <span><%=new SimpleDateFormat(" dd/MM/yy").format(tranDau.getThoiGianBatDau())%></span>
        </div>
        <div style="display: none">Kết thúc</div>
    </div>
    <div class="team-and-score">
        <div class="logo-and-name">
            <img src="logo_doi_bong/<%=doiNha.getDoiBong().getLogo()%>" width="48px" height="48px">
            <p><%=doiNha.getDoiBong().getTen()%>
            </p>
            <p>Đội nhà</p>
        </div>
        <div style="font-size: 36px; line-height: 36px">-</div>
        <div class="logo-and-name">
            <img src="logo_doi_bong/<%=doiKhach.getDoiBong().getLogo()%>" width="48px" height="48px">
            <p><%=doiKhach.getDoiBong().getTen()%>
            </p>
            <P>Đội khách</P>
        </div>
    </div>
</div>

<div class="body">
    <div class="row" style="font-size: 16px">THÊM SỰ KIỆN CHO TRẬN ĐẤU</div>

    <div class="row">
        <div>
            <label>Sự kiện <span class="required">*</span></label>
            <select name="idSuKien" id="event" required>
                <option disabled selected value> -- chọn 1 sự kiện --</option>
                <%--danh sach su kien--%>
                <%
                    for (SuKienEntity suKien : suKiens) {
                %>
                <option type="<%=suKien.getKieu()%>" value="<%=suKien.getId()%>"><%=suKien.getTen()%>
                </option>
                <%
                    }
                    ;
                %>
            </select>
        </div>
    </div>

    <div class="row">
        <div class="player-create">
            <label>Cầu thủ tạo <span class="required">*</span></label>
            <select name="idCauThuTao" id="player-create">
                <option disabled selected value> -- chọn 1 cầu thủ --</option>
                <%--danh sach cau thu doi nha--%>
                <%
                    for (CauThuDaEntity cauThuDa : cauThuDaDoiNhas) {
                %>
                <option value="<%=cauThuDa.getId()%>" class="player-create-home <%=getClassOfStatus(cauThuDa)%>">Dn : <%=cauThuDa.getCauThu().getTen()%>
                    , <%=cauThuDa.getTrangThai()%>
                </option>
                <%
                    }
                    ;
                %>
                <%--danh sach cau thu doi khach--%>
                <%
                    for (CauThuDaEntity cauThuDa : cauThuDaDoiKhachs) {
                %>
                <option value="<%=cauThuDa.getId()%>" class="player-create-guest <%=getClassOfStatus(cauThuDa)%>">Dk
                    : <%=cauThuDa.getCauThu().getTen()%>, <%=cauThuDa.getTrangThai()%>
                </option>
                <%
                    }
                    ;
                %>
            </select>

        </div>
        <div class="player-get">
            <label>Cầu thủ nhận <span class="required">*</span></label>
            <select name="idCauThuNhan" id="player-get">
                <option disabled selected value> -- chọn 1 cầu thủ --</option>
                <%--danh sach cau thu doi nha--%>
                <%
                    for (CauThuDaEntity cauThuDa : cauThuDaDoiNhas) {
                %>
                <option value="<%=cauThuDa.getId()%>" class="player-get-home <%=getClassOfStatus(cauThuDa)%>">Dn : <%=cauThuDa.getCauThu().getTen()%>
                    , <%=cauThuDa.getTrangThai()%>
                </option>
                <%
                    }
                    ;
                %>
                <%--danh sach cau thu doi khach--%>
                <%
                    for (CauThuDaEntity cauThuDa : cauThuDaDoiKhachs) {
                %>
                <option value="<%=cauThuDa.getId()%>" class="player-get-guest <%=getClassOfStatus(cauThuDa)%>">Dk : <%=cauThuDa.getCauThu().getTen()%>
                    , <%=cauThuDa.getTrangThai()%>
                </option>
                <%
                    }
                    ;
                %>
            </select>
        </div>
    </div>

    <div class="row">
        <div>
            <label>Phút <span class="required">*</span></label>
            <input name="thoiGianDienRa" type="number" id="time-of-the-event" min="0" max="150" value="0" required>
        </div>
        <div class="event-reason">
            <label>SK nguyên nhân</label>
            <select name="idSuKienNguyenNhan" id="event-reason">
                <option value="0"> -- không có --</option>
                <%--danh sach su kien tran dau--%>
                <%
                    for (SuKienTranDauEntity suKienTranDau : suKienTranDaus) {
                %>
                <option value="<%=suKienTranDau.getId()%>"><%=suKienTranDau.getId() + ", "
                        + suKienTranDau.getSuKien().getTen() + ", phút "
                        + (int) (suKienTranDau.getThoiGianDienRa().doubleValue()) + ""%>
                </option>
                <%
                    }
                    ;
                %>
            </select>
        </div>
    </div>

    <div class="row">
        <div>
            <label>Mô tả chi tiết (giới hạn 500 kí tự) :</label>
        </div>
    </div>

    <div class="row">
        <div style="width: 100%">
            <textarea name="moTa" id="description" rows="5" cols="140%" maxlength="500"></textarea>
        </div>
    </div>

    <div class="row">
        <div></div>
        <div style="justify-content: flex-end">
            <input type="submit" onclick="addEvent()" value="Thêm sự kiện">
        </div>
    </div>

    <div class="row">
        <div style="width: 100%">
            <label style="height: auto; color: #70757A">Hướng dẫn : Select "SK nguyên nhân" là ô chọn sự kiện nguyên
                nhân dẫn tới sự kiện đang thêm này.
                Yêu cầu sự kiện nguyên nhân đã được thêm trước đó.
                (thẻ đỏ là nguyên nhân của thay người, sút phạt là nguyên nhân của bàn thắng, ...)</label>
        </div>
    </div>
</div>

<div class="list-event">
    <div style="font-size: 16px">BẢNG CÁC SỰ KIỆN TRONG TRẬN</div>
    <table>
        <tr>
            <th>ID</th>
            <th>Phút</th>
            <th>Sự kiện</th>
            <th>phạm lỗi, ra sân, kiến tạo</th>
            <th>sút phạt, vào sân, ghi bàn, nhận thẻ</th>
            <th>Nguyên nhân</th>
            <th>Mô Tả</th>
            <th></th>
        </tr>
        <%
            for (SuKienTranDauEntity suKienTranDau : suKienTranDaus) {
        %>
        <tr>
            <%
                SuKienTranDauEntity nguyenNhan = suKienTranDau.getSuKienNguyenNhan();
                CauThuDaEntity cauThuTao = suKienTranDau.getCauThuDaByNguoiTao();
                CauThuDaEntity cauThuNhan = suKienTranDau.getCauThuDaByNguoiNhan();
                String moTa = suKienTranDau.getMoTa();
            %>
            <td><%=suKienTranDau.getId()%>
            </td>
            <td><%=(int) suKienTranDau.getThoiGianDienRa().doubleValue()%>
            </td>
            <td><%=suKienTranDau.getSuKien().getTen()%>
            </td>
            <td><%=cauThuTao == null ? "" : cauThuTao.getCauThu().getTen()%>
            </td>
            <td><%=cauThuNhan == null ? "" : cauThuNhan.getCauThu().getTen()%>
            </td>
            <td><%=nguyenNhan == null ? "" : "id=" + nguyenNhan.getId() + "," + nguyenNhan.getSuKien().getTen()%>
            </td>
            <td><%=moTa == null ? "" : moTa%>
            </td>
            <td>
                <input type="submit"
                       onclick="removeEvent('<%=suKienTranDau.getSuKien().getTen()%>', '<%=suKienTranDau.getId()%>')"
                       value="Xóa">
            </td>
        </tr>
        <%
            }
            ;
        %>

    </table>
</div>

<%--<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>--%>
<script src="js/jquery.min.js"></script>
<script src="js/trandau.js"></script>
</body>
</html>