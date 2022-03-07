package com.example.a1_7.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;

public class imageplay extends AppCompatActivity {
    private String a;
    private TextView title;
    private ImageView iv;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_imageplay);
        initView();
        Intent intent = getIntent();
        a = intent.getStringExtra("number");
        switch (Integer.parseInt(a)) {
            case 1:
                iv.setImageResource(R.drawable.weizhang2);
                break;
            case 2:
                iv.setImageResource(R.drawable.weizhang6);
                break;
            case 3:
                iv.setImageResource(R.drawable.weizhang7);
                break;
            case 4:
                iv.setImageResource(R.drawable.weizhang8);
                break;
        }
    }
    public void onclick(View view)
    {
        imageplay.this.finish();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        iv = (ImageView) findViewById(R.id.iv);
    }
}