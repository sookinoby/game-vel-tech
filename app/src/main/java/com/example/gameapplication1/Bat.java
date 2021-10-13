package com.example.gameapplication1;

import android.graphics.RectF;

public class Bat {
    //RectF is an object that holds four coordinates
    private RectF rect;

    //How long will our paddle will be
    private float length;
    private float height;

    //X is the far left of the rectangle which forms our paddle
    private float x;
    private float y;

    private float paddleSpeed;

    //Which ways can the paddle move
    final int STOPPED = 0;
    final int LEFT = 1;
    final int RIGHT = 2;

    //Is the paddle moving?
    private int paddleMoving = STOPPED;

    //This is the constructor method
    //When we create an object from this class we will pass
    //in the screen width and height.
    Bat(int screenX, int screenY) {
        x = screenX / 2;
        y = screenY - 20;
        length = 130;
        height = 20;
        rect = new RectF(x, y, x + length, y + height);
        //How fast is the paddle in pixels per second
        paddleSpeed = 300;
    }

    //This is a a getter method to make the rectangle that
    //defines our paddle available in GameEngine class
    RectF getRect(){
        return rect;
    }

    //This method will be used to change/set if the paddle is going
    //left, right, or nowhere
    void setMovementState(int state)
    {
        paddleMoving = state;
    }

    //This update method will be called from update in the GameEngineClass
    //It determines if the paddle needs to move and changes the coordinates
    //contained in rect if necessary

    void update(long fps)
    {
        if(paddleMoving==LEFT)
        {
            x=x-paddleSpeed/fps;
        }
        if(paddleMoving==RIGHT)
        {
            x=x+paddleSpeed/fps;
        }
        rect.left = x;
        rect.right = x + length;
    }
}
