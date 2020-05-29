package com.example.demo.controller;

import com.example.demo.entity.Message;
import com.example.demo.entity.User;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.MsgReplyMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.MessageService;
import com.example.demo.util.Client;
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

import java.sql.Timestamp;

@RestController
@Client
@RequestMapping(value = "/message")
@Api(tags = "7.留言")
public class MessageController {
    @Autowired
    private MsgReplyMapper msgReplyMapper;

    @ApiOperation(value = "所有留言")
    @GetMapping(value = "/getAllMessage")
    public ResponseResult getAllMessage() {
        return messageService.allMessage();
    }

     @ApiOperation(value = "添加留言")
    @PostMapping(value = "insertMessage")
    public ResponseResult insertMessage(String content,Integer userId) {
        return messageService.insertMessage(content, userId);
    }

    @Autowired
    private MessageService messageService;
    @ApiOperation(value = "删除留言")
    @GetMapping(value = "/removeMsg")
    public ResponseResult removeMsg(Integer id) {
        return messageService.delMsg(id);
    }

    @ApiOperation(value = "添加留言回复")
    @PostMapping(value = "/addMsgReply")
    public ResponseResult addMsgReply(Integer uId, Integer replyUserId, Integer pid, String content) {
        return messageService.addMsgReply(uId, replyUserId, pid, content);
    }

    @ApiOperation(value = "删除留言回复")
    @GetMapping(value = "/delMsgReply")
    public ResponseResult delMsgReply(Integer id) {
        int i = msgReplyMapper.removeMsgReply(id);
        if (i == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.PASSWORD_ERROR);
    }



}
