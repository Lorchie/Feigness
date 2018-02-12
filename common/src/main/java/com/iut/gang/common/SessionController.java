package com.iut.gang.common;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.ChildEventListener;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.iut.gang.common.Session;

/**
 * Created by mahel on 05/02/2018.
 */

public class SessionController {

    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("sessionsFitness");


    public SessionController()
    {

    }

    public void addSession()
    {
        String key = myRef.push().getKey();
        Session session = new Session(key, "5415");
        myRef.child("one").setValue(session);
    }

    public void listenToSession(ChildEventListener valueEventListener)
    {
        myRef.addChildEventListener(valueEventListener);
    }


}
