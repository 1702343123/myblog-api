package com.example.demo.controller;


import com.example.demo.service.TimelineService;
import com.example.demo.util.Client;
import com.example.demo.util.ResponseResult;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Client
@RequestMapping(value = "/timeline")
@Api(tags = "9.时间线")
public class TimelineController {
    @Autowired
    private TimelineService timelineService;

    @ApiOperation(value = "所有时间线数据")
    @GetMapping(value = "/getTimelineList")
    public ResponseResult getTimelineList() {
        return timelineService.getAllTimeline();
    }

    @ApiOperation(value = "添加一条开发记录")
    @PostMapping(value = "/addTimeline")
    public ResponseResult addTimeline(String time, String content) {
        return timelineService.insertTimeline(time, content);
    }

    @ApiOperation(value = "修改记录")
    @PostMapping(value = "/updateTimeline")
    public ResponseResult updateTimeline(Integer id, String time, String content) {
        return timelineService.changeTimeline(id, time, content);
    }

    @ApiOperation(value = "删除一条记录")
    @GetMapping(value = "delTimeline")
    public ResponseResult delTimeline(Integer id) {
        return timelineService.removeTimeline(id);
    }
}
