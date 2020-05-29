package com.example.demo.mapper;

import com.example.demo.entity.Article;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ArticleMapper {
    int deleteByPrimaryKey(Integer aId);

    int insert(Article record);

    Article selectByPrimaryKey(Integer aId);

    List<Article> selectAll();

    int updateByPrimaryKey(Article record);

    //    阅读量增加
    @Update("update t_article set a_scan=a_scan+1 where a_id=#{id}")
    int addScan(@Param("id") Integer id);

//
}