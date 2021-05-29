package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "san_bong", schema = "quanlybongda_totnghiep", catalog = "")
public class SanBongEntity {
    private int id;
    private String ten;
    private String diaChi;
    private Integer sucChua;
    private String tinhTrang;
    private Date ngayXayDung;
    private String trangThaiBanGhi;
    private Collection<DoiBongEntity> doiBongs;
    private Collection<TranDauEntity> tranDaus;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEN", nullable = false, length = 300)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "DIA_CHI", nullable = true, length = 300)
    public String getDiaChi() {
        return diaChi;
    }

    public void setDiaChi(String diaChi) {
        this.diaChi = diaChi;
    }

    @Basic
    @Column(name = "SUC_CHUA", nullable = true)
    public Integer getSucChua() {
        return sucChua;
    }

    public void setSucChua(Integer sucChua) {
        this.sucChua = sucChua;
    }

    @Basic
    @Column(name = "TINH_TRANG", nullable = true, length = 500)
    public String getTinhTrang() {
        return tinhTrang;
    }

    public void setTinhTrang(String tinhTrang) {
        this.tinhTrang = tinhTrang;
    }

    @Basic
    @Column(name = "NGAY_XAY_DUNG", nullable = true)
    public Date getNgayXayDung() {
        return ngayXayDung;
    }

    public void setNgayXayDung(Date ngayXayDung) {
        this.ngayXayDung = ngayXayDung;
    }

    @Basic
    @Column(name = "TRANG_THAI_BAN_GHI", nullable = true, length = 30)
    public String getTrangThaiBanGhi() {
        return trangThaiBanGhi;
    }

    public void setTrangThaiBanGhi(String trangThaiBanGhi) {
        this.trangThaiBanGhi = trangThaiBanGhi;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        SanBongEntity that = (SanBongEntity) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (sucChua != null ? !sucChua.equals(that.sucChua) : that.sucChua != null) return false;
        if (tinhTrang != null ? !tinhTrang.equals(that.tinhTrang) : that.tinhTrang != null) return false;
        if (ngayXayDung != null ? !ngayXayDung.equals(that.ngayXayDung) : that.ngayXayDung != null) return false;
        if (trangThaiBanGhi != null ? !trangThaiBanGhi.equals(that.trangThaiBanGhi) : that.trangThaiBanGhi != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (sucChua != null ? sucChua.hashCode() : 0);
        result = 31 * result + (tinhTrang != null ? tinhTrang.hashCode() : 0);
        result = 31 * result + (ngayXayDung != null ? ngayXayDung.hashCode() : 0);
        result = 31 * result + (trangThaiBanGhi != null ? trangThaiBanGhi.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "sanBong")
    public Collection<DoiBongEntity> getDoiBongs() {
        return doiBongs;
    }

    public void setDoiBongs(Collection<DoiBongEntity> doiBongs) {
        this.doiBongs = doiBongs;
    }

    @OneToMany(mappedBy = "sanBong")
    public Collection<TranDauEntity> getTranDaus() {
        return tranDaus;
    }

    public void setTranDaus(Collection<TranDauEntity> tranDaus) {
        this.tranDaus = tranDaus;
    }
}
