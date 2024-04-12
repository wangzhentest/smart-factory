package com.example.backend.service;

import com.example.backend.bean.Attendance;
import com.example.backend.bean.Employee;
import com.example.backend.mapper.AttendanceMapper;
import com.example.backend.mapper.EmployeeMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class AttendanceService {
    @Autowired
    private AttendanceMapper attendanceMapper;

    public void employeeClockIn(int employee_id,String date,String clock_in_time,int state){
        attendanceMapper.employeeClockIn(employee_id,date,clock_in_time,state);
    }

    public void employeeClockOut(int employee_id,String date,String clock_out_time,int state){
        attendanceMapper.employeeClockOut(employee_id,date,clock_out_time,state);
    }

    public void addAttendance(String name,int employee_id,String date){
        attendanceMapper.addAttendance(name,employee_id,date);
    }


    public void updateState(int attendance_id,int state){
        attendanceMapper.updateState(attendance_id,state);
    }

    public int get_state(int attendance_id,String date){
        return attendanceMapper.get_state(attendance_id,date);
    }

    public List<Attendance> getTodayAttendance(String date){
        List<Attendance> list = attendanceMapper.getTodayAttendance(date);
        return list;
    }
}
