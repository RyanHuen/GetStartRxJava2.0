
package com.chaozhuo.observerdownload.observer.base;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by RyanHuen on 17-1-11.
 */

public abstract class Subject {
    /**
     * 用来保存注册的观察者对象
     */
    public static List<Observer> OBSERVERS_LIST = new ArrayList<>();

    /**
     * 注册观察者对象
     * 
     * @param observer 观察者对象
     */
    public static void attach(Observer observer) {
        OBSERVERS_LIST.add(observer);
    }

    /**
     * 删除观察者对象
     * 
     * @param observer 观察者对象
     */
    public static void dettach(Observer observer) {
        OBSERVERS_LIST.remove(observer);
    }

    /**
     * 通知所有注册的观察者对象
     */
    public void notifyAllObservers(int progress) {
        for (int i = 0; i < OBSERVERS_LIST.size(); i++) {
            Observer observer = OBSERVERS_LIST.get(i);
            observer.updateProgress(progress);
        }
    }
}
