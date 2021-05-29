package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doi_bong", schema = "quanlybongda_totnghiep", catalog = "")
public class DoiBongEntity extends com.example.demo.repository.Entity {
    private int id;
    private String ten;
    private String logo;
    private String trangPhucTruyenThong;
    private String huanLuyenVien;
    private String chuSoHuu;
    private String diaChi;
    private String moTa;
    private String quocTich;
    private Integer soBanThangSanNha;
    private Integer soBanThangSanKhach;
    private String trangThaiBanGhi;
    private Collection<ChiSoCauThuEntity> chiSoCauThus;
    private GiaiDauEntity giaiDau;
    private SanBongEntity sanBong;
    private Collection<DoiDaEntity> doiDas;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TEN", nullable = false, length = 50)
    public String getTen() {
        return ten;
    }

    public void setTen(String ten) {
        this.ten = ten;
    }

    @Basic
    @Column(name = "LOGO", nullable = true, length = 30)
    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo;
    }

    @Basic
    @Column(name = "TRANG_PHUC_TRUYEN_THONG", nullable = true, length = 50)
    public String getTrangPhucTruyenThong() {
        return trangPhucTruyenThong;
    }

    public void setTrangPhucTruyenThong(String trangPhucTruyenThong) {
        this.trangPhucTruyenThong = trangPhucTruyenThong;
    }

    @Basic
    @Column(name = "HUAN_LUYEN_VIEN", nullable = true, length = 50)
    public String getHuanLuyenVien() {
        return huanLuyenVien;
    }

    public void setHuanLuyenVien(String huanLuyenVien) {
        this.huanLuyenVien = huanLuyenVien;
    }

    @Basic
    @Column(name = "CHU_SO_HUU", nullable = true, length = 50)
    public String getChuSoHuu() {
        return chuSoHuu;
    }

    public void setChuSoHuu(String chuSoHuu) {
        this.chuSoHuu = chuSoHuu;
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
    @Column(name = "MO_TA", nullable = true, length = 300)
    public String getMoTa() {
        return moTa;
    }

    public void setMoTa(String moTa) {
        this.moTa = moTa;
    }

    @Basic
    @Column(name = "QUOC_TICH", nullable = true, length = 100)
    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    @Basic
    @Column(name = "SO_BAN_THANG_SAN_NHA", nullable = true)
    public Integer getSoBanThangSanNha() {
        return soBanThangSanNha;
    }

    public void setSoBanThangSanNha(Integer soBanThangSanNha) {
        this.soBanThangSanNha = soBanThangSanNha;
    }

    @Basic
    @Column(name = "SO_BAN_THANG_SAN_KHACH", nullable = true)
    public Integer getSoBanThangSanKhach() {
        return soBanThangSanKhach;
    }

    public void setSoBanThangSanKhach(Integer soBanThangSanKhach) {
        this.soBanThangSanKhach = soBanThangSanKhach;
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

        DoiBongEntity that = (DoiBongEntity) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (logo != null ? !logo.equals(that.logo) : that.logo != null) return false;
        if (trangPhucTruyenThong != null ? !trangPhucTruyenThong.equals(that.trangPhucTruyenThong) : that.trangPhucTruyenThong != null)
            return false;
        if (huanLuyenVien != null ? !huanLuyenVien.equals(that.huanLuyenVien) : that.huanLuyenVien != null)
            return false;
        if (chuSoHuu != null ? !chuSoHuu.equals(that.chuSoHuu) : that.chuSoHuu != null) return false;
        if (diaChi != null ? !diaChi.equals(that.diaChi) : that.diaChi != null) return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;
        if (quocTich != null ? !quocTich.equals(that.quocTich) : that.quocTich != null) return false;
        if (soBanThangSanNha != null ? !soBanThangSanNha.equals(that.soBanThangSanNha) : that.soBanThangSanNha != null)
            return false;
        if (soBanThangSanKhach != null ? !soBanThangSanKhach.equals(that.soBanThangSanKhach) : that.soBanThangSanKhach != null)
            return false;
        if (trangThaiBanGhi != null ? !trangThaiBanGhi.equals(that.trangThaiBanGhi) : that.trangThaiBanGhi != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (logo != null ? logo.hashCode() : 0);
        result = 31 * result + (trangPhucTruyenThong != null ? trangPhucTruyenThong.hashCode() : 0);
        result = 31 * result + (huanLuyenVien != null ? huanLuyenVien.hashCode() : 0);
        result = 31 * result + (chuSoHuu != null ? chuSoHuu.hashCode() : 0);
        result = 31 * result + (diaChi != null ? diaChi.hashCode() : 0);
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        result = 31 * result + (quocTich != null ? quocTich.hashCode() : 0);
        result = 31 * result + (soBanThangSanNha != null ? soBanThangSanNha.hashCode() : 0);
        result = 31 * result + (soBanThangSanKhach != null ? soBanThangSanKhach.hashCode() : 0);
        result = 31 * result + (trangThaiBanGhi != null ? trangThaiBanGhi.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "doiBong")
    public Collection<ChiSoCauThuEntity> getChiSoCauThus() {
        return chiSoCauThus;
    }

    public void setChiSoCauThus(Collection<ChiSoCauThuEntity> chiSoCauThus) {
        this.chiSoCauThus = chiSoCauThus;
    }

    @ManyToOne
    @JoinColumn(name = "GIAI_DAUID", referencedColumnName = "ID", nullable = false)
    public GiaiDauEntity getGiaiDau() {
        return giaiDau;
    }

    public void setGiaiDau(GiaiDauEntity giaiDau) {
        this.giaiDau = giaiDau;
    }

    @ManyToOne
    @JoinColumn(name = "SAN_BONGID", referencedColumnName = "ID")
    public SanBongEntity getSanBong() {
        return sanBong;
    }

    public void setSanBong(SanBongEntity sanBong) {
        this.sanBong = sanBong;
    }

    @OneToMany(mappedBy = "doiBong")
    public Collection<DoiDaEntity> getDoiDas() {
        return doiDas;
    }

    public void setDoiDas(Collection<DoiDaEntity> doiDas) {
        this.doiDas = doiDas;
    }
}
