package com.iut.gang.feigness;

import android.net.Uri;
import android.os.Bundle;
import android.support.annotation.NonNull;
import android.support.design.widget.BottomNavigationView;
import android.support.v7.app.AppCompatActivity;
import android.view.MenuItem;
import android.widget.TextView;

import com.iut.gang.common.SessionController;
import com.iut.gang.feigness.fragments.Fragment_profil;
import com.iut.gang.feigness.fragments.Fragment_session_user;

public class MainActivity extends AppCompatActivity implements Fragment_profil.OnFragmentInteractionListener, Fragment_session_user.OnFragmentInteractionListener {

    private TextView mTextMessage;
    android.support.v4.app.FragmentTransaction fragmentManager;



    private BottomNavigationView.OnNavigationItemSelectedListener mOnNavigationItemSelectedListener

            = new BottomNavigationView.OnNavigationItemSelectedListener() {

        @Override
        public boolean onNavigationItemSelected(@NonNull MenuItem item) {

            switch (item.getItemId()) {
                case R.id.navigation_session:
                    fragmentManager = getSupportFragmentManager().beginTransaction();
                    fragmentManager.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmentManager.replace(R.id.home_layout_container, new Fragment_session_user()).commit();
                    return true;
                case R.id.navigation_profil:
                    fragmentManager =  getSupportFragmentManager().beginTransaction();
                    fragmentManager.setCustomAnimations(R.anim.slide_in_left, R.anim.slide_out_right);
                    fragmentManager.replace(R.id.home_layout_container, new Fragment_profil()).commit();
                    return true;
            }
            return false;
        }
    };

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        //Session s = new Session(1, " dofi");

        SessionController sessionController = new SessionController();

        


        BottomNavigationView navigation = findViewById(R.id.navigation);
        navigation.setOnNavigationItemSelectedListener(mOnNavigationItemSelectedListener);
    }


    @Override
    public void onFragmentInteraction(Uri uri) {

    }
}
