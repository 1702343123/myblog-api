package com.example.demo.mapper;

import com.example.demo.entity.MsgReply;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;

@Mapper
@Component
public interface MsgReplyMapper {
    int deleteByPrimaryKey(Integer id);

    int insert(MsgReply record);

    MsgReply selectByPrimaryKey(Integer id);

    List<MsgReply> selectAll(@Param("mId") Integer id);

    int updateByPrimaryKey(MsgReply record);


    @Update("update t_msg_reply set is_del=1 where id=#{id}")
    int removeMsgReply(@Param("id") Integer id);

    @Select("select * from t_msg_reply where is_del=0 and pid=#{id}")
    List<MsgReply> selReplyListById(@Param("id") Integer id);
}