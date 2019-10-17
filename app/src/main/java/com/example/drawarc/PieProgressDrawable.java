package com.example.drawarc;

import android.graphics.Canvas;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.Rect;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;
import android.util.DisplayMetrics;


public class PieProgressDrawable extends Drawable {
    Paint mPaint1;
    RectF mBoundsF;
    RectF mInnerBoundsF;
    final float START_ANGLE = 0.f;
    float mDrawTo;
    public PieProgressDrawable() {
        super();
        mPaint1 = new Paint(Paint.ANTI_ALIAS_FLAG);  //anti-alias 는 표면처리를 부드럽게 하는 기술이다.


    }

    public void setBorderWidth(float widthDp, DisplayMetrics dm) {
        float borderWidth = widthDp * dm.density;
        mPaint1.setStrokeWidth(borderWidth);
    }

    public void setColor(int color) {
        mPaint1.setColor(color);
    }

    @Override
    public void draw(Canvas canvas) {

        canvas.rotate(-90f, getBounds().centerX(), getBounds().centerY());
        mPaint1.setStyle(Paint.Style.STROKE);
        canvas.drawOval(mBoundsF, mPaint1);
        mPaint1.setStyle(Paint.Style.FILL);
        canvas.drawArc(mInnerBoundsF, START_ANGLE, mDrawTo, true, mPaint1);


    }

    @Override
    protected void onBoundsChange(Rect bounds) {
        super.onBoundsChange(bounds);
        mBoundsF = mInnerBoundsF = new RectF(bounds);
        final int halfBorder = (int) (mPaint1.getStrokeWidth()/2f + 0.5f);
        mInnerBoundsF.inset(halfBorder, halfBorder);
    }

    @Override
    protected boolean onLevelChange(int level) {
        final float drawTo = START_ANGLE + ((float)360*level)/100f;
        boolean update = drawTo != mDrawTo;
        mDrawTo = drawTo;
        return update;
    }

    @Override
    public void setAlpha(int alpha) {
        mPaint1.setAlpha(alpha);
    }

    @Override
    public void setColorFilter(ColorFilter cf) {
        mPaint1.setColorFilter(cf);
    }

    @Override
    public int getOpacity() {
        return mPaint1.getAlpha();
    }
}
