package com.iut.gang.feigness.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.AlphaAnimation;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.animation.ScaleAnimation;
import android.widget.Button;
import android.widget.TextView;

import com.iut.gang.feigness.R;
import com.iut.gang.feigness.listener.HeartListener;


public class HeartRateFragment extends Fragment implements View.OnClickListener{

    private TextView mTextView;
    private com.iut.gang.feigness.utils.HeartBeatView heartbeat;
    private Intent heartServiceIntent;
    private BroadcastReceiver brStep;
    private int cumulButton;
    private Button buttonMinus;
    private Button buttonPlus;
    private HeartListener heartlistener;
    private int oldHeartBeatInt;

    public HeartRateFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        this.cumulButton=0;
        this.oldHeartBeatInt=0;
        heartlistener=new HeartListener();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.heart_rate_fragment, container, false);
        mTextView = rootView.findViewById(R.id.tvheartrate);
        heartbeat = rootView.findViewById(R.id.heartbeat);

        buttonMinus=rootView.findViewById(R.id.buttonMinus);
        buttonPlus=rootView.findViewById(R.id.buttonPlus);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);

         Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
         pulse.setAnimationListener(heartlistener);

//        pulse.setAnimationListener(new Animation.AnimationListener() {
//            @Override
//            public void onAnimationStart(Animation animation) {
//                System.out.println("Start");
//            }
//
//            @Override
//            public void onAnimationEnd(Animation animation) {
//                System.out.println("End");
//            }
//
//            @Override
//            public void onAnimationRepeat(Animation animation) {
//                System.out.println("Repeat");
//            }
//        });
//        AlphaAnimation animation2 = new AlphaAnimation(1.0f, 0.0f);
//        animation2.setDuration(1000);
//        animation2.setStartOffset(5000);

        heartbeat.startAnimation(pulse);
//        ScaleAnimation scaleAnimation=new ScaleAnimation(0.0f,2.5f,0.0f,0.0f,50,50);
//        scaleAnimation.setDuration(1000);
//        scaleAnimation.setStartOffset(5000);
//        heartbeat.startAnimation(scaleAnimation);

        heartServiceIntent = new Intent(getActivity(), com.iut.gang.feigness.utils.WearHeartEmulatorService.class);
        brStep=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int rate=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);
                System.out.println("Je recois coeur"+Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)));
                int pulse=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);

                    heartlistener.heartRate=oldHeartBeatInt;

//


                mTextView.setText(Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)+cumulButton) );

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



    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.buttonMinus:
                this.cumulButton-=5;
                break;
            case R.id.buttonPlus:
                this.cumulButton+=5;
                break;
        }
    }
}
