package com.example.a1_7.Activity;

import android.content.ContentValues;
import android.content.Intent;
import android.database.sqlite.SQLiteDatabase;
import android.database.sqlite.SQLiteOpenHelper;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;

import com.example.a1_7.R;
import com.example.a1_7.bean.ETC;
import com.example.a1_7.bean.LSSJ;
import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONObject;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

public class WDZH_Activity extends AppCompatActivity {

    private TextView title;
    private TextView tvYue;
    private Spinner spCarnum;
    private EditText etJe;
    private Button btCx;
    private Button btCz;
    private LSSJ dbhelper;
    private int id,je;
    private ArrayList<ETC> etcs=new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_w_d_z_h_);
        initView();
        dbhelper=new LSSJ(this,"ETCLS.db",null,1);
        ChaXun("1");
        title.setText("我的账户");
    }
    public void onclick(View view)
    {
        switch (view.getId())
        {
            case R.id.bt_cx:

                ChaXun(spCarnum.getSelectedItem().toString());
                break;
            case R.id.bt_cz:
                je=Integer.parseInt(etJe.getText().toString().trim());
                if (je<999&&je>0)
                {
                CZ(spCarnum.getSelectedItem().toString(),je);
                }
                else
                {
                    Toast.makeText(this,"请输入0-999的整数",Toast.LENGTH_SHORT).show();
                }
                break;
        }
    }
    public void caidan(View view) {
        startActivity(new Intent(this,MainActivity.class));
    }

    public void ChaXun(String id)
    {
        new OkHttpTo()
                .setIsloop(false)
                .setUrl("get_balance_b")
                .setJsonObject("UserName","user1")
                .setJsonObject("number",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        String bala=jsonObject.optString("balance");
                        tvYue.setText(bala);
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();
    }
    public void CZ(final String id, final int num)
    {
        new OkHttpTo()
                .setUrl("get_plate")
                .setIsloop(false)
                .setJsonObject("UserName","user1")
                .setJsonObject("number",id)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        final String car=jsonObject.optString("plate");
                        new OkHttpTo()
                                .setIsloop(false)
                                .setUrl("set_balance")
                                .setJsonObject("UserName","user1")
                                .setJsonObject("plate",car)
                                .setJsonObject("balance",num)
                                .setOkHttpLo(new OkHttpLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        ChaXun(id);
                                        Toast.makeText(WDZH_Activity.this,"充值成功",Toast.LENGTH_SHORT).show();
                                        SimpleDateFormat date=new SimpleDateFormat("MM-dd HH:mm:ss");
                                        String time=date.format(new Date());
                                        add(String.valueOf(num),id,time);
                                    }

                                    @Override
                                    public void onFailure(IOException e) {
                                        Toast.makeText(WDZH_Activity.this,"充值失败",Toast.LENGTH_LONG);
                                    }
                                }).start();
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }) .start();

    }
    public void add(String m,String c,String time)
    {
        SQLiteDatabase db=dbhelper.getWritableDatabase();
        ContentValues values=new ContentValues();
        values.put("Czje",m);
        values.put("Cph",c);
        values.put("Time",time);
        db.insert("JiLu",null,values);
        values.clear();
    }

    private void initView() {
        title = (TextView) findViewById(R.id.title);
        tvYue = (TextView) findViewById(R.id.tv_yue);
        spCarnum = (Spinner) findViewById(R.id.sp_carnum);
        etJe = (EditText) findViewById(R.id.et_je);
        btCx = (Button) findViewById(R.id.bt_cx);
        btCz = (Button) findViewById(R.id.bt_cz);
    }
}