package com.data;

import jakarta.persistence.*;

@Entity
@Table
public class MonHocSinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    // nhieu mon hoc sinh vien thuoc 1 mon hoc
    @ManyToOne
    @JoinColumn(name = "mon_hoc_id")
    private MonHoc monHoc;

    // nhieu mon hoc sinh vien thuoc 1 sinh vine
    @ManyToOne
    @JoinColumn(name = "sinh_vien_id")
    private SinhVien sinhVien;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public MonHoc getMonHoc() {
        return monHoc;
    }

    public void setMonHoc(MonHoc monHoc) {
        this.monHoc = monHoc;
    }

    public SinhVien getSinhVien() {
        return sinhVien;
    }

    public void setSinhVien(SinhVien sinhVien) {
        this.sinhVien = sinhVien;
    }
}
