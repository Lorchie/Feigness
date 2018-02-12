package com.iut.gang.common;

import java.util.ArrayList;

/**
 * Created by guill on 07/02/2018.
 */

public class Session {

    public String uid;
    public String name;
    private ArrayList<UserSession> userSessions;

    //Leave empty
    public Session()
    {

    }

    public Session(String uid, String name)
    {
        this.uid = uid;
        this.name = name;
    }

    public String getId() {
        return uid;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public void addUser(UserSession userSession)
    {
        this.userSessions.add(userSession);
    }
}
