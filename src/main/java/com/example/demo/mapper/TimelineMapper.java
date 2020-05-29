package com.example.demo.mapper;

import com.example.demo.entity.Timeline;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface TimelineMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Timeline record);

    Timeline selectByPrimaryKey(Integer id);

    List<Timeline> selectAll();

    int updateByPrimaryKey(Timeline record);
}