
package com.example.rxbusdownload;

import android.app.Activity;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;


import com.example.rxbusdownload.event.ProgressUpdateEvent;
import com.example.rxbusdownload.rxbus.RxBus;

import org.reactivestreams.Subscriber;
import org.reactivestreams.Subscription;

import butterknife.Bind;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import io.reactivex.functions.LongConsumer;

public class AppDialogActivity extends Activity {

    @Bind(R.id.image_in_layout_item)
    ImageView mImageInLayoutItem;
    @Bind(R.id.title_in_layout_item)
    TextView mTitleInLayoutItem;
    @Bind(R.id.progress_in_layout_item)
    ProgressBar mProgressInLayoutItem;
    @Bind(R.id.activity_app_dialog)
    RelativeLayout mActivityAppDialog;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_app_dialog);
        ButterKnife.bind(this);
        subscribeProgressUpdate();
    }

    private Subscription mSubscription;

    private void subscribeProgressUpdate() {
        RxBus.INSTANCE.toSubscriber(ProgressUpdateEvent.class)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeWith(new Subscriber<ProgressUpdateEvent>() {
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
                               }

                );
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mSubscription.cancel();
    }
}
