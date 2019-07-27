package com.up360.mi.view;

import android.content.Context;
import android.graphics.Canvas;
import android.util.AttributeSet;
import android.widget.EditText;

public class MiEditText extends EditText {
    public MiEditText(Context context, AttributeSet attrs) {
        super(context, attrs);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawColor(0xff66bb6a);
        super.draw(canvas);
    }
}
