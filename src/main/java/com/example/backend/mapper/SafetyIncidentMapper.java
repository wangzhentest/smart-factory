package com.example.backend.mapper;

import com.example.backend.bean.SafetyIncident;
import com.example.backend.bean.Shifts;
import org.apache.ibatis.annotations.*;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface SafetyIncidentMapper {
    @Insert("INSERT INTO `Safety_Incidents` (`incident_type`, `occur_date`, `location`, `regulated_area`, `equipment_id`," +
            " `handle_employee_id`,`on_site_picture`) VALUES (#{incident_type},#{occur_date},(SELECT location From Cameras where equipment_id=#{equipment_id})," +
            "(SELECT regulated_area From Cameras where equipment_id=#{equipment_id}),#{equipment_id},#{handle_employee_id},#{on_site_picture})")
    void addIncidentCamera(@Param("incident_type") String incident_type,@Param("occur_date") String occur_date,@Param("equipment_id") String equipment_id,
                     @Param("handle_employee_id") int handle_employee_id,@Param("on_site_picture") String on_site_picture);

    @Insert("INSERT INTO `Safety_Incidents` (`incident_type`, `occur_date`, `location`, `regulated_area`, `equipment_id`,`handle_employee_id`,`on_site_picture`)" +
            " VALUES (#{incident_type},#{occur_date},(SELECT location From Patrol_Cars where equipment_id=#{equipment_id}),(SELECT regulated_area From Patrol_Cars where equipment_id=#{equipment_id})," +
            "#{equipment_id},#{handle_employee_id},#{on_site_picture})")
    void addIncidentCar(@Param("incident_type") String incident_type,@Param("occur_date") String occur_date,@Param("equipment_id") String equipment_id,
                           @Param("handle_employee_id") int handle_employee_id,@Param("on_site_picture") String on_site_picture);

    @Select("SELECT * FROM `Safety_Incidents` WHERE `solve_date` = 'null' ")
    List<SafetyIncident> getUnfinishedIncident();

    @Select("SELECT * FROM `Safety_Incidents` WHERE `solve_date` != 'null';")
    List<SafetyIncident> getFinishedIncident();

    @Update("UPDATE `Safety_Incidents` SET `reason`=#{reason}, `solve_date`=#{solve_date}, `resolve_employee_id`=#{resolve_employee_id} WHERE `incident_id`=#{incident_id};")
    void finishIncident(@Param("reason") String reason,@Param("solve_date") String solve_date,@Param("resolve_employee_id") int resolve_employee_id, @Param("incident_id") int incident_id);

    @Select("SELECT * from Safety_Incidents where incident_id = #{incident_id}")
    SafetyIncident getIncidentById(@Param("incident_id") int incident_id);
}
