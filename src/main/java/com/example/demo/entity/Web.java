package com.example.demo.entity;

import java.io.Serializable;

public class Web implements Serializable {
    private Integer wId;

    private String icp;

    private String bgImg;
    private String bgImg2;


    private String logo;
    private String logo2;


    private String payZfb;

    private String payWechat;

    private String gzh;

    private String wBar;
    private String img;
    private String nightImg;

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public String getNightImg() {
        return nightImg;
    }

    public void setNightImg(String nightImg) {
        this.nightImg = nightImg;
    }

    public String getwBar2() {
        return wBar2;
    }

    public void setwBar2(String wBar2) {
        this.wBar2 = wBar2;
    }

    private String wBar2;

    public String getBgImg2() {
        return bgImg2;
    }

    public void setBgImg2(String bgImg2) {
        this.bgImg2 = bgImg2;
    }

    public String getLogo2() {
        return logo2;
    }

    public void setLogo2(String logo2) {
        this.logo2 = logo2;
    }

    private static final long serialVersionUID = 1L;

    public Integer getwId() {
        return wId;
    }

    public void setwId(Integer wId) {
        this.wId = wId;
    }

    public String getIcp() {
        return icp;
    }

    public void setIcp(String icp) {
        this.icp = icp == null ? null : icp.trim();
    }

    public String getBgImg() {
        return bgImg;
    }

    public void setBgImg(String bgImg) {
        this.bgImg = bgImg == null ? null : bgImg.trim();
    }

    public String getLogo() {
        return logo;
    }

    public void setLogo(String logo) {
        this.logo = logo == null ? null : logo.trim();
    }

    public String getPayZfb() {
        return payZfb;
    }

    public void setPayZfb(String payZfb) {
        this.payZfb = payZfb == null ? null : payZfb.trim();
    }

    public String getPayWechat() {
        return payWechat;
    }

    public void setPayWechat(String payWechat) {
        this.payWechat = payWechat == null ? null : payWechat.trim();
    }

    public String getGzh() {
        return gzh;
    }

    public void setGzh(String gzh) {
        this.gzh = gzh == null ? null : gzh.trim();
    }

    public String getwBar() {
        return wBar;
    }

    public void setwBar(String wBar) {
        this.wBar = wBar == null ? null : wBar.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", wId=").append(wId);
        sb.append(", icp=").append(icp);
        sb.append(", bgImg=").append(bgImg);
        sb.append(", logo=").append(logo);
        sb.append(", payZfb=").append(payZfb);
        sb.append(", payWechat=").append(payWechat);
        sb.append(", gzh=").append(gzh);
        sb.append(", wBar=").append(wBar);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}