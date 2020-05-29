package com.example.demo.mapper;

import com.example.demo.entity.Link;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LinkMapper {
    int deleteByPrimaryKey(Integer lId);

    int insert(Link record);

    Link selectByPrimaryKey(Integer lId);

    List<Link> selectAll();

    int updateByPrimaryKey(Link record);
}