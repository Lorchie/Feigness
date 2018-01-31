package com.iutbm.example.iutbm.couchot.healthybody.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.support.v7.widget.AppCompatImageView;
import android.util.AttributeSet;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;

import com.iutbm.example.iutbm.couchot.healthybody.R;


public class HeartBeatView extends AppCompatImageView {

    private Drawable heartDrawable;

    public HeartBeatView(Context context) {
        super(context);
        init();
    }

    public HeartBeatView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public HeartBeatView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        heartDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_heart_red_24dp);

        setImageDrawable(heartDrawable);
    }
}