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

    public void findSessionWithCode(String code, ChildEventListener childEventListener)
    {
        Query query = myRef.orderByChild("code").equalTo(code);
        query.addChildEventListener(childEventListener);

    }

    public void addUserToSession(Session session, UserSession user)
    {
        session.addUser(user);
        String key = myRef.child("users").push().getKey();
        myRef.child(session.getId()).child("users").child(key).setValue(user);
    }

    public void listenToSession(Session session, ValueEventListener valueEventListener)
    {
        myRef.child(session.getId()).addValueEventListener(valueEventListener);
    }
}
