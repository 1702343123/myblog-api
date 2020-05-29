package com.example.demo.controller;

import com.example.demo.entity.Icon;
import com.example.demo.mapper.IconMapper;
import com.example.demo.service.WebService;
import com.example.demo.util.Client;
import com.example.demo.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/15 19:57
 **/
@RestController
@Client
@RequestMapping(value = "/web")
@Api(tags = "3.网站信息")
public class WebController {
    @Autowired
    private WebService webService;

    @ApiOperation(value = "网站的公共信息")
    @GetMapping(value = "/getWebInfo")
    public ResponseResult getWebInfo() {
        return webService.webInfo();
    }

    @Autowired
    private IconMapper iconMapper;

    @ApiOperation(value = "默认头像")
    @GetMapping(value = "/getDefaultIcon")
    public ResponseResult getDefaultIcon() {
        List<Icon> icons = iconMapper.selectAll();
        return ResponseResult.success(icons);
    }

    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateWeb")
    public ResponseResult updateWeb(String url,String column) {
        return webService.updateWeb(url,column);
    }

}
