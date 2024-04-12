package com.example.backend.service;

import com.example.backend.bean.Shifts;
import com.example.backend.mapper.ShiftsMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class ShiftsService {
    @Autowired
    private ShiftsMapper shiftsMapper;

    public List<Shifts> getShifts(){
        List<Shifts> list = shiftsMapper.getShifts();
        return list;
    }

    public ArrayList<Shifts> getTodayShifts(String week){
        ArrayList<Shifts> list = shiftsMapper.getTodayShifts(week);
        return list;
    }
}
