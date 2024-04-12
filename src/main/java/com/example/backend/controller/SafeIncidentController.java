package com.example.backend.controller;

import com.example.backend.bean.LeaveRequest;
import com.example.backend.bean.SafetyIncident;
import com.example.backend.bean.Shifts;
import com.example.backend.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class SafeIncidentController {

    @Autowired
    private SafeIncidentService safeIncidentService;
    @Autowired
    private SummaryService summaryService;

    @CrossOrigin
    @RequestMapping("addIncidentCamera")
    @ResponseBody
    public void addIncidentCamera(String incident_type, String equipment_id,int handle_employee_id,String on_site_picture){
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = nowDateTime.format(formatter);
        safeIncidentService.addIncidentCamera(incident_type,date,equipment_id,handle_employee_id,on_site_picture);
    }

    @CrossOrigin
    @RequestMapping("addIncidentCar")
    @ResponseBody
    public void addIncidentCar(String incident_type,String equipment_id, int handle_employee_id,String on_site_picture){
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = nowDateTime.format(formatter);
        safeIncidentService.addIncidentCar(incident_type,date,equipment_id,handle_employee_id,on_site_picture);
    }
    @CrossOrigin
    @RequestMapping("finishIncident")
    @ResponseBody
    public void finishIncident(String reason,int resolve_employee_id,int incident_id,String expenditures,String potential_risk){
        LocalDateTime nowDateTime = LocalDateTime.now();
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss");
        String date = nowDateTime.format(formatter);
        safeIncidentService.finishIncident(reason,date,resolve_employee_id,incident_id);
        summaryService.add_summary(incident_id,reason,date,resolve_employee_id,expenditures,potential_risk);
    }
    @CrossOrigin
    @RequestMapping("getUnfinishedIncident")
    @ResponseBody
    public List<SafetyIncident> getUnfinishedIncident(){
        List<SafetyIncident> list = safeIncidentService.getUnfinishedIncident();
        return list;
    }

    @CrossOrigin
    @RequestMapping("getFinishedIncident")
    @ResponseBody
    public List<SafetyIncident> getFinishedIncident(){
        List<SafetyIncident> list = safeIncidentService.getFinishedIncident();
        return list;
    }
}


