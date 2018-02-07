package com.iut.gang.common;

/**
 * Created by guill on 07/02/2018.
 */

public class UserSession {
    private int user_id;
    private int heartBeat;


    public UserSession(int user_id, int heartBeat)
    {
        this.user_id = user_id;
        this.heartBeat = heartBeat;
    }

    public int getUser_id() {
        return user_id;
    }

    public int getHeartBeat() {
        return heartBeat;
    }
}
