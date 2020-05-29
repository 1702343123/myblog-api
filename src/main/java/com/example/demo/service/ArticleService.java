package com.example.demo.service;

import com.example.demo.entity.*;
import com.example.demo.mapper.*;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import com.example.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.Date;
import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/15 16:50
 **/
@Service
public class ArticleService {
    @Autowired
    ArticleMapper articleMapper;
    @Autowired
    CommentMapper commentMapper;
    @Autowired
    LikeMapper likeMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private ReplyMapper replyMapper;

    public ResponseResult getArticleList() {
        List<Article> articles = articleMapper.selectAll();
        return ResponseResult.success(articles);
    }
    public ResponseResult getArticleDetail(Integer id) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        Article article = articleMapper.selectByPrimaryKey(id);
        return ResponseResult.success(article);
    }
    public List<Article> articleInfo(List<Article>articles) {
        if (articles != null) {
            for (Article article : articles) {
                article.setUserName(userMapper.selectByPrimaryKey(article.getuId()).getUserName());
                String userIcon = userMapper.selectByPrimaryKey(article.getuId()).getUserIcon();
                article.setUserIcon(userIcon);
//                转换时间
                if (article.getaTime() != null) {
                    article.setTime(StringUtil.getDateString(article.getaTime()));
                }
//                文章的点赞数量
                int size = likeMapper.selectAll(article.getaId()).size();
                article.setLove(size);
//                文章的评论
                List<Comment> comments = commentMapper.selectAllById(article.getaId());
                if (comments != null) {
                    for (Comment comment : comments) {
                        if (comment.getcTime() != null) {
                            comment.setTime(StringUtil.getDateString(comment.getcTime()));
                        }
                        User user = userMapper.selectByPrimaryKey(comment.getuId());
                        comment.setUserIcon(user.getUserIcon());
                        comment.setUserName(user.getUserName());
                        List<Reply> replies = replyMapper.selectAll(comment.getcId());
                        if (replies != null) {
                            for (Reply reply : replies) {
                                User u = userMapper.selectByPrimaryKey(reply.getUserId());
                                reply.setUserName(u.getUserName());
                                String userName =
                                        userMapper.selectByPrimaryKey(reply.getReplyUserId()).getUserName();
                                reply.setReplyUserName(userName);
                                reply.setUserIcon(u.getUserIcon());
                                if (reply.getCreateTime() != null) {
                                    reply.setTime(StringUtil.getDateString(reply.getCreateTime()));
                                }
                            }
                        }
                        comment.setReplyList(replies);
                    }
                    article.setComments(comments);
                }

            }
        }
        return articles;
    }
    private CollectDTO count(Integer articleId, Boolean like) {
        int num = likeMapper.countNum(articleId);
        CollectDTO dto = new CollectDTO();
        dto.setCount(num);
        dto.setLike(like);
        return dto;
    }

    //首次点赞就插入记录，否则切换状态
    public ResponseResult checkLike(Integer userId, String column, Integer articleId) {
        Like likeOrNo;
        likeOrNo = likeMapper.isLikeOrNo(userId, column, articleId);
        String success = "点赞成功";
        String cancel = "已取消点赞";
        if (likeOrNo == null) {
            int i = likeMapper.addLike(column, userId, articleId);
            if (i == 1) {
                return ResponseResult.suc(count(articleId, true), success);
            }
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
        int i = likeMapper.updateStatus(likeOrNo.getIsLike(), likeOrNo.getlId());
        if (i == 1) {
            likeOrNo = likeMapper.isLikeOrNo(userId, column, articleId);
            if (likeOrNo.getIsLike() == 1) {
                return ResponseResult.suc(count(articleId, false), cancel);
            }
            return ResponseResult.suc(count(articleId, true), success);
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //查询一篇文章
    public ResponseResult getArticleById(Integer id) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        Article article = articleMapper.selectByPrimaryKey(id);
        //                文章的点赞数量
        int size = likeMapper.selectAll(article.getaId()).size();
        User user = userMapper.selectByPrimaryKey(article.getuId());
        article.setUserName(user.getUserName());
        article.setLove(size);
        return ResponseResult.success(article);
    }

    //    添加文章
    public ResponseResult insertArticle(String title, String content, String img, String hoverImg,
                                        String img2, String hoverImg2,Integer like,Integer scan,
                                        String time,Integer userId) {
        if (userId == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        if (title == null) {
            return ResponseResult.error(StatusConst.ERROR, "标题不能为空");
        }
        if (content == null) {
            return ResponseResult.error(StatusConst.ERROR, "内容不能为空");
        }

        Article article = new Article();
        article.setaImg(img);
        article.setHoverImg(hoverImg);
        article.setaImg2(img2);
        article.setHoverImg2(hoverImg2);
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (time!=null&&!time.equals("")) {
            date = Timestamp.valueOf(time);
        }
        article.setaTime(date);
        int love = 99;
        int eye = 3456;
        if (like != null) {
            love = like;
        }
        if (scan != null) {
            eye = scan;
        }
        article.setLove(love);
        article.setaScan(eye);
        article.setIsDel(0);
        article.setIsTop(0);
        article.setaTitle(title);
        article.setaContent(content);
        article.setuId(userId);

        int insert = articleMapper.insert(article);
        if (insert == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "发布成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    /*修改文章*/
    public ResponseResult updateArticle(Integer id, String title, String content, String time,
                                        Integer scan, String img,
                                        String img2, String hoverImg, String hoverImg2) {
        if (id == null || title == null || content == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
        Article article = new Article();
        article.setaId(id);
        article.setaTitle(title);
        article.setaContent(content);
        if (time != null&&!time.equals("")) {
            article.setaTime(Timestamp.valueOf(time));
        }else {
            Timestamp timestamp = new Timestamp(System.currentTimeMillis());
            article.setaTime(timestamp);
        }
        int eye = 99;
        if (scan != null) {
            eye = scan;
        }
        article.setaScan(eye);
        article.setaImg(img);
        article.setaImg2(img2);
        article.setHoverImg2(hoverImg2);
        article.setHoverImg(hoverImg);
        int i = articleMapper.updateByPrimaryKey(article);
        if (i == 1) {
            return ResponseResult.success(article);
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
