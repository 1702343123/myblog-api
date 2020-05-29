package com.example.demo.mapper;

import com.example.demo.entity.Carousel;
import org.apache.ibatis.annotations.Mapper;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface CarouselMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Carousel record);

    Carousel selectByPrimaryKey(Integer cId);

    List<Carousel> selectAll();

    int updateByPrimaryKey(Carousel record);
}