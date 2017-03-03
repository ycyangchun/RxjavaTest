package com.example.yc.rxjavatest;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

import butterknife.Bind;
import butterknife.ButterKnife;
import butterknife.OnClick;
import rx.Observable;
import rx.Observable.OnSubscribe;
import rx.Observer;
import rx.Producer;
import rx.Subscriber;
import rx.Subscription;
import rx.functions.Action0;
import rx.functions.Action1;

/**
 * Created by Administrator on 2017/3/3.
 */

public class RxCreate extends Activity {
    @Bind(R.id.textView)
    TextView textView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxcreate);
        ButterKnife.bind(this);
        textView.append("");
    }

    @OnClick(R.id.button6)
    void onClick6Button(){
//        method_1(); // create
//        method_2();//from
//        method_3();//just
        method_4();
    }

    void method_1(){ // create  创建
        Observable observable = Observable.create(new OnSubscribe<String>() {
            @Override
            public void call(Subscriber<? super String> subscriber) {
                subscriber.onNext("111");
                subscriber.onNext("222");
                subscriber.onNext("333");
                subscriber.onNext("444");
                subscriber.onNext("555");
                subscriber.onCompleted();
            }
        });


        Observer obsercer = new Observer() {
            @Override
            public void onCompleted() {

            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(Object o) {

            }
        };
        //
        Subscription subscription = new Subscription() {
            @Override
            public void unsubscribe() {

            }

            @Override
            public boolean isUnsubscribed() {
                return false;
            }
        };
        // Subscriber<T> implements Observer<T>, Subscription
        // Subscriber 实现了 Observer 的抽象类：Subscriber。 Subscriber 对 Observer 接口进行了一些扩展，但他们的基本使用方式是完全一样的
        Subscriber<String> subscriber = new Subscriber<String>() {
            @Override
            public void onStart() {
                textView.append("\n"+"onStart() ==> "+"\n");
                super.onStart();
            }

            @Override
            public void setProducer(Producer p) {
                textView.append("setProducer() ==> ");
                super.setProducer(p);
            }

            @Override
            public void onCompleted() {
                textView.append("onCompleted() <== ");
            }

            @Override
            public void onError(Throwable e) {

            }

            @Override
            public void onNext(String s) {
                textView.append("onNext() == "+s+"\n");
            }

        };

        observable.subscribe(subscriber);

    }

    void method_2(){// from(T[])
        String arr[] = {"1111","22222","33333","44444","555555","666666"};
        Observable.from(arr)
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        textView.append("onCompleted() <== ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        textView.append("onNext() == "+s+"\n");
                    }
                });
    }

    void method_3(){//just(T t1, T t2, T t3, T t4, T t5, T t6)
        Observable.just("1111","22222","33333","44444","555555","666666")
                .subscribe(new Subscriber<String>() {
                    @Override
                    public void onCompleted() {
                        textView.append("onCompleted() <== ");
                    }

                    @Override
                    public void onError(Throwable e) {

                    }

                    @Override
                    public void onNext(String s) {
                        textView.append("onNext() == "+s+"\n");
                    }
                });
    }

    void method_4(){
        Action0 onCompletedAction = new Action0() {
            @Override
            public void call() {
                textView.append("onCompletedAction <== ");
            }
        };
        Action1<String> onNextAction = new Action1<String>() {
            @Override
            public void call(String o) {
                textView.append(o +"\n");
            }
        };

        Action1<String> onErrorAction = new Action1<String>() {
            @Override
            public void call(String s) {

            }
        };

        Observable observable = Observable.just("qqq","wwww");
        // 自动创建 Subscriber ，并使用 onNextAction 来定义 onNext()
        observable.subscribe(onNextAction);
        // 自动创建 Subscriber ，并使用 onNextAction 和 onErrorAction 来定义 onNext() 和 onError()
        observable.subscribe(onNextAction, onErrorAction);
        // 自动创建 Subscriber ，并使用 onNextAction、 onErrorAction 和 onCompletedAction 来定义 onNext()、 onError() 和 onCompleted()
        observable.subscribe(onNextAction, onErrorAction, onCompletedAction);
    }
}
