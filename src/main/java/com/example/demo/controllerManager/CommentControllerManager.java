package com.example.demo.controllerManager;

import com.example.demo.entity.Article;
import com.example.demo.entity.Reply;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.ReplyMapper;
import com.example.demo.service.CommentService;
import com.example.demo.service.MessageService;
import com.example.demo.util.Manager;
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
@Manager
@RequestMapping(value = "/commentManager")
@Api(tags = "3.评论")
public class CommentControllerManager {
    @Autowired
    private CommentService commentService;

    @ApiOperation(value = "所有评论")
    @GetMapping(value = "/getAllComment")
    public ResponseResult getAllComment() {
        return commentService.getAllComment();
    }

    @ApiOperation(value = "一条评论")
    @GetMapping(value = "getComById")
    public ResponseResult getComById(Integer id) {
        return  commentService.getCommentById(id);
    }

    @ApiOperation(value = "修改评论")
    @PostMapping(value = "/updateComment")
    public ResponseResult updateComment(String content, Integer id) {
        return commentService.updateComment(content, id);
    }

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "可批量删除评论")
    @GetMapping(value = "/delComment")
    public ResponseResult delComment(int[] ids) {
        return messageService.delMessageList("t_comment", "c_id", ids,1);
    }

    @ApiOperation(value = "搜索评论")
    @GetMapping(value = "searchComs")
    public ResponseResult searchComs(String keyword) {
        return commentService.searchComs(keyword);
    }

    @ApiOperation(value = "评论的回复")
    @GetMapping(value = "/getComReplyById")
    public ResponseResult getComReplyById(Integer id) {
        return commentService.getComReplys(id);
    }

    @Autowired
    private ReplyMapper replyMapper;
    @ApiOperation(value = "可批量删除评论回复")
    @GetMapping(value = "/delComReplyList")
    public ResponseResult delComReplyList(int[] ids) {
        Reply reply = replyMapper.selectByPrimaryKey(ids[0]);
        return messageService.delMessageList("t_reply", "id", ids,reply.getcId());
    }

    @Autowired
    private ArticleMapper articleMapper;
    @ApiOperation(value = "所有文章")
    @GetMapping(value = "/getAllArticle")
    public ResponseResult getAllArticle() {
        List<Article> articles = articleMapper.selectAll();
        return ResponseResult.success(articles);
    }

    @ApiOperation(value = "文章的评论")
    @GetMapping(value = "/getArticleComs")
    public ResponseResult getArticleComs(Integer id) {
        return commentService.getArticleComs(id);
    }


}
