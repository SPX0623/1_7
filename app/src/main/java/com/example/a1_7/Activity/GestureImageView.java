package com.example.a1_7.Activity;

import android.content.Context;
import android.util.AttributeSet;
import android.util.Log;
import android.view.MotionEvent;
import android.view.View;
import android.widget.ImageView;

import androidx.annotation.Nullable;

public class GestureImageView extends androidx.appcompat.widget.AppCompatImageView {
    public GestureImageView(Context context) {
        super(context);
    }

    public GestureImageView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public GestureImageView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
    }

    public void GestureImageViewinit()
    {
        this.setOnTouchListener((OnTouchListener) this);
    }
    public boolean onTouch(View v, MotionEvent event)
    {
        switch (event.getAction()&MotionEvent.ACTION_MASK)
        {
            case MotionEvent.ACTION_DOWN:
                //手指按下事件
                Log.e("TouchEvent","ActionDown");
                break;
            case MotionEvent.ACTION_POINTER_DOWN:
                //屏幕上已经有一个点按住 再按下一点时触发该事件
                Log.e("TouchEvent","ActionPointerDown");
                break;
            case MotionEvent.ACTION_POINTER_UP:
                //屏幕上已经有两个点按住 再松开一点时触发该事件
                Log.e("TouchEvent","ActionPointerUp");
                break;
            case MotionEvent.ACTION_MOVE:
                //手指移动时触发事件
                Log.e("TouchEvent","ActionMove");
                break;
            case MotionEvent.ACTION_UP:
                //手指松开时触发事件
                Log.e("TouchEvent","ActionUp");
                break;
        }
        return true;
    }

}
