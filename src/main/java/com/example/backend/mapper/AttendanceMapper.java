package com.example.backend.mapper;

import com.example.backend.bean.Attendance;
import com.example.backend.bean.Shifts;
import org.apache.ibatis.annotations.*;

import java.util.List;

@Mapper
public interface AttendanceMapper {
    @Insert("UPDATE `Attendance` SET `clock_in_time` = #{clock_in_time} ,`state` = #{state} WHERE `date` = #{date} AND `employee_id` = #{employee_id};")
    void employeeClockIn(@Param("employee_id") int employee_id,@Param("date") String date,@Param("clock_in_time") String clock_in_time,@Param("state") int state);

    @Update("UPDATE `Attendance` SET `clock_out_time` = #{clock_out_time} ,`state` = #{state} WHERE `date` = #{date} AND `employee_id` = #{employee_id};")
    void employeeClockOut(@Param("employee_id") int employee_id,@Param("date") String date,@Param("clock_out_time") String clock_out_time,@Param("state") int state);

    @Insert("INSERT INTO `Attendance` (`name`,`employee_id`, `date`) VALUES (#{name},#{employee_id},#{date});")
    void addAttendance(@Param("name") String name,@Param("employee_id") int employee_id,@Param("date") String date);

    @Update("UPDATE `Attendance` SET `state`=#{state} WHERE `attendance_id`=#{attendance_id};")
    void updateState(@Param("attendance_id") int attendance_id,@Param("state") int state);

    @Select("SELECT state from Attendance WHERE employee_id = #{employee_id} and date = #{date}")
    int get_state(@Param("employee_id") int employee_id,@Param("date") String date);

    @Select("SELECT * FROM Attendance WHERE date = #{date}")
    List<Attendance> getTodayAttendance(@Param("date") String date);
}
