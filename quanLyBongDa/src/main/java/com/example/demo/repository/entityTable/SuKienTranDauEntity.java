package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "su_kien_tran_dau", schema = "quanlybongda_totnghiep", catalog = "")
public class SuKienTranDauEntity extends com.example.demo.repository.Entity {
    private int id;
    private Double thoiGianDienRa;
    private String moTa;
    private TranDauEntity tranDau;
    private SuKienEntity suKien;
    private SuKienTranDauEntity suKienNguyenNhan;
    private Collection<SuKienTranDauEntity> suKienKetQuas;
    private CauThuDaEntity cauThuDaByNguoiTao;
    private CauThuDaEntity cauThuDaByNguoiNhan;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "THOI_GIAN_DIEN_RA", nullable = true, precision = 0)
    public Double getThoiGianDienRa() {
        return thoiGianDienRa;
    }

    public void setThoiGianDienRa(Double thoiGianDienRa) {
        this.thoiGianDienRa = thoiGianDienRa;
    }

    @Basic
    @Column(name = "MO_TA", nullable = true, length = 500)
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

        SuKienTranDauEntity that = (SuKienTranDauEntity) o;

        if (id != that.id) return false;
        if (thoiGianDienRa != null ? !thoiGianDienRa.equals(that.thoiGianDienRa) : that.thoiGianDienRa != null)
            return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (thoiGianDienRa != null ? thoiGianDienRa.hashCode() : 0);
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        return result;
    }

    @ManyToOne
    @JoinColumn(name = "TRAN_DAU", referencedColumnName = "ID", nullable = false)
    public TranDauEntity getTranDau() {
        return tranDau;
    }

    public void setTranDau(TranDauEntity tranDau) {
        this.tranDau = tranDau;
    }

    @ManyToOne
    @JoinColumn(name = "SU_KIEN", referencedColumnName = "ID", nullable = false)
    public SuKienEntity getSuKien() {
        return suKien;
    }

    public void setSuKien(SuKienEntity suKien) {
        this.suKien = suKien;
    }

    @ManyToOne
    @JoinColumn(name = "SU_KIEN_NGUYEN_NHAN", referencedColumnName = "ID")
    public SuKienTranDauEntity getSuKienNguyenNhan() {
        return suKienNguyenNhan;
    }

    public void setSuKienNguyenNhan(SuKienTranDauEntity suKienNguyenNhan) {
        this.suKienNguyenNhan = suKienNguyenNhan;
    }

    @OneToMany(mappedBy = "suKienNguyenNhan")
    public Collection<SuKienTranDauEntity> getSuKienKetQuas() {
        return suKienKetQuas;
    }

    public void setSuKienKetQuas(Collection<SuKienTranDauEntity> suKienKetQuas) {
        this.suKienKetQuas = suKienKetQuas;
    }

    @ManyToOne
    @JoinColumn(name = "NGUOI_TAO", referencedColumnName = "ID", nullable = true)
    public CauThuDaEntity getCauThuDaByNguoiTao() {
        return cauThuDaByNguoiTao;
    }

    public void setCauThuDaByNguoiTao(CauThuDaEntity cauThuDaByNguoiTao) {
        this.cauThuDaByNguoiTao = cauThuDaByNguoiTao;
    }

    @ManyToOne
    @JoinColumn(name = "NGUOI_NHAN", referencedColumnName = "ID", nullable = true)
    public CauThuDaEntity getCauThuDaByNguoiNhan() {
        return cauThuDaByNguoiNhan;
    }

    public void setCauThuDaByNguoiNhan(CauThuDaEntity cauThuDaByNguoiNhan) {
        this.cauThuDaByNguoiNhan = cauThuDaByNguoiNhan;
    }
}
