package com.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class SinhVien {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String maSinhVien;

    private String fullName;

    private String gioiTinh;

    // 1 sv co nhieu mon hoc sinh vien
    @OneToMany(mappedBy = "sinhVien")
    private List<MonHocSinhVien> monHocSinhViens;

    public List<MonHocSinhVien> getMonHocSinhViens() {
        return monHocSinhViens;
    }

    public void setMonHocSinhViens(List<MonHocSinhVien> monHocSinhViens) {
        this.monHocSinhViens = monHocSinhViens;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getMaSinhVien() {
        return maSinhVien;
    }

    public void setMaSinhVien(String maSinhVien) {
        this.maSinhVien = maSinhVien;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getGioiTinh() {
        return gioiTinh;
    }

    public void setGioiTinh(String gioiTinh) {
        this.gioiTinh = gioiTinh;
    }
}
