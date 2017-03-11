package com.example.rxbusdownload;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.example.rxbusdownload.event.ProgressUpdateEvent;
import com.example.rxbusdownload.rxbus.RxBus;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Bind(R.id.image_in_layout_item)
    ImageView mImageInLayoutItem;
    @Bind(R.id.title_in_layout_item)
    TextView mTitleInLayoutItem;
    @Bind(R.id.progress_in_layout_item)
    ProgressBar mProgressInLayoutItem;
    @Bind(R.id.activity_main)
    LinearLayout mActivityMain;
    @Bind(R.id.button_to_dialog)
    Button mButtonToDialog;
    @Bind(R.id.button_download_start)
    Button mButtonDownloadStart;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
        subscribeProgressUpdate();
        mButtonToDialog.setOnClickListener(this);
        mButtonDownloadStart.setOnClickListener(this);
    }

    private Subscription mSubscription;

    private void subscribeProgressUpdate() {
        RxBus.INSTANCE
                .toSubscriber(ProgressUpdateEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new Subscriber<ProgressUpdateEvent>() {
                    @Override
                    public void onSubscribe(Subscription s) {
                        mSubscription = s;
                        mSubscription.request(Long.MAX_VALUE);
                    }

                    @Override
                    public void onNext(ProgressUpdateEvent progressUpdateEvent) {
                        mProgressInLayoutItem.setProgress(progressUpdateEvent.getProgress());
                    }

                    @Override
                    public void onError(Throwable t) {

                    }

                    @Override
                    public void onComplete() {

                    }
                });
    }

    @Override
    public void onClick(View v) {
        if (v.getId() == R.id.button_download_start) {
            new DownLoadingComponent().start();
        } else if (v.getId() == R.id.button_to_dialog) {
            startActivity(new Intent(this, AppDialogActivity.class));
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.cancel();
    }
}
