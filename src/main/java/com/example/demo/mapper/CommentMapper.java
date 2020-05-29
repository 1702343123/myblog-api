package com.example.demo.mapper;

import com.example.demo.entity.Comment;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface CommentMapper {
    int deleteByPrimaryKey(Integer cId);

    int insert(Comment record);

    Comment selectByPrimaryKey(Integer cId);

    //查询文章的评论
    List<Comment> selectAllById(Integer id);

    //    查询所有评论
    List<Comment> selectAll();

    int updateByPrimaryKey(Comment record);

    //    删除评论
    @Update("update t_comment set is_del=1 where c_id=#{id}")
    int removeCom(@Param("id") Integer id);

    //    今天的评论数量上限
    @Select("select count(*) from t_comment where to_days(c_time) = to_days(now()) and u_id=#{uId} and a_id=#{aId};")
    int selCount(@Param("uId") Integer uId, @Param("aId") Integer aId);

    //    搜索评论
    List<Comment> searchCom(Map<Object, Object> map);

    //    文章的评论
    @Select("select * from t_comment where a_id=#{id}")
    List<Comment> articleComsById(@Param("id") Integer id);
}