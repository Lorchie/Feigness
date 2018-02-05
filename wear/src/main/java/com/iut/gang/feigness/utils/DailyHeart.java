package com.iut.gang.feigness.utils;

/**
 * Created by Victor on 10/01/2018.
 */

public class DailyHeart {
     private static int heart =0;

    public static void updateSteps(int stepstaken){
        heart = stepstaken;
    }

    public static int getHeart(){
        return heart;
    }
}
