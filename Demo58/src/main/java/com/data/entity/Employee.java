package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;
import lombok.Getter;
import lombok.Setter;

@Entity
@Table
@Getter
@Setter
public class Employee {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    // Lazy loading: việc lười tải:
    // LAZY: Dữ liệu ở mối quan hệ sẽ tải dần
    // khi nào cần dùng tới thì tải, k dùng tới thì k tải
    // EAGER: Dùng dữ liệu ở MQH liên quan hay không thì cũng tải

    // Vd: EAGER: lấy 1 department(query luôn bảng employee),
    // rồi get list employee in ra
    // ds nhân viên

    // Vd: LAZY: lấy 1 department ( chỉ query bảng department),
    // rồi get list employee => query vào bảng employee
    // nếu k dùng list employee => k query vào bảng employee

    // dùng EAGER, dù có lấy đối tượng dưới hay k cũng truy vấn
    // cả 2 bảng
    @ManyToOne
    @JoinColumn(name = "department_id")
    private Department department;

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", fullName='" + fullName + '\'' +
                '}';
    }
}
