package com.example.demo.controller;

import com.example.demo.mapper.CommentMapper;
import com.example.demo.mapper.ReplyMapper;
import com.example.demo.service.CommentService;
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

/**
 * @author 伊天敬
 * @date 2019/10/19 9:39
 **/
@RestController
@Client
@RequestMapping(value = "/comment")
@Api(tags = "8.评论")
public class CommentController {

    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "删除评论")
    @GetMapping(value = "/removeCom")
    public ResponseResult removeCom(Integer id) {
        int i = commentMapper.removeCom(id);
        if (i == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
    @ApiOperation(value = "添加评论")
    @PostMapping(value = "/addCom")
    public ResponseResult addCom(Integer aId, Integer uId, String content,String time) {
        return commentService.insertCom(aId, uId, content,time);
    }
    @Autowired
    private ReplyMapper replyMapper;

    @ApiOperation(value = "给评论添加回复")
    @PostMapping(value = "/addReply")
    public ResponseResult addReply(Integer cId, Integer uId, Integer replyUserId, String content) {
        return commentService.insertReply(cId, uId, replyUserId, content);
    }


    @ApiOperation(value = "删除评论回复")
    @GetMapping(value = "/delReply")
    public ResponseResult delReply(Integer id) {
        int i = replyMapper.removeReply(id);
        if (i == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

}
