package com.example.a1_7.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;
import com.example.a1_7.adapter.TableAdapter;
import com.example.a1_7.bean.HLD;
import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class HLD_Activity extends AppCompatActivity {

    private TextView title;
    private Spinner spPx;
    private ListView listView;
    private TableAdapter adapter;
    private ArrayList<HLD> hlds=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_h_l_d_);
        initView();
        title.setText("红绿灯管理");
        setHld();
    }
    public void ChaXun(View view)
    {
        setListView();
        setSortList();
    }
    public void setHld()
    {
        for (int a=1;a<4;a++)
        {
        new OkHttpTo()
                .setUrl("get_traffic_light")
                .setIsloop(false)
                .setJsonObject("UserName","user1")
                .setJsonObject("number",a)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject Object=jsonArray.optJSONObject(0);
                        int num=Object.optInt("number");
                        int red=Object.optInt("red");
                        int yellow=Object.optInt("yellow");
                        int green=Object.optInt("green");
                        hlds.add(new HLD().setNum(num).setGreen(green).setRed(red).setYellow(yellow));
                        Log.v("num","=-="+num);

                    }

                    @Override
                    public void onFailure(IOException e) {
                        Toast.makeText(HLD_Activity.this,"访问失败",Toast.LENGTH_SHORT).show();
                    }
                }).start();
        }
    }
    public void setListView()
    {
        if (adapter==null)
        {
            adapter=new TableAdapter(this,hlds);
            listView.setAdapter(adapter);
        }
        else
        {
            adapter.notifyDataSetChanged();
        }
    }
    public void setSortList()
    {
        Collections.sort(hlds, new Comparator<HLD>() {
            @Override
            public int compare(HLD o1, HLD o2) {
                switch ((int)spPx.getSelectedItemId())
                {
                    case 0:
                        return o1.getNum()-o2.getNum();
                    case 1:
                        return o2.getNum()-o1.getNum();
                    case 2:
                        return o1.getRed()-o2.getRed();
                    case 3:
                        return o2.getRed()-o1.getRed();
                    case 4:
                        return o1.getGreen()-o2.getGreen();
                    case 5:
                        return o2.getGreen()-o1.getGreen();
                    case 6:
                        return o1.getYellow()-o2.getYellow();
                    case 7:
                        return o2.getYellow()-o1.getYellow();
                }
                return 0;
            }
        });
    }
    public void caidan(View view)
    {
        startActivity(new Intent(this,MainActivity.class));
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        spPx = (Spinner) findViewById(R.id.sp_px);
        listView = (ListView) findViewById(R.id.list_view);
    }
}