
package com.chaozhuo.observerdownload.observer.impl;

import android.os.SystemClock;

import com.chaozhuo.observerdownload.observer.base.Subject;

/**
 * Created by ryanhuencompany on 16-12-8.
 */

public class DownLoadingComponent extends Subject implements Runnable {
    @Override
    public void run() {
        for (int i = 0; i < 100; i++) {
            SystemClock.sleep(300);
            progressChange(i);
        }
    }

    private int progress;

    /**
     * 模拟正在下载，进度正在更新
     *
     * @param progress
     */
    public void progressChange(int progress) {
        this.progress = progress;
        System.out.println("后台下载进度" + progress);
        notifyAllObservers(progress);
    }
}
