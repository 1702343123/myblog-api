package com.example.demo.controller;
import com.example.demo.service.UserService;
import com.example.demo.util.Client;
import com.example.demo.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * @author 伊天敬
 * @date 2019/10/15 20:15
 **/
@RestController
@Client
@RequestMapping(value = "/user")
@Api(tags = "6.用户")
public class UserController {
    @Autowired
    private UserService userService;

    @ApiOperation(value = "注册")
    @PostMapping(value = "/register")
    public ResponseResult register(String userName, String userPass, String phoneNumber,
                                   String userIcon, String verify) throws Exception {
        return userService.signUp(userName, userPass, phoneNumber, userIcon, verify);
    }

    @ApiOperation(value = "登录")
    @PostMapping(value = "/login")
    public ResponseResult login(String phoneNumber, String userPass) {
        return userService.login(phoneNumber, userPass);
    }

    @ApiOperation(value = "发送验证码", notes = "通过手机号获取验证码")
    @PostMapping(value = "/sendCode")
    public ResponseResult sendCode(String phoneNumber) {
        return userService.saveCode(phoneNumber);
    }


    @ApiOperation(value = "修改密码")
    @PostMapping(value = "/updatePass")
    public ResponseResult updatePass(String phone, String code, String newPass) throws Exception {
        return userService.updatePass(phone, code, newPass);
    }

    @ApiOperation(value = "随机用户头像")
    @GetMapping(value = "/defaultIcon")
    public ResponseResult registerDefaultIcon() {
        return userService.randomIcon();
    }



}
