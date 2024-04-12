package com.example.backend.service;

import com.example.backend.bean.Employee;
import com.example.backend.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class EmployeeService {
    @Autowired
    private EmployeeMapper employeeMapper;

    public List<Employee> getEmployees(){
        List<Employee> list = employeeMapper.getEmployees();
        return list;
    }

    public List<Employee> getDepartmentEmployees(String department){
        List<Employee> list = employeeMapper.getDepartmentEmployees(department);
        return list;
    }
}
