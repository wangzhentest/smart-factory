package com.example.backend.mapper;

import com.example.backend.bean.Camera;
import com.example.backend.bean.SafeEquipment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;

import java.util.List;

@Mapper
public interface CameraMapper {

    @Select("SELECT * FROM `Cameras` WHERE `location` = #{location} ")
    List<Camera> getSurveillanceEquipments(@Param("location") String location);

}
