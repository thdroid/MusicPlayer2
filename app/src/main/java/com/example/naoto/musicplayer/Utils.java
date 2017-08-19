package com.example.naoto.musicplayer;

import java.util.concurrent.TimeUnit;

/**
 * Created by Naoto on 2017/08/07.
 */

public class Utils {
    public static int toMin(int times){
        return (int)TimeUnit.MILLISECONDS.toMinutes(times);
    }

    public static int toSec(int times){
       return (int)(TimeUnit.MILLISECONDS.toSeconds(times) -
                TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.
                        toMinutes(times)));
    }
}
