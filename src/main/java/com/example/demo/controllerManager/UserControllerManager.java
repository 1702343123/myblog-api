package com.example.demo.controllerManager;

import com.example.demo.entity.User;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.UserService;
import com.example.demo.util.Manager;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Manager
@RequestMapping(value = "/userManager")
@Api(tags = "1.用户")
public class UserControllerManager {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "查询所有用户")
    @GetMapping(value = "/getAllUsers")
    public ResponseResult getAllUsers() {
        return userService.selUserAll();
    }

    @ApiOperation(value = "删除用户")
    @GetMapping(value = "/delUser")
    public ResponseResult delUser(Integer userId) {
        return userService.removeUser(userId);
    }

    @ApiOperation(value = "修改用户信息")
    @PostMapping(value = "/updateUser")
    public ResponseResult updateUser(Integer userId,String userName, String pass, String userIcon) {
        return userService.changeUser(userId, userName, pass, userIcon);
    }

    @ApiOperation(value = "用户批量封号")
    @GetMapping(value = "forbidUser")
    public ResponseResult forbidUser(int[] ids) {
        return userService.forbidUser(ids);
    }

    @ApiOperation(value = "用户封号/解封")
    @GetMapping(value = "/forbid")
    public ResponseResult forbid(Integer id) {
        return userService.forbid(id);
    }

    @ApiOperation(value = "可批量删除用户")
    @GetMapping(value = "/delUserArray")
    public ResponseResult delUserArray(int[] ids) {
        return userService.delUserList("t_user","user_id",ids);
    }

    @Autowired
    private UserMapper userMapper;
    @ApiOperation(value = "据用户id查询用户")
    @GetMapping(value = "/getUserById")
    public ResponseResult getUserById(Integer userId) {
        return userService.selectUserById(userId);
    }

    @ApiOperation(value = "添加用户")
    @PostMapping(value = "/addUser")
    public ResponseResult addUser(String userName,String pass,String userIcon) {
        return userService.insertUser(userName, pass, userIcon);
    }
    @ApiOperation(value = "关键字搜索用户")
    @GetMapping(value = "/getUsersByKeyword")
    public ResponseResult getUsersByKeyword(String keyword) {
        return userService.searchUsers(keyword);
    }

    @ApiOperation(value = "查询自己创建的用户")
    @GetMapping(value = "/getCreateUsers")
    public ResponseResult getCreateUsers() {
        return userService.myCreateUser();
    }


}
