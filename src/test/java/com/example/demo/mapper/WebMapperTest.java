package com.example.demo.mapper;

import com.example.demo.BasicTest;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;

import static org.junit.jupiter.api.Assertions.*;

class WebMapperTest extends BasicTest {
    @Autowired
    private WebMapper webMapper;
    @Test
    void updateByPrimaryKey() {
        webMapper.updateByPrimaryKey("bg_img", "123");
    }
}