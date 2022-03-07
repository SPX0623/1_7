package com.example.a1_7.net;

import android.os.Handler;
import android.os.Message;

import androidx.annotation.NonNull;

import org.json.JSONException;
import org.json.JSONObject;

import java.io.IOException;

import okhttp3.Call;
import okhttp3.Callback;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;

public class OkHttpTo extends Thread{
    private String Url="http://10.173.112.85:8080/traffic/";
    private boolean isloop;
    private OkHttpLo okHttpLo;
    private JSONObject jsonObject=new JSONObject();
    private Handler handler=new Handler(new Handler.Callback() {
        @Override
        public boolean handleMessage(@NonNull Message msg) {
            if (msg.what==1)
            {
                okHttpLo.onResponse((JSONObject) msg.obj);
            }
            else if (msg.what==2)
            {
                okHttpLo.onFailure((IOException) msg.obj);
            }
            return false;
        }
    });

    public OkHttpTo setUrl(String url) {
        Url+= url;
        return this;
    }

    public OkHttpTo setIsloop(boolean isloop) {
        this.isloop = isloop;
        return this;
    }

    public OkHttpTo setOkHttpLo(OkHttpLo okHttpLo) {
        this.okHttpLo = okHttpLo;
        return this;
    }

    public OkHttpTo setJsonObject(String k,Object v) {
        try {
            jsonObject.put(k,v);
        } catch (JSONException e) {
            e.printStackTrace();
        }
        return this;
    }

    @Override
    public void run() {
        super.run();
        do {
            OkHttpClient okHttpClient = new OkHttpClient();
            RequestBody requestBody = RequestBody.create(MediaType.get("Weather_2/json;charset=utf-8"), jsonObject.toString());
            Request request = new Request.Builder()
                    .url(Url)
                    .post(requestBody)
                    .build();
            okHttpClient.newCall(request)
                    .enqueue(new Callback() {
                        @Override
                        public void onFailure(Call call, IOException e) {
                            Message message = new Message();
                            message.what = 2;
                            message.obj = e;
                            handler.sendMessage(message);

                        }

                        @Override
                        public void onResponse(Call call, Response response) throws IOException {
                            try {
                                Message message = new Message();
                                message.what = 1;
                                message.obj = new JSONObject(response.body().string());
                                handler.sendMessage(message);
                            } catch (JSONException e) {
                                e.printStackTrace();
                            }
                        }
                    });
            try {
                Thread.sleep(3000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }while (isloop);
    }
}
