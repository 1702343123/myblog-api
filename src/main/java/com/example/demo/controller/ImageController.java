package com.example.demo.controller;

import com.aliyun.oss.OSSClient;
import com.example.demo.service.ImageService;
import com.example.demo.util.Client;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;


@RestController
@Client
@RequestMapping(value = "/image")
@Api(tags = "9a.图片")
public class ImageController {
    @Autowired
    private ImageService imageService;

    @ApiOperation(value = "网站信息图片")
    @PostMapping(value = "/webInfoImage")
    public String webInfoImage(MultipartFile file) {
        return imageService.uploadImage(file, "web-info/");
    }

    @ApiOperation(value = "轮播、关注及头像图片")
    @PostMapping(value = "/carouselIconImage")
    public String carouselIconImage(MultipartFile file) {
        return imageService.uploadImage(file, "atten-carousel-icon/");
    }

    @ApiOperation(value = "文章封面图片")
    @PostMapping(value = "/articleHeaderImage")
    public String articleHeaderImage(MultipartFile file) {
        return imageService.uploadImage(file, "article-header/");
    }
    @ApiOperation(value = "用户自定义头像")
    @PostMapping(value = "/userImage")
    public String userImage(MultipartFile file) {
        return imageService.uploadImage(file, "user/");
    }

    @ApiOperation(value = "文章内容里的图片")
    @PostMapping(value = "/articleContentImage")
    public String articleContentImage(MultipartFile file) {
        return imageService.uploadImage(file, "article-content/");
    }
}
