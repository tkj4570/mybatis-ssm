package com.xiana.mybatis.dao;

import com.xiana.mybatis.bean.Department;
import java.util.List;

public interface DepartmentMapper {
    int insert(Department record);

    List<Department> selectAll();
}