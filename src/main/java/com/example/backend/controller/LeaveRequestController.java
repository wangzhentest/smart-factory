package com.example.backend.controller;

import com.example.backend.bean.Employee;
import com.example.backend.bean.LeaveRequest;
import com.example.backend.bean.Shifts;
import com.example.backend.service.EmployeeService;
import com.example.backend.service.LeaveRequestService;
import com.example.backend.service.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class LeaveRequestController {
    @Autowired
    private LeaveRequestService leaveRequestService;


}
