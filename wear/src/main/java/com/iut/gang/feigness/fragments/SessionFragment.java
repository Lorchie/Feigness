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
import android.widget.Adapter;
import android.widget.AdapterView;
import android.widget.Button;
import android.widget.CheckedTextView;
import android.widget.EditText;
import android.widget.ImageView;
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
import com.iut.gang.feigness.listener.TextChangedListener;
import com.iut.gang.feigness.utils.DailyHeart;
import com.iut.gang.feigness.utils.heartService;

import java.util.Observable;
import java.util.Observer;

/**
 * Created by Victor on 07/02/2018.
 */

public class SessionFragment  extends Fragment {
    private EditText editTextCode;
    private EditText editTextPseudo;
    public static final String SESSION_MESSAGE = "message_session_";
    public static final String SESSION_CODE = "code_session_";
    public static final String SESSION_PSEUDO = "pseudo_session_";
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
        final View rootView = inflater.inflate(R.layout.session_fragment, container, false);

        //GET editTexts
        editTextCode=(EditText) rootView.findViewById(R.id.inputCode);

        editTextPseudo=(EditText) rootView.findViewById(R.id.inputPseudo);
        final RelativeLayout input= (RelativeLayout) rootView.findViewById(R.id.rel_lay_input);
        final RelativeLayout check= (RelativeLayout) rootView.findViewById(R.id.rel_lay_check);

        Button buttonJoinSession = rootView.findViewById(R.id.button2);
        buttonJoinSession.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                SessionController sess= new SessionController();
                String text=editTextCode.getText().toString();

                sess.findSessionWithCode(text, new ValueEventListener() {
                    @Override
                    public void onDataChange(DataSnapshot dataSnapshot) {
                        Session session= dataSnapshot.getValue(Session.class);
                        if(session == null){
                            System.out.println("Session introuvable");
                            Toast.makeText(rootView.getContext(),"Session introuvable!",
                                    Toast.LENGTH_SHORT).show();

                        }else {
                            System.out.println("Session trouvée !!!!!!!!!!!!!!!!!");
                            Intent intent = new Intent();
                            intent.setAction(SESSION_MESSAGE);
                            intent.putExtra(SESSION_CODE,editTextCode.getText().toString());
                            intent.putExtra(SESSION_PSEUDO,editTextPseudo.getText().toString());
//                            session.addUser(new UserSession());
                            rootView.getContext().sendBroadcast(intent);

                        }
                    }


                    @Override
                    public void onCancelled(DatabaseError databaseError) {

                    }
                });
//
            }
        });
//

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
