package com.iut.gang.feigness;

import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;

/**
 * Created by mahel on 05/02/2018.
 */

public class SessionController {
    FirebaseDatabase database = FirebaseDatabase.getInstance();
    DatabaseReference myRef = database.getReference("session");

    public SessionController()
    {

    }


    public void getAll(ValueEventListener valueEventListener)
    {
        myRef.addValueEventListener(valueEventListener);
    }
}
