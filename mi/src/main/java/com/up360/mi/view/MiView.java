package com.up360.mi.view;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.BitmapShader;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.PorterDuff;
import android.graphics.PorterDuffXfermode;
import android.graphics.Shader;
import android.graphics.Xfermode;
import android.util.AttributeSet;
import android.view.View;

import com.up360.mi.R;
import com.up360.mi.utils.DesUtils;

public class MiView extends View {
    private Context context;
    private Paint paint = new Paint();
    public MiView(Context context) {
        super(context);
        init(context);
    }

    public MiView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context);
    }
    public MiView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init(context);
    }

    private void init(Context context){
        this.context = context;
        paint.setColor(0xff1e90ff);
    }
    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        Bitmap bitmap = BitmapFactory.decodeResource(getResources(), R.drawable.batman);
        Shader shader = new BitmapShader(bitmap, Shader.TileMode.CLAMP, Shader.TileMode.CLAMP);
        paint.setShader(shader);
        paint.setShadowLayer(10, 15, 15, Color.GREEN);
        canvas.drawCircle(DesUtils.dip2px(context, 50), DesUtils.dip2px(context, 50), DesUtils.dip2px(context, 50), paint);


//        int saved = canvas.saveLayer(null, null, Canvas.ALL_SAVE_FLAG);
//
//        canvas.drawCircle(300, 300, 150, paint);
//        paint.setXfermode(new PorterDuffXfermode(PorterDuff.Mode.DST_IN));
//        paint.setColor(0xff63a309);
//        canvas.drawRect(100,100, 600, 600, paint);
//        paint.setXfermode(null);
//
//        canvas.restoreToCount(saved);
    }

    @Override
    public void draw(Canvas canvas) {
        super.draw(canvas);
    }
}
