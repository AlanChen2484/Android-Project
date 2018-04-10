package com.example.quxing.quxing.Main;

/**
 * Created by 陈若韬 on 2018/3/27.
 */

public class Item {
    private String name;
    private int imageId;

    public Item(String name, int imageId) {
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

