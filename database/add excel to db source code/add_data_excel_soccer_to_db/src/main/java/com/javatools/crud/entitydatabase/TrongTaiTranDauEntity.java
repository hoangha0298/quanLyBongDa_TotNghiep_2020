package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.math.BigDecimal;

@Entity
@Table(name = "trong_tai_tran_dau", schema = "quanlybongda_totnghiep", catalog = "")
public class TrongTaiTranDauEntity {
    private int id;
    private BigDecimal tienCong;
    private TranDauEntity tranDau;
    private TrongTaiEntity trongTai;
    private ViTriEntity viTriTrongTran;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "TIEN_CONG", nullable = true, precision = 2)
    public BigDecimal getTienCong() {
        return tienCong;
    }

    public void setTienCong(BigDecimal tienCong) {
        this.tienCong = tienCong;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        TrongTaiTranDauEntity that = (TrongTaiTranDauEntity) o;

        if (id != that.id) return false;
        if (tienCong != null ? !tienCong.equals(that.tienCong) : that.tienCong != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + (tienCong != null ? tienCong.hashCode() : 0);
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
    @JoinColumn(name = "TRONG_TAI", referencedColumnName = "ID", nullable = false)
    public TrongTaiEntity getTrongTai() {
        return trongTai;
    }

    public void setTrongTai(TrongTaiEntity trongTai) {
        this.trongTai = trongTai;
    }

    @ManyToOne
    @JoinColumn(name = "VI_TRI_TRONG_TRAN", referencedColumnName = "ID", nullable = false)
    public ViTriEntity getViTriTrongTran() {
        return viTriTrongTran;
    }

    public void setViTriTrongTran(ViTriEntity viTriTrongTran) {
        this.viTriTrongTran = viTriTrongTran;
    }
}
