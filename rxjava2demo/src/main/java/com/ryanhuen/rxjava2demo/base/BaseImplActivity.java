
package com.ryanhuen.rxjava2demo.base;

import com.ryanhuen.rxjava2demo.R;
import com.ryanhuen.rxjava2demo.utils.LogUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.LinearLayout;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class BaseImplActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = BaseImplActivity.class.getName();

    private LinearLayout mActivityBaseImpl;
    private Button mRockAndLaunch;
    private TextView mConsoleOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_base_impl);
        mActivityBaseImpl = (LinearLayout) findViewById(R.id.activity_base_impl);
        mRockAndLaunch = (Button) findViewById(R.id.rock_and_launch);
        mConsoleOutput = (TextView) findViewById(R.id.console_output);
        mRockAndLaunch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        doRxjavaWork();
    }

    private void doRxjavaWork() {
        // 创建一个上游的Observable（被观察者）
        Observable<String> observable = Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e/* 事件发射器 */) throws Exception {
                Log.d(TAG, "上游 调用onNext发射：事件1");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上游 调用onNext发射：事件1");
                // 发射事件
                e.onNext("事件1");

                Log.d(TAG, "上游 调用onNext发射：事件2");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上游 调用onNext发射：事件2");
                // 发射事件
                e.onNext("事件2");

                Log.d(TAG, "上游 调用onNext发射：事件3");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上游 调用onNext发射：事件3");
                // 发射事件
                e.onNext("事件3");

                e.onComplete();
            }
        });

        // 创建一个下游Observer（观察者）
        Observer<String> observer = new Observer<String>() {

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "上下游已连接: ");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上下游已连接");
            }

            @Override
            public void onNext(String string) {
                Log.d(TAG, "下游 onNext: " + string);
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onNext 接收到: " + string);
            }

            @Override
            public void onError(Throwable e) {
                Log.d(TAG, "下游 onError: " + e.getMessage());
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onError 接收到: " + e.getMessage());
            }

            @Override
            public void onComplete() {
                Log.d(TAG, "下游 onComplete: ");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onComplete 订阅完成: ");
            }
        };
        // 上下游建立连接（观察者被观察者建立联系）
        observable.subscribe(observer);
    }
}
