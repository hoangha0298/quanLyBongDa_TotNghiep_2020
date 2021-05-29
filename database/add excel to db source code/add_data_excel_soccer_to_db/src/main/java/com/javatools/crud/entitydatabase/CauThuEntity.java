package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.sql.Date;
import java.util.Collection;

@Entity
@Table(name = "cau_thu", schema = "quanlybongda_totnghiep", catalog = "")
public class CauThuEntity {
    private int id;
    private String ten;
    private String quocTich;
    private Date ngaySinh;
    private Integer gioiTinh;
    private String dacDiem;
    private Collection<CauThuDaEntity> cauThuDas;
    private Collection<ChiSoCauThuEntity> chiSoCauThus;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
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
    @Column(name = "QUOC_TICH", nullable = true, length = 100)
    public String getQuocTich() {
        return quocTich;
    }

    public void setQuocTich(String quocTich) {
        this.quocTich = quocTich;
    }

    @Basic
    @Column(name = "NGAY_SINH", nullable = true)
    public Date getNgaySinh() {
        return ngaySinh;
    }

    public void setNgaySinh(Date ngaySinh) {
        this.ngaySinh = ngaySinh;
    }

    @Basic
    @Column(name = "GIOI_TINH", nullable = true)
    public Integer getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(Integer gioiTinh) {
        this.gioiTinh = gioiTinh;
    }

    @Basic
    @Column(name = "DAC_DIEM", nullable = true, length = 300)
    public String getDacDiem() {
        return dacDiem;
    }

    public void setDacDiem(String dacDiem) {
        this.dacDiem = dacDiem;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        CauThuEntity that = (CauThuEntity) o;

        if (id != that.id) return false;
        if (ten != null ? !ten.equals(that.ten) : that.ten != null) return false;
        if (quocTich != null ? !quocTich.equals(that.quocTich) : that.quocTich != null) return false;
        if (ngaySinh != null ? !ngaySinh.equals(that.ngaySinh) : that.ngaySinh != null) return false;
        if (gioiTinh != null ? !gioiTinh.equals(that.gioiTinh) : that.gioiTinh != null) return false;
        if (dacDiem != null ? !dacDiem.equals(that.dacDiem) : that.dacDiem != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (ten != null ? ten.hashCode() : 0);
        result = 31 * result + (quocTich != null ? quocTich.hashCode() : 0);
        result = 31 * result + (ngaySinh != null ? ngaySinh.hashCode() : 0);
        result = 31 * result + (gioiTinh != null ? gioiTinh.hashCode() : 0);
        result = 31 * result + (dacDiem != null ? dacDiem.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "cauThu")
    public Collection<CauThuDaEntity> getCauThuDas() {
        return cauThuDas;
    }

    public void setCauThuDas(Collection<CauThuDaEntity> cauThuDas) {
        this.cauThuDas = cauThuDas;
    }

    @OneToMany(mappedBy = "cauThu")
    public Collection<ChiSoCauThuEntity> getChiSoCauThus() {
        return chiSoCauThus;
    }

    public void setChiSoCauThus(Collection<ChiSoCauThuEntity> chiSoCauThus) {
        this.chiSoCauThus = chiSoCauThus;
    }
}
