package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "su_kien", schema = "quanlybongda_totnghiep", catalog = "")
public class SuKienEntity {
    private int id;
    private String kieu;
    private String ten;
    private String moTa;
    private Collection<SuKienTranDauEntity> suKienTranDaus;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "KIEU", nullable = false, length = 100)
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

        SuKienEntity that = (SuKienEntity) o;

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

    @OneToMany(mappedBy = "suKien")
    public Collection<SuKienTranDauEntity> getSuKienTranDaus() {
        return suKienTranDaus;
    }

    public void setSuKienTranDaus(Collection<SuKienTranDauEntity> suKienTranDaus) {
        this.suKienTranDaus = suKienTranDaus;
    }
}
