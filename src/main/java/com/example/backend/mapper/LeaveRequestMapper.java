package com.example.backend.mapper;

import com.example.backend.bean.Employee;
import com.example.backend.bean.LeaveRequest;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface LeaveRequestMapper {
    @Select("SELECT * FROM `LeaveRequests` WHERE `date` = #{date} AND `status` = '休假中' ")
    List<LeaveRequest> getLeaveRequests(@Param("date")String date);

}
