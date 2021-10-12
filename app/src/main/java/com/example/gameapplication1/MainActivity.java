package com.example.gameapplication1;

import android.app.Activity;
import android.graphics.Point;
import android.os.Bundle;
import android.view.Display;

public class MainActivity extends Activity {
   GameEngine gameEngine;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        Display display = getWindowManager().getDefaultDisplay();
        Point size = new Point();
        display.getSize(size);
        gameEngine = new GameEngine(this, size.x, size.y);
        setContentView(gameEngine);
    }

    @Override
    protected void onResume() {
        super.onResume();
        gameEngine.resume();
    }

    @Override
    protected void onPause() {
        super.onPause();
        gameEngine.pause();
    }
}