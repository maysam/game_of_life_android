package me.torabi.gameoflife;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.view.View;

/**
 * Created by maysam on 5/18/15.
 */

public class CanvasView  extends View {

    private final Game game;
    private Paint paint;
    private int parentWidth;
    private int parentHeight;

    protected boolean[][] numbers = null;
    private int side_length = 10;
    private int h_count = 10;
    private int v_count = 10;

    public CanvasView(Context context, Game _game) {
        super(context);
        paint = new Paint();
        game = _game;
    }

    public void update_numbers(boolean[][] _numbers){
        numbers = _numbers;
        invalidate();
    }
    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        parentWidth = MeasureSpec.getSize(widthMeasureSpec);
        parentHeight = MeasureSpec.getSize(heightMeasureSpec);
        this.setMeasuredDimension(parentWidth, parentHeight);
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        if (game != null) {
            float side_length_dip = 20;
            final float scale = getContext().getResources().getDisplayMetrics().density;
            side_length = (int) (side_length_dip * scale + 0.5f);
            h_count = parentWidth/side_length;
            v_count = parentHeight/side_length;
            game.init(h_count, v_count);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        h_count = numbers.length;
        v_count = numbers[0].length;
        int left = (parentWidth - side_length* h_count)/2;
        int top = (parentHeight- side_length* v_count)/2;
        paint.setColor(Color.BLACK);
        paint.setStrokeWidth(1);
        canvas.drawRect(new RectF(left - 10, top - 10, left + 10 + h_count * side_length, top + 10 + v_count * side_length), paint);
        for (int i=0; i< h_count; i++) {
            for (int j = 0; j < v_count; j++) {
                if (numbers[i][j]) {
                    paint.setColor(Color.BLUE);
                } else {
                    paint.setColor(Color.YELLOW);
                }
                canvas.drawRect(new RectF(left + side_length * i, top + side_length * j, left + side_length + side_length * i, top + side_length + side_length * j), paint);
            }
        }
    }
}

