package com.example.demo.service;

import com.example.demo.entity.Admin;
import com.example.demo.mapper.AdminMapper;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AdminService {
    @Autowired
    private AdminMapper adminMapper;

    /**
     * 管理员登录
     */
    public ResponseResult adminLogin(String name,String pass) {
        if (name == null || pass == null) {
            return ResponseResult.error(StatusConst.ERROR, "用户名或密码不能为空");
        }
        Admin admin = adminMapper.selByNamePass(name, pass);
        if (admin == null) {
            return ResponseResult.error(StatusConst.ERROR, "用户名或密码错误");
        }
        return ResponseResult.success(admin);
    }
}
