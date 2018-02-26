package com.iut.gang.feigness;

import android.app.FragmentManager;
import android.content.BroadcastReceiver;
import android.content.Context;
import android.content.Intent;
import android.content.IntentFilter;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.support.wear.widget.drawer.WearableActionDrawerView;
import android.support.wear.widget.drawer.WearableNavigationDrawerView;
import android.support.wearable.activity.WearableActivity;
import android.util.Log;
import android.view.MenuItem;
import android.view.View;

import com.iut.gang.feigness.fragments.HeartRateFragment;
import com.iut.gang.feigness.fragments.InSessionFragment;
import com.iut.gang.feigness.fragments.SessionFragment;
import com.iut.gang.feigness.utils.DrawerItem;
import com.iut.gang.feigness.utils.SessionView;

import java.util.ArrayList;

public class MainActivity extends WearableActivity implements
        WearableNavigationDrawerView.OnItemSelectedListener {

    public final static int ITEM_MENU_HEART_RATE=0;
    private static final int ITEM_MENU_SESSION = 1;

    private WearableNavigationDrawerView mWearableNavigationDrawer;
    private ArrayList<DrawerItem> drawer_itemArrayList;
    private int mSelectedScreen;
    private BroadcastReceiver br;
    private FragmentManager fragmentManager;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        drawer_itemArrayList = initializeScreenSystem();
        mSelectedScreen = 0;

        HeartRateFragment hrf= new HeartRateFragment();
        fragmentManager = getFragmentManager();
        fragmentManager.beginTransaction().replace(R.id.content_frame, hrf).commit();

        // Top Navigation Drawer
        mWearableNavigationDrawer =
                findViewById(R.id.top_navigation_drawer);
        mWearableNavigationDrawer.setAdapter(
                new WearableNavigationDrawerView.WearableNavigationDrawerAdapter(){

                    @Override
                    public int getCount() {
                        return drawer_itemArrayList.size();
                    }

                    @Override
                    public String getItemText(int pos) {
                        return drawer_itemArrayList.get(pos).getName();
                    }

                    @Override
                    public Drawable getItemDrawable(int pos) {
                        String navigationIcon = drawer_itemArrayList.get(pos).getNavigationIcon();

                        int drawableNavigationIconId =
                                getResources().getIdentifier(navigationIcon, "drawable", getPackageName());
                        return getDrawable(drawableNavigationIconId);
                    }
                });
        // Peeks navigation drawer on the top.
        mWearableNavigationDrawer.getController().peekDrawer();
        mWearableNavigationDrawer.addOnItemSelectedListener(this);

        this.br=new BroadcastReceiver() {
            @Override
            public void onReceive(Context context, Intent intent) {
                String code=intent.getStringExtra(SessionFragment.SESSION_CODE);
                String pseudo=intent.getStringExtra(SessionFragment.SESSION_PSEUDO);
                InSessionFragment inSessionFragment=new InSessionFragment();
                inSessionFragment.setSessioncode(code);
                inSessionFragment.setSessionpseudo(pseudo);
                   fragmentManager.beginTransaction().replace(R.id.content_frame,inSessionFragment).commit();
            }
        };
    }

    private ArrayList<DrawerItem> initializeScreenSystem() {
        ArrayList<DrawerItem> screens = new ArrayList<DrawerItem>();
        String[] FragmentArrayNames = getResources().getStringArray(R.array.screens);

        for (int i = 0; i < FragmentArrayNames.length; i++) {
            String fragmentName = FragmentArrayNames[i];
            int FragmentResourceId =
                    getResources().getIdentifier(fragmentName, "array", getPackageName());
            String[] fragmentInformation = getResources().getStringArray(FragmentResourceId);

            screens.add(new DrawerItem(
                    fragmentInformation[0],   // Name
                    fragmentInformation[1])); // Icon
        }

        return screens;
    }

    @Override
    public void onItemSelected(int position) {
        mSelectedScreen = position;

        switch (mSelectedScreen) {
            case ITEM_MENU_SESSION:
              SessionFragment sessionFrag = new SessionFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, sessionFrag)
                        .commit();
                break;
            case ITEM_MENU_HEART_RATE:
                if (getFragmentManager().getPrimaryNavigationFragment().getClass()==InSessionFragment.class){
                    InSessionFragment frag= (InSessionFragment) getFragmentManager().getPrimaryNavigationFragment();
                    frag.removeUser();
                }
                HeartRateFragment sectionFragment = new HeartRateFragment();
                getFragmentManager()
                        .beginTransaction()
                        .replace(R.id.content_frame, sectionFragment)
                        .commit();
                break;
        }
    }
    public void onResume() {

        this.registerReceiver(br,new IntentFilter(SessionFragment.SESSION_MESSAGE));
        super.onResume();
    }

    @Override
    public void onPause() {
        this.unregisterReceiver(br);
        super.onPause();
    }


//    @Override
//    public AmbientMode.AmbientCallback getAmbientCallback() {
//        return null;
//    }

}

