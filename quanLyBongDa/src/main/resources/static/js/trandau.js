
$("#event").change(function () {
    var labelTao = $(".player-create label:first");
    var labelNhan = $(".player-get label:first");

    // lỗi có người phạm lỗi và người sút phạt , khác đội, chỉ hiển cầu thủ đang đá
    if($("#event option:selected").attr("type") == "lỗi") {
        $(".event-reason:first").css("display", "none");

        $(".prepared").css("display", "none");
        $(".playing_soccer").css("display", "flex");

        labelTao.html("Người hạm lỗi <span class=\"required\">*</span>");
        labelNhan.html("Người sút phạt <span class=\"required\">*</span>");
    }

    // thẻ chỉ có người nhận thẻ
    if($("#event option:selected").attr("type") == "thẻ") {
        $(".event-reason:first").css("display", "none");
        $(".player-create").css("display", "none");

        $(".prepared").css("display", "none");
        $(".playing_soccer").css("display", "flex");

        labelNhan.html("Người nhận thẻ <span class=\"required\">*</span>");
    } else {
        $(".player-create").css("display", "flex");
    }

    // thay người có người ra hoặc người vào hoặc cả 2 (hết quyền thay người), cùng đội
    if($("#event option:selected").attr("type") == "thay người") {
        $(".event-reason:first").css("display", "flex");

        $(".player-create-home.prepared").css("display", "none");
        $(".player-create-guest.prepared").css("display", "none");
        $(".player-create-home.playing_soccer").css("display", "flex");
        $(".player-create-guest.playing_soccer").css("display", "flex");

        $(".player-get-home.prepared").css("display", "flex");
        $(".player-get-guest.prepared").css("display", "flex");
        $(".player-get-home.playing_soccer").css("display", "none");
        $(".player-get-guest.playing_soccer").css("display", "none");

        labelTao.html("Người ra <span class=\"required\">*</span>");
        labelNhan.html("Người vào");
    }

    // kiến tạo có người kiến tạo hoặc k, người ghi bàn, có thể cùng đội hoặc k
    if($("#event option:selected").attr("type") == "kiến tạo") {
        $(".event-reason:first").css("display", "flex");

        $(".prepared").css("display", "none");
        $(".playing_soccer").css("display", "flex");

        labelTao.html("Người kiến tạo");
        labelNhan.html("Người ghi bàn <span class=\"required\">*</span>");
    }

})

function addEvent() {
    var addData = {
        'idTranDau' : $("#match").attr("value"),
        'idSuKien' : $("#event").val(),
        // id là id của cầu thủ đá không phải cầu thủ
        'idCauThuTao' : $("#player-create").val(),
        'idCauThuNhan' : $("#player-get").val(),
        'idSuKienNguyenNhan' : $("#event-reason").val(),
        'thoiGianDienRa' : $("#time-of-the-event").val(),
        'moTa' : $("#description").val(),
    }

    if (!addData.idSuKien){
        alert("chưa chọn sự kiện");
        return;
    }
    if (!addData.thoiGianDienRa){
        alert("chưa chọn thời gian diễn ra");
        return;
    }

    $.ajax({
        type: "POST",
        url: "/tran_dau/them_su_kien",
        data: JSON.stringify(addData),
        dataType: "json",
        contentType : 'application/json; charset=utf-8',
        complete: function (res) {
            var response = res.responseJSON;
            alert(response.message);
            if (response.code == 1) {
                location.reload();
            }
        },
    });
}

function removeEvent(envet, idEventMatch) {

    if(!confirm("Bạn muốn xóa sự kiện "+ envet + " có id " + idEventMatch)) return;
    var addData = {
        'id' : idEventMatch
    }

    $.ajax({
        type: "POST",
        url: "/tran_dau/xoa_su_kien",
        data: JSON.stringify(addData),
        dataType: "json",
        contentType : 'application/json; charset=utf-8',
        complete: function (res) {
            var response = res.responseJSON;
            alert(response.message);
            if (response.code == 1) {
                location.reload();
            }
        },
    });
}
