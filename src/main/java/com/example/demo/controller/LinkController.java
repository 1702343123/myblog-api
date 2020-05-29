package com.example.demo.controller;

import com.example.demo.entity.Link;
import com.example.demo.mapper.LinkMapper;
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

@RestController
@Client
@RequestMapping(value = "/link")
@Api(tags = "4.友情链接")
public class LinkController {
    @Autowired
    private LinkMapper linkMapper;

    @ApiOperation(value = "获取所有友情链接")
    @GetMapping(value = "/getAllLink")
    public ResponseResult getAllLink() {
        List<Link> links = linkMapper.selectAll();
        return ResponseResult.success(links);
    }
    @Autowired
    private WebService webService;
    @ApiOperation(value = "添加友情链接")
    @PostMapping(value = "/addLink")
    public ResponseResult addLink(String name, String url) {
        return webService.addLink(name, url);
    }

    @ApiOperation(value = "修改友情链接")
    @PostMapping(value = "/updateLink")
    public ResponseResult updateLink(Integer id, String name, String url) {
        return webService.changeLink(id, name, url);
    }

    @ApiOperation(value = "删除友情链接")
    @GetMapping(value = "/delLink")
    public ResponseResult delLink(Integer id) {
        return webService.delLink(id);
    }
}
