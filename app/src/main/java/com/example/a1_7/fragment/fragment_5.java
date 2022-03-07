package com.example.a1_7.fragment;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.fragment.app.Fragment;

import com.example.a1_7.R;
import com.example.a1_7.bean.ZX_ZS;
import com.github.mikephil.charting.charts.LineChart;
import com.github.mikephil.charting.components.XAxis;
import com.github.mikephil.charting.data.Entry;
import com.github.mikephil.charting.data.LineData;
import com.github.mikephil.charting.data.LineDataSet;
import com.github.mikephil.charting.formatter.IndexAxisValueFormatter;

import java.util.ArrayList;

public class fragment_5 extends Fragment {
    private TextView tv;
    private LineChart chart;
    private ArrayList<ZX_ZS> zx_zs;
    private ArrayList<String> time=new ArrayList<>();
    private ArrayList<Entry> entries=new ArrayList<>();
    public fragment_5(ArrayList<ZX_ZS> zx_zs)
    {
        this.zx_zs=zx_zs;
    }
    @Nullable
    @Override
    public View onCreateView(@NonNull LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState) {
        return inflater.inflate(R.layout.zxt_item, null);
    }

    @Override
    public void onViewCreated(@NonNull View view, @Nullable Bundle savedInstanceState) {
        super.onViewCreated(view, savedInstanceState);
        initView();
        tv.setText("PM25");
        setData();
    }
    public void setData()
    {
        for (int i=0;i<zx_zs.size();i++)
        {
            time.add(zx_zs.get(i).getDate());
        entries.add(new Entry(i,Integer.parseInt(zx_zs.get(i).getPm())));
        }
        LineDataSet lineDataSet=new LineDataSet(entries,"");
        LineData data=new LineData(lineDataSet);
        chart.setData(data);
        setX();
    }
    public void setX()
    {
        XAxis xAxis= chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(time.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(time));
    }

    private void initView() {
        tv = (TextView) getView().findViewById(R.id.tv);
        chart = (LineChart) getView().findViewById(R.id.chart);
    }
}
