
package com.chaozhuo.observerdownload.ui;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ProgressBar;

import com.chaozhuo.observerdownload.R;
import com.chaozhuo.observerdownload.observer.base.Observer;
import com.chaozhuo.observerdownload.observer.impl.DownLoadingComponent;

public class MainActivity extends AppCompatActivity implements Observer, View.OnClickListener {

    private ProgressBar mProgressInLayoutItem;
    private Button mButtonToDialog;
    private Button mButtonDownloadStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
    }

    private void initView() {
        mProgressInLayoutItem = (ProgressBar) findViewById(R.id.progress_in_layout_item);
        mButtonToDialog = (Button) findViewById(R.id.button_to_dialog);
        mButtonDownloadStart = (Button) findViewById(R.id.button_download_start);
        mButtonToDialog.setOnClickListener(this);
        mButtonDownloadStart.setOnClickListener(this);
    }

    @Override
    public void updateProgress(int progress) {
        System.out.println("我是MainActivity,在更新进度" + progress);
        mProgressInLayoutItem.setProgress(progress);
    }

    @Override
    public void onClick(View view) {
        if (view.getId() == R.id.button_download_start) {
            DownLoadingComponent.attach(this);
            new Thread(new DownLoadingComponent()).start();
        } else if (view.getId() == R.id.button_to_dialog) {
            startActivity(new Intent(this, DetailDialogActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        DownLoadingComponent.dettach(this);
        super.onDestroy();
    }
}
