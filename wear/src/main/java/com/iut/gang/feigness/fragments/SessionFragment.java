package com.iut.gang.feigness.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.TextView;

import com.iut.gang.feigness.R;

/**
 * Created by Victor on 07/02/2018.
 */

public class SessionFragment  extends Fragment {
    private TextView mTextView;
    private com.iut.gang.feigness.utils.SessionView sessionView;
    private Intent heartServiceIntent;
    private BroadcastReceiver brStep;

    public SessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.session_fragment, container, false);

        //TODO verify if in session
        if(!isInSession()){

        }

//        mTextView = () rootView.findViewById(R.id.tvheartrate);
        sessionView = rootView.findViewById(R.id.heartbeat);
        final Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
        sessionView.startAnimation(pulse);

        return rootView;
    }

    @Override
    public void onResume() {
        super.onResume();
    }

    @Override
    public void onPause() {
         super.onPause();
    }


    public boolean isInSession(){
        return false;
    }
}
