package com.example.drawarc;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.RectF;
import android.os.CountDownTimer;
import android.util.AttributeSet;
import android.view.View;

import androidx.annotation.Nullable;


public class NewPieProgress extends View {
    private float mProgress;
    final float START_ANGLE = 360.f;
    public RectF rectF;
    public Paint paint;
//    private static final float MAX_DEGREE = 360.0f;
//    private static final int DEFAULT_MAX = 100;
//    private boolean mDrawBackgroundOutsideProgress = false;


    // timer // 시간 설정 가능하게
    // listener -> 진행사항, 완료사항
    //


    public interface onProgressListener {
        void onProgress(int percent);
        void onComplete();
    }


    public NewPieProgress(Context context) {
        super(context);
    }

    public NewPieProgress(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public NewPieProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public NewPieProgress(Context context, @Nullable AttributeSet attrs, int defStyleAttr, int defStyleRes) {
        super(context, attrs, defStyleAttr, defStyleRes);
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        drawOval(canvas);
    }

    public void start() {

    }

    public void setProgress(int progress) {
        mProgress = (float) ((float) progress * 3.6);
        invalidate();
    }

    public void drawOval(Canvas canvas) {
        canvas.drawColor(Color.WHITE);
        paint = new Paint();
        paint.setAntiAlias(true);
        rectF = new RectF();

        rectF.set(50, 50, 500, 500);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.RED);
        canvas.drawOval(rectF, paint);

        paint.setStyle(Paint.Style.FILL);
        paint.setColor(Color.BLUE);
        canvas.drawArc(rectF, START_ANGLE, START_ANGLE - mProgress, true, paint);


    }

//
//    private void drawProgress(Canvas canvas) {
//
//        int mMax = DEFAULT_MAX;
//
//        if (mDrawBackgroundOutsideProgress) {
//            float startAngle = MAX_DEGREE * mProgress / mMax;
//            float sweepAngle = MAX_DEGREE - startAngle;
//            canvas.drawArc(rectF, startAngle, sweepAngle, true, paint);
//        } else {
//            canvas.drawArc(rectF, 0.0f, MAX_DEGREE, true, paint);
//        }
//        canvas.drawArc(rectF, 0.0f, MAX_DEGREE * mProgress / mMax, true, paint);


}


