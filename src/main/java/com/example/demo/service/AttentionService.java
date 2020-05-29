package com.example.demo.service;

import com.example.demo.entity.Attention;
import com.example.demo.mapper.AttentionMapper;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class AttentionService {
    @Autowired
    private AttentionMapper attentionMapper;
//修改
    public ResponseResult changeAtten(String name, String link, Integer id, String icon) {
        Attention attention = new Attention();
        attention.setIcon(icon);
        attention.setId(id);
        attention.setName(name);
        attention.setLink(link);
        int i = attentionMapper.updateByPrimaryKey(attention);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    添加
    public ResponseResult addAtten(String name, String icon, String link) {
        if (name == null || icon == null || link == null) {
            return ResponseResult.error(StatusConst.ERROR, "空指针");
        }
        Attention attention = new Attention();
        attention.setIsDel(0);
        attention.setLink(link);
        attention.setName(name);
        attention.setIcon(icon);
        int insert = attentionMapper.insert(attention);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    删除
    public ResponseResult removeAtten(Integer id) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        int i = attentionMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

}
