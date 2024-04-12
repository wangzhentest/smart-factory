package com.example.backend.service;

import com.example.backend.bean.SafetyIncident;
import com.example.backend.bean.Summary;
import com.example.backend.mapper.SafetyIncidentMapper;
import com.example.backend.mapper.SummaryMapper;
import org.apache.ibatis.annotations.Param;
import org.springframework.beans.factory.annotation.Autowired;

import java.util.List;

@org.springframework.stereotype.Service
public class SummaryService {
    @Autowired
    private  SummaryMapper summaryMapper;
    @Autowired
    private SafetyIncidentMapper safetyIncidentMapper;

    public void add_summary(int incident_id,String incident_reason, String dispose_date,int dispose_staff_id,String expenditures,String potential_risk){
        SafetyIncident safetyIncident = safetyIncidentMapper.getIncidentById(incident_id);
        summaryMapper.add_summary(incident_id,safetyIncident.getIncident_type(),safetyIncident.getOccur_date(),safetyIncident.getLocation(),safetyIncident.getRegulated_area(),
                incident_reason,dispose_date,dispose_staff_id,safetyIncident.getEquipment_id(),expenditures,potential_risk);
    }

    public Summary get_summary_by_id(int incident_id){
        Summary summary = summaryMapper.get_summary_by_id(incident_id);
        return summary;
    }


}
