package com.example.demo.controllerManager;

import com.example.demo.service.AttentionService;
import com.example.demo.util.Manager;
import com.example.demo.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Manager
@RequestMapping(value = "/attentionManager")
@Api(tags = "5.关注我")
public class AttenControllerManager {

    @Autowired
    private AttentionService attentionService;
    @ApiOperation(value = "修改")
    @PostMapping(value = "/updateAtten")
    public ResponseResult updateAtten(String name, String link, Integer id, String icon) {
        return attentionService.changeAtten(name, link, id, icon);
    }

    @ApiOperation(value = "添加")
    @PostMapping(value = "/insertAtten")
    public ResponseResult insertAtten(String name, String link, String icon) {
        return attentionService.addAtten(name, icon, link);
    }

    @ApiOperation(value = "删除")
    @GetMapping(value = "/delAtten")
    public ResponseResult delAtten(Integer id) {
        return attentionService.removeAtten(id);
    }
}
