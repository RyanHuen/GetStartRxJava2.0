
package com.ryanhuen.rxjava2demo;

import com.ryanhuen.Person;
import com.ryanhuen.rxjava2demo.base.BaseImplActivity;
import com.ryanhuen.rxjava2demo.base.DisposeInProcessActivity;
import com.ryanhuen.rxjava2demo.base.IterableEmitterActivity;
import com.ryanhuen.rxjava2demo.base.JustEmitterActivity;
import com.ryanhuen.rxjava2demo.base.LineHandleActivity;
import com.ryanhuen.rxjava2demo.flowable.BaseFlowableActivity;
import com.ryanhuen.rxjava2demo.flowable.UnlimitPostActivity;
import com.ryanhuen.rxjava2demo.operators.ConcatMapOperatorActivity;
import com.ryanhuen.rxjava2demo.operators.FlatMapOperatorActivity;
import com.ryanhuen.rxjava2demo.operators.MapOperatorActivity;
import com.ryanhuen.rxjava2demo.operators.ZipOperatorActivity;
import com.ryanhuen.rxjava2demo.thread_control.ChangeThreadActivity;
import com.ryanhuen.rxjava2demo.thread_control.DefaultThreadActivity;
import com.ryanhuen.rxjava2demo.thread_control.MultiThreadChangeActivity;
import com.ryanhuen.rxjava2demo.usebus.UseBusActivity;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.widget.Button;

public class MainActivity extends AppCompatActivity implements View.OnClickListener {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();
        System.out.println(Person.INSTANCE.toString());
    }

    private void initView() {
        Button baseImpl = (Button) findViewById(R.id.base_impl);
        baseImpl.setOnClickListener(this);
        Button lineHandle = (Button) findViewById(R.id.line_handle);
        lineHandle.setOnClickListener(this);
        Button disposeInProcess = (Button) findViewById(R.id.dispose_in_process);
        disposeInProcess.setOnClickListener(this);
        Button justEmitter = (Button) findViewById(R.id.just_emitter);
        justEmitter.setOnClickListener(this);
        Button fromIterableEmitter = (Button) findViewById(R.id.from_iterable_emitter);
        fromIterableEmitter.setOnClickListener(this);
        Button defaultThread = (Button) findViewById(R.id.default_thread);
        defaultThread.setOnClickListener(this);
        Button changeThread = (Button) findViewById(R.id.change_thread);
        changeThread.setOnClickListener(this);
        Button multiThreadChange = (Button) findViewById(R.id.multi_thread_change);
        multiThreadChange.setOnClickListener(this);
        Button mapOperator = (Button) findViewById(R.id.map_operator);
        mapOperator.setOnClickListener(this);
        Button flatMapOperator = (Button) findViewById(R.id.flat_map_operator);
        flatMapOperator.setOnClickListener(this);
        Button concatMapOperator = (Button) findViewById(R.id.concat_map_operator);
        concatMapOperator.setOnClickListener(this);
        Button zipOperator = (Button) findViewById(R.id.zip_operator);
        zipOperator.setOnClickListener(this);
        Button unlimitPostOperator = (Button) findViewById(R.id.unlimit_post_operator);
        unlimitPostOperator.setOnClickListener(this);
        Button baseFlowable = (Button) findViewById(R.id.base_flowable);
        baseFlowable.setOnClickListener(this);
        Button useBus = (Button) findViewById(R.id.use_bus);
        useBus.setOnClickListener(this);
    }

    @Override
    public void onClick(View view) {
        switch (view.getId()) {
            /** 基本实现 */
            case R.id.base_impl:
                startActivity(new Intent(this, BaseImplActivity.class));
                break;
            /** 链式调用的实现 */
            case R.id.line_handle:
                startActivity(new Intent(this, LineHandleActivity.class));
                break;
            /** 中途切断上下游订阅 */
            case R.id.dispose_in_process:
                startActivity(new Intent(this, DisposeInProcessActivity.class));
                break;
            /** 使用just方式发射事件 */
            case R.id.just_emitter:
                startActivity(new Intent(this, JustEmitterActivity.class));
                break;
            case R.id.from_iterable_emitter:
                startActivity(new Intent(this, IterableEmitterActivity.class));
                break;
            /** 不指定线程（默认线程） */
            case R.id.default_thread:
                startActivity(new Intent(this, DefaultThreadActivity.class));
                break;
            /** 切换线程的基本使用 */
            case R.id.change_thread:
                startActivity(new Intent(this, ChangeThreadActivity.class));
                break;
            /** 多次切换线程 */
            case R.id.multi_thread_change:
                startActivity(new Intent(this, MultiThreadChangeActivity.class));
                break;
            /** map操作符 */
            case R.id.map_operator:
                startActivity(new Intent(this, MapOperatorActivity.class));
                break;
            /** flatMap操作符 (事件无序) */
            case R.id.flat_map_operator:
                startActivity(new Intent(this, FlatMapOperatorActivity.class));
                break;
            /** concatMap操作符(事件有序) */
            case R.id.concat_map_operator:
                startActivity(new Intent(this, ConcatMapOperatorActivity.class));
                break;
            /** zip操作符(两个上游事件的合并) */
            case R.id.zip_operator:
                startActivity(new Intent(this, ZipOperatorActivity.class));
                break;
            /** 不适用BackPressure的无限事件发送情况,注意查看内存使用情况 */
            case R.id.unlimit_post_operator:
                startActivity(new Intent(this, UnlimitPostActivity.class));
                break;
            /** Flowable的基本使用 */
            case R.id.base_flowable:
                startActivity(new Intent(this, BaseFlowableActivity.class));
                break;
            /** 使用RxBus */
            case R.id.use_bus:
                startActivity(new Intent(this, UseBusActivity.class));
                break;
        }

    }
}
