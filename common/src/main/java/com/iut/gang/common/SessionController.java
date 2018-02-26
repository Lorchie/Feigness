package com.iut.gang.common;

import android.app.Activity;

import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mahel on 05/02/2018.
 */

public class SessionController {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sessionsFitness");
    Activity activity;


    public SessionController(Activity activity)
    {

    }

    public Session newSession(String name)
    {
        String key = myRef.push().getKey();
        Session session = new Session(key, name);
        myRef.child(key).setValue(session);

        return session;
    }

    public void findSessionWithCode(String code, ValueEventListener valueEventListener)
    {
        Query query = myRef.orderByChild("code").equalTo(code);
        query.addValueEventListener(valueEventListener);
    }
    public void findUserInSessionWithCode(String code,String user_id,ValueEventListener valueEventListener)
    {
        Query query = myRef.child(code).orderByChild("users").equalTo(user_id);
        query.addValueEventListener(valueEventListener);
    }

    public String addUserToSession(String code, UserSession user)
    {

        String key = myRef.child("users").push().getKey();
        myRef.child(code).child("users").child(key).setValue(user);
        user.setId(key);
        return key;
    }
    public void removeUserFromSession(String code,String id_user){
        myRef.child(code).child("users").child(id_user).removeValue();
    }

    public void listenToSession(Session session, ValueEventListener valueEventListener)
    {
        myRef.child(session.getId()).addValueEventListener(valueEventListener);
    }
    public void listenToUserSession(Session session, ValueEventListener valueEventListener)
    {
        myRef.child(session.getId()).child("users").addValueEventListener(valueEventListener);
    }


}
