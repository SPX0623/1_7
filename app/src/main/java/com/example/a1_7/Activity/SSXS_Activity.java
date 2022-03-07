package com.example.a1_7.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;
import com.example.a1_7.bean.LSSJ;
import com.example.a1_7.bean.WDLS;
import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class SSXS_Activity extends AppCompatActivity {

    private TextView title;
    private TextView tvWd;
    private TextView tvSd;
    private TextView tvGz;
    private TextView tvCo;
    private TextView tvPm;
    private TextView tvRoad;
    private String road;
    private WDLS dbhelper=new WDLS(this, "WDLS.db",null,1);

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_s_x_s_);
        initView();
        HuoQu();
        title.setText("环境指标");

    }
    public void caidan(View view) {
        startActivity(new Intent(SSXS_Activity.this, MainActivity.class));
    }
    public void HuoQu()
    {
        new OkHttpTo()
                .setIsloop(true)
                .setJsonObject("UserName","user1")
                .setUrl("get_all_sense")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String wd=jsonObject.optString("temperature");
                        String sd=jsonObject.optString("humidity");
                        String gz=jsonObject.optString("illumination");
                        String co=jsonObject.optString("co2");
                        String pm=jsonObject.optString("pm25");
                        tvWd.setText(wd);
                        tvSd.setText(sd);
                        tvGz.setText(gz);
                        tvCo.setText(co);
                        tvPm.setText(pm);
                        HuoQu2();
                        setSQ(wd,sd,gz,co,pm,road);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
    public void HuoQu2()
    {
        new OkHttpTo()
                .setIsloop(false)
                .setUrl("get_road_status")
                .setJsonObject("Username","user1")
                .setJsonObject("RoadId",1)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject object=jsonArray.optJSONObject(0);
                        road=object.optString("state");
                        Log.v("road","==="+road);
                        tvRoad.setText(road);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
    public void setSQ(String wd,String sd,String gz,String co,String pm,String road)
    {
        SQLiteDatabase database=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("wd",wd);
        values.put("sd",sd);
        values.put("gz",gz);
        values.put("co",co);
        values.put("pm",pm);
        values.put("road",road);
        database.insert("TQ",null,values);
        values.clear();
    }
    public void onclick()
    {
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        tvWd = (TextView) findViewById(R.id.tv_wd);
        tvSd = (TextView) findViewById(R.id.tv_sd);
        tvGz = (TextView) findViewById(R.id.tv_gz);
        tvCo = (TextView) findViewById(R.id.tv_co);
        tvPm = (TextView) findViewById(R.id.tv_pm);
        tvRoad = (TextView) findViewById(R.id.tv_road);
    }
}