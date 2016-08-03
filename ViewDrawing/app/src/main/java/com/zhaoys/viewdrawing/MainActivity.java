package com.zhaoys.viewdrawing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhaoys.viewdrawing.activity.AnnulusProgressActivity;
import com.zhaoys.viewdrawing.activity.PieChartActivity;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {
    private Button pieChartBtn;
    private Button progressBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartBtn = (Button) findViewById(R.id.pieChartBtn);
        pieChartBtn.setOnClickListener(this);

        progressBtn = (Button) findViewById(R.id.progressBtn);
        progressBtn.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.pieChartBtn:
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
                break;
            case R.id.progressBtn:
                Intent intent2 = new Intent();
                intent2.setClass(MainActivity.this, AnnulusProgressActivity.class);
                startActivity(intent2);
                break;
        }
    }
}
