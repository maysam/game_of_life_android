package me.torabi.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.view.MotionEvent;
import android.view.View;

import java.util.Random;

public class GameOfLife extends Activity {

    private Game game = new Game();
    private boolean[][] numbers = new boolean[100][100];
    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasView = new CanvasView(this);
        canvasView.update_numbers(numbers);
        setContentView(canvasView);
    }

    @Override
    protected void onPostCreate(Bundle savedInstanceState) {
        super.onPostCreate(savedInstanceState);

        delayedRun(100);
    }

    Handler mHideHandler = new Handler();
    Runnable mHideRunnable = new Runnable() {
        @Override
        public void run() {
            game.next();
            Random rand = new Random(System.currentTimeMillis());
            for(int i=0; i<numbers.length; i++) {
                for (int j=0; j<numbers[i].length; j++) {
                    numbers[i][j] = rand.nextBoolean();
                }
            }
            canvasView.update_numbers(numbers);
            delayedRun(500);
        }
    };

    /**
     * Schedules a call to hide() in [delay] milliseconds, canceling any
     * previously scheduled calls.
     */
    private void delayedRun(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
