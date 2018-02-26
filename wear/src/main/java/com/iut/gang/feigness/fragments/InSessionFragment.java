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
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.ValueEventListener;
import com.iut.gang.common.Session;
import com.iut.gang.common.SessionController;
import com.iut.gang.common.UserSession;
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
    private BroadcastReceiver brSession;
    private int cumulButton;
    private Button buttonMinus;
    private Button buttonPlus;
    private HeartListener heartlistener;
    private int oldHeartBeatInt;
    private WearableActionDrawerView actionDrawerViewDisconnect;
    private String codeSession;
    private String session_id;
    private String pseudo;
    private String user_id=null;
    private SessionController sess;
    public static final String IN_SESSION_MESSAGE = "in_message_session_";
    public static final String IN_SESSION_ID = "in_code_session_";
    public static final String IN_SESSION_USER_ID = "in_user_session_";



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
        sess = new SessionController();
        // Inflate the layout for this fragment
        final View rootView = inflater.inflate(R.layout.in_session_fragment, container, false);
        mTextView = (TextView) rootView.findViewById(R.id.tvheartrate_in_session);
//        actionDrawerViewDisconnect=rootView.findViewById(R.id.bottom_action_drawer);
//        actionDrawerViewDisconnect.getController().closeDrawer();
//        actionDrawerViewDisconnect.setOnMenuItemClickListener(this);

        buttonMinus=rootView.findViewById(R.id.buttonMinus_in_session);
        buttonPlus=rootView.findViewById(R.id.buttonPlus_in_session);
        buttonMinus.setOnClickListener(this);
        buttonPlus.setOnClickListener(this);

        Animation pulse = AnimationUtils.loadAnimation(this.getContext(), R.anim.pulse);
        pulse.setAnimationListener(heartlistener);
        brSession=new BroadcastReceiver() {


            @Override
            public void onReceive(Context context, Intent intent) {
                session_id=intent.getStringExtra(IN_SESSION_ID);
                System.out.println(session_id);

            }
        };
        this.user_id=sess.addUserToSession(this.codeSession,new UserSession("-1",0,this.pseudo));
        sess.findSessionWithCode(this.codeSession, new ValueEventListener() {
            @Override
            public void onDataChange(DataSnapshot dataSnapshot) {
                Session session= dataSnapshot.getValue(Session.class);
                if(session == null){
                    System.out.println("Session introuvable");
                    Toast.makeText(rootView.getContext(),"Session introuvable!",
                            Toast.LENGTH_SHORT).show();

                }else {
                    System.out.println("CA MARCHE ");
//                    sess.findUserInSessionWithCode(session,);
                    Intent intent = new Intent();
                    intent.setAction(IN_SESSION_MESSAGE);
                    intent.putExtra(IN_SESSION_ID,session.getId());
                    System.out.println("Session_id"+session.getId());
//                    intent.putExtra(SESSION_PSEUDO,editTextPseudo.getText().toString());
//                            session.addUser(new UserSession());
                    rootView.getContext().sendBroadcast(intent);
                }
            }


            @Override
            public void onCancelled(DatabaseError databaseError) {

            }
        });

//        heartbeat.startAnimation(pulse);


        heartServiceIntent = new Intent(getActivity(), com.iut.gang.feigness.utils.WearHeartEmulatorService.class);
        brStep=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                int rate=intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0);
                System.out.println("Je recois coeur yoloo"+Integer.toString(intent.getIntExtra(com.iut.gang.feigness.utils.heartService.HEART_COUNT_VALUE,0)));
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
        this.getContext().registerReceiver(brSession,new IntentFilter(IN_SESSION_MESSAGE));
        super.onResume();
    }

    @Override
    public void onPause() {
        this.getContext().stopService(heartServiceIntent);
        this.getContext().unregisterReceiver(brStep);
        this.getContext().unregisterReceiver(brSession);
        super.onPause();
    }



    @Override
    public void onClick(View v) {
        //do what you want to do when button is clicked
        switch (v.getId()) {
            case R.id.buttonMinus_in_session:
                this.cumulButton-=5;
                break;
            case R.id.buttonPlus_in_session:
                this.cumulButton+=5;
                break;
        }
    }

    @Override
    public boolean onMenuItemClick(MenuItem menuItem) {
        return false;
    }

    public void setSessioncode(String code){
        this.codeSession=code;
    }

    public void setSessionpseudo(String sessionpseudo) {
        this.pseudo = sessionpseudo;
    }

    public void removeUser(){
        if(user_id!=null){
            sess.removeUserFromSession(this.codeSession,this.user_id);
            this.user_id=null;
        }

    }
}
