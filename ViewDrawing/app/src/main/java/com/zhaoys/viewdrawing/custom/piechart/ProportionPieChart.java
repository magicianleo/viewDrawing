package com.zhaoys.viewdrawing.custom.piechart;

import android.animation.ValueAnimator;
import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.view.animation.Animation;
import android.view.animation.ScaleAnimation;

import com.zhaoys.viewdrawing.R;
import com.zhaoys.viewdrawing.utils.DensityUtils;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by pc on 2016/8/1.
 * 比例饼状图
 */
public class ProportionPieChart extends View {
    private ArrayList<PieChartData> datas = new ArrayList<>();
    public float totalVal;

    private int outerStrokeWidth;// 外环宽度
    private int ringSpc;// 内外环间距
    private int innerStrokeWidth;// 内环宽度

    private RectF outerRectF;// 外环绘制区
    private RectF innerRectF;// 内环绘制区

    private Paint defOuterPaint;
    private Paint defInnerPaint;

    // 数据是否初始初始化
    private boolean isInitData = false;

    // 圆环默认值
    private int mWidth, mHeight;

    public ProportionPieChart(Context context) {
        super(context);
        init(context, null);
    }

    public ProportionPieChart(Context context, AttributeSet attrs) {
        super(context, attrs);
        init(context, attrs);
    }

    public ProportionPieChart(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    /**
     * 饼图View初始化
     *
     * @param context
     * @param attrs
     */
    private void init(Context context, AttributeSet attrs) {
        TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ProportionPieChart);
        outerStrokeWidth = a.getDimensionPixelSize(R.styleable.ProportionPieChart_outerStrokeWidth, -1);
        ringSpc = a.getDimensionPixelSize(R.styleable.ProportionPieChart_ringSpc, -1);
        innerStrokeWidth = a.getDimensionPixelSize(R.styleable.ProportionPieChart_innerStrokeWidth, -1);
        a.recycle();

        mWidth = DensityUtils.dp2px(getContext(), 80);
        mHeight = DensityUtils.dp2px(getContext(), 80);

        defOuterPaint = getOuterPaint(getResources().getColor(R.color.gray_light));
        defInnerPaint = getInnerPaint(getResources().getColor(R.color.gray_light));
    }

    /**
     * 初始化饼状图数据
     *
     * @param items
     */
    public void initData(List<PieChartItem> items) {
        if (items == null) {
            return;
        }

        isInitData = true;

        // 填充数据 && 计算总值
        for (PieChartItem item : items) {
            PieChartData data = new PieChartData(item);
            totalVal += item.getValue();
            data.setOuterPaint(getOuterPaint(item.getColor()));
            data.setInnerPaint(getInnerPaint(item.getColor()));
            datas.add(data);
        }

        // 有没数据,各部分所占比例为默认值0
        if (noValue()) {
            return;
        }

        // 计算饼图每个部分所占百分比例
        for (PieChartData itemData : datas) {
            itemData.setProportion(itemData.getItem().getValue() / totalVal);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        // 继承View默认情况下wrap_content和match_parent效用是一样的，需要进行处理
        int widthSpecMode = MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize = MeasureSpec.getSize(widthMeasureSpec);
        int heightSpecMode = MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize = MeasureSpec.getSize(heightMeasureSpec);
        if (widthSpecMode == MeasureSpec.AT_MOST && heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, mHeight);
        } else if (widthSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(mWidth, heightSpecSize);
        } else if (heightSpecMode == MeasureSpec.AT_MOST) {
            setMeasuredDimension(widthSpecSize, mHeight);
        }
    }

    @Override
    protected void onLayout(boolean changed, int left, int top, int right, int bottom) {
        super.onLayout(changed, left, top, right, bottom);
        measurePieRect();
    }

    /**
     * 确定内外环绘制区域
     */
    private void measurePieRect() {
        // 继承View默认情况下padding设置不会生效，需要根据具体需求进行处理
        int width = getWidth() - getPaddingLeft() - getPaddingRight();
        int height = getHeight() - getPaddingTop() - getPaddingBottom();

        float outerSpc = outerStrokeWidth / 2f;
        // 确定外环绘制区域
//        outerRectF = new RectF(outerSpc, outerSpc, width - outerSpc, height - outerSpc);

        float innerSpc = outerStrokeWidth + ringSpc + innerStrokeWidth / 2f;
        // 确定内环绘制区域
//        innerRectF = new RectF(innerSpc, innerSpc, width - innerSpc, height - innerSpc);

        if (height > width) {
            float d = (height - width) / 2f;
            float ol = outerSpc;
            float ot = d + outerSpc;
            float or = width - outerSpc;
            float ob = height - d - outerSpc;
            // 确定外环绘制区域
            outerRectF = buildRectF(ol, ot, or, ob);

            float il = innerSpc;
            float it = d + innerSpc;
            float ir = width - innerSpc;
            float ib = height - d - innerSpc;
            // 确定内环绘制区域
            innerRectF = buildRectF(il, it, ir, ib);
        } else {
            float d = (width - height) / 2f;
            float ol = d + outerSpc;
            float ot = outerSpc;
            float or = width - d - outerSpc;
            float ob = height - outerSpc;
            // 确定外环绘制区域
            outerRectF = buildRectF(ol, ot, or, ob);

            float il = d + innerSpc;
            float it = innerSpc;
            float ir = width - d - innerSpc;
            float ib = height - innerSpc;
            // 确定内环绘制区域
            innerRectF = buildRectF(il, it, ir, ib);
        }
    }

    /**
     * 依据padding确定绘制区域的位置
     *
     * @param left   The X coordinate of the left side of the rectangle
     * @param top    The Y coordinate of the top of the rectangle
     * @param right  The X coordinate of the right side of the rectangle
     * @param bottom The Y coordinate of the bottom of the rectangle
     */
    private RectF buildRectF(float left, float top, float right, float bottom) {
        return new RectF(left + getPaddingLeft(), top + getPaddingTop(), right + getPaddingLeft(), bottom + getPaddingTop());
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);

        if (!isInitData) {// 数据没有初始化，不显示饼图
            return;
        }

        if (noValue()) {
            canvas.drawArc(outerRectF, -90, 360, false, defOuterPaint);
            canvas.drawArc(innerRectF, -90, 360, false, defInnerPaint);
            return;
        }

        float startAngle = -90;
        for (int i = 0; i < datas.size(); i++) {
            PieChartData data = datas.get(i);
            canvas.drawArc(outerRectF, startAngle, data.getProgress(), false, data.getOuterPaint());
            canvas.drawArc(innerRectF, startAngle, data.getProgress(), false, data.getInnerPaint());
            startAngle += data.getProportion() * 360;
        }
    }

    /**
     * 投资组合动画
     */
    public void startAnimation() {
        if (noValue()) {
            return;
        }

        Animation scaleAnimation = new ScaleAnimation(0.1f, 1.0f, 0.1f, 1.0f, getMeasuredWidth() / 2, getMeasuredHeight() / 2);
        scaleAnimation.setDuration(1500);
        this.startAnimation(scaleAnimation);

        ValueAnimator valueAnim = ValueAnimator.ofFloat(0, 360);

        valueAnim.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
            @Override
            public void onAnimationUpdate(ValueAnimator valueAnimator) {
                float animatedValue = (float) valueAnimator.getAnimatedValue();
                for (PieChartData data : datas) {
                    data.setProgress(animatedValue * data.getProportion());
                }
                invalidate();
            }
        });

        valueAnim.setInterpolator(new AccelerateDecelerateInterpolator());
//        valueAnim.setInterpolator(new DecelerateInterpolator());
        valueAnim.setDuration(1000);
//        valueAnim.setTarget(canvsImgv);
        valueAnim.start();
    }


    /**
     * @return 判断总值是否为0，如果为0：true，反之为false
     */
    private boolean noValue() {
        return totalVal == 0;
    }

    /**
     * 根据颜色获取外环画笔
     *
     * @param color
     * @return
     */
    private Paint getOuterPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(outerStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        return paint;
    }

    /**
     * 根据颜色获取内环画笔
     *
     * @param color
     * @return
     */
    private Paint getInnerPaint(int color) {
        Paint paint = new Paint();
        paint.setAntiAlias(true);
        paint.setStrokeWidth(innerStrokeWidth);
        paint.setStyle(Paint.Style.STROKE);
        paint.setColor(color);
        return paint;
    }
}
