package com.example.a1_7.fragment;

import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.widget.DialogTitle;
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

public class fragment_2 extends Fragment {
    private ArrayList<ZX_ZS> zx_zs;
    private TextView tv;
    private LineChart chart;
    private ArrayList<Entry> entries=new ArrayList<>();
    private ArrayList<String> time=new ArrayList<>();

    public fragment_2(ArrayList<ZX_ZS> zx_zs) {
        this.zx_zs = zx_zs;
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
        tv.setText("湿度");
        HuoQu();

    }
    public void HuoQu()
    {
        for (int a=0;a<zx_zs.size();a++)
        {
            time.add(zx_zs.get(a).getDate());
            Log.v("time","=="+time.get(a));
            entries.add(new Entry(a,Integer.parseInt(zx_zs.get(a).getSd())));
        }
        LineDataSet lineDataSet=new LineDataSet(entries,"");
        LineData data=new LineData(lineDataSet);
        chart.setData(data);
        setX();
    }
    public void setX()
    {
        XAxis xAxis=chart.getXAxis();
        xAxis.setPosition(XAxis.XAxisPosition.BOTTOM);
        xAxis.setLabelCount(time.size());
        xAxis.setValueFormatter(new IndexAxisValueFormatter(time));
    }

    private void initView() {
        tv = (TextView) getView().findViewById(R.id.tv);
        chart = (LineChart) getView().findViewById(R.id.chart);
    }
}
