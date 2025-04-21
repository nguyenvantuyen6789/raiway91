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

    @Column(nullable = false)
    private String fullName;

    private String address;

    // java có kiểu số: => sql
    // int      => int
    // bigint, smallint, short: columnDefinition = "dữ liệu"
    @Column(name = "age_number", columnDefinition = "bigint")
    private int ageNumber;

    @Column(columnDefinition = "smallint")
    private int money;

    // unique = true: Dữ liệu phải duy nhất
    @Column(unique = true)
    private String email;

    // String: mặc định sang sql là varchar(255)
    // text: custom sang kiểu text
    @Column(columnDefinition = "text")
    private String content;

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public int getAgeNumber() {
        return ageNumber;
    }

    public void setAgeNumber(int ageNumber) {
        this.ageNumber = ageNumber;
    }

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
