package com.zhaoys.viewdrawing;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

import com.zhaoys.viewdrawing.activity.PieChartActivity;

public class MainActivity extends AppCompatActivity {
    private Button pieChartBtn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        pieChartBtn = (Button) findViewById(R.id.pieChartBtn);
        pieChartBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent();
                intent.setClass(MainActivity.this, PieChartActivity.class);
                startActivity(intent);
            }
        });
    }
}
