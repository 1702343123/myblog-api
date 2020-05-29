package com.example.demo.service;

import com.example.demo.entity.Message;
import com.example.demo.entity.MsgReply;
import com.example.demo.entity.User;
import com.example.demo.mapper.MessageMapper;
import com.example.demo.mapper.MsgReplyMapper;
import com.example.demo.mapper.ReplyMapper;
import com.example.demo.mapper.UserMapper;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import com.example.demo.util.StringUtil;
import io.swagger.models.auth.In;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import sun.misc.resources.Messages;

import java.sql.Timestamp;
import java.util.HashMap;
import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/16 23:37
 **/
@Service
public class MessageService {
    @Autowired
    private MessageMapper messageMapper;

    public ResponseResult delMsg(Integer id) {
        int i = messageMapper.removeMsg(id);
        if (i == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    @Autowired
    private MsgReplyMapper msgReplyMapper;
    @Autowired
    private UserMapper userMapper;

/*添加留言回复*/
    public ResponseResult addMsgReply(Integer uId, Integer replyUserId, Integer pid,
                                      String content) {
        if (uId == null || replyUserId == null || pid == null || content == null) {
            return ResponseResult.error(StatusConst.ERROR, "不能为空");
        }
        if (userMapper.selectByPrimaryKey(uId).getIsForbid() == 1) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FORBID);
        }

        MsgReply msgReply = new MsgReply();
        msgReply.setCreateTime(new Timestamp(System.currentTimeMillis()));
        msgReply.setIsDel(0);
        msgReply.setContent(content);
        msgReply.setUserId(uId);
        msgReply.setPid(pid);
        msgReply.setReplyUserId(replyUserId);
        int insert = msgReplyMapper.insert(msgReply);
        if (insert == 1) {
            msgReply.setUserName(userMapper.selectByPrimaryKey(msgReply.getUserId()).getUserName());
            msgReply.setReplyUserName(userMapper.selectByPrimaryKey(msgReply.getReplyUserId()).getUserName());
            msgReply.setUserIcon(userMapper.selectByPrimaryKey(msgReply.getUserId()).getUserIcon());
            msgReply.setTime(StringUtil.getDateString(msgReply.getCreateTime()));
            return ResponseResult.suc(msgReply, "回复成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    public ResponseResult allMessage() {
        List<Message> messages = messageMapper.selectAll();
        for (Message message : messages) {
            Integer uId = message.getuId();
            User user = userMapper.selectByPrimaryKey(uId);
            message.setUserIcon(user.getUserIcon());
            message.setUserName(user.getUserName());
            if (message.getmTime() != null) {
                message.setTime(StringUtil.getDateString(message.getmTime()));
            }
//            留言的所有回复
            List<MsgReply> msgReplies = msgReplyMapper.selectAll(message.getmId());
            for (MsgReply msgReply : msgReplies) {
                msgReply.setUserName(userMapper.selectByPrimaryKey(msgReply.getUserId()).getUserName());
                msgReply.setReplyUserName(userMapper.selectByPrimaryKey(msgReply.getReplyUserId()).getUserName());
                msgReply.setUserIcon(userMapper.selectByPrimaryKey(msgReply.getUserId()).getUserIcon());
                msgReply.setTime(StringUtil.getDateString(msgReply.getCreateTime()));
            }
            message.setReplyList(msgReplies);
            int i = messageMapper.selNum("t_msg_reply", "pid", message.getmId());
            message.setNum(i);
        }
        return ResponseResult.success(messages);
    }

    @Autowired
    private ReplyMapper replyMapper;
    //可批量删除
    public ResponseResult delMessageList(String table,String column, int[] ids,Integer pid) {
        if (ids.length == 0) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        int i = userMapper.delArray(table,column, ids);
        if (i == ids.length) {
            return ResponseResult.suc(pid, "删除成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    修改留言
    public ResponseResult updateMsg(Integer id, String content) {
        if (id == null || content == null || content == "") {
            return ResponseResult.error(StatusConst.ERROR, "空指针");
        }
        Message message = new Message();
        message.setContent(content);
        message.setmId(id);
        int i = messageMapper.updateByPrimaryKey(message);
        if (i == 1) {
            return new ResponseResult(StatusConst.SUCCESS, "修改成功");
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    查看单条留言
    public ResponseResult getMsgById(Integer id) {
        Message message = messageMapper.selectByPrimaryKey(id);
        return ResponseResult.success(message);
    }

    //    搜索留言
    public ResponseResult searchMsgByKeyword(String keyword) {
        HashMap<Object, Object> map = new HashMap<>();
        map.put("keyword", keyword);
        List<Message> messages = messageMapper.searchMsg(map);
        if (messages != null) {
            for (Message message : messages) {
                int i = messageMapper.selNum("t_msg_reply", "pid", message.getmId());
                message.setNum(i);
            }
        }
        return ResponseResult.success(messages);
    }

    //    留言回复
    public ResponseResult getMsgReplyById(Integer id) {
        List<MsgReply> list = msgReplyMapper.selReplyListById(id);
        if (list != null) {
            for (MsgReply msgReply : list) {
                User user = userMapper.selectByPrimaryKey(msgReply.getReplyUserId());
                msgReply.setReplyUserName(user.getUserName());
                msgReply.setReplyUserIcon(user.getUserIcon());
                User user2 = userMapper.selectByPrimaryKey(msgReply.getUserId());
                msgReply.setUserName(user2.getUserName());
                msgReply.setUserIcon(user2.getUserIcon());
            }
        }
        return ResponseResult.success(list);
    }

    //    后台添加留言
    public ResponseResult insertMessage(Integer userId, String content,String time) {
        Message message = new Message();
        message.setuId(userId);
        message.setContent(content);
        message.setIsDel(0);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        if (time != null && !time.trim().equals("")) {
            timestamp = timestamp.valueOf(time);
        }
        message.setmTime(timestamp);
        int insert = messageMapper.insert(message);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    /**
     * 添加留言
     */
    public ResponseResult insertMessage(String content, Integer userId) {
        if (userId == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        User user = userMapper.selectByPrimaryKey(userId);
        Integer isForbid = user.getIsForbid();
        if (isForbid==1) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FORBID);
        }
        if (content == null) {
            return ResponseResult.error(StatusConst.ERROR,"内容不能为空");
        }
        Message message = new Message();
        message.setContent(content);
        message.setuId(userId);
        Timestamp timestamp = new Timestamp(System.currentTimeMillis());
        message.setmTime(timestamp);
        message.setIsDel(0);
        int count = messageMapper.setMessageCount(userId);
        if (count >= 5) {
            return ResponseResult.error(StatusConst.ERROR, "今天留言次数已到上限，请明天再来");
        }
        int insert = messageMapper.insert(message);
        if (insert == 1) {
            return ResponseResult.suc(message,"留言成功");
        } else {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
        }
    }

}
