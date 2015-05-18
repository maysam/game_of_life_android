package me.torabi.gameoflife;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;

public class GameOfLife extends Activity {

    private Game game = new Game();
    private CanvasView canvasView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        canvasView = new CanvasView(this);
        canvasView.update_numbers(game.init(100,100));
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
            canvasView.update_numbers(game.next());
            delayedRun(500);
        }
    };

    private void delayedRun(int delayMillis) {
        mHideHandler.removeCallbacks(mHideRunnable);
        mHideHandler.postDelayed(mHideRunnable, delayMillis);
    }
}
