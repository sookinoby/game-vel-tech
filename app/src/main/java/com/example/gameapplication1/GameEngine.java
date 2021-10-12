package com.example.gameapplication1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class GameEngine extends SurfaceView implements Runnable {
    //This is our thread
    private Thread gameThread = null;
    //We need a SurfaceHolder when we use Paint and Canvas
    //in a thread. We will see it in action in the draw
    //method soon.
    private SurfaceHolder ourHolder;
    //A boolean which we will set and unset
    //when the game is running ofr not.
    private volatile boolean playing;
    //Game is paused the start
    private boolean paused;
    //A canvas and a paint object
    private Canvas canvas;
    private Paint paint;

    //How wide and high is the screen?
    private int screenX;
    private int screenY;

    //This variable tracks game frame rate
    private long fps;

    //This is used to help calculate the fps
    private long timeThisFrame;


    public GameEngine(Context context, int x, int y) {
        super(context);
        screenX = x;
        screenY = y;
        ourHolder = getHolder();
        paint = new Paint();

    }

    public void resume()
    {
      playing = true;
      gameThread = new Thread(this);
      gameThread.start();
    }
    public void pause()
    {

    }
    public void run()
    {
        while(playing)
        {
            //Capture the current time in milliseconds in startTimeFrame
            long startFrameTime = System.currentTimeMillis();
            //Update the frame
            if(!paused) {
                update();
            }
            //Draw the frame
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame>0)
                fps = 1000/timeThisFrame;
        }
    }

    public void draw()
    {

    }
    public void update()
    {
        if(ourHolder.getSurface().isValid())
        {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,26, 128, 182));
            // Change the brush color for drawing
            paint.setColor(Color.argb(255,  249, 129, 0));
            ourHolder.unlockCanvasAndPost(canvas);
        }

    }
}
