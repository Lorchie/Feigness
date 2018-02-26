package com.iut.gang.common;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.Random;

/**
 * Created by guill on 07/02/2018.
 */

public class Session implements Serializable {

    public String uid;
    public String name;
    public String code;
    private ArrayList<UserSession> userSessions;

    //Leave empty
    public Session()
    {

    }

    public Session(String uid, String name)
    {
        this.uid = uid;
        this.name = name;
        Random random = new Random();
        this.code = String.format("%04d", random.nextInt(999));
        userSessions = new ArrayList<UserSession>();
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

    public String getCode() {
        return code;
    }
}
