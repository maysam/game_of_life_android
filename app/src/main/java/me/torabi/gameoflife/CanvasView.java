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

    protected int[][] numbers = null;

    public CanvasView(Context context) {
        super(context);
        paint = new Paint();
    }

    public void update_numbers(int[][] _numbers){
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

    @Override
    protected void onDraw(Canvas canvas) {
        canvas.drawColor(Color.YELLOW);
        int side = (int)((parentHeight>parentWidth? parentWidth: parentHeight)/6);
        int left = (parentWidth - side*4)/2;
        int top = (parentHeight- side*4)/2;
        paint.setColor(Color.MAGENTA);
        float text_size_dip = side/2;
        final float scale = getContext().getResources().getDisplayMetrics().density;
        int text_size = (int) (text_size_dip * scale + 0.5f);
        paint.setTextSize(text_size);
        paint.setTextAlign(Paint.Align.CENTER);

        canvas.drawRoundRect(new RectF(left-10,top-10,left+10+4*side, top+10+4*side), 10, 10, paint);
    }
}

