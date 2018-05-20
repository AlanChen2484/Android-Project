package com.example.quxing.quxing.model;

import java.io.Serializable;

/**
 * Created by 陈若韬 on 2018/5/7.
 */

public class CommunicationBean implements Serializable {

    private int communicationid;

    private String comcontentinfo;

    private int itemid;

    private String username;

    private String itemname;

    public int getCommunicationid() {
        return communicationid;
    }

    public void setCommunicationid(int communicationid) {
        this.communicationid = communicationid;
    }

    public String getComcontentinfo() {
        return comcontentinfo;
    }

    public void setComcontentinfo(String comcontentinfo) {
        this.comcontentinfo = comcontentinfo;
    }

    public int getItemid() {
        return itemid;
    }

    public void setItemid(int itemid) {
        this.itemid = itemid;
    }

    public String getUsername() {
        return username;
    }

    public void setUsername(String username) {
        this.username = username;
    }

    public String getItemname() {
        return itemname;
    }

    public void setItemname(String itemname) {
        this.itemname = itemname;
    }
}
