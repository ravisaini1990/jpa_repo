package com.ravi.jpa_repo.api.entity;

import jakarta.persistence.*;

@Entity
@Table(name = "department")
public class Department {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "dept_id")
    private int id;

    @Column(name = "dept_name")
    private String deptName;

    @Column(name = "dept_address")
    private String deptAddress;

    Department() {}

//    public Department(String deptName, String deptAddress) {
//        this.deptName = deptName;
//        this.deptAddress = deptAddress;
//    }

    public int getDeptId() {
        return id;
    }

    public void setDeptId(int id) {
        this.id = id;
    }

    public String getDeptName() {
        return deptName;
    }

    public void setDeptName(String deptName) {
        this.deptName = deptName;
    }

    public String getDeptAddress() {
        return deptAddress;
    }

    public void setDeptAddress(String deptAddress) {
        this.deptAddress = deptAddress;
    }

    @Override
    public String toString() {
        return "Department{" +
                "deptId=" + id +
                ", deptName='" + deptName + '\'' +
                ", deptAddress='" + deptAddress + '\'' +
                '}';
    }
}
