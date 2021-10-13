package com.example.gameapplication1;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.view.MotionEvent;
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
    private Bat bat;

    public GameEngine(Context context, int x, int y) {
        super(context);
        screenX = x;
        screenY = y;
        ourHolder = getHolder();
        paint = new Paint();

        //Initialize the player's bat
        bat = new Bat(screenX, screenY);

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
        while(playing) {
            //Capture the current time in milliseconds in startTimeFrame
            long startFrameTime = System.currentTimeMillis();
            if (!paused){
                update();
            }
            draw();
            timeThisFrame = System.currentTimeMillis() - startFrameTime;
            if(timeThisFrame>=1)
                fps = 1000/timeThisFrame;
        }
    }

    public void draw()
    {
        if(ourHolder.getSurface().isValid())
        {
            canvas = ourHolder.lockCanvas();
            canvas.drawColor(Color.argb(255,26, 128, 182));
            // Change the brush color for drawing
            paint.setColor(Color.argb(255,255,255,255));
            canvas.drawRect(bat.getRect(), paint);
            ourHolder.unlockCanvasAndPost(canvas);
        }
    }
    private void update()
    {
        bat.update(fps);

    }

    //The Surfaceview class implements onTouchListener
    //So we can override this method and detect screen touches


    /*public boolean onTouchEvent(MotionEvent motionEvent) {
        switch(motionEvent.getAction() & MotionEvent.ACTION_MASK)
        {
            //Player has touched the screen
            case MotionEvent.ACTION_DOWN:
                paused = false;
                if(motionEvent.getX()>screenX/2){
                    bat.setMovementState(bat.RIGHT);
                }
                else
                {
                    bat.setMovementState(bat.LEFT);
                }
                break;

                //Player has removed the finger from screen
            case MotionEvent.ACTION_UP:
                bat.setMovementState(bat.STOPPED);
                break;
        }

        return true;
    }*/
}
