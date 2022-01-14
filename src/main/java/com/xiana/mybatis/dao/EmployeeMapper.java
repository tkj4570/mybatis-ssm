package com.xiana.mybatis.dao;

import com.xiana.mybatis.bean.Employee;

import java.util.List;

public interface EmployeeMapper {

    Employee getEmployeeById(Integer id);

    List<Employee> list();
}
