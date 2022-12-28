package com.Polymor;

import android.graphics.Color;

import java.io.Serializable;

public class Settingvalues implements Serializable {
    private int color;
    private  int colorindex;
    private int round;
    private String version;

    Settingvalues()
    {
        color= Color.GREEN;
        colorindex=1;
        round=2;
        version="v1.0";
    }

    public int getColor() {
        return color;
    }

    public int getColorindex() {
        return colorindex;
    }

    public int getRound() {
        return round;
    }

    public String getVersion() {
        return version;
    }

    public void setColor(int color) {
        this.color = color;
    }

    public void setColorindex(int colorindex) {
        this.colorindex = colorindex;
    }

    public void setRound(int round) {
        this.round = round;
    }

    public void setVersion(String version) {
        this.version = version;
    }
}
