package com.example.mark.whatarethoseapp;

import android.graphics.Bitmap;

public class RetailerInfo {
    String title;
    Bitmap img;

    public RetailerInfo(String title, Bitmap img) {
        this.title = title;
        this.img = img;
    }

    public RetailerInfo(String title) {
        this.title = title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getTitle() {
        return title;
    }

    public void setImg(Bitmap img) {
        this.img = img;
    }

    public Bitmap getImg() {
        return img;
    }
}
