package com.example.rxbusdownload;

import android.os.SystemClock;

import com.example.rxbusdownload.event.ProgressUpdateEvent;
import com.example.rxbusdownload.rxbus.RxBus;


/**
 * Created by ryanhuencompany on 16-12-8.
 */

public class DownLoadingComponent extends Thread {
    @Override
    public void run() {
        super.run();
        for (int i = 0; i < 100; i++) {
            SystemClock.sleep(300);
            RxBus.INSTANCE.postEvent(new ProgressUpdateEvent(i));
        }
    }
}
