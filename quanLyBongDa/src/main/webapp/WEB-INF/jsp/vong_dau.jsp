<%@ page import="com.example.demo.repository.entityTable.VongDauEntity" %>
<%@ page import="java.util.List" %>
<%@ page import="com.example.demo.repository.entityTable.TranDauEntity" %>
<%@ page import="com.example.demo.repository.entityTable.DoiBongEntity" %>
<%@ page import="com.example.demo.repository.entityTable.DoiDaEntity" %>
<%@ page import="java.text.SimpleDateFormat" %>
<%@ page import="org.hibernate.Session" %>
<%@ page import="com.example.demo.service.VongDauService" %>
<%@ page contentType="text/html; charset=UTF-8" %>

<!DOCTYPE html>
<html xmlns:th="http://www.thymeleaf.org" lang="en">
<head>
    <meta charset="UTF-8">
    <title>Vòng đấu ngoại hạng anh</title>
    <link rel="stylesheet" type="text/css" href="/css/common.css"/>
    <link rel="stylesheet" type="text/css" href="/css/header.css"/>
    <link rel="stylesheet" type="text/css" href="/css/vongdau.css"/>
</head>
<body>
<%
    VongDauService vongDauService = (VongDauService) request.getAttribute("vongDauService");
%>

<div class="header">
    <a class="header-button"><p>VÒNG ĐẤU</p></a>
</div>
<div style="height:50px; width:100%;"></div>

<div class="list-match">
    <%
        Session DBsession = (Session) request.getAttribute("session");
        List<VongDauEntity> vongDaus = vongDauService.findAllVongDau(DBsession);
        for (VongDauEntity vongDau : vongDaus) {
    %>
    <div class="header-match-round">Vòng <%=vongDau.getVong()%> của <%=vongDaus.size()%>
    </div>
    <%
        List<TranDauEntity> tranDaus = (List<TranDauEntity>) vongDau.getTranDaus();
        TranDauEntity tranDau;
        DoiBongEntity doiKhach;
        DoiBongEntity doiNha;
        for (int i = 0; i < tranDaus.size(); ) {
    %>
    <div>
        <div class="row">
            <% for (int j=0; j<2; j++){
                tranDau = tranDaus.get(i++);
                List<DoiDaEntity> doiDa = (List) tranDau.getDoiDas();

                if (doiDa.get(0).getViTri().getTen().equals("đội khách")) { // nếu phần tử 0 là độ khách
                    doiKhach = doiDa.get(0).getDoiBong();
                    doiNha = doiDa.get(1).getDoiBong();
                } else {
                    doiKhach = doiDa.get(1).getDoiBong();
                    doiNha = doiDa.get(0).getDoiBong();
                }
            %>
                <div class="item-match" onclick="location.href='/tran_dau?id=<%=tranDau.getId()%>'"> <%--Biểu diễn 1 trận đấu--%>
                    <div class="item-match-left">
                        <div style="padding:5px 0px; line-height: 0">
                            <img src="logo_doi_bong/<%=doiKhach.getLogo()%>" width="24px" height="24px">
                        </div>
                        <div style="padding:5px 0px; line-height: 0">
                            <img src="logo_doi_bong/<%=doiNha.getLogo()%>" width="24px" height="24px">
                        </div>
                    </div>

                    <div class="item-match-middle">
                        <div style="padding:7px 0px; color:#70757A">
                            <div style="margin-left: 0">
                                <%=doiKhach.getTen()%>
                            </div>
                            <div style="margin-right: 0px; display: none">
                                <div>0</div>
                                <div class="arrow-left" style="border: solid rgba(0,0,0,0)"></div>
                            </div>
                        </div>
                        <div style="padding:7px 0px; ">
                            <div style="margin-left: 0">
                                <%=doiNha.getTen()%>
                            </div>
                            <div style="margin-right: 0; display: none">
                                <div>3</div>
                                <div class="arrow-left" style=""></div>
                            </div>
                        </div>
                    </div>

                    <div class="item-match-right">
                        <div style="display: none">FT</div>
                        <div style="color:#70757A"><%= new SimpleDateFormat("h:mm").format(tranDau.getThoiGianBatDau())%>
                        </div>
                        <div style="color:#70757A"><%= new SimpleDateFormat("dd/MM/yy").format(tranDau.getThoiGianBatDau())%>
                        </div>
                        <div style="">GMT+7</div>
                    </div>
                </div>
                <%if(j == 0){%> <%--Đường kẻ ở giữa--%>
                    <div class="line-middle"></div>
                <%}
            }%>
        </div>
    </div>
    <%
            }
        }
    %>
</div>

</body>
</html>