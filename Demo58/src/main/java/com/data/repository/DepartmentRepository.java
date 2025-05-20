package com.data.repository;

import com.data.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Integer> {

    // SELECT * FROM Department WHERE departmentName=:departmentName;
    List<Department> findByDepartmentName(String departmentName);

    List<Department> findByDepartmentNameAndAddress(String departmentName,
                                                    String address);

    // tên method k quan trọng
    @Query("FROM Department WHERE departmentName = :departmentName")
    List<Department> getData2(String departmentName);
}
