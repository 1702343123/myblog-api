package com.example.demo.mapper;

import com.example.demo.entity.Reply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface ReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(Reply record);

    Reply selectByPrimaryKey(Integer id);

    List<Reply> selectAll(@Param("cId") Integer cId);

    int updateByPrimaryKey(Reply record);

    //    删除评论的回复
    @Update("update  t_reply set is_del=1 where id=#{id}")
    int removeReply(@Param("id") Integer id);


    @Select("select * from t_reply where is_del=0 and c_id=#{id}")
    List<Reply> selComReplyById(@Param("id") Integer id);
}