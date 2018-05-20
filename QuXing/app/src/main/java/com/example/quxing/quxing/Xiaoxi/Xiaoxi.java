package com.example.quxing.quxing.Xiaoxi;

import com.example.quxing.quxing.model.ItemInfoBean;

/**
 * Created by 陈若韬 on 2018/4/17.
 */

public class Xiaoxi extends ItemInfoBean {
    private String name;
    private int imageId;

    public Xiaoxi(String name, int imageId) {
        this.name = name;
        this.imageId = imageId;
    }

    public String getName() {
        return name;
    }

    public int getImageId() {
        return imageId;
    }
}
