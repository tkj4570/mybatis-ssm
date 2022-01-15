package com.xiana.mybatis.controller;

import com.xiana.mybatis.bean.Employee;
import com.xiana.mybatis.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.ResponseBody;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * <p>包名称: com.xiana.mybatis.controller </p>
 * <p>项目名称: mybatis-ssm </p>
 * <p>文件名称: null.java </p>
 * <p>描述: [类型描述] </p>
 * <p>创建时间: 2022/1/14 下午4:22 </p>
 * <p>公司信息: 全球贸易通公司</p>
 *
 * @author <a href="mail to: 457066709@qq.com" rel="nofollow">唐科技</a>
 * @version v1.0
 * @update [序号][日期YYYY-MM-DD] [更改人姓名][变更描述]
 */
@Controller
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;

    @RequestMapping(value = "/employee", method = RequestMethod.GET)
    public String list(Model model) {
        List<Employee> employeeList = employeeService.list();
        model.addAttribute("employeeList", employeeList);
        return "employee/employee_list";
    }

    @RequestMapping(value = "/employeeAdd", method = RequestMethod.GET)
    public String addPage() {
        return "employee/employee_add";
    }

    @RequestMapping(value = "/employeeEdit/{id}", method = RequestMethod.GET)
    public String editPage(@PathVariable("id") Integer id,Model model){
        Employee employee = employeeService.getEmployeeById(id);
        model.addAttribute("employee", employee);
        return "employee/employee_edit";
    }

    @RequestMapping(value = "/employee", method = RequestMethod.POST)
    public String add(Employee employee){
        int add = employeeService.add(employee);
        if(add != 0){
            return "redirect:/employee";
        }else{
            System.out.println("添加失败");
            return "error";
        }
    }

    @RequestMapping(value = "/employee/{id}", method = RequestMethod.DELETE)
    @ResponseBody
    public Map<String, Object> delete(@PathVariable("id") Integer id){
        employeeService.delete(id);
        Map<String,Object> res = new HashMap();
        res.put("code", 200);
        res.put("message","删除成功");
        return res;
    }

    @RequestMapping(value = "/employee", method = RequestMethod.PUT)
    public String  edit(Employee employee){
        employeeService.edit(employee);
        return "redirect:/employee";
    }
}
