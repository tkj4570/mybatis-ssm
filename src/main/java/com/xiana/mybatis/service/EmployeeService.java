package com.xiana.mybatis.service;

import com.xiana.mybatis.bean.Employee;
import com.xiana.mybatis.dao.EmployeeMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * <p>包名称: com.xiana.mybatis.service </p>
 * <p>项目名称: mybatis-ssm </p>
 * <p>文件名称: null.java </p>
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2022/1/13 下午7:30 </p>
 * <p>公司信息: 全球贸易通公司</p>
 *
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">唐科技</a>
 * @version v1.0
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> list(){
        List<Employee> list = employeeMapper.list();
        return list;
    }
}
