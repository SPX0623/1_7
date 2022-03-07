package com.example.a1_7.Activity;

import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a1_7.R;
import com.example.a1_7.adapter.MyAdapter;
import com.example.a1_7.fragment.clwz_1;
import com.example.a1_7.fragment.clwz_2;

import java.util.ArrayList;

public class CLWZ_Activity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private TextView title;
    private LinearLayout linearWzsp;
    private TextView tvSp;
    private LinearLayout linearWztp;
    private TextView tvTp;
    private ViewPager2 viewpager;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_c_l_w_z_);
        fragments.add(new clwz_1());
        fragments.add(new clwz_2());
        initView();
        init_pager();

    }

    public void caidan(View view) {
        startActivity(new Intent(CLWZ_Activity.this, MainActivity.class));
    }
    public void init_pager()
    {
        MyAdapter myAdapter=new MyAdapter(getSupportFragmentManager(),getLifecycle(),fragments);
        viewpager.setAdapter(myAdapter);
    }
    public void onclick(View view) {
        switch (view.getId())
        {
            case R.id.iv_1:
                tz("1");
                break;
            case R.id.iv_2:
                tz("2");
                break;
            case R.id.iv_3:
                tz("3");
                break;
            case R.id.iv_4:
                tz("4");
                break;
            case R.id.iv_5:
                tz2("1");
                break;
            case R.id.iv_6:
                tz2("2");
                break;
            case R.id.iv_7:
                tz2("3");
                break;
            case R.id.iv_8:
                tz2("4");
                break;
        }
    }
    public void tz(String a)
    {
        Intent intent=new Intent(CLWZ_Activity.this,mediaplay.class);
        intent.putExtra("number",a);
        startActivity(intent);
    }
    public void tz2(String a)
    {
        Intent intent=new Intent(CLWZ_Activity.this,imageplay.class);
        intent.putExtra("number",a);
        startActivity(intent);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        linearWzsp = (LinearLayout) findViewById(R.id.linear_wzsp);
        tvSp = (TextView) findViewById(R.id.tv_sp);
        linearWztp = (LinearLayout) findViewById(R.id.linear_wztp);
        tvTp = (TextView) findViewById(R.id.tv_tp);
        viewpager = (ViewPager2) findViewById(R.id.viewpager);
    }
}