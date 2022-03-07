package com.example.a1_7.Activity;

import android.content.Intent;
import android.database.Cursor;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.ListView;
import android.widget.Spinner;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;
import com.example.a1_7.adapter.czAdapter;
import com.example.a1_7.bean.ETC;
import com.example.a1_7.bean.LSSJ;

import java.util.ArrayList;
import java.util.Collections;
import java.util.Comparator;

public class ZDGL_Activity extends AppCompatActivity {

    private TextView title;
    private Spinner spinnr;
    private TextView lk;
    private TextView hd;
    private TextView huangd;
    private TextView ld;
    private LSSJ lssj;
    private czAdapter myAdapter;
    private ArrayList<ETC> etcs = new ArrayList<>();
    private ListView listview;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_z_d_g_l_);
        initView();
        lssj = new LSSJ(this, "ETCLS.db", null, 1);
        hqShuJu();

        ETC etc = new ETC();

    }

    public void caidan(View view) {
        startActivity(new Intent(ZDGL_Activity.this, MainActivity.class));
    }

    public void hqShuJu() {
        SQLiteDatabase db = lssj.getWritableDatabase();
        Cursor cursor = db.query("JiLu", null, null, null, null, null, null);
        if (cursor.moveToFirst()) {
            do {
                String je = cursor.getString(cursor.getColumnIndex("Czje"));

                String cph = cursor.getString(cursor.getColumnIndex("Cph"));
                String time = cursor.getString(cursor.getColumnIndex("Time"));
                int id=cursor.getInt(cursor.getColumnIndex("id"));
                etcs.add(new ETC().setCzje(je).setCarnum(cph).setCztm(time).setId(id));
            } while (cursor.moveToNext());
        }
        setData();

    }

    public void setData() {
        if (myAdapter == null) {
            myAdapter = new czAdapter(this, etcs);
            listview.setAdapter(myAdapter);
        }
        else
        {
            myAdapter.notifyDataSetChanged();
        }

    }

    public void ChaXun(View view) {
        Compare();
    }

    public void Compare() {
        Collections.sort(etcs, new Comparator<ETC>() {
            @Override
            public int compare(ETC o1, ETC o2) {
                switch ((int) spinnr.getSelectedItemId()) {
                    case 0:
                        return o1.getId() - o2.getId();
                    case 1:
                        return o2.getId() - o1.getId();

                }
                return 0;
            }
        });

    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        spinnr = (Spinner) findViewById(R.id.spinnr);
        lk = (TextView) findViewById(R.id.lk);
        hd = (TextView) findViewById(R.id.hd);
        huangd = (TextView) findViewById(R.id.huangd);
        ld = (TextView) findViewById(R.id.ld);
        listview = (ListView) findViewById(R.id.listview);
    }
}