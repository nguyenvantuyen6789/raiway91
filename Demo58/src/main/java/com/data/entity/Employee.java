package com.data.entity;

import jakarta.persistence.*;
import lombok.Data;

@Entity
@Table
@Data
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int id;

    private int departmentName;

}
