package com.iut.gang.feigness;

/**
 * Created by mahel on 05/02/2018.
 */

public class Session {
    public Integer id;
    public String name;

    public Session()
    {

    }

    public Session(Integer id, String name)
    {
        this.id = id;
        this.name = name;
    }

    public String getName() {
        return name;
    }
}
