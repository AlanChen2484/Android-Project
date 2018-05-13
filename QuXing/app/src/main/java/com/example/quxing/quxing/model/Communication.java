package com.example.quxing.quxing.model;

import java.io.Serializable;

/**
 * Created by 陈若韬 on 2018/5/7.
 */

public class Communication implements Serializable {

    private int communicationid;

    private String communicationtexttime;

    private String communicationtext;

    public int getCommunicationid() {
        return communicationid;
    }

    public void setCommunicationid(int communicationid) {
        this.communicationid = communicationid;
    }

    public String getCommunicationtexttime() {
        return communicationtexttime;
    }

    public void setCommunicationtexttime(String communicationtexttime) {
        this.communicationtexttime = communicationtexttime;
    }

    public String getCommunicationtext() {
        return communicationtext;
    }

    public void setCommunicationtext(String communicationtext) {
        this.communicationtext = communicationtext;
    }
}
