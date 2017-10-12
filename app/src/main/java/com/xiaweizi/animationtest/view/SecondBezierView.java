package com.xiaweizi.animationtest.view;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.Path;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;

/**
 * class：   AnimationTest
 * author：  xiaweizi
 * date：    2017/10/12 21:15
 * e-mail:   1012126908@qq.com
 * desc:
 */
public class SecondBezierView extends View {

    private float mStartX;
    private float mStartY;

    private float mEndX;
    private float mEndY;

    private float mFlagX;
    private float mFlagY;

    private Paint mBezierPaint;
    private Paint mPointPaint;
    private Paint mTextPaint;
    private Path mPath;

    public SecondBezierView(Context context) {
        this(context, null);
    }

    public SecondBezierView(Context context, AttributeSet attrs) {
        this(context, attrs, 0);
    }

    public SecondBezierView(Context context, AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
        mBezierPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mBezierPaint.setStrokeWidth(7);
        mBezierPaint.setStyle(Paint.Style.STROKE);

        mPointPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mPointPaint.setStrokeWidth(7);
        mPointPaint.setStyle(Paint.Style.STROKE);

        mTextPaint = new Paint(Paint.ANTI_ALIAS_FLAG);
        mTextPaint.setTextSize(20);
        mTextPaint.setStyle(Paint.Style.STROKE);


        mPath = new Path();
    }

    @Override
    protected void onSizeChanged(int w, int h, int oldw, int oldh) {
        super.onSizeChanged(w, h, oldw, oldh);
        mStartX = w / 4;
        mStartY = h + 200;
        mEndX = w / 4 * 3;
        mEndY = h + 200;

        mFlagX = w / 2;
        mFlagY = h / 2;
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        mPath.reset();
        mPath.moveTo(mStartX, mStartY);
        mPath.quadTo(mFlagX, mFlagY, mEndX, mEndY);
        canvas.drawPoint(mFlagX, mFlagY, mPointPaint);
        canvas.drawText("控制点", mFlagX, mFlagY, mTextPaint);
        canvas.drawPath(mPath, mBezierPaint);
    }

    @Override
    public boolean onTouchEvent(MotionEvent event) {
        switch (event.getAction()) {
            case MotionEvent.ACTION_MOVE:
                mFlagX = event.getX();
                mFlagY = event.getY();
                invalidate();
                break;
        }
        return true;
    }
}
