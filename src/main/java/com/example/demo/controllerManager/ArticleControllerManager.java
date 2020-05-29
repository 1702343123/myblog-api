package com.example.demo.controllerManager;

import com.example.demo.entity.Article;
import com.example.demo.entity.User;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.service.ArticleService;
import com.example.demo.service.MessageService;
import com.example.demo.util.Manager;
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

/**
 * @author 伊天敬
 * @date 2019/10/31 13:42
 **/
@RestController
@Manager
@RequestMapping(value = "/articleManager")
@Api(tags = "4.文章")
public class ArticleControllerManager {

    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "删除文章")
    @GetMapping(value = "/delArticle")
    public ResponseResult delArticle(int[] ids) {
        return messageService.delMessageList("t_article", "a_id", ids, 1);
    }

    @ApiOperation(value = "修改文章")
    @PostMapping(value = "/updateArticle")
    public ResponseResult updateArticle(Integer id, String title, String content, String time,
                                       Integer scan, String img,
                                        String img2, String hoverImg, String hoverImg2) {
        return articleService.updateArticle(id, title, content, time, scan, img, img2, hoverImg, hoverImg2);
    }

    @Autowired
    private ArticleService articleService;
    @ApiOperation(value = "查询一篇文章")
    @GetMapping(value = "/getArticleById")
    public ResponseResult getArticleById(Integer id) {
        return articleService.getArticleById(id);
    }

    @ApiOperation(value = "添加文章")
    @PostMapping(value = "/addArticle")
    public ResponseResult addArticle(String title, String content, String img, String hoverImg,
                                     String img2, String hoverImg2,Integer like,Integer scan,
                                     String time,Integer userId) {
        return articleService.insertArticle(title, content, img, hoverImg, img2, hoverImg2, like, scan, time, userId);
    }
}
