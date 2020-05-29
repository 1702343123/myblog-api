package com.example.demo.mapper;

import com.example.demo.entity.User;
import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;
import org.apache.ibatis.annotations.Update;
import org.springframework.stereotype.Component;

import java.util.List;
import java.util.Map;

@Mapper
@Component
public interface UserMapper {
    int deleteByPrimaryKey(Integer userId);

    int insert(User record);

    User selectByPrimaryKey(Integer userId);

    List<User> selectAll();

    int updateByPrimaryKey(User record);

    //    手机号查找用户
    List<User> selectByPhone(String phoneNumber);

    //    修改密码
    @Update("update t_user set user_pass=#{newPass} where phone_number=#{phone}")
    int updatePassword(@Param("phone") String phone, @Param("newPass") String pass);

    //    删除用户
    int removeUser(@Param("userId") Integer userId,@Param("isDel")Integer isDel);

    //    用户封号
    int forbidUser(@Param("ids") int[] ids);

    //    批量删除
    int delArray(@Param("table") String table, @Param("id") String id, @Param("ids") int[] ids);

    //查询用户
    List<User> searchUsers(Map<Object, Object> map);

    //    封号/解封
    int forbid(@Param("is_forbid")Integer is_forbid,@Param("id") Integer id);
}