package com.example.quxing.quxing.model;

import java.util.Date;

/**
 * Created by 陈若韬 on 2018/4/30.
 */

public class ItemInfoBean {

    private int itemid;

    private String itemname;

    private String itemtime;

    private int enrolment = 0;

    private int follownumber = 0;

    private String releasetime;

    private String hostname;

    private int itemlabel = 1;

    private String callnumber;

    private int moneuy = 0;

    private String address;

    private String details;

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

    public String getReleasetime() {
        return releasetime;
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

    public int getMoneuy() {
        return moneuy;
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

    public void setReleasetime(String releasetime) {
        this.releasetime = releasetime;
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

    public void setMoneuy(int moneuy) {
        this.moneuy = moneuy;
    }

    public String getAddress() {
        return address;
    }

    public void setAddress(String address) {
        this.address = address;
    }
}
