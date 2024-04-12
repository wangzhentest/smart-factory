package com.example.backend.controller;

import com.example.backend.bean.Employee;
import com.example.backend.bean.Shifts;
import com.example.backend.service.EmployeeService;
import com.example.backend.service.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class EmployeeController {
    @Autowired
    private EmployeeService employeeService;


    @CrossOrigin
    @RequestMapping("get_Employees")
    @ResponseBody
    public List<Employee> getEmployee(){
        List<Employee> list = employeeService.getEmployees();
        return list;
    }
    @CrossOrigin
    @RequestMapping("getDepartmentEmployees")
    @ResponseBody
    public List<Employee> getDepartmentEmployees(String department){
        List<Employee> list = employeeService.getDepartmentEmployees(department);
        return list;
    }
}
