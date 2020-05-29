package com.example.demo.mapper;

import com.example.demo.entity.Message;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Select;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface MessageMapper {
    int deleteByPrimaryKey(Integer mId);

    int insert(Message record);

    Message selectByPrimaryKey(Integer mId);

    List<Message> selectAll();

    int updateByPrimaryKey(Message record);
//删除留言
    int removeMsg(@Param("id") Integer id);

    //    今日的留言数量
    @Select("select count(*) from t_message where to_days(now()) and u_id=#{uId}")
    int setMessageCount(@Param("uId") Integer uId);

//    回复数量
    @Select("SELECT COUNT(*) FROM ${reply} WHERE ${pid}=#{id} and is_del=0")
    int selNum(@Param("reply") String reply, @Param("pid") String pid, @Param("id") Integer id);

    //    搜索留言
    List<Message> searchMsg(Map<Object, Object> map);


}