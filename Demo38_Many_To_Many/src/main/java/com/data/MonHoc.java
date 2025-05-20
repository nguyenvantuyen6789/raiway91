package com.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class MonHoc {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    @Column(unique = true)
    private String tenMon;

    private Integer soGio;

    private Integer soBuoi;

    // 1 mon hoc co nhieu mon hoc sinh vien
    @OneToMany(mappedBy = "monHoc")
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

    public String getTenMon() {
        return tenMon;
    }

    public void setTenMon(String tenMon) {
        this.tenMon = tenMon;
    }

    public Integer getSoGio() {
        return soGio;
    }

    public void setSoGio(Integer soGio) {
        this.soGio = soGio;
    }

    public Integer getSoBuoi() {
        return soBuoi;
    }

    public void setSoBuoi(Integer soBuoi) {
        this.soBuoi = soBuoi;
    }
}
