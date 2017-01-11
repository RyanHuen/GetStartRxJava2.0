
package com.ryanhuen.rxjava2demo.base;

import com.ryanhuen.rxjava2demo.R;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseImplActivity extends AppCompatActivity {
    public static final String TAG = BaseImplActivity.class.getName();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_impl);
        doRxjavaWork();
    }

    private void doRxjavaWork() {
        // 创建一个上游的Observable（被观察者）
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e/* 事件发射器 */) throws Exception {
                // 发射事件
                Log.d(TAG, "上游调用onNext1");
                e.onNext("事件1");
                Log.d(TAG, "上游调用onNext2");
                e.onNext("事件2");
                Log.d(TAG, "上游调用onNext3");
                e.onNext("事件3");
                Log.d(TAG, "上游调用onComplete");
                e.onComplete();
            }
        });

        // 创建一个下游Observer（观察者）
        Observer<String> observer = new Observer<String>() {
            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "onSubscribe: ");
            }

            @Override
            public void onNext(String string) {
                Log.d(TAG, "下游onNext: " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游onError: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "下游onComplete: ");
            }
        };
        // 上下游建立连接（观察者被观察者建立联系）
        observable.subscribe(observer);
    }

}
