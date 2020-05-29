package com.example.demo.controller;

import com.example.demo.entity.Attention;
import com.example.demo.mapper.AttentionMapper;
import com.example.demo.util.Client;
import com.example.demo.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/15 20:15
 **/
@RestController
@Client
@RequestMapping(value = "/attention")
@Api(tags = "5.关注我")
public class AttentionController {
    @Autowired
    private AttentionMapper attentionMapper;

    @ApiOperation(value = "获取可关注我所有方式")
    @GetMapping(value = "/getMyContact")
    public ResponseResult getMyContact() {
        List<Attention> attentions = attentionMapper.selectAll();
        return ResponseResult.success(attentions);
    }

}
