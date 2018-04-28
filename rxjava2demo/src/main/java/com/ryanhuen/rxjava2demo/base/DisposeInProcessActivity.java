
package com.ryanhuen.rxjava2demo.base;

import com.ryanhuen.rxjava2demo.R;
import com.ryanhuen.rxjava2demo.utils.LogUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.ObservableEmitter;
import io.reactivex.ObservableOnSubscribe;
import io.reactivex.Observer;
import io.reactivex.disposables.CompositeDisposable;
import io.reactivex.disposables.Disposable;

public class DisposeInProcessActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = DisposeInProcessActivity.class.getName();
    private Button mRockAndLaunch;
    private TextView mConsoleOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_dispose_in_process);
        mRockAndLaunch = (Button) findViewById(R.id.rock_and_launch);
        mConsoleOutput = (TextView) findViewById(R.id.console_output);
        mRockAndLaunch.setOnClickListener(this);
    }

    @Override
    public void onClick(View v) {
        doRxjavaWork();
    }

    private void doRxjavaWork() {
        Observable.create(new ObservableOnSubscribe<String>() {
            @Override
            public void subscribe(ObservableEmitter<String> e) throws Exception {
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

                Log.d(TAG, "上游 调用onNext发射：事件4");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上游 调用onNext发射：事件4");
                // 发射事件
                e.onNext("事件4");

                e.onComplete();

            }
        }).subscribe(new Observer<String>() {
            Disposable mDisposable;

            @Override
            public void onSubscribe(Disposable d) {
                Log.d(TAG, "上下游已连接: ");
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "上下游已连接");
                mDisposable = d;
                CompositeDisposable compositeDisposable = new CompositeDisposable();
                compositeDisposable.add(d);
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "拦河大坝已建立");
            }

            @Override
            public void onNext(String string) {
                Log.d(TAG, "下游 onNext: " + string);
                LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onNext 接收到: " + string);
                if (string.equals("事件2")) {
                    mDisposable.dispose();
                    Log.d(TAG, "拦河大坝关上水闸");
                }
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
        });
    }

}
