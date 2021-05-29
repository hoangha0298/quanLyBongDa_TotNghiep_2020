package com.example.demo.repository.entityTable;

import javax.persistence.*;
import java.util.Collection;

@Entity
@Table(name = "vong_dau", schema = "quanlybongda_totnghiep", catalog = "")
public class VongDauEntity extends com.example.demo.repository.Entity {
    private int id;
    private int vong;
    private String moTa;
    private Collection<TranDauEntity> tranDaus;
    private GiaiDauEntity giaiDau;

    @Id
    @Column(name = "ID", nullable = false)
    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    @Basic
    @Column(name = "VONG", nullable = false)
    public int getVong() {
        return vong;
    }

    public void setVong(int vong) {
        this.vong = vong;
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

        VongDauEntity that = (VongDauEntity) o;

        if (id != that.id) return false;
        if (vong != that.vong) return false;
        if (moTa != null ? !moTa.equals(that.moTa) : that.moTa != null) return false;

        return true;
    }

    @Override
    public int hashCode() {
        int result = id;
        result = 31 * result + vong;
        result = 31 * result + (moTa != null ? moTa.hashCode() : 0);
        return result;
    }

    @OneToMany(mappedBy = "vongDau")
    public Collection<TranDauEntity> getTranDaus() {
        return tranDaus;
    }

    public void setTranDaus(Collection<TranDauEntity> tranDaus) {
        this.tranDaus = tranDaus;
    }

    @ManyToOne
    @JoinColumn(name = "GIAI_DAU", referencedColumnName = "ID", nullable = false)
    public GiaiDauEntity getGiaiDau() {
        return giaiDau;
    }

    public void setGiaiDau(GiaiDauEntity giaiDau) {
        this.giaiDau = giaiDau;
    }
}
