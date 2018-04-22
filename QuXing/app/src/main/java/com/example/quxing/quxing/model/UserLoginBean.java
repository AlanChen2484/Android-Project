package com.example.quxing.quxing.model;

import android.content.Intent;

/**
 * Created by 陈若韬 on 2018/4/22.
 */

public class UserLoginBean {
    private Intent Userid;
    private String Username;
    private String Password;
    private boolean Loginstate;

    public Intent getUserid() {
        return Userid;
    }

    public String getUsername() {
        return Username;
    }

    public String getPassword() {
        return Password;
    }

    public boolean isLoginstate() {
        return Loginstate;
    }

    public void setUserid(Intent userid) {
        Userid = userid;
    }

    public void setUsername(String username) {
        Username = username;
    }

    public void setPassword(String password) {
        Password = password;
    }

    public void setLoginstate(boolean loginstate) {
        Loginstate = loginstate;
    }
}
