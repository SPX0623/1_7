package com.example.a1_7.Activity;

import android.content.Intent;
import android.os.Bundle;
import android.view.MenuItem;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.view.GravityCompat;
import androidx.drawerlayout.widget.DrawerLayout;

import com.example.a1_7.R;
import com.google.android.material.navigation.NavigationView;

public class MainActivity extends AppCompatActivity {

    private NavigationView navView;
    private DrawerLayout drawLayout;
    private TextView title;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        title.setText("欢迎你");
        navView.setNavigationItemSelectedListener(new NavigationView.OnNavigationItemSelectedListener() {
            @Override
            public boolean onNavigationItemSelected(@NonNull MenuItem menuItem) {
                Class iclass = null;
                switch (menuItem.getItemId()) {
                    case R.id.WdZh:
                        iclass = WDZH_Activity.class;
                        break;
                    case R.id.Hld:
                        iclass = HLD_Activity.class;
                        break;
                    case R.id.ZdGl:
                        iclass = ZDGL_Activity.class;
                        break;
                    case R.id.ClWz:
                        iclass = CLWZ_Activity.class;
                        break;
                    case R.id.HjZb:
                        iclass = SSXS_Activity.class;
                        break;
                    case R.id.SsXs:
                        iclass = SSTP_Activity.class;
                        break;
                    case R.id.YzSz:
                        iclass = YZSZ_Activity.class;
                        break;
                }
                if (iclass != null) {
                    Intent intent = new Intent(MainActivity.this, iclass);
                    startActivity(intent);
                    drawLayout.closeDrawer(GravityCompat.START);
                }
                return false;
            }
        });
    }


    public void caidan(View view) {
        if (drawLayout.isDrawerOpen(GravityCompat.START)) {
            drawLayout.closeDrawer(GravityCompat.START);
        } else {
            drawLayout.openDrawer(GravityCompat.START);
        }
    }

    private void initView() {
        navView = (NavigationView) findViewById(R.id.nav_view);
        drawLayout = (DrawerLayout) findViewById(R.id.draw_layout);
        title = (TextView) findViewById(R.id.title);
    }
}