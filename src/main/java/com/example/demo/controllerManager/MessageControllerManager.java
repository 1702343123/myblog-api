package com.example.demo.controllerManager;

import com.example.demo.entity.MsgReply;
import com.example.demo.mapper.MsgReplyMapper;
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

/**
 * @author 伊天敬
 * @date 2019/10/30 20:53
 **/
@RestController
@Manager
@RequestMapping(value = "/messageManager")
@Api(tags = "2.留言")
public class MessageControllerManager {
    @Autowired
    private MessageService messageService;

    @ApiOperation(value = "可批量删除留言")
    @GetMapping(value = "/delMessageList")
    public ResponseResult delMessageList(int[] ids) {
        return messageService.delMessageList("t_message", "m_id",ids,1);
    }

    @Autowired
    private MsgReplyMapper msgReplyMapper;
    @ApiOperation(value = "可批量删除留言回复")
    @GetMapping(value = "/delMsgReplyList")
    public ResponseResult delMsgReplyList(int[] ids) {
        MsgReply msgReply = msgReplyMapper.selectByPrimaryKey(ids[0]);
        return messageService.delMessageList("t_msg_reply", "id",ids,msgReply.getPid());
    }

    @ApiOperation(value = "修改留言")
    @PostMapping(value = "/updateMsg")
    public ResponseResult updateMsg(Integer id, String content) {
        return messageService.updateMsg(id, content);
    }

    @ApiOperation(value = "查看单条留言")
    @GetMapping(value = "/getMsgById")
    public ResponseResult getMsgById(Integer id) {
        return messageService.getMsgById(id);
    }

    @ApiOperation(value = "搜索留言")
    @GetMapping(value = "/searchMsg")
    public ResponseResult searchMsg(String keyword) {
        return messageService.searchMsgByKeyword(keyword);
    }

    @ApiOperation(value = "据留言id查询留言回复")
    @GetMapping(value = "/getMsgReplyById")
    public ResponseResult getMsgReplyById(Integer mId) {
        return messageService.getMsgReplyById(mId);
    }

    @ApiOperation(value = "添加留言")
    @PostMapping(value = "/addMessage")
    public ResponseResult addMessage(Integer userId, String content, String time) {
        return messageService.insertMessage(userId, content, time);
    }

}
