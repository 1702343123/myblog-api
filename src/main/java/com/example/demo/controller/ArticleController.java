package com.example.demo.controller;

import com.example.demo.entity.Article;
import com.example.demo.entity.Like;
import com.example.demo.mapper.ArticleMapper;
import com.example.demo.mapper.LikeMapper;
import com.example.demo.service.ArticleService;
import com.example.demo.util.Client;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/15 14:47
 **/
@RestController
@Client
@RequestMapping(value = "/article")
@Api(tags = "1.文章")
public class ArticleController {
    @Autowired
    private ArticleService articleService;
    @Autowired
    private ArticleMapper articleMapper;

    @GetMapping(value = "/articleList")
    @ApiOperation(value = "文章列表")
    public ResponseResult getArticleList() {
        List<Article> articles = articleMapper.selectAll();
        List<Article> articles1 = articleService.articleInfo(articles);
        return ResponseResult.success(articles1);
    }
    @ApiOperation(value = "文章详情")
    @GetMapping(value = "/articleDetail")
    public ResponseResult articleDetail(Integer id) {
        return articleService.getArticleDetail(id);
    }
    @ApiOperation(value = "最新文章标题")
    @GetMapping(value = "/getArticleTitle")
    public ResponseResult getArticleTitle() {
        List<Article> articles = articleMapper.selectAll();
        List<Article> articles1 = articleService.articleInfo(articles);
        if (articles1 != null&&articles1.size()>1) {
            List<Article> articles2 = articles1.subList(0, 2);
            return ResponseResult.success(articles2);
        }
        return ResponseResult.success(articles1);
    }

    @ApiOperation(value = "点赞/取消点赞-文章")
    @GetMapping(value = "checkArticleLike")
    public ResponseResult checkReplyLike(Integer userId, Integer articleId) {
        String column = "a_id";

        return articleService.checkLike(userId, column, articleId);
    }



    @Autowired
    private LikeMapper likeMapper;

    @ApiOperation(value = "判断是否已经点赞")
    @GetMapping(value = "/isLike")
    public ResponseResult isLike(Integer aId, Integer uId) {
        Like like = likeMapper.isLikeOrNo(uId, "a_id", aId);
        if (like == null || like.getIsLike() == 1) {
            return ResponseResult.suc(false, "未点赞");
        }
        return ResponseResult.suc(true, "已点赞");
    }

    @ApiOperation(value = "文章访问量")
    @GetMapping(value = "/addArticleScan")
    public ResponseResult addArticleScan(Integer aId) {
        int i = articleMapper.addScan(aId);
        if (i == 1) {
            Article article = articleMapper.selectByPrimaryKey(aId);
            Integer integer = article.getaScan();
            return ResponseResult.success(integer);
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
