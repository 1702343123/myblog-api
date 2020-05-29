package com.example.demo.mapper;

import com.example.demo.entity.Icon;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface IconMapper {
    int deleteByPrimaryKey(Integer iId);

    int insert(Icon record);

    Icon selectByPrimaryKey(Integer iId);

    List<Icon> selectAll();

    int updateByPrimaryKey(Icon record);
}