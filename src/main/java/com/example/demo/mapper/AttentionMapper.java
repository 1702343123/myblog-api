package com.example.demo.mapper;

import com.example.demo.entity.Attention;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface AttentionMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Attention record);

    Attention selectByPrimaryKey(Integer id);

    List<Attention> selectAll();

    int updateByPrimaryKey(Attention record);
}