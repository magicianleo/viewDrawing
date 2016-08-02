package com.zhaoys.viewdrawing.activity;

import android.graphics.Color;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import com.zhaoys.viewdrawing.R;
import com.zhaoys.viewdrawing.custom.piechart.PieChartItem;
import com.zhaoys.viewdrawing.custom.piechart.ProportionPieChart;

import java.util.ArrayList;
import java.util.List;

public class PieChartActivity extends AppCompatActivity {
    private ProportionPieChart pieChart1, pieChart2, pieChart3, pieChart4;
    private List<PieChartItem> items;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pie_chart);

        items = new ArrayList<>();
        PieChartItem item1 = new PieChartItem(Color.parseColor("#f9da82"), 9623);
        PieChartItem item2 = new PieChartItem(Color.parseColor("#9dbde4"), 4623);
        PieChartItem item3 = new PieChartItem(Color.parseColor("#ff969d"), 1623);
        PieChartItem item4 = new PieChartItem(Color.parseColor("#97cea2"), 5623);
        PieChartItem item5 = new PieChartItem(Color.parseColor("#71c1e4"), 6623);
        items.add(item1);
        items.add(item2);
        items.add(item3);
        items.add(item4);
        items.add(item5);

        pieChart1 = (ProportionPieChart) findViewById(R.id.pieChart1);
        pieChart1.initData(items);
        pieChart1.startAnimation();

        pieChart2 = (ProportionPieChart) findViewById(R.id.pieChart2);
        pieChart2.initData(items);
        pieChart2.startAnimation();

        pieChart3 = (ProportionPieChart) findViewById(R.id.pieChart3);
        pieChart3.initData(items);
        pieChart3.startAnimation();

        pieChart4 = (ProportionPieChart) findViewById(R.id.pieChart4);
        pieChart4.initData(items);
        pieChart4.startAnimation();
    }
}
