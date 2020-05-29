package com.example.demo.service;

import com.example.demo.entity.Link;
import com.example.demo.entity.Web;
import com.example.demo.mapper.LinkMapper;
import com.example.demo.mapper.WebMapper;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 伊天敬
 * @date 2019/10/15 19:09
 **/
@Service
public class WebService {
    @Autowired
    WebMapper webMapper;

    public ResponseResult webInfo() {
        int id=1;
        Web web = webMapper.selectByPrimaryKey(id);
        return ResponseResult.success(web);
    }

    public ResponseResult updateWeb(String url,String column) {
        int i = webMapper.updateByPrimaryKey(column,url);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    @Autowired
    private LinkMapper linkMapper;
    //添加友情链接
    public ResponseResult addLink(String name, String url) {
        Link link = new Link();
        link.setIsDel(0);
        link.setlName(name);
        link.setlUrl(url);
        int insert = linkMapper.insert(link);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    修改友情链接
    public ResponseResult changeLink(Integer id, String name, String url) {
        Link link = new Link();
        link.setlId(id);
        link.setlName(name);
        link.setlUrl(url);
        int i = linkMapper.updateByPrimaryKey(link);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    删除友情链接
    public ResponseResult delLink(Integer id) {
        int i = linkMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
