
package com.chaozhuo.observerdownload.observer.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanHuen on 17-1-11.
 */

public abstract class Subject {
    public static List<Observer> OBSERVERS_LIST = new ArrayList<>();

    public static void attach(Observer observer) {
        OBSERVERS_LIST.add(observer);
    }

    public static void dettach(Observer observer) {
        OBSERVERS_LIST.remove(observer);
    }

    public void notifyAllObservers(int progress) {
        for (int i = 0; i < OBSERVERS_LIST.size(); i++) {
            Observer observer = OBSERVERS_LIST.get(i);
            observer.updateProgress(progress);
        }
    }
}
