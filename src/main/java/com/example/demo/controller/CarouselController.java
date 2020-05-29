package com.example.demo.controller;

import com.example.demo.service.CarouselService;
import com.example.demo.util.Client;
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
 * @date 2019/10/15 20:01
 **/
@RestController
@Client
@RequestMapping(value = "/carousel")
@Api(tags = "2.轮播图")
public class CarouselController {
    @Autowired
    private CarouselService carouselService;

    @ApiOperation(value = "pc和手机端的轮播图")
    @GetMapping(value = "/getAllCarousel")
    public ResponseResult getAllCarousel() {
        return carouselService.carouselList();
    }

    @ApiOperation(value = "添加轮播图")
    @PostMapping(value = "/addCarousel")
    public ResponseResult addCarousel(String pcImg,String pcImg2, String mobileImg) {
        return carouselService.insertCarousel(pcImg, pcImg2,mobileImg);
    }

    @ApiOperation(value = "修改轮播图")
    @PostMapping(value = "/updateCarousel")
    public ResponseResult updateCarousel(Integer id, String pcImg,String pcImg2, String mobileImg) {
        return carouselService.changeCarousel(id, pcImg,pcImg2, mobileImg);
    }

    @ApiOperation(value = "添加默认头像")
    @PostMapping(value = "/addDefaultIcon")
    public ResponseResult addDefaultIcon(String icon) {
        return carouselService.insertIcon(icon);
    }

    @ApiOperation(value = "修改默认头像")
    @PostMapping(value = "/updateDefaultIcon")
    public ResponseResult updateDefaultIcon(Integer id,String icon) {
        return carouselService.updateDefaultIcon(id,icon);
    }

    @ApiOperation(value = "删除默认头像")
    @GetMapping(value = "/delDefaultIcon")
    public ResponseResult delDefaultIcon(Integer id) {
        return carouselService.delDefaultIcon(id);
    }

    @ApiOperation(value = "删除轮播图")
    @GetMapping(value = "/delCarousel")
    public ResponseResult delCarousel(Integer id) {
        return carouselService.delCarousel(id);
    }
}
