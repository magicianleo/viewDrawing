package com.zhaoys.viewdrawing.activity;

import android.animation.Animator;
import android.animation.ObjectAnimator;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.animation.AccelerateDecelerateInterpolator;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.zhaoys.viewdrawing.R;
import com.zhaoys.viewdrawing.custom.progress.AnnulusProgressDrawable;

public class AnnulusProgressActivity extends AppCompatActivity implements View.OnClickListener {
    private TextView loadingTv;
    private ImageView proImgv;
    private Button startAnimBtn;
    private AnnulusProgressDrawable proDrawable1, proDrawable2;
    private ObjectAnimator objAnimator1, objAnimator2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_annulus_progress);

        loadingTv = (TextView) findViewById(R.id.loadingTv);

        proImgv = (ImageView) findViewById(R.id.proImgv);

        startAnimBtn = (Button) findViewById(R.id.startAnimBtn);
        startAnimBtn.setOnClickListener(this);
    }

    @Override
    public void onWindowFocusChanged(boolean hasFocus) {
        super.onWindowFocusChanged(hasFocus);
        if (proDrawable1 == null) {
            proDrawable1 = new AnnulusProgressDrawable();
            proDrawable1.setBounds(0, 0, loadingTv.getHeight(), loadingTv.getHeight());
            loadingTv.setCompoundDrawables(proDrawable1, null, null, null);
        }

        if (objAnimator1 == null) {
            objAnimator1 = ObjectAnimator.ofFloat(proDrawable1, AnnulusProgressDrawable.PROPERTY_NAME_PROGRESS, 0, 360);
            objAnimator1.setDuration(2000);
            objAnimator1.setInterpolator(new AccelerateDecelerateInterpolator());
            objAnimator1.addListener(new Animator.AnimatorListener() {
                @Override
                public void onAnimationStart(Animator animation) {
                    loadingTv.setText("加载中...");
                }

                @Override
                public void onAnimationEnd(Animator animation) {
                    loadingTv.setText("加载完毕！");
                }

                @Override
                public void onAnimationCancel(Animator animation) {

                }

                @Override
                public void onAnimationRepeat(Animator animation) {

                }
            });
        }
        objAnimator1.start();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.startAnimBtn:
                if (proDrawable2 == null) {
                    proDrawable2 = new AnnulusProgressDrawable();
                    proDrawable2.setBounds(0, 0, proImgv.getHeight(), proImgv.getHeight());
                    proImgv.setImageDrawable(proDrawable2);
                }

                if (objAnimator2 == null) {
                    objAnimator2 = ObjectAnimator.ofFloat(proDrawable2, AnnulusProgressDrawable.PROPERTY_NAME_PROGRESS, 0, 360);
                    objAnimator2.setDuration(2000);
                    objAnimator2.setInterpolator(new AccelerateDecelerateInterpolator());
                }
                objAnimator2.start();
                break;
        }
    }
}
