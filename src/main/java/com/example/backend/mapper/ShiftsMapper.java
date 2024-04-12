package com.example.backend.mapper;

import com.example.backend.bean.Shifts;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.ArrayList;
import java.util.List;

@Mapper
public interface ShiftsMapper {
    @Select("select * from Shifts")
    List<Shifts> getShifts();

    @Select("SELECT * FROM `Shifts` WHERE `week` = #{week}")
    ArrayList<Shifts> getTodayShifts(@Param("week")String week);

}
