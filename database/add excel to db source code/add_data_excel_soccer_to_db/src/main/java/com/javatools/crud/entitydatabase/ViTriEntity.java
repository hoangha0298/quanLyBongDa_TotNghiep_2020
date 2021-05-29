package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vi_tri", schema = "quanlybongda_totnghiep", catalog = "")
public class ViTriEntity {
    private int id;
    private String kieu;
    private String ten;
    private String moTa;
    private Collection<CauThuDaEntity> cauThuDas;
    private Collection<ChiSoCauThuEntity> chiSoCauThus;
    private Collection<DoiDaEntity> doiDas;
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
    @Column(name = "KIEU", nullable = false, length = 200)
    public String getKieu() {
        return kieu;
    }

    public void setKieu(String kieu) {
        this.kieu = kieu;
    }

    @Basic
    @Column(name = "TEN", nullable = false, length = 100)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
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

        ViTriEntity that = (ViTriEntity) o;

        if (id != that.id) return false;
        if (kieu != null ? !kieu.equals(that.kieu) : that.kieu != null) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (kieu != null ? kieu.hashCode() : 0);
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "viTri")
    public Collection<CauThuDaEntity> getCauThuDas() {
        return cauThuDas;
    }

    public void setCauThuDas(Collection<CauThuDaEntity> cauThuDas) {
        this.cauThuDas = cauThuDas;
    }

    @OneToMany(mappedBy = "viTriSoTruong")
    public Collection<ChiSoCauThuEntity> getChiSoCauThus() {
        return chiSoCauThus;
    }

    public void setChiSoCauThus(Collection<ChiSoCauThuEntity> chiSoCauThus) {
        this.chiSoCauThus = chiSoCauThus;
    }

    @OneToMany(mappedBy = "viTri")
    public Collection<DoiDaEntity> getDoiDas() {
        return doiDas;
    }

    public void setDoiDas(Collection<DoiDaEntity> doiDas) {
        this.doiDas = doiDas;
    }

    @OneToMany(mappedBy = "viTriTrongTran")
    public Collection<TrongTaiTranDauEntity> getTrongTaiTranDaus() {
        return trongTaiTranDaus;
    }

    public void setTrongTaiTranDaus(Collection<TrongTaiTranDauEntity> trongTaiTranDaus) {
        this.trongTaiTranDaus = trongTaiTranDaus;
    }
}
