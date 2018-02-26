package com.iut.gang.common;

/**
 * Created by guill on 07/02/2018.
 */

public class UserSession {
    private String user_id;
    private int heartBeat;
    private String pseudo;


    public UserSession(String user_id, int heartBeat,String pseudo)
    {
        this.user_id = user_id;
        this.heartBeat = heartBeat;
        this.pseudo=pseudo;
    }

    public String getUser_id() {
        return user_id;
    }

    public int getHeartBeat() {
        return heartBeat;
    }

    public String getPseudo() { return pseudo; }

    public void setId(String id) {
        this.user_id = id;
    }
}
