package com.example.backend.mapper;


import com.example.backend.bean.messages;
import org.apache.ibatis.annotations.*;
import org.springframework.stereotype.Repository;

import java.util.List;

@Mapper
@Repository

public interface MessageMapper {

    //获取警告信息
    @Select("select * from `SPSMS_Database`.`Messages` order by id DESC")
    List<messages> get_messages();

    //新增警告信息
    @Insert("INSERT INTO Messages (`occur_date`,`occur_area`, `occur_place`, `camera_id`, `alarm_reason`)\n" +
            "VALUES(#{occur_date},(SELECT location FROM Cameras WHERE equipment_id = #{camera_id}),(SELECT regulated_area FROM Cameras WHERE equipment_id = #{camera_id}),#{camera_id},#{alarm_reason});")
    void add_message(@Param("occur_date") String occur_date,@Param("camera_id") String camera_id,@Param("alarm_reason") String alarm_reason);

    //获取未提醒的警告信息
    @Select("select * from `SPSMS_Database`.`Messages` where if_warn='未提醒' ")
    List<messages> get_not_reminded_messages();

    //设置警告信息已提醒
    @Update("UPDATE `Messages` SET `if_warn`='已提醒' WHERE `id`=#{id}")
    void remind_message(@Param("id") int id);

    //删除信息
    @Delete("DELETE FROM `Messages` WHERE `id`=#{id}")
    void delete_message(@Param("id") int id);
}
