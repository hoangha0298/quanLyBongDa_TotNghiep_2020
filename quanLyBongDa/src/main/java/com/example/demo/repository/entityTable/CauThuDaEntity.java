package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "cau_thu_da", schema = "quanlybongda_totnghiep", catalog = "")
public class CauThuDaEntity extends com.example.demo.repository.Entity {
    private int id;
    private double phutHieuLuc;
    private String trangThai;
    private DoiDaEntity doiDa;
    private CauThuEntity cauThu;
    private ViTriEntity viTri;
    private Collection<SuKienTranDauEntity> suKienTaos;
    private Collection<SuKienTranDauEntity> suKienNhans;

    @Id
    @Column(name = "ID")
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "PHUT_HIEU_LUC")
    public double getPhutHieuLuc() {
        return phutHieuLuc;
    }

    public void setPhutHieuLuc(double phutHieuLuc) {
        this.phutHieuLuc = phutHieuLuc;
    }

    @Basic
    @Column(name = "TRANG_THAI")
    public String getTrangThai() {
        return trangThai;
    }

    public void setTrangThai(String trangThai) {
        this.trangThai = trangThai;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CauThuDaEntity that = (CauThuDaEntity) o;

        if (id != that.id) return false;
        if (Double.compare(that.phutHieuLuc, phutHieuLuc) != 0) return false;
        if (trangThai != null ? !trangThai.equals(that.trangThai) : that.trangThai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result;
        long temp;
        result = id;
        temp = Double.doubleToLongBits(phutHieuLuc);
        result = 31 * result + (int) (temp ^ (temp >>> 32));
        result = 31 * result + (trangThai != null ? trangThai.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "DOI_DA", referencedColumnName = "ID", nullable = false)
    public DoiDaEntity getDoiDa() {
        return doiDa;
    }

    public void setDoiDa(DoiDaEntity doiDa) {
        this.doiDa = doiDa;
    }

    @ManyToOne
    @JoinColumn(name = "CAU_THU", referencedColumnName = "ID", nullable = false)
    public CauThuEntity getCauThu() {
        return cauThu;
    }

    public void setCauThu(CauThuEntity cauThu) {
        this.cauThu = cauThu;
    }

    @ManyToOne
    @JoinColumn(name = "VI_TRI", referencedColumnName = "ID")
    public ViTriEntity getViTri() {
        return viTri;
    }

    public void setViTri(ViTriEntity viTri) {
        this.viTri = viTri;
    }

    @OneToMany(mappedBy = "cauThuDaByNguoiTao")
    public Collection<SuKienTranDauEntity> getSuKienTaos() {
        return suKienTaos;
    }

    public void setSuKienTaos(Collection<SuKienTranDauEntity> suKienTaos) {
        this.suKienTaos = suKienTaos;
    }

    @OneToMany(mappedBy = "cauThuDaByNguoiNhan")
    public Collection<SuKienTranDauEntity> getSuKienNhans() {
        return suKienNhans;
    }

    public void setSuKienNhans(Collection<SuKienTranDauEntity> suKienNhans) {
        this.suKienNhans = suKienNhans;
    }

}

