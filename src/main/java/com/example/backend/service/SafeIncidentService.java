package com.example.backend.service;

import com.example.backend.bean.SafetyIncident;
import com.example.backend.mapper.AttendanceMapper;
import com.example.backend.mapper.SafetyIncidentMapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class SafeIncidentService {
    @Autowired
    private SafetyIncidentMapper safetyIncidentMapper;

    public void addIncidentCamera(String incident_type,String occur_date, String equipment_id,int handle_employee_id,String on_site_picture){
        System.out.println(incident_type);
        System.out.println(occur_date);
        System.out.println(equipment_id);
        System.out.println(handle_employee_id);
        System.out.println(on_site_picture);
        safetyIncidentMapper.addIncidentCamera(incident_type,occur_date,equipment_id,handle_employee_id,on_site_picture);

    }

    public void addIncidentCar(String incident_type,String occur_date,String equipment_id, int handle_employee_id,String on_site_picture){
        safetyIncidentMapper.addIncidentCar(incident_type,occur_date,equipment_id,handle_employee_id,on_site_picture);
    }

    public List<SafetyIncident> getUnfinishedIncident(){
        List<SafetyIncident> list = safetyIncidentMapper.getUnfinishedIncident();
        return list;
    }
    public List<SafetyIncident> getFinishedIncident(){
        List<SafetyIncident> list = safetyIncidentMapper.getFinishedIncident();
        return list;
    }
    public void finishIncident(String reason,String solve_date,int resolve_employee_id,int incident_id){
        safetyIncidentMapper.finishIncident(reason,solve_date,resolve_employee_id,incident_id);
    }
}
