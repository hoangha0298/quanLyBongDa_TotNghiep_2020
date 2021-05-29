package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "trong_tai", schema = "quanlybongda_totnghiep", catalog = "")
public class TrongTaiEntity extends com.example.demo.repository.Entity {
    private int id;
    private String ten;
    private String soDienThoai;
    private Integer namSinh;
    private Integer soTranBatChinh;
    private Integer soTranDaBat;
    private String trangThaiBanGhi;
    private Collection<TrongTaiTranDauEntity> trongTaiTranDaus;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEN", nullable = true, length = 50)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "SO_DIEN_THOAI", nullable = true, length = 20)
    public String getSoDienThoai() {
        return soDienThoai;
    }

    public void setSoDienThoai(String soDienThoai) {
        this.soDienThoai = soDienThoai;
    }

    @Basic
    @Column(name = "NAM_SINH", nullable = true)
    public Integer getNamSinh() {
        return namSinh;
    }

    public void setNamSinh(Integer namSinh) {
        this.namSinh = namSinh;
    }

    @Basic
    @Column(name = "SO_TRAN_BAT_CHINH", nullable = true)
    public Integer getSoTranBatChinh() {
        return soTranBatChinh;
    }

    public void setSoTranBatChinh(Integer soTranBatChinh) {
        this.soTranBatChinh = soTranBatChinh;
    }

    @Basic
    @Column(name = "SO_TRAN_DA_BAT", nullable = true)
    public Integer getSoTranDaBat() {
        return soTranDaBat;
    }

    public void setSoTranDaBat(Integer soTranDaBat) {
        this.soTranDaBat = soTranDaBat;
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

        TrongTaiEntity that = (TrongTaiEntity) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (soDienThoai != null ? !soDienThoai.equals(that.soDienThoai) : that.soDienThoai != null) return false;
        if (namSinh != null ? !namSinh.equals(that.namSinh) : that.namSinh != null) return false;
        if (soTranBatChinh != null ? !soTranBatChinh.equals(that.soTranBatChinh) : that.soTranBatChinh != null)
            return false;
        if (soTranDaBat != null ? !soTranDaBat.equals(that.soTranDaBat) : that.soTranDaBat != null) return false;
        if (trangThaiBanGhi != null ? !trangThaiBanGhi.equals(that.trangThaiBanGhi) : that.trangThaiBanGhi != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (soDienThoai != null ? soDienThoai.hashCode() : 0);
        result = 31 * result + (namSinh != null ? namSinh.hashCode() : 0);
        result = 31 * result + (soTranBatChinh != null ? soTranBatChinh.hashCode() : 0);
        result = 31 * result + (soTranDaBat != null ? soTranDaBat.hashCode() : 0);
        result = 31 * result + (trangThaiBanGhi != null ? trangThaiBanGhi.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "trongTai")
    public Collection<TrongTaiTranDauEntity> getTrongTaiTranDaus() {
        return trongTaiTranDaus;
    }

    public void setTrongTaiTranDaus(Collection<TrongTaiTranDauEntity> trongTaiTranDaus) {
        this.trongTaiTranDaus = trongTaiTranDaus;
    }
}
