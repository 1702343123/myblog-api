package com.example.demo.entity;

import java.io.Serializable;
import java.sql.Timestamp;
import java.util.List;

public class Article implements Serializable {
    private Integer aId;

    private String aTitle;

    private Integer uId;

    private Timestamp aTime;

    private String aImg;

    private Integer aScan;

    private Integer isDel;

    private Integer isTop;

    private String aContent;
    private Integer love;
    private Integer count;
    private String time;
    private String userName;
    private String userIcon;
    private String hoverImg;

    private String aImg2;

    private String hoverImg2;

    public Timestamp getaTime() {
        return aTime;
    }

    public void setaTime(Timestamp aTime) {
        this.aTime = aTime;
    }

    public String getHoverImg() {
        return hoverImg;
    }

    public void setHoverImg(String hoverImg) {
        this.hoverImg = hoverImg;
    }

    public String getaImg2() {
        return aImg2;
    }

    public void setaImg2(String aImg2) {
        this.aImg2 = aImg2;
    }

    public String getHoverImg2() {
        return hoverImg2;
    }

    public void setHoverImg2(String hoverImg2) {
        this.hoverImg2 = hoverImg2;
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

    private List<Comment> comments;

    public List<Comment> getComments() {
        return comments;
    }

    public void setComments(List<Comment> comments) {
        this.comments = comments;
    }

    public String getTime() {
        return time;
    }

    public void setTime(String time) {
        this.time = time;
    }

    public Integer getLove() {
        return love;
    }

    public void setLove(Integer love) {
        this.love = love;
    }

    public Integer getCount() {
        return count;
    }

    public void setCount(Integer count) {
        this.count = count;
    }

    private static final long serialVersionUID = 1L;

    public Integer getaId() {
        return aId;
    }

    public void setaId(Integer aId) {
        this.aId = aId;
    }

    public String getaTitle() {
        return aTitle;
    }

    public void setaTitle(String aTitle) {
        this.aTitle = aTitle == null ? null : aTitle.trim();
    }

    public Integer getuId() {
        return uId;
    }

    public void setuId(Integer uId) {
        this.uId = uId;
    }



    public String getaImg() {
        return aImg;
    }

    public void setaImg(String aImg) {
        this.aImg = aImg == null ? null : aImg.trim();
    }

    public Integer getaScan() {
        return aScan;
    }

    public void setaScan(Integer aScan) {
        this.aScan = aScan;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public Integer getIsTop() {
        return isTop;
    }

    public void setIsTop(Integer isTop) {
        this.isTop = isTop;
    }

    public String getaContent() {
        return aContent;
    }

    public void setaContent(String aContent) {
        this.aContent = aContent == null ? null : aContent.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", aId=").append(aId);
        sb.append(", aTitle=").append(aTitle);
        sb.append(", uId=").append(uId);
        sb.append(", aTime=").append(aTime);
        sb.append(", aImg=").append(aImg);
        sb.append(", aScan=").append(aScan);
        sb.append(", isDel=").append(isDel);
        sb.append(", isTop=").append(isTop);
        sb.append(", aContent=").append(aContent);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}