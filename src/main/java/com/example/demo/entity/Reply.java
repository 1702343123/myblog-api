package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;

public class Reply implements Serializable {
    private Integer id;

    private Integer cId;

    private Integer userId;

    private Integer replyUserId;

    private Timestamp createTime;

    private Integer isDel;

    private String content;
    private String userName;
    private String replyUserName;
    private String time;
    private String userIcon;
    private String replyUserIcon;

    public String getReplyUserIcon() {
        return replyUserIcon;
    }

    public void setReplyUserIcon(String replyUserIcon) {
        this.replyUserIcon = replyUserIcon;
    }

    public Reply() {
    }

    public String getUserIcon() {
        return userIcon;
    }

    public void setUserIcon(String userIcon) {
        this.userIcon = userIcon;
    }

    public String getUserName() {
        return userName;
    }

    public void setUserName(String userName) {
        this.userName = userName;
    }

    public String getReplyUserName() {
        return replyUserName;
    }

    public void setReplyUserName(String replyUserName) {
        this.replyUserName = replyUserName;
    }

    private static final long serialVersionUID = 1L;

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getUserId() {
        return userId;
    }

    public void setUserId(Integer userId) {
        this.userId = userId;
    }

    public Integer getReplyUserId() {
        return replyUserId;
    }

    public void setReplyUserId(Integer replyUserId) {
        this.replyUserId = replyUserId;
    }

    public Timestamp getCreateTime() {
        return createTime;
    }

    public void setCreateTime(Timestamp createTime) {
        this.createTime = createTime;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getContent() {
        return content;
    }

    public void setContent(String content) {
        this.content = content == null ? null : content.trim();
    }

    @Override
    public String toString() {
        return "Reply{" +
                "id=" + id +
                ", cId=" + cId +
                ", userId=" + userId +
                ", replyUserId=" + replyUserId +
                ", createTime=" + createTime +
                ", isDel=" + isDel +
                ", content='" + content + '\'' +
                ", userName='" + userName + '\'' +
                ", replyUserName='" + replyUserName + '\'' +
                ", time='" + time + '\'' +
                ", userIcon='" + userIcon + '\'' +
                '}';
    }
}