package com.data;

import jakarta.persistence.*;

import java.util.List;

@Entity
@Table
public class MonHoc {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;

    private String tenMon;

    private Integer soGio;

    private Integer soBuoi;

    @ManyToMany
    // @JoinTalbe: khai báo tên bảng thứ 3 và 2 cột khóa ngoại
    @JoinTable(name = "mon_hoc_sinh_vien",
        joinColumns = @JoinColumn(name = "mon_hoc_id"),
        inverseJoinColumns = @JoinColumn(name = "sinh_vien_id")
    )
    private List<SinhVien> sinhViens;

    public List<SinhVien> getSinhViens() {
        return sinhViens;
    }

    public void setSinhViens(List<SinhVien> sinhViens) {
        this.sinhViens = sinhViens;
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
