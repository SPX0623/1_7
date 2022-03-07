package com.example.a1_7.Activity;

import android.graphics.Color;
import android.os.Bundle;
import android.text.InputType;
import android.view.View;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;
import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;

public class YZSZ_Activity extends AppCompatActivity {

    private TextView title;
    private TextView tv1;
    private EditText et1;
    private TextView tv2;
    private EditText et2;
    private TextView tv3;
    private EditText et3;
    private TextView tv4;
    private EditText et4;
    private TextView tv5;
    private EditText et5;
    private TextView tv6;
    private EditText et6;
    private int t1, t2, t3, t4, t5, t6;
    private CheckBox cb;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_y_z_s_z_);
        initView();
        pd();
    }
    public void change(View  view)
    {
        pd();
    }
    public void onclick(View view) {
        if ((et1.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et1.getText().toString().trim();
            t1 = Integer.parseInt(s1);
        }
        if ((et2.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et2.getText().toString().trim();
            t2 = Integer.parseInt(s1);
        }
        if ((et3.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et3.getText().toString().trim();
            t3 = Integer.parseInt(s1);
        }
        if ((et4.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et4.getText().toString().trim();
            t4 = Integer.parseInt(s1);
        }
        if ((et5.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et5.getText().toString().trim();
            t5 = Integer.parseInt(s1);
        }
        if ((et6.getText().toString().trim()).isEmpty()) {
        } else {
            String s1 = et6.getText().toString().trim();
            t6 = Integer.parseInt(s1);
        }
        setYZ();
    }
    public void setYZ()
    {
        new OkHttpTo()
                .setUrl("set_threshold")
                .setJsonObject("UserName","user1")
                .setJsonObject("temperature",t1)
                .setJsonObject("humidity",t2)
                .setJsonObject("illumination",t3)
                .setJsonObject("co2",t4)
                .setJsonObject("pm25",t5)
                .setJsonObject("path",t6)
                .setIsloop(false)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {

                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    public void pd() {
        if (cb.isChecked())
        {
//            et1.setBackgroundColor(Color.parseColor("#808080"));
//            et2.setBackgroundColor(Color.parseColor("#808080"));
//            et3.setBackgroundColor(Color.parseColor("#808080"));
//            et4.setBackgroundColor(Color.parseColor("#808080"));
//            et5.setBackgroundColor(Color.parseColor("#808080"));
//            et6.setBackgroundColor(Color.parseColor("#808080"));
            et1.setEnabled(false);
            et2.setEnabled(false);
            et3.setEnabled(false);
            et4.setEnabled(false);
            et5.setEnabled(false);
            et6.setEnabled(false);

        }
        else
        {
//            et1.setBackgroundColor(Color.parseColor("#ffffff"));
//            et2.setBackgroundColor(Color.parseColor("#ffffff"));
//            et3.setBackgroundColor(Color.parseColor("#ffffff"));
//            et4.setBackgroundColor(Color.parseColor("#ffffff"));
//            et5.setBackgroundColor(Color.parseColor("#ffffff"));
//            et6.setBackgroundColor(Color.parseColor("#ffffff"));
            et1.setEnabled(true);
            et2.setEnabled(true);
            et3.setEnabled(true);
            et4.setEnabled(true);
            et5.setEnabled(true);
            et6.setEnabled(true);
        }
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        tv1 = (TextView) findViewById(R.id.tv_1);
        et1 = (EditText) findViewById(R.id.et_1);
        tv2 = (TextView) findViewById(R.id.tv_2);
        et2 = (EditText) findViewById(R.id.et_2);
        tv3 = (TextView) findViewById(R.id.tv_3);
        et3 = (EditText) findViewById(R.id.et_3);
        tv4 = (TextView) findViewById(R.id.tv_4);
        et4 = (EditText) findViewById(R.id.et_4);
        tv5 = (TextView) findViewById(R.id.tv_5);
        et5 = (EditText) findViewById(R.id.et_5);
        tv6 = (TextView) findViewById(R.id.tv_6);
        et6 = (EditText) findViewById(R.id.et_6);
        cb = (CheckBox) findViewById(R.id.cb);
    }
}