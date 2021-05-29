package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "giai_dau", schema = "quanlybongda_totnghiep", catalog = "")
public class GiaiDauEntity {
    private int id;
    private String ten;
    private String muaGiai;
    private Integer soDoiThamDu;
    private Integer soVongDau;
    private Date ngayBatDau;
    private Date ngayKetThuc;
    private String moTa;
    private Collection<DoiBongEntity> doiBongs;
    private Collection<VongDauEntity> vongDaus;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEN", nullable = true, length = 200)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "MUA_GIAI", nullable = true, length = 100)
    public String getMuaGiai() {
        return muaGiai;
    }

    public void setMuaGiai(String muaGiai) {
        this.muaGiai = muaGiai;
    }

    @Basic
    @Column(name = "SO_DOI_THAM_DU", nullable = true)
    public Integer getSoDoiThamDu() {
        return soDoiThamDu;
    }

    public void setSoDoiThamDu(Integer soDoiThamDu) {
        this.soDoiThamDu = soDoiThamDu;
    }

    @Basic
    @Column(name = "SO_VONG_DAU", nullable = true)
    public Integer getSoVongDau() {
        return soVongDau;
    }

    public void setSoVongDau(Integer soVongDau) {
        this.soVongDau = soVongDau;
    }

    @Basic
    @Column(name = "NGAY_BAT_DAU", nullable = true)
    public Date getNgayBatDau() {
        return ngayBatDau;
    }

    public void setNgayBatDau(Date ngayBatDau) {
        this.ngayBatDau = ngayBatDau;
    }

    @Basic
    @Column(name = "NGAY_KET_THUC", nullable = true)
    public Date getNgayKetThuc() {
        return ngayKetThuc;
    }

    public void setNgayKetThuc(Date ngayKetThuc) {
        this.ngayKetThuc = ngayKetThuc;
    }

    @Basic
    @Column(name = "MO_TA", nullable = true, length = 300)
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        GiaiDauEntity that = (GiaiDauEntity) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (muaGiai != null ? !muaGiai.equals(that.muaGiai) : that.muaGiai != null) return false;
        if (soDoiThamDu != null ? !soDoiThamDu.equals(that.soDoiThamDu) : that.soDoiThamDu != null) return false;
        if (soVongDau != null ? !soVongDau.equals(that.soVongDau) : that.soVongDau != null) return false;
        if (ngayBatDau != null ? !ngayBatDau.equals(that.ngayBatDau) : that.ngayBatDau != null) return false;
        if (ngayKetThuc != null ? !ngayKetThuc.equals(that.ngayKetThuc) : that.ngayKetThuc != null) return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (muaGiai != null ? muaGiai.hashCode() : 0);
        result = 31 * result + (soDoiThamDu != null ? soDoiThamDu.hashCode() : 0);
        result = 31 * result + (soVongDau != null ? soVongDau.hashCode() : 0);
        result = 31 * result + (ngayBatDau != null ? ngayBatDau.hashCode() : 0);
        result = 31 * result + (ngayKetThuc != null ? ngayKetThuc.hashCode() : 0);
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "giaiDau")
    public Collection<DoiBongEntity> getDoiBongs() {
        return doiBongs;
    }

    public void setDoiBongs(Collection<DoiBongEntity> doiBongs) {
        this.doiBongs = doiBongs;
    }

    @OneToMany(mappedBy = "giaiDau")
    public Collection<VongDauEntity> getVongDaus() {
        return vongDaus;
    }

    public void setVongDaus(Collection<VongDauEntity> vongDaus) {
        this.vongDaus = vongDaus;
    }
}
