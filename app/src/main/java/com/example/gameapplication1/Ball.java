package com.example.gameapplication1;

import android.graphics.RectF;

public class Ball {
    private RectF rect;
    private float xVelocity;
    private float yVelocity;
    private float ballWidth;
    private float ballHeight;

    Ball()
    {
        xVelocity = 200;
        yVelocity = -400;
        rect = new RectF();
    }

    RectF getRect()
    {
        return rect;
    }

    void reverseXVelocity(){
        xVelocity = -xVelocity;
    }

    void reverseYVelocity(){
        yVelocity = -yVelocity;
    }

}

