package com.example.demo.service;

import com.example.demo.entity.Carousel;
import com.example.demo.entity.Icon;
import com.example.demo.mapper.CarouselMapper;
import com.example.demo.mapper.IconMapper;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

/**
 * @author 伊天敬
 * @date 2019/10/15 19:50
 **/
@Service
public class CarouselService {
    @Autowired
    private CarouselMapper carouselMapper;

    public ResponseResult carouselList() {
        List<Carousel> carousels = carouselMapper.selectAll();
        return ResponseResult.success(carousels);
    }

    //    添加轮播图
    public ResponseResult insertCarousel(String pcImg, String pcImg2,String mobileImg) {
        Carousel carousel = new Carousel();
        carousel.setcMobile(mobileImg);
        carousel.setcPc(pcImg);
        carousel.setcNight(pcImg2);
        carousel.setIsDel(0);
        int insert = carouselMapper.insert(carousel);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    修改轮播图
    public ResponseResult changeCarousel(Integer id, String pcImg,String pcImg2, String mobileImg) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        Carousel carousel = new Carousel();
        carousel.setcId(id);
        carousel.setcPc(pcImg);
        carousel.setcNight(pcImg2);
        carousel.setcMobile(mobileImg);
        int i = carouselMapper.updateByPrimaryKey(carousel);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);

    }

    @Autowired
    private IconMapper iconMapper;

    //    添加默认头像
    public ResponseResult insertIcon(String img) {
        Icon icon = new Icon();
        icon.setIsDel(0);
        icon.setiUrl(img);
        int insert = iconMapper.insert(icon);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    修改默认头像
    public ResponseResult updateDefaultIcon(Integer id,String img) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        Icon icon = new Icon();
        icon.setiUrl(img);
        icon.setiId(id);
        int i = iconMapper.updateByPrimaryKey(icon);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    删除轮播图
    public ResponseResult delCarousel(Integer id) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        int i = carouselMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    //    删除默认头像
    public ResponseResult delDefaultIcon(Integer id) {
        int i = iconMapper.deleteByPrimaryKey(id);
        if (i == i) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

}
