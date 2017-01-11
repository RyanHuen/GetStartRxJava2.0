
package com.chaozhuo.observerdownload.ui;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ProgressBar;

import com.chaozhuo.observerdownload.R;
import com.chaozhuo.observerdownload.observer.base.Observer;
import com.chaozhuo.observerdownload.observer.impl.DownLoadingComponent;

public class DetailDialogActivity extends Activity implements Observer {

    private ProgressBar mProgressInLayoutItem;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_detail_dialog);
        initView();
        DownLoadingComponent.attach(this);
    }

    private void initView() {
        mProgressInLayoutItem = (ProgressBar) findViewById(R.id.progress_in_layout_item);
    }

    @Override
    public void updateProgress(int progress) {
        System.out.println("我是DialogActivity，在更新进度" + progress);
        mProgressInLayoutItem.setProgress(progress);
    }

    @Override
    protected void onDestroy() {
        DownLoadingComponent.dettach(this);
        super.onDestroy();
    }
}
