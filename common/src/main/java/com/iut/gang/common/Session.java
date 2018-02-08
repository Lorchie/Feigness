package com.iut.gang.common;

import java.util.ArrayList;

/**
 * Created by guill on 07/02/2018.
 */

public class Session {

    public Integer id;
    public String name;
    private ArrayList<UserSession> userSessions;

    public Session(int session_id, String name)
    {
        this.id = session_id;
        this.name = name;
    }

    public int getId() {
        return id;
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
