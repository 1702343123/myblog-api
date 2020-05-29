package com.example.demo.service;

import com.example.demo.mapper.ArticleMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * @author 伊天敬
 * @date 2019/10/31 13:44
 **/
@Service
public class ArticleServiceManager {
    @Autowired
    private ArticleMapper articleMapper;

}
