package com.example.a1_7.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager2.widget.ViewPager2;

import com.example.a1_7.R;
import com.example.a1_7.adapter.MyAdapter;
import com.example.a1_7.bean.ZX_ZS;
import com.example.a1_7.fragment.fragment_1;
import com.example.a1_7.fragment.fragment_2;
import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class SSTP_Activity extends AppCompatActivity {
    private ArrayList<Fragment> fragments = new ArrayList<>();
    private ArrayList<ZX_ZS> zx_zs = new ArrayList<>();
    private TextView title;
    private ViewPager2 viewpager;
    private String wd;
    private String sd;
    private String gz;
    private String co;
    private String pm;
    private String dl;
    private int index;
    private ImageView imageView;
    private LinearLayout linearlayout;
    int a = 0;
    private TextView tvTips;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_s_s_t_p_);
        initView();
        HuoQu();
        fragments.add(new fragment_1(zx_zs));
        fragments.add(new fragment_2(zx_zs));
        viewpager.setCurrentItem(index);
        for (int a = 0; a < fragments.size(); a++) {
            imageView = new ImageView(this);
            if (a == index) {
                imageView.setImageResource(R.mipmap.page_indicator_focused);
            } else {
                imageView.setImageResource(R.mipmap.page_indicator_unfocused);
            }
            imageView.setLayoutParams(new ViewGroup.LayoutParams(70, 70));
            linearlayout.addView(imageView);
        }
        viewpager.registerOnPageChangeCallback(new ViewPager2.OnPageChangeCallback() {
            @Override
            public void onPageScrolled(int position, float positionOffset, int positionOffsetPixels) {
                super.onPageScrolled(position, positionOffset, positionOffsetPixels);
            }

            @Override
            public void onPageSelected(int position) {
                super.onPageSelected(position);
                for (int i=0;i<fragments.size();i++)
                {
                    ImageView imageView= (ImageView) linearlayout.getChildAt(i);
                    if (i==position)
                    {
                        imageView.setImageResource(R.mipmap.page_indicator_focused);
                    }
                    else
                    {
                        imageView.setImageResource(R.mipmap.page_indicator_unfocused);
                    }
                }
            }

            @Override
            public void onPageScrollStateChanged(int state) {
                super.onPageScrollStateChanged(state);
            }
        });
//        Log.v("spx","==="+fragments.size());
    }

    public void caidan(View view) {
        startActivity(new Intent(SSTP_Activity.this, MainActivity.class));
    }

    public void HuoQu() {
        new OkHttpTo()
                .setIsloop(true)
                .setUrl("get_all_sense")
                .setJsonObject("UserName", "user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        final String wd = jsonObject.optString("temperature");
                        final String sd = jsonObject.optString("humidity");
                        final String gz = jsonObject.optString("illumination");
                        final String co = jsonObject.optString("co2");
                        final String pm = jsonObject.optString("pm25");
                        new OkHttpTo()
                                .setIsloop(false)
                                .setUrl("get_road_status")
                                .setJsonObject("UserName", "user1")
                                .setJsonObject("RoadId", "1")
                                .setOkHttpLo(new OkHttpLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        JSONArray jsonArray = jsonObject.optJSONArray("ROWS_DETAIL");
                                        JSONObject object = jsonArray.optJSONObject(0);
                                        String dl = object.optString("state");
                                        SimpleDateFormat date=new SimpleDateFormat("HH:mm");
                                        zx_zs.add(new ZX_ZS().setCo(co).setWd(wd).setGz(gz).setSd(sd).setPm(pm).setDl(dl).setDate(date.format(new Date())));
                                        if ((60-a)>0)
                                        {
                                        a = a + 3;
                                        }
                                        if (a<60)
                                        {
                                        String s = "拼了老命加载中，还需" + (60 - a) + "秒";
                                            tvTips.setText(s);
                                        }
                                        else
                                        {
                                            tvTips.setText("");
                                        }

                                        if (zx_zs.size() == 20) {
                                            init_pager();
                                        }
                                    }

                                    @Override
                                    public void onFailure(IOException e) {

                                    }
                                }).start();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }

    public void init_pager() {
        MyAdapter myAdapter = new MyAdapter(getSupportFragmentManager(), getLifecycle(), fragments);
        viewpager.setAdapter(myAdapter);
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        viewpager = (ViewPager2) findViewById(R.id.viewpager);
        linearlayout = (LinearLayout) findViewById(R.id.linearlayout);
        tvTips = (TextView) findViewById(R.id.tv_tips);
    }
}