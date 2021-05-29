package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.sql.Date;

@Entity
@Table(name = "chi_so_cau_thu", schema = "quanlybongda_totnghiep", catalog = "")
public class ChiSoCauThuEntity extends com.example.demo.repository.Entity {
    private int id;
    private Integer soAo;
    private String chanThuan;
    private Integer chieuCao;
    private Double canNang;
    private String tinhTrangSucKhoe;
    private Date ngayTao;
    private Integer soBanThangSanNha;
    private Integer soBanThangSanKhach;
    private Integer soHoTroSanNha;
    private Integer soHoTroSanKhach;
    private String trangThaiBanGhi;
    private CauThuEntity cauThu;
    private DoiBongEntity doiBong;
    private ViTriEntity viTriSoTruong;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "SO_AO", nullable = true)
    public Integer getSoAo() {
        return soAo;
    }

    public void setSoAo(Integer soAo) {
        this.soAo = soAo;
    }

    @Basic
    @Column(name = "CHAN_THUAN", nullable = true, length = 50)
    public String getChanThuan() {
        return chanThuan;
    }

    public void setChanThuan(String chanThuan) {
        this.chanThuan = chanThuan;
    }

    @Basic
    @Column(name = "CHIEU_CAO", nullable = true)
    public Integer getChieuCao() {
        return chieuCao;
    }

    public void setChieuCao(Integer chieuCao) {
        this.chieuCao = chieuCao;
    }

    @Basic
    @Column(name = "CAN_NANG", nullable = true, precision = 0)
    public Double getCanNang() {
        return canNang;
    }

    public void setCanNang(Double canNang) {
        this.canNang = canNang;
    }

    @Basic
    @Column(name = "TINH_TRANG_SUC_KHOE", nullable = true, length = 500)
    public String getTinhTrangSucKhoe() {
        return tinhTrangSucKhoe;
    }

    public void setTinhTrangSucKhoe(String tinhTrangSucKhoe) {
        this.tinhTrangSucKhoe = tinhTrangSucKhoe;
    }

    @Basic
    @Column(name = "NGAY_TAO", nullable = true)
    public Date getNgayTao() {
        return ngayTao;
    }

    public void setNgayTao(Date ngayTao) {
        this.ngayTao = ngayTao;
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
    @Column(name = "SO_HO_TRO_SAN_NHA", nullable = true)
    public Integer getSoHoTroSanNha() {
        return soHoTroSanNha;
    }

    public void setSoHoTroSanNha(Integer soHoTroSanNha) {
        this.soHoTroSanNha = soHoTroSanNha;
    }

    @Basic
    @Column(name = "SO_HO_TRO_SAN_KHACH", nullable = true)
    public Integer getSoHoTroSanKhach() {
        return soHoTroSanKhach;
    }

    public void setSoHoTroSanKhach(Integer soHoTroSanKhach) {
        this.soHoTroSanKhach = soHoTroSanKhach;
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

        ChiSoCauThuEntity that = (ChiSoCauThuEntity) o;

        if (id != that.id) return false;
        if (soAo != null ? !soAo.equals(that.soAo) : that.soAo != null) return false;
        if (chanThuan != null ? !chanThuan.equals(that.chanThuan) : that.chanThuan != null) return false;
        if (chieuCao != null ? !chieuCao.equals(that.chieuCao) : that.chieuCao != null) return false;
        if (canNang != null ? !canNang.equals(that.canNang) : that.canNang != null) return false;
        if (tinhTrangSucKhoe != null ? !tinhTrangSucKhoe.equals(that.tinhTrangSucKhoe) : that.tinhTrangSucKhoe != null)
            return false;
        if (ngayTao != null ? !ngayTao.equals(that.ngayTao) : that.ngayTao != null) return false;
        if (soBanThangSanNha != null ? !soBanThangSanNha.equals(that.soBanThangSanNha) : that.soBanThangSanNha != null)
            return false;
        if (soBanThangSanKhach != null ? !soBanThangSanKhach.equals(that.soBanThangSanKhach) : that.soBanThangSanKhach != null)
            return false;
        if (soHoTroSanNha != null ? !soHoTroSanNha.equals(that.soHoTroSanNha) : that.soHoTroSanNha != null)
            return false;
        if (soHoTroSanKhach != null ? !soHoTroSanKhach.equals(that.soHoTroSanKhach) : that.soHoTroSanKhach != null)
            return false;
        if (trangThaiBanGhi != null ? !trangThaiBanGhi.equals(that.trangThaiBanGhi) : that.trangThaiBanGhi != null)
            return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (soAo != null ? soAo.hashCode() : 0);
        result = 31 * result + (chanThuan != null ? chanThuan.hashCode() : 0);
        result = 31 * result + (chieuCao != null ? chieuCao.hashCode() : 0);
        result = 31 * result + (canNang != null ? canNang.hashCode() : 0);
        result = 31 * result + (tinhTrangSucKhoe != null ? tinhTrangSucKhoe.hashCode() : 0);
        result = 31 * result + (ngayTao != null ? ngayTao.hashCode() : 0);
        result = 31 * result + (soBanThangSanNha != null ? soBanThangSanNha.hashCode() : 0);
        result = 31 * result + (soBanThangSanKhach != null ? soBanThangSanKhach.hashCode() : 0);
        result = 31 * result + (soHoTroSanNha != null ? soHoTroSanNha.hashCode() : 0);
        result = 31 * result + (soHoTroSanKhach != null ? soHoTroSanKhach.hashCode() : 0);
        result = 31 * result + (trangThaiBanGhi != null ? trangThaiBanGhi.hashCode() : 0);
        return result;
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
    @JoinColumn(name = "DOI_BONG", referencedColumnName = "ID", nullable = false)
    public DoiBongEntity getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        this.doiBong = doiBong;
    }

    @ManyToOne
    @JoinColumn(name = "VI_TRI_SO_TRUONG", referencedColumnName = "ID")
    public ViTriEntity getViTriSoTruong() {
        return viTriSoTruong;
    }

    public void setViTriSoTruong(ViTriEntity viTriSoTruong) {
        this.viTriSoTruong = viTriSoTruong;
    }
}
