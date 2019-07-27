package com.up360.mi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.widget.LinearLayout;

public class SpottedLinearLayout extends LinearLayout {
    private Paint paint = new Paint();
    public SpottedLinearLayout(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }
    private void init(){
        paint.setColor(0x551e90ff);
    }
    @Override
    protected void dispatchDraw(Canvas canvas) {
        super.dispatchDraw(canvas);

        canvas.drawCircle(40, 40, 40, paint);
        canvas.drawCircle(100,140, 30, paint);
        canvas.drawCircle(140,200, 30, paint);
        canvas.drawCircle(300,350, 35, paint);


    }
}
