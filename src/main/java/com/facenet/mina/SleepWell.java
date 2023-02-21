package com.facenet.mina;

public class SleepWell {
    public static void sleepSilently(int timeInMS) {
        try {
            Thread.sleep(timeInMS);
        } catch (InterruptedException e) {
            throw new RuntimeException(e);
        }
    }
}
