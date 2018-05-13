package com.example.quxing.quxing.model;

import java.io.Serializable;

/**
 * Created by 陈若韬 on 2018/5/3.
 */

public class ItembackupInfoBean implements Serializable {
    private int itemid;

    private String itemname;

    private String itemtime;

    private int enrolment = 0;

    private int follownumber = 0;

    private String createtime;

    private String hostname;

    private int itemlabel = 1;

    private String callnumber;

    private int money = 0;

    private String address;

    private String details;

    private String imageurl;

    public int getItemid() {
        return itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public String getItemtime() {
        return itemtime;
    }

    public int getEnrolment() {
        return enrolment;
    }

    public int getFollownumber() {
        return follownumber;
    }

    public String getHostname() {
        return hostname;
    }

    public int getItemlabel() {
        return itemlabel;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public String getDetails() {
        return details;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public void setItemtime(String itemtime) {
        this.itemtime = itemtime;
    }

    public void setEnrolment(int enrolment) {
        this.enrolment = enrolment;
    }

    public void setFollownumber(int follownumber) {
        this.follownumber = follownumber;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public void setItemlabel(int itemlabel) {
        this.itemlabel = itemlabel;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public int getMoney() {
        return money;
    }

    public void setMoney(int money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getCreatetime() {
        return createtime;
    }

    public void setCreatetime(String createtime) {
        this.createtime = createtime;
    }

    public String getImageurl() {
        return imageurl;
    }

    public void setImageurl(String imageurl) {
        this.imageurl = imageurl;
    }
}
