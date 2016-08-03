package com.zhaoys.viewdrawing.activity;

import android.animation.ObjectAnimator;
import android.animation.ValueAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.TextView;

import com.zhaoys.viewdrawing.R;
import com.zhaoys.viewdrawing.custom.progress.AnnulusProgressDrawable;

public class AnnulusProgressActivity extends AppCompatActivity {
    private TextView loadingTv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annulus_progress);

        loadingTv = (TextView) findViewById(R.id.loadingTv);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        AnnulusProgressDrawable progressDrawable = new AnnulusProgressDrawable();
        progressDrawable.setBounds(0, 0, loadingTv.getHeight(), loadingTv.getHeight());
        loadingTv.setCompoundDrawables(progressDrawable, null, null, null);

        ObjectAnimator objectAnimator = ObjectAnimator.ofFloat(progressDrawable, AnnulusProgressDrawable.PROPERTY_NAME_PROGRESS, 0, 360);
        objectAnimator.setDuration(2000);
        objectAnimator.setInterpolator(new AccelerateDecelerateInterpolator());
        objectAnimator.start();
    }
}
