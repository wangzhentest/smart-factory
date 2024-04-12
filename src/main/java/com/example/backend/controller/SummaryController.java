package com.example.backend.controller;

import com.example.backend.bean.LeaveRequest;
import com.example.backend.bean.SafetyIncident;
import com.example.backend.bean.Shifts;
import com.example.backend.bean.Summary;
import com.example.backend.service.*;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;

import java.time.DayOfWeek;
import java.time.LocalDate;
import java.time.LocalTime;
import java.time.format.DateTimeFormatter;
import java.time.format.TextStyle;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

@RestController
public class SummaryController {

    @Autowired
    private SummaryService summaryService;

    @CrossOrigin
    @RequestMapping("get_summary_by_id")
    @ResponseBody
    public Summary get_summary_by_id(int incident_id){
        Summary summary = summaryService.get_summary_by_id(incident_id);
        return summary;
    }

}


