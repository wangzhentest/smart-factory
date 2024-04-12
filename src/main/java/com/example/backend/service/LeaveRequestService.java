package com.example.backend.service;

import com.example.backend.bean.Employee;
import com.example.backend.bean.LeaveRequest;
import com.example.backend.mapper.EmployeeMapper;
import com.example.backend.mapper.LeaveRequestMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class LeaveRequestService {
    @Autowired
    private LeaveRequestMapper leaveRequestMapper;

    public List<LeaveRequest> getLeaveRequests(String date){
        List<LeaveRequest> list = leaveRequestMapper.getLeaveRequests(date);
        return list;
    }


}
