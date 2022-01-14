package com.xiana.mybatis.bean;

import java.io.Serializable;

/**
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">Administrator</a>
 * @version v1.0
 * @package com.xiana.mybatis.bean
 * @project mybatis
 * @description [类型描述]
 * @createTime 2022/1/8 9:50
 */

public class Employee implements Serializable {
    private Integer id;
    private String lastName;
    private String email;
    private String gender;
    private Department department;

    public Department getDepartment() {
        return department;
    }

    public void setDepartment(Department department) {
        this.department = department;
    }

    public Employee() {
    }

    public Employee(Integer id, String lastName, String email, String gender) {
        this.id = id;
        this.lastName = lastName;
        this.email = email;
        this.gender = gender;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getLastName() {
        return lastName;
    }

    public void setLastName(String lastName) {
        this.lastName = lastName;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public String getGender() {
        return gender;
    }

    public void setGender(String gender) {
        this.gender = gender;
    }

    @Override
    public String toString() {
        return "Employee{" +
                "id=" + id +
                ", lastName='" + lastName + '\'' +
                ", email='" + email + '\'' +
                ", gender='" + gender + '\'' +
                '}';
    }
}