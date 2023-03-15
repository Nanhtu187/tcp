package com.facenet.mina.utils;


/**
 * @author: nanhtu
 * Date created: 20/02/2023
 */

public class SleepWell {

    /**
     *
     * @param timeInMS
     */
    public static void sleepSilently(int timeInMS) {
        try {
            Thread.sleep(timeInMS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
