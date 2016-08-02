package com.zhaoys.viewdrawing.custom.piechart;

import android.support.annotation.ColorInt;

/**
 * Created by pc on 2016/8/1.
 */
public class PieChartItem {
    private float value;

    private int color;// 这里使用色值而不用color id，因为color的获取方式有多种

    public PieChartItem() {

    }

    public PieChartItem(int color, float value) {
        this.color = color;
        this.value = value;
    }

    public float getValue() {
        return value;
    }

    public void setValue(float value) {
        this.value = value;
    }

    public int getColor() {
        return color;
    }

    public void setColor(@ColorInt int color) {
        this.color = color;
    }
}
