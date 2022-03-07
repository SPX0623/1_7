package com.example.a1_7.Activity;

import android.content.Intent;
import android.media.MediaPlayer;
import android.net.Uri;
import android.os.Bundle;
import android.view.View;
import android.widget.MediaController;
import android.widget.VideoView;

import androidx.appcompat.app.AppCompatActivity;

import com.example.a1_7.R;

public class mediaplay extends AppCompatActivity {
    String uri;
    private VideoView vd;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_mediaplay);
        initView();
        android.widget.MediaController mediaController = new MediaController(this);
        Intent intent = getIntent();
        String a = intent.getStringExtra("number");
        switch (Integer.parseInt(a)) {
            case 1:
                uri = "android.resource://" + getPackageName() + "/" + R.raw.car1;
                break;
            case 2:
                uri = "android.resource://" + getPackageName() + "/" + R.raw.car2;
                break;
            case 3:
                uri = "android.resource://" + getPackageName() + "/" + R.raw.car3;
                break;
            case 4:
                uri = "android.resource://" + getPackageName() + "/" + R.raw.car4;
                break;
        }
        vd.setVideoURI(Uri.parse(uri));
        vd.setMediaController(mediaController);
        mediaController.setMediaPlayer(vd);
        vd.requestFocus();
        vd.start();
        vd.setOnCompletionListener(new MediaPlayer.OnCompletionListener() {
            @Override
            public void onCompletion(MediaPlayer mp) {
                mediaplay.this.finish();
            }
        });


    }
    public void onclick(View view)
    {
        mediaplay.this.finish();
    }

    private void initView() {
        vd = (VideoView) findViewById(R.id.vd);
    }
}