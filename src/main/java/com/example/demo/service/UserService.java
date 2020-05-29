package com.example.demo.service;

import com.example.demo.entity.Icon;
import com.example.demo.entity.User;
import com.example.demo.mapper.IconMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.data.redis.core.RedisTemplate;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import java.util.concurrent.TimeUnit;

/**
 * @author 伊天敬
 * @date 2019/10/15 20:27
 **/
@Service
public class UserService {
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private IconMapper iconMapper;

    public ResponseResult signUp(String userName, String userPass, String phoneNumber,
                                 String userIcon, String verify) throws Exception {
        if (userName == null || userPass == null || phoneNumber == null) {
            return ResponseResult.error(StatusConst.ERROR, "请输入完整的注册信息");
        }
        if (verify == null) {
            return ResponseResult.error(StatusConst.ERROR, "请输入验证码");
        }
        int x = plogin(phoneNumber,verify);
        if (x != StatusConst.SUCCESS) {
            return ResponseResult.error(StatusConst.ERROR, "验证码错误");
        }

        List<User> users = userMapper.selectByPhone(phoneNumber);
        if (users.size()!=0) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.MOBILE_EXIST);
        }
        User user1 = new User();
        if (userIcon == null) {
            List<Icon> icons = iconMapper.selectAll();
            Random random = new Random();
            int index = random.nextInt(5);
            Icon icon = icons.get(index);
            user1.setUserIcon(icon.getiUrl());
        } else {
            user1.setUserIcon(userIcon);
        }
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        user1.setRegisterTime(timestamp);
        user1.setPhoneNumber(phoneNumber);
        user1.setUserName(userName);
        user1.setUserPass(userPass);
        user1.setIsDel(0);
        user1.setIsForbid(0);
        int i = userMapper.insert(user1);
        if (i != 1) {
            return ResponseResult.error(StatusConst.ERROR, "注册失败");
        }
        return new ResponseResult(StatusConst.SUCCESS, "恭喜你，注册成功,请登录");
    }

    public ResponseResult login(String phoneNumber, String userPass) {
        if (phoneNumber == null || userPass == null) {
            return ResponseResult.error(StatusConst.ERROR, "请输入手机号和密码");
        }
        List<User> users = userMapper.selectByPhone(phoneNumber);
        System.out.println(users+"------");
        if (users.size()==0) {
            return ResponseResult.error(StatusConst.ERROR, "该手机号尚未注册");
        }
        if (!users.get(0).getUserPass().equals(userPass)) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.PASSWORD_ERROR);
        }
        return new ResponseResult(StatusConst.SUCCESS, "登录成功", users.get(0));
    }

    @Autowired
    @Qualifier("redisTemplate")//实例化
    private RedisTemplate<Object, Object> rts;

    //发送并保存验证码
    public ResponseResult saveCode(String phoneNumber) {
        if (phoneNumber == null||phoneNumber=="") {
            return ResponseResult.error(StatusConst.ERROR,"手机号不能为空");
        }
        String newCode = NewCodeUtil.getNewCode();
//            发送
        int send = SMSUtil.send(phoneNumber, newCode);
        if (send == StatusConst.SUCCESS) {
//            保存
            rts.opsForValue().set(phoneNumber, newCode, 15, TimeUnit.DAYS);
            return new ResponseResult(StatusConst.SUCCESS, "验证码发送成功", newCode);
        }
        return ResponseResult.error(StatusConst.ERROR, "验证码发送失败");
    }

    //验证验证码
    public int plogin(String phoneNumber, String pcode) throws Exception {
        String code = (String) rts.opsForValue().get(phoneNumber);
        if (code == null) {
            return StatusConst.VERIFYCODE_ERROR;

        } else {
            if (code.equals(pcode)) {
//                验证码正确
                return StatusConst.SUCCESS;
            } else {
//                验证码错误
                return StatusConst.VERIFYCODE_ERROR;
            }
        }
    }

    //    找回密码
    public ResponseResult updatePass(String phone, String code, String newPass) throws Exception {
        int plogin = plogin(phone, code);
        if (plogin != StatusConst.SUCCESS) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.VERIFYCODE_ERROR);
        }
        int i = userMapper.updatePassword(phone, newPass);
        if (i != 1) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
        return ResponseResult.suc(newPass, "密码修改成功");
    }

    public ResponseResult selUserAll() {
        List<User> users = userMapper.selectAll();
        return ResponseResult.success(users);
    }

    public ResponseResult changeUser(Integer userId, String userName, String pass, String userIcon) {
        User user = userMapper.selectByPrimaryKey(userId);
        if (userIcon != null) {
            user.setUserIcon(userIcon);
        }
        if (userName != null) {
            user.setUserName(userName);
        }
        if (pass != null) {
            user.setUserPass(pass);
        }
        int i = userMapper.updateByPrimaryKey(user);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    public ResponseResult removeUser(Integer userId) {
        User user = userMapper.selectByPrimaryKey(userId);
        Integer isDel = user.getIsDel();
        int i = userMapper.removeUser(userId,isDel);
        if (i == 1) {
            if (isDel == 0) {
                return new ResponseResult(StatusConst.SUCCESS,"删除成功");
            }
            return new ResponseResult(StatusConst.SUCCESS, "已恢复删除");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    public ResponseResult forbidUser(int[] ids) {
        int i = userMapper.forbidUser(ids);
        if (i == ids.length) {
            return new ResponseResult(StatusConst.SUCCESS,"已封掉所选账号");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    /*封号/解封*/
    public ResponseResult forbid(Integer id) {
        User user = userMapper.selectByPrimaryKey(id);
        Integer isForbid = user.getIsForbid();
        int forbid = userMapper.forbid(isForbid, id);
        if (forbid == 1) {
            if (isForbid == 1) {
                return new ResponseResult(StatusConst.SUCCESS, "已解封");
            }
            return new ResponseResult(StatusConst.SUCCESS, "已封号");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
    public ResponseResult delUserList(String table,String column, int[] ids) {
        int i = userMapper.delArray(table,column,ids);
        if (i == ids.length) {
            return new ResponseResult(0,"删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    public ResponseResult searchUsers(String keyword) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        List<User> users = userMapper.searchUsers(map);
        return ResponseResult.success(users);
    }

    public ResponseResult insertUser(String userName,String pass,String userIcon) {
        User user = new User();
        user.setUserIcon(userIcon);
        user.setUserName(userName);
        user.setUserPass(pass);
        user.setIsDel(0);
        user.setIsForbid(0);
        user.setPhoneNumber("电脑人");
        user.setRegisterTime(new Date(System.currentTimeMillis()));
        int insert = userMapper.insert(user);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    查询自己创建的用户
    public ResponseResult myCreateUser() {
        String phone = "电脑人";
        List<User> users = userMapper.selectByPhone(phone);
        return ResponseResult.success(users);
    }

    /*据用户id查询用户*/
    public ResponseResult selectUserById(Integer userId) {
        if (userId == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        User user = userMapper.selectByPrimaryKey(userId);
        if (user == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
        return ResponseResult.success(user);
    }

    /**
     * 随机用户头像
     */
    public ResponseResult randomIcon() {
        Random random = new Random();
        int i = random.nextInt(5);
        Icon icon = iconMapper.selectByPrimaryKey(i+1);
        if (icon != null) {
            return ResponseResult.success(icon);
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
