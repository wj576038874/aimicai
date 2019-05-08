package com.aimicai.entitiy;

import java.io.Serializable;

public class GridInfo implements Serializable {

    private int drawableId;

    private String name;

    public int getDrawableId() {
        return drawableId;
    }

    public void setDrawableId(int drawableId) {
        this.drawableId = drawableId;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }
}
