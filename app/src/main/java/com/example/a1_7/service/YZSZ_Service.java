package com.example.a1_7.service;

import android.app.NotificationManager;
import android.app.Service;
import android.content.Intent;
import android.os.IBinder;

import androidx.annotation.Nullable;
import androidx.core.app.NotificationCompat;

import com.example.a1_7.net.OkHttpLo;
import com.example.a1_7.net.OkHttpTo;

import org.json.JSONArray;
import org.json.JSONObject;

import java.io.IOException;

public class YZSZ_Service extends Service {
    int t1,t2,t3,t4,t5,t6;
    int a1,a2,a3,a4,a5,a6;
    @Nullable
    @Override
    public IBinder onBind(Intent intent) { return null; }

    @Override
    public int onStartCommand(Intent intent, int flags, int startId) {
        bj();
        return super.onStartCommand(intent, flags, startId);

    }
    public void bj()
    {
        new OkHttpTo()
                .setUrl("get_threshold")
                .setJsonObject("UserName","user1")
                .setIsloop(true)
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                        JSONObject object=jsonArray.optJSONObject(0);
                        t1=object.optInt("temperature");
                        t2=object.optInt("humidity");
                        t3=object.optInt("illumination");
                        t4=object.optInt("co2");
                        t5=object.optInt("pm25");
                        t6=object.optInt("path");
                    }

                    @Override
                    public void onFailure(IOException e) {

                    }
                }).start();

        new OkHttpTo()
                .setUrl("get_all_sense")
                .setIsloop(true)
                .setJsonObject("UserName","user1")
                .setOkHttpLo(new OkHttpLo() {
                    @Override
                    public void onResponse(JSONObject jsonObject) {
                        a1=jsonObject.optInt("temperature");
                        t2=jsonObject.optInt("humidity");
                        t3=jsonObject.optInt("illumination");
                        t4=jsonObject.optInt("co2");
                        t5=jsonObject.optInt("pm25");
                        new OkHttpTo()
                                .setUrl("get_road_status")
                                .setIsloop(false)
                                .setJsonObject("UserName","user1")
                                .setJsonObject("RoadId","1")
                                .setOkHttpLo(new OkHttpLo() {
                                    @Override
                                    public void onResponse(JSONObject jsonObject) {
                                        JSONArray jsonArray=jsonObject.optJSONArray("ROWS_DETAIL");
                                        JSONObject object=jsonArray.optJSONObject(0);
                                         a6=object.optInt("state");
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

        if (a1>t1)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("温度报警");
            notificationManager.notify(10,builder.build());
        }
        if (a2>t2)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("湿度报警");
            notificationManager.notify(10,builder.build());
        }
        if (a3>t3)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("光照报警");
            notificationManager.notify(10,builder.build());
        }
        if (a4>t4)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("co报警");
            notificationManager.notify(10,builder.build());
        }
        if (a5>t5)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("pm报警");
            notificationManager.notify(10,builder.build());
        }
        if (a6>t6)
        {
            NotificationManager notificationManager= (NotificationManager) getSystemService(NOTIFICATION_SERVICE);
            NotificationCompat.Builder builder=new NotificationCompat.Builder(this);
            builder.setContentTitle("道路报警");
            notificationManager.notify(10,builder.build());
        }



    }
}

