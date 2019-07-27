package com.up360.mi.view.hencoder;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.util.AttributeSet;
import android.view.View;
import android.view.WindowManager;

import com.up360.mi.bean.FloatPoint;
import com.up360.mi.utils.DesUtils;

import java.util.ArrayList;

//https://hencoder.com/ui-1-1/
//https://v.youku.com/v_show/id_XMjg3MDIwMjgyMA==.html

public class HistogramView extends View {
    private final int SPACE_WIDTH = 20;//柱间隙
    private final int MAX_HEIGHT = 200;//直方图高度
    private final int MIN_WIDTH_PILLAR = 50;//柱子的宽度
    private final int MARGIN = 10;
    private Context context;
    private int widthScreen;

    public HistogramView(Context context) {
        this(context, null);
    }
    public HistogramView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }
    public HistogramView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        this.context = context;
        WindowManager wm = (WindowManager) context.getSystemService(Context.WINDOW_SERVICE);
        widthScreen = wm.getDefaultDisplay().getWidth();
        init();
    }

    private FloatPoint fpOrigin = new FloatPoint();
    private FloatPoint fpY = new FloatPoint();
    private FloatPoint fpX = new FloatPoint();
    private int mSpace = 0;
    private int mWidthPillar = 0;

    private ArrayList<FloatPoint> fpLT = new ArrayList<>();
    private void init(){
        paintLine.setStyle(Paint.Style.STROKE);
        paintLine.setStrokeWidth(3);
        paintLine.setColor(0xff557fcd);

        fpY.setX(DesUtils.dip2px(context, MARGIN));
        fpY.setY(DesUtils.dip2px(context, MARGIN));
        fpOrigin.setX(DesUtils.dip2px(context, MARGIN));
        fpOrigin.setY(DesUtils.dip2px(context, MAX_HEIGHT));
        fpX.setX(widthScreen - DesUtils.dip2px(context, MARGIN));
        fpX.setY(DesUtils.dip2px(context, MAX_HEIGHT));

        mData.add(0.2f);
        mData.add(0.8f);
        mData.add(1f);
        mData.add(0.34f);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(0xff47cf5b);

        float total_w = widthScreen - 2 * DesUtils.dip2px(context, MARGIN);
        mSpace = DesUtils.dip2px(context, SPACE_WIDTH);
        mWidthPillar = DesUtils.dip2px(context, MIN_WIDTH_PILLAR);
        //间隔+柱+间隔+柱+...
        if(total_w / mData.size() - mSpace > mWidthPillar){
            float per = total_w / mData.size();
            for(int i=0; i<mData.size(); i++){
                FloatPoint fp = new FloatPoint();
                fp.setX(fpOrigin.getX() + per * (i+1) - mWidthPillar);
                fp.setY(fpOrigin.getY() - mData.get(i) * (fpOrigin.getY() - fpY.getY()));
                fpLT.add(fp);
            }
        }

    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        canvas.drawColor(0xfff4f4f4);
        drawCoordinate(canvas);
        drawHistogram(canvas);
    }


    Paint paint = new Paint();
    Paint paintLine = new Paint();
    private ArrayList<Float> mData = new ArrayList<>();

    private void drawCoordinate(Canvas canvas){
        canvas.drawLine(fpY.getX(), fpY.getY(), fpOrigin.getX(), fpOrigin.getY(), paintLine);
        canvas.drawLine(fpOrigin.getX(), fpOrigin.getY(), fpX.getX(), fpX.getY(), paintLine);
    }

    private void drawHistogram(Canvas canvas){
        for(int i=0; i<fpLT.size(); i++){
            canvas.drawRect(fpLT.get(i).getX(), fpLT.get(i).getY(),
                    fpLT.get(i).getX() + mWidthPillar, fpOrigin.getY() - 3, paint);
        }
    }

}
