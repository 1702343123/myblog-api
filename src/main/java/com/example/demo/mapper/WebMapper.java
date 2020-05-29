package com.example.demo.mapper;

import com.example.demo.entity.Web;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface WebMapper {
    int deleteByPrimaryKey(Integer wId);

    int insert(Web record);

    Web selectByPrimaryKey(Integer wId);

    List<Web> selectAll();

    @Update("update t_web set ${column_name} = #{value} where w_id = 1")
    int updateByPrimaryKey(@Param("column_name") String column_name,@Param("value")String value);
}