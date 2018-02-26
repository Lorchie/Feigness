package com.iut.gang.feigness.listener;

import android.util.Log;
import android.view.animation.Animation;

/**
 * Created by Vicou96 on 07/02/2018.
 */

public class HeartListener implements Animation.AnimationListener {
    public int heartRate=75;
    @Override
    public void onAnimationStart(Animation animation) {
        Log.d("startani", "onAsqdnRepeat: "+heartRate);
        System.out.println("aaaaaaaaaaaa "+heartRate);
    }

    @Override
    public void onAnimationEnd(Animation animation) {
        Log.d("endani", "onAnimatqqqqqqqionRepeat: "+heartRate);
        System.out.println("bbbbbbbbbb "+heartRate+" "+animation.hasEnded()+" "+animation.getFillAfter());
//        animation.get
//            animation.restrictDuration(1000);
            animation.cancel();
            animation.reset();
            animation.start();
//        animation.setDuration((60*1000) /heartRate);
//        animation.hasEnded()

    }

    @Override
    public void onAnimationRepeat(Animation animation) {
//        Log.d("yolo", "hhhh: "+heartRate);
        System.out.println("cccccccc "+heartRate);
//        animation.setDuration(heartRate);
    }
}
