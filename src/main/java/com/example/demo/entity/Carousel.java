package com.example.demo.entity;

import java.io.Serializable;

public class Carousel implements Serializable {
    private Integer cId;

    private Integer isDel;

    private String cPc;

    private String cMobile;
    private String cNight;

    public String getcNight() {
        return cNight;
    }

    public void setcNight(String cNight) {
        this.cNight = cNight;
    }

    private static final long serialVersionUID = 1L;

    public Integer getcId() {
        return cId;
    }

    public void setcId(Integer cId) {
        this.cId = cId;
    }

    public Integer getIsDel() {
        return isDel;
    }

    public void setIsDel(Integer isDel) {
        this.isDel = isDel;
    }

    public String getcPc() {
        return cPc;
    }

    public void setcPc(String cPc) {
        this.cPc = cPc == null ? null : cPc.trim();
    }

    public String getcMobile() {
        return cMobile;
    }

    public void setcMobile(String cMobile) {
        this.cMobile = cMobile == null ? null : cMobile.trim();
    }

    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append(getClass().getSimpleName());
        sb.append(" [");
        sb.append("Hash = ").append(hashCode());
        sb.append(", cId=").append(cId);
        sb.append(", isDel=").append(isDel);
        sb.append(", cPc=").append(cPc);
        sb.append(", cMobile=").append(cMobile);
        sb.append(", serialVersionUID=").append(serialVersionUID);
        sb.append("]");
        return sb.toString();
    }
}