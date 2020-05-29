package com.example.demo.service;

import com.example.demo.entity.Article;
import com.example.demo.entity.Comment;
import com.example.demo.entity.Reply;
import com.example.demo.entity.User;
import com.example.demo.mapper.*;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import com.example.demo.util.StringUtil;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Time;
import java.sql.Timestamp;
import java.util.Date;
import java.util.HashMap;
import java.util.List;

@Service
public class CommentService {
    @Autowired
    private CommentMapper commentMapper;
    @Autowired
    private ArticleMapper articleMapper;
    @Autowired
    private UserMapper userMapper;
    @Autowired
    private MessageMapper messageMapper;
    public ResponseResult getAllComment() {
        List<Comment> comments = commentMapper.selectAll();
        if (comments != null) {
            for (Comment comment : comments) {
                User user = userMapper.selectByPrimaryKey(comment.getuId());
                comment.setUserName(user.getUserName());
                comment.setUserIcon(user.getUserIcon());
                Article article = articleMapper.selectByPrimaryKey(comment.getaId());
                comment.setTitle(article.getaTitle());
                int i = messageMapper.selNum("t_reply", "c_id", comment.getcId());
                comment.setNum(i);
            }
        }
        return ResponseResult.success(comments);
    }

    public ResponseResult updateComment(String content, Integer id) {
        if (content == null || id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
        Comment comment = new Comment();
        comment.setcId(id);
        comment.setContent(content);
        int i = commentMapper.updateByPrimaryKey(comment);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
    /*添加文章评论*/
    public ResponseResult insertCom(Integer aId, Integer uId, String content,String time) {
        if (uId == null) {
            return ResponseResult.error(StatusConst.ERROR, "请先登录");
        }
        if (aId == null) {
            return ResponseResult.error(StatusConst.ERROR, "请到要评论的文章下方评论");
        }
        if (content == null) {
            return ResponseResult.error(StatusConst.ERROR, "请填写评论内容");
        }
        if (userMapper.selectByPrimaryKey(uId).getIsForbid() == 1) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FORBID);
        }
        int count = commentMapper.selCount(uId,aId);
        if (count >= 5) {
            return ResponseResult.error(StatusConst.ERROR, "今天对该篇文章评论次数已达上限");
        }
        Comment comment = new Comment();
        comment.setaId(aId);
        comment.setContent(content);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());

        if (time != null && !time.trim().equals("")) {
            timestamp=Timestamp.valueOf(time);
        }
        comment.setcTime(timestamp);
        comment.setuId(uId);
        comment.setIsDel(0);
        User user = userMapper.selectByPrimaryKey(uId);
        comment.setUserName(user.getUserName());
        comment.setUserIcon(user.getUserIcon());
        int insert = commentMapper.insert(comment);
        Date date = comment.getcTime();
        comment.setTime(StringUtil.getDateString(date));
        if (insert == 1) {
            return ResponseResult.suc(comment, "评论成功");
        }
        return ResponseResult.error(StatusConst.ERROR, "评论失败");
    }

    //    搜索评论
    public ResponseResult searchComs(String keyword) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        List<Comment> comments = commentMapper.searchCom(map);
        if (comments != null) {
            for (Comment comment : comments) {
                int i = messageMapper.selNum("t_reply", "c_id", comment.getcId());
                comment.setNum(i);
            }
        }
        return ResponseResult.success(comments);
    }

    @Autowired
    private ReplyMapper replyMapper;
    //    评论的回复
    public ResponseResult getComReplys(Integer id) {
        List<Reply> list = replyMapper.selComReplyById(id);
        if (list != null) {
            for (Reply reply : list) {
                User user = userMapper.selectByPrimaryKey(reply.getReplyUserId());
                reply.setReplyUserName(user.getUserName());
                reply.setReplyUserIcon(user.getUserIcon());
                User user2 = userMapper.selectByPrimaryKey(reply.getUserId());
                reply.setUserName(user2.getUserName());
                reply.setUserIcon(user2.getUserIcon());
            }
        }
        return ResponseResult.success(list);
    }

    public ResponseResult getArticleComs(Integer id) {
        List<Comment> comments = commentMapper.articleComsById(id);
        if (comments != null) {
            for (Comment comment : comments) {
                int i = messageMapper.selNum("t_reply", "c_id", comment.getcId());
                comment.setNum(i);
                Article article = articleMapper.selectByPrimaryKey(id);
                comment.setTitle(article.getaTitle());
                User user = userMapper.selectByPrimaryKey(comment.getuId());
                comment.setUserName(user.getUserName());
                comment.setUserIcon(user.getUserIcon());
            }
        }
        return ResponseResult.success(comments);
    }

    /*一条评论*/
    public ResponseResult getCommentById(Integer id) {
        Comment comment = commentMapper.selectByPrimaryKey(id);
        return ResponseResult.success(comment);
    }

    /*给评论添加回复*/
    public ResponseResult insertReply(Integer cId, Integer uId, Integer replyUserId, String content) {
        User user2 = userMapper.selectByPrimaryKey(uId);
        Integer isForbid = user2.getIsForbid();
        if (isForbid == 1) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FORBID);
        }
        Reply reply = new Reply();
        reply.setcId(cId);
        reply.setUserId(uId);
        reply.setContent(content);
        reply.setCreateTime(new Timestamp(System.currentTimeMillis()));
        reply.setIsDel(0);
        reply.setReplyUserId(replyUserId);
        int insert = replyMapper.insert(reply);
        if (insert == 1) {
            User user = userMapper.selectByPrimaryKey(reply.getUserId());
            reply.setUserName(user.getUserName());
            reply.setUserIcon(userMapper.selectByPrimaryKey(reply.getUserId()).getUserIcon());
            User user1 = userMapper.selectByPrimaryKey(reply.getReplyUserId());
            reply.setReplyUserName(user1.getUserName());
            reply.setTime(StringUtil.getDateString(reply.getCreateTime()));
            return ResponseResult.suc(reply, "回复成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
