
package com.ryanhuen.rxjava2demo.base;

import java.util.ArrayList;
import java.util.List;

import com.ryanhuen.rxjava2demo.R;
import com.ryanhuen.rxjava2demo.utils.LogUtils;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import io.reactivex.Observable;
import io.reactivex.Observer;
import io.reactivex.disposables.Disposable;

public class IterableEmitterActivity extends AppCompatActivity implements View.OnClickListener {
    public static final String TAG = IterableEmitterActivity.class.getName();

    private Button mRockAndLaunch;
    private TextView mConsoleOutput;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_iterable_emitter);

        mRockAndLaunch = (Button) findViewById(R.id.rock_and_launch);
        mConsoleOutput = (TextView) findViewById(R.id.console_output);
        mRockAndLaunch.setOnClickListener(this);

    }

    @Override
    public void onClick(View v) {
        doRxJavaWork();
    }

    private void doRxJavaWork() {
        List<String> strings = new ArrayList<>();
        for (int i = 0; i < 10; i++) {
            strings.add("事件" + i);
        }
        Observable.fromIterable(strings)
                .subscribe(new Observer<String>() {
                    @Override
                    public void onSubscribe(Disposable d) {
                        LogUtils.log2ConsoleOuputView(mConsoleOutput, "上下游已连接");
                        Log.d(TAG, "上下游已连接: ");
                    }

                    @Override
                    public void onNext(String string) {
                        LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onNext 接收到: " + string);
                        Log.d(TAG, "下游 onNext 接收到: " + string);
                    }

                    @Override
                    public void onError(Throwable e) {
                        LogUtils.log2ConsoleOuputView(mConsoleOutput,
                                "下游 onError 接收到: " + e.getMessage());
                        Log.d(TAG, "下游 onError: ");
                    }

                    @Override
                    public void onComplete() {
                        Log.d(TAG, "下游 onComplete 订阅完成: ");
                        LogUtils.log2ConsoleOuputView(mConsoleOutput, "下游 onComplete 订阅完成: ");
                    }
                });

    }

}
