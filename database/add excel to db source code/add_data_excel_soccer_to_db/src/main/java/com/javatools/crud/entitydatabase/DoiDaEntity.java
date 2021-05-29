package com.javatools.crud.entitydatabase;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "doi_da", schema = "quanlybongda_totnghiep", catalog = "")
public class DoiDaEntity {
    private int id;
    private Collection<CauThuDaEntity> cauThuDas;
    private TranDauEntity tranDau;
    private DoiBongEntity doiBong;
    private ViTriEntity viTri;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;

        DoiDaEntity that = (DoiDaEntity) o;

        if (id != that.id) return false;

        return true;
    }

    @Override
    public int hashCode() {
        return id;
    }

    @OneToMany(mappedBy = "doiDa")
    public Collection<CauThuDaEntity> getCauThuDas() {
        return cauThuDas;
    }

    public void setCauThuDas(Collection<CauThuDaEntity> cauThuDas) {
        this.cauThuDas = cauThuDas;
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
    @JoinColumn(name = "DOI_BONG", referencedColumnName = "ID", nullable = false)
    public DoiBongEntity getDoiBong() {
        return doiBong;
    }

    public void setDoiBong(DoiBongEntity doiBong) {
        this.doiBong = doiBong;
    }

    @ManyToOne
    @JoinColumn(name = "VI_TRI", referencedColumnName = "ID", nullable = false)
    public ViTriEntity getViTri() {
        return viTri;
    }

    public void setViTri(ViTriEntity viTri) {
        this.viTri = viTri;
    }
}
