package com.zhaoys.viewdrawing.custom.piechart;

import android.graphics.Paint;

/**
 * Created by pc on 2016/8/1.
 */
public class PieChartData {
    // 原始数据
    private PieChartItem item;

    // 占百分比
    private float proportion;

    // 进度，这个值由属性动画控制
    private float progress;

    // 内环画笔
    private Paint innerPaint;

    // 外环画笔
    private Paint outerPaint;

    public Paint getInnerPaint() {
        return innerPaint;
    }

    public void setInnerPaint(Paint innerPaint) {
        this.innerPaint = innerPaint;
    }

    public Paint getOuterPaint() {
        return outerPaint;
    }

    public void setOuterPaint(Paint outerPaint) {
        this.outerPaint = outerPaint;
    }

    public PieChartData() {
    }

    public PieChartData(PieChartItem item) {
        this.item = item;
    }

    public PieChartItem getItem() {
        return item;
    }

    public void setItem(PieChartItem item) {
        this.item = item;
    }

    public float getProportion() {
        return proportion;
    }

    public void setProportion(float proportion) {
        this.proportion = proportion;
    }

    public float getProgress() {
        return progress;
    }

    public void setProgress(float progress) {
        this.progress = progress;
    }
}
