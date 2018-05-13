package com.example.quxing.quxing.model;

import java.util.Date;

/**
 * Created by 陈若韬 on 2018/4/30.
 */

public class ItemInfoBean {
    private Integer itemid;
    private String itemname;
    private String itemtime;
    private Integer enrolment;
    private Integer follownumber;
    private String releasetime;
    private String hostname;
    private Integer itemlabel;
    private String callnumber;
    private Integer money = 0;
    private String address;
    private String details;
    private String path;

    public ItemInfoBean() {
    }

    public ItemInfoBean(Integer itemid, String itemname, String itemtime, Integer enrolment, Integer follownumber, String releasetime, String hostname, Integer itemlabel, String callnumber, Integer money, String address, String details) {
        this.itemid = itemid;
        this.itemname = itemname;
        this.itemtime = itemtime;
        this.enrolment = enrolment;
        this.follownumber = follownumber;
        this.releasetime = releasetime;
        this.hostname = hostname;
        this.itemlabel = itemlabel;
        this.callnumber = callnumber;
        this.money = money;
        this.address = address;
        this.details = details;
        this.path = path;
    }

    public Integer getItemid() {
        return itemid;
    }

    public void setItemid(Integer itemid) {
        this.itemid = itemid;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }

    public String getItemtime() {
        return itemtime;
    }

    public void setItemtime(String itemtime) {
        this.itemtime = itemtime;
    }

    public Integer getEnrolment() {
        return enrolment;
    }

    public void setEnrolment(Integer enrolment) {
        this.enrolment = enrolment;
    }

    public Integer getFollownumber() {
        return follownumber;
    }

    public void setFollownumber(Integer follownumber) {
        this.follownumber = follownumber;
    }

    public String getReleasetime() {
        return releasetime;
    }

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
    }

    public String getHostname() {
        return hostname;
    }

    public void setHostname(String hostname) {
        this.hostname = hostname;
    }

    public Integer getItemlabel() {
        return itemlabel;
    }

    public void setItemlabel(Integer itemlabel) {
        this.itemlabel = itemlabel;
    }

    public String getCallnumber() {
        return callnumber;
    }

    public void setCallnumber(String callnumber) {
        this.callnumber = callnumber;
    }

    public Integer getMoney() {
        return money;
    }

    public void setMoney(Integer money) {
        this.money = money;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }

    public String getDetails() {
        return details;
    }

    public void setDetails(String details) {
        this.details = details;
    }

    public String getPath() {
        return path;
    }

    public void setPath(String path) {
        this.path = path;
    }
}
