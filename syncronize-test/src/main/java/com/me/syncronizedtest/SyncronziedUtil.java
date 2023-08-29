package com.me.syncronizedtest;

import java.util.HashMap;
import java.util.Map;

public class SyncronziedUtil {

    private static final Object lock = new Object();
    private static final int waitTime = 100;
    private static int i1 = 0;
    private static int i2 = 0;
    private static int i3 = 0;

    public static void simpleFunctionWithoutLock() {
        i1++;
    }

    public static void simpleFunctionWithLock() {
        synchronized (lock) {
            i2++;
        }
    }

    public static void simpleFunctionWithLockSleep() throws InterruptedException {
        synchronized (lock) {
            Thread.sleep(waitTime);
            i3++;
        }
    }

    private static Map<String, String> globalMap = new HashMap<>();
    public static void putWithoutLock(Map<String, String> map) {
        globalMap = map;
    }

    public static void putWithLock(Map<String, String> map) {
        synchronized (lock) {
            globalMap = map;
        }
    }

    public static Map<String, String> getWithoutLock() {
        return globalMap;
    }

    public static Map<String, String> getWithLock() {
        Map<String, String> retValue;
        synchronized (lock) {
            retValue = globalMap;
        }
        return retValue;
    }

}
