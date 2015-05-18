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

    private Paint paint;
    private int parentWidth;
    private int parentHeight;

    protected boolean[][] numbers = null;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
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
    }

    int side_count = 100;
    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.LTGRAY);
        int side_length = (int)((parentHeight>parentWidth? parentWidth: parentHeight)/(side_count+1));
        int left = (parentWidth - side_length* side_count)/2;
        int top = (parentHeight- side_length* side_count)/2;
        paint.setColor(Color.BLACK);
        float text_size_dip = side_length/2;
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int text_size = (int) (text_size_dip * scale + 0.5f);
        paint.setTextSize(text_size);
        paint.setTextAlign(Paint.Align.CENTER);
        paint.setStrokeWidth(1);
        canvas.drawRect(new RectF(left - 10, top - 10, left + 10 + side_count * side_length, top + 10 + side_count * side_length), paint);
        for (int i=0; i< side_count; i++) {
            for (int j = 0; j < side_count; j++) {
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

