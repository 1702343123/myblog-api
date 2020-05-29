package com.example.demo.mapper;

import com.example.demo.entity.Like;
import org.apache.ibatis.annotations.Insert;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface LikeMapper {
    int deleteByPrimaryKey(Integer lId);

    int insert(Like record);

    Like selectByPrimaryKey(Integer lId);

    //查询文章的点赞数量
    List<Like> selectAll(Integer id);

    int updateByPrimaryKey(Like record);

    //    用户是否曾经点赞
    @Select("SELECT * FROM t_like WHERE u_id=#{userId} and ${column}=#{id}")
    Like isLikeOrNo(@Param("userId") Integer userId, String column, @Param("id") Integer id);
    //首次点击喜欢
    @Insert("INSERT INTO t_like(u_id,${column}) VALUES(#{userId},#{id})")
    int addLike(String column, @Param("userId") Integer userId, @Param("id") Integer id);

    //切换喜欢/未喜欢状态
    int updateStatus(@Param("status") Integer status, @Param("id") Integer id);

    //    统计点赞数量
    @Select("SELECT COUNT(*) FROM t_like WHERE is_like=0 and a_id=#{aId}")
    int countNum(@Param("aId") Integer aId);

}