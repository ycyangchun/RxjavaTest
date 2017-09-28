package com.example.yc.rxjavatest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Subscriber;
import rx.functions.Func1;

/**
 * Transforming Observables(Observable的转换操作符)，比如：observable.map()、observable.flatMap()、observable.buffer()等等
 */

public class RxMap extends Activity {
    @Bind(R.id.textView2)
    TextView textView2;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxmap);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.button7)
    void onClick7Button() {
        mothod_1();
    }

    void mothod_1() {
        Observable.just(1, 2, 3, 4, 5, 6)
//                .filter(new Func1<Integer, Boolean>() {
//                    @Override
//                    public Boolean call(Integer value) {
//                        return value % 2 == 1;
//                    }
//                })
                .map(new Func1<Integer, Double>() {
                    @Override
                    public Double call(Integer value) {
                        return Math.sqrt(value);
                    }
                })
                .subscribe(new Subscriber<Double>() {
                    @Override
                    public void onStart() {
                        textView2.append("\nonStart\n ");
                        super.onStart();
                    }

                    @Override
                    public void onCompleted() {
                        textView2.append("onCompleted <===============");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(Double s) {
                        textView2.append(s.toString()+"\n");
                    }
                });
    }
}
