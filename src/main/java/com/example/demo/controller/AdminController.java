package com.example.demo.controller;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.service.AdminService;
import com.example.demo.util.Manager;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 伊天敬
 * @date 2019/10/15 13:15
 **/
@RestController
@Manager
@RequestMapping(value = "/admin")
@Api(tags = "1.站长")
public class AdminController {
    @Autowired
    private AdminService adminService;
    @ApiOperation(value = "登录", notes = "用账号密码登录")
    @PostMapping(value = "/login")
    public ResponseResult login(String name, String pass) {
        return adminService.adminLogin(name, pass);
    }
}
