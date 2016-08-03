package com.zhaoys.viewdrawing.custom.progress;

import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.ColorFilter;
import android.graphics.Paint;
import android.graphics.RectF;
import android.graphics.drawable.Drawable;

/**
 * 简易环形进度条
 * Created by pc on 2016/8/3.
 */
public class AnnulusProgressDrawable extends Drawable {
    public static final String PROPERTY_NAME_PROGRESS = "progress";

    private Paint mPaint;
    private RectF rectF;
    private float ringWidth = 10;
    private float progress;

    public AnnulusProgressDrawable() {
        mPaint = new Paint();
        mPaint.setColor(Color.parseColor("#f9da82"));
        mPaint.setAntiAlias(true);
        mPaint.setStyle(Paint.Style.STROKE);
        mPaint.setStrokeWidth(ringWidth);
    }

    @Override
    public void setBounds(int left, int top, int right, int bottom) {
        super.setBounds(left, top, right, bottom);
        float offset = ringWidth / 2;
        rectF = new RectF(left + offset, top + offset, right - offset, bottom - offset);
    }

    @Override
    public void draw(Canvas canvas) {
        canvas.drawArc(rectF, -90, progress, false, mPaint);
    }

    @Override
    public void setAlpha(int alpha) {

    }

    @Override
    public void setColorFilter(ColorFilter colorFilter) {

    }

    @Override
    public int getOpacity() {
        return 0;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
        invalidateSelf();
    }
}
