package com.iut.gang.feigness.fragments;

import android.annotation.SuppressLint;
import android.content.Context;
import android.net.Uri;
import android.support.v4.app.Fragment;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.iut.gang.feigness.R;

 @SuppressLint("ValidFragment")
 public class Fragment_session_create_log extends Fragment {
     @Override
     public View onCreateView(LayoutInflater inflater, ViewGroup container,
                              Bundle savedInstanceState) {
         ViewGroup rootView = (ViewGroup) inflater.inflate(
                 R.layout.fragment_session_create_log, container, false);


         return rootView;
     }
}
