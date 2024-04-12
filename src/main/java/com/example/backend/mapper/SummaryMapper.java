package com.example.backend.mapper;


import com.example.backend.bean.Summary;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Repository;

import java.util.List;

@org.apache.ibatis.annotations.Mapper
@Repository

public interface SummaryMapper {
    @Select("select * from  `Summaries`")
    List<Summary> get_summaries();

    @Select("select * from `Summaries` where incident_id = #{incident_id}")
    Summary get_summary_by_id(@Param("incident_id") int incident_id);

    @Insert("INSERT INTO `Summaries` (`incident_id`, `incident_type`, `occur_date`, `location`,`occur_area`,`incident_reason`,`dispose_date`,`dispose_staff_id`,`equipment_id` ,`expenditures`, `potential_risk`) VALUES " +
            "(#{incident_id},#{incident_type},#{occur_date},#{location},#{occur_area},#{incident_reason},#{dispose_date},#{dispose_staff_id},#{equipment_id},#{expenditures},#{potential_risk})")
    void add_summary(@Param("incident_id") int incident_id,@Param("incident_type") String incident_type,@Param("occur_date") String occur_date,@Param("location") String location,@Param("occur_area") String occur_area,
    @Param("incident_reason") String incident_reason,@Param("dispose_date") String dispose_date,@Param("dispose_staff_id") int dispose_staff_id,@Param("equipment_id") String equipment_id,@Param("expenditures") String expenditures,@Param("potential_risk") String potential_risk);


}
