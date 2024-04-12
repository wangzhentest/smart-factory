package com.example.backend.controller;

import com.example.backend.bean.Shifts;
import com.example.backend.service.ShiftsService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

@RestController
public class ShiftsController {
    @Autowired
    private ShiftsService shiftsService;

    @CrossOrigin
    @RequestMapping("get_staff_Shifts")
    @ResponseBody
    public List<Shifts> getShifts(){
        List<Shifts> list = shiftsService.getShifts();
        return list;
    }
}
