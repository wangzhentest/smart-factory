package com.example.backend.mapper;

import com.example.backend.bean.Employee;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface EmployeeMapper {
    @Select("select * from Employees")
    List<Employee> getEmployees();

    @Select("SELECT * FROM `Employees` WHERE `department` = #{department}")
    List<Employee> getDepartmentEmployees(@Param("department") String department);
}
