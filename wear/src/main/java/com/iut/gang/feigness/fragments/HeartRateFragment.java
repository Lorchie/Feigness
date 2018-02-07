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


public class HeartRateFragment extends Fragment {

    private TextView mTextView;
    private com.iut.gang.feigness.utils.HeartBeatView heartbeat;
    private Intent heartServiceIntent;
    private BroadcastReceiver brStep;

    public HeartRateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.heart_rate_fragment, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.tvheartrate);
        heartbeat = rootView.findViewById(R.id.heartbeat);
        final Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
        heartbeat.startAnimation(pulse);
        heartServiceIntent = new Intent(getActivity(), com.iut.gang.feigness.utils.WearHeartEmulatorService.class);
        brStep=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int rate=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);
                System.out.println("Je recois coeur"+Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)));
//                pulse.;
                mTextView.setText(Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)));
            }
        };
        return rootView;
    }

    @Override
    public void onResume() {
        this.getContext().startService(heartServiceIntent);
        this.getContext().registerReceiver(brStep,new IntentFilter(com.iut.gang.feigness.utils.heartService.HEART_COUT_MESSAGE));
        super.onResume();
    }

    @Override
    public void onPause() {
        this.getContext().stopService(heartServiceIntent);
        this.getContext().unregisterReceiver(brStep);
        super.onPause();
    }

    public void onClickMinus(){

    }
}
