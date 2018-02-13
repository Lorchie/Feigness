package com.iut.gang.feigness.fragments;

import android.app.Fragment;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.util.Log;
import android.view.KeyEvent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.view.inputmethod.EditorInfo;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.iut.gang.feigness.R;
import com.iut.gang.feigness.listener.TextChangedListener;

/**
 * Created by Victor on 07/02/2018.
 */

public class SessionFragment  extends Fragment {
    private EditText editTextCode;
    private EditText editTextPseudo;
    private com.iut.gang.feigness.utils.SessionView sessionView;
    private Intent heartServiceIntent;
    private BroadcastReceiver brStep;

    public SessionFragment() {
        // Required empty public constructor
    }


    @Override
    public View onCreateView(final LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        View rootView = inflater.inflate(R.layout.session_fragment, container, false);

        //GET editTexts
        editTextCode=(EditText) rootView.findViewById(R.id.inputCode);
        editTextPseudo=(EditText) rootView.findViewById(R.id.inputPseudo);
        final RelativeLayout input= (RelativeLayout) rootView.findViewById(R.id.rel_lay_input);
        final RelativeLayout check= (RelativeLayout) rootView.findViewById(R.id.rel_lay_check);


        editTextCode.setOnEditorActionListener(new TextView.OnEditorActionListener() {
            @Override
            public boolean onEditorAction(TextView v, int actionId, KeyEvent event) {
                boolean handled = false;
                if (actionId == EditorInfo.IME_ACTION_DONE && !editTextPseudo.getText().toString().equals("")) {
                    // TODO do something
                    Log.d("LOGGG", "onEditorAction: "+v.getText());
//                    handled = true;
                    input.setVisibility(View.INVISIBLE);
                    check.setVisibility(View.VISIBLE);
                     }
                return handled;
            }
        });
        //TODO verify if in session
        if(!isInSession()){

        }

//        mTextView = () rootView.findViewById(R.id.tvheartrate);
//        sessionView = rootView.findViewById(R.id.heartbeat);
//        final Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
//        sessionView.startAnimation(pulse);

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
