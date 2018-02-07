package com.iut.gang.feigness.utils;


import android.content.Context;
import android.graphics.drawable.Drawable;
import android.support.v4.content.ContextCompat;
import android.util.AttributeSet;
import android.widget.ImageView;

import com.iut.gang.feigness.R;


public class SessionView extends ImageView {

    private Drawable sessionDrawable;

    public SessionView(Context context) {
        super(context);
        init();
    }

    public SessionView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public SessionView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        sessionDrawable = ContextCompat.getDrawable(getContext(), R.drawable.ic_group_24dp);

        setImageDrawable(sessionDrawable);
    }
}