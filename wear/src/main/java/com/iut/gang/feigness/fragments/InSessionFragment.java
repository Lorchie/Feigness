package com.iut.gang.feigness.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.MenuItem;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.EditText;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iut.gang.feigness.R;
import com.iut.gang.feigness.listener.HeartListener;

/**
 * Created by Vicou96 on 10/02/2018.
 */

public class InSessionFragment extends Fragment implements View.OnClickListener, MenuItem.OnMenuItemClickListener {
    private TextView mTextView;
    private com.iut.gang.feigness.utils.HeartBeatView heartbeat;
    private Intent heartServiceIntent;
    private BroadcastReceiver brStep;
    private int cumulButton;
    private Button buttonMinus;
    private Button buttonPlus;
    private HeartListener heartlistener;
    private int oldHeartBeatInt;
    private WearableActionDrawerView actionDrawerViewDisconnect;


    public InSessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        this.cumulButton=0;
        this.oldHeartBeatInt=0;
        heartlistener=new HeartListener();
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.in_session_fragment, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.tvheartrate_in_session);
        actionDrawerViewDisconnect=rootView.findViewById(R.id.bottom_action_drawer);
        actionDrawerViewDisconnect.getController().closeDrawer();
        actionDrawerViewDisconnect.setOnMenuItemClickListener(this);

        buttonMinus=rootView.findViewById(R.id.buttonMinus_in_session);
        buttonPlus=rootView.findViewById(R.id.buttonPlus_in_session);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);

        Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
        pulse.setAnimationListener(heartlistener);


        heartbeat.startAnimation(pulse);


        heartServiceIntent = new Intent(getActivity(), com.iut.gang.feigness.utils.WearHeartEmulatorService.class);
        brStep=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int rate=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);
                System.out.println("Je recois coeur"+Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)));
                int pulse=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);

                heartlistener.heartRate=oldHeartBeatInt;

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

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }
}
