package com.data;

import jakarta.persistence.*;

// khai báo Account là 1 entity để làm việc với bảng account trong sql
// entity: class Java + @Entity
@Entity
@Table(name = "account")
public class Account {

    // khoá chính: @Id
    @Id
    // tự tăng: Generated IDENTITY
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private String fullName;

    private String address;

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getFullName() {
        return fullName;
    }

    public void setFullName(String fullName) {
        this.fullName = fullName;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
