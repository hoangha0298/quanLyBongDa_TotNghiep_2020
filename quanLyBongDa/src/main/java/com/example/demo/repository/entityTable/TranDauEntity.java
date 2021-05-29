package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.sql.Timestamp;
import java.util.Collection;

@Entity
@Table(name = "tran_dau", schema = "quanlybongda_totnghiep", catalog = "")
public class TranDauEntity extends com.example.demo.repository.Entity {
    private int id;
    private Timestamp thoiGianBatDau;
    private Integer soKhanGiaDenSan;
    private Double thoiGianTranDau;
    private Integer soBanThangDoiNha;
    private Integer soBanThangDoiKhach;
    private String trangThai;
    private Collection<DoiDaEntity> doiDas;
    private Collection<SuKienTranDauEntity> suKienTranDaus;
    private SanBongEntity sanBong;
    private VongDauEntity vongDau;
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
    @Column(name = "THOI_GIAN_BAT_DAU", nullable = true)
    public Timestamp getThoiGianBatDau() {
        return thoiGianBatDau;
    }

    public void setThoiGianBatDau(Timestamp thoiGianBatDau) {
        this.thoiGianBatDau = thoiGianBatDau;
    }

    @Basic
    @Column(name = "SO_KHAN_GIA_DEN_SAN", nullable = true)
    public Integer getSoKhanGiaDenSan() {
        return soKhanGiaDenSan;
    }

    public void setSoKhanGiaDenSan(Integer soKhanGiaDenSan) {
        this.soKhanGiaDenSan = soKhanGiaDenSan;
    }

    @Basic
    @Column(name = "THOI_GIAN_TRAN_DAU", nullable = true, precision = 0)
    public Double getThoiGianTranDau() {
        return thoiGianTranDau;
    }

    public void setThoiGianTranDau(Double thoiGianTranDau) {
        this.thoiGianTranDau = thoiGianTranDau;
    }

    @Basic
    @Column(name = "SO_BAN_THANG_DOI_NHA", nullable = true)
    public Integer getSoBanThangDoiNha() {
        return soBanThangDoiNha;
    }

    public void setSoBanThangDoiNha(Integer soBanThangDoiNha) {
        this.soBanThangDoiNha = soBanThangDoiNha;
    }

    @Basic
    @Column(name = "SO_BAN_THANG_DOI_KHACH", nullable = true)
    public Integer getSoBanThangDoiKhach() {
        return soBanThangDoiKhach;
    }

    public void setSoBanThangDoiKhach(Integer soBanThangDoiKhach) {
        this.soBanThangDoiKhach = soBanThangDoiKhach;
    }

    @Basic
    @Column(name = "TRANG_THAI", nullable = true, length = 30)
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

        TranDauEntity that = (TranDauEntity) o;

        if (id != that.id) return false;
        if (thoiGianBatDau != null ? !thoiGianBatDau.equals(that.thoiGianBatDau) : that.thoiGianBatDau != null)
            return false;
        if (soKhanGiaDenSan != null ? !soKhanGiaDenSan.equals(that.soKhanGiaDenSan) : that.soKhanGiaDenSan != null)
            return false;
        if (thoiGianTranDau != null ? !thoiGianTranDau.equals(that.thoiGianTranDau) : that.thoiGianTranDau != null)
            return false;
        if (soBanThangDoiNha != null ? !soBanThangDoiNha.equals(that.soBanThangDoiNha) : that.soBanThangDoiNha != null)
            return false;
        if (soBanThangDoiKhach != null ? !soBanThangDoiKhach.equals(that.soBanThangDoiKhach) : that.soBanThangDoiKhach != null)
            return false;
        if (trangThai != null ? !trangThai.equals(that.trangThai) : that.trangThai != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (thoiGianBatDau != null ? thoiGianBatDau.hashCode() : 0);
        result = 31 * result + (soKhanGiaDenSan != null ? soKhanGiaDenSan.hashCode() : 0);
        result = 31 * result + (thoiGianTranDau != null ? thoiGianTranDau.hashCode() : 0);
        result = 31 * result + (soBanThangDoiNha != null ? soBanThangDoiNha.hashCode() : 0);
        result = 31 * result + (soBanThangDoiKhach != null ? soBanThangDoiKhach.hashCode() : 0);
        result = 31 * result + (trangThai != null ? trangThai.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "tranDau")
    public Collection<DoiDaEntity> getDoiDas() {
        return doiDas;
    }

    public void setDoiDas(Collection<DoiDaEntity> doiDas) {
        this.doiDas = doiDas;
    }

    @OneToMany(mappedBy = "tranDau")
    public Collection<SuKienTranDauEntity> getSuKienTranDaus() {
        return suKienTranDaus;
    }

    public void setSuKienTranDaus(Collection<SuKienTranDauEntity> suKienTranDaus) {
        this.suKienTranDaus = suKienTranDaus;
    }

    @ManyToOne
    @JoinColumn(name = "SAN_BONG", referencedColumnName = "ID", nullable = false)
    public SanBongEntity getSanBong() {
        return sanBong;
    }

    public void setSanBong(SanBongEntity sanBong) {
        this.sanBong = sanBong;
    }

    @ManyToOne
    @JoinColumn(name = "VONG_DAU", referencedColumnName = "ID", nullable = false)
    public VongDauEntity getVongDau() {
        return vongDau;
    }

    public void setVongDau(VongDauEntity vongDau) {
        this.vongDau = vongDau;
    }

    @OneToMany(mappedBy = "tranDau")
    public Collection<TrongTaiTranDauEntity> getTrongTaiTranDaus() {
        return trongTaiTranDaus;
    }

    public void setTrongTaiTranDaus(Collection<TrongTaiTranDauEntity> trongTaiTranDaus) {
        this.trongTaiTranDaus = trongTaiTranDaus;
    }
}
