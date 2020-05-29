package com.example.demo.service;

import com.example.demo.entity.Timeline;
import com.example.demo.mapper.TimelineMapper;
import com.example.demo.util.MsgConst;
import com.example.demo.util.ResponseResult;
import com.example.demo.util.StatusConst;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.sql.Timestamp;
import java.util.List;

@Service
public class TimelineService {

    @Autowired
    private TimelineMapper timelineMapper;
/*查询时间线数据*/
    public ResponseResult getAllTimeline() {
        List<Timeline> timelines = timelineMapper.selectAll();
        return ResponseResult.success(timelines);
    }

    public  Timeline setTimeline(String time, String content) {
//        Timestamp timestamp=new Timestamp(System.currentTimeMillis());
        Timestamp date = new Timestamp(System.currentTimeMillis());
        if (time != null&&!time.trim().equals("")) {
            date = Timestamp.valueOf(time);
        }
        Timeline timeline = new Timeline();
        timeline.setIsDel(0);
        timeline.setTime(date);
        timeline.setContent(content);
        return timeline;
    }
    /*添加时间线数据*/
    public ResponseResult insertTimeline(String time, String content) {
        if (content == null &&!content.trim().equals("")) {
            return ResponseResult.error(StatusConst.ERROR, "内容为空");
        }
        Timeline timeline = setTimeline(time, content);
        int insert = timelineMapper.insert(timeline);
        if (insert == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }

    /*修改时间线*/
    public ResponseResult changeTimeline(Integer id,String time, String content) {
        if (id == null) {
            return ResponseResult.error(StatusConst.ERROR, MsgConst.ID_NULL);
        }
        Timeline timeline = setTimeline(time, content);
        timeline.setId(id);
        int i = timelineMapper.updateByPrimaryKey(timeline);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);    }

    /*删除时间线*/
    public ResponseResult removeTimeline(Integer id) {
        int i = timelineMapper.deleteByPrimaryKey(id);
        if (i == 1) {
            return new ResponseResult();
        }
        return ResponseResult.error(StatusConst.ERROR, MsgConst.FAIL);
    }
}
