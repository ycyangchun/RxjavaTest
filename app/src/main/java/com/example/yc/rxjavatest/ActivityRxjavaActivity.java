package com.example.yc.rxjavatest;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class ActivityRxjavaActivity extends Activity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_rxjava);
        ButterKnife.bind(this);

    }

    @OnClick(R.id.button)
    void onButtonClick() {
       startActivity(new Intent(this, RxCreate.class));
    }


    @OnClick(R.id.button2)
    void onButton2Click() {
        //TODO implement
    }


    @OnClick(R.id.button3)
    void onButton3Click() {
        //TODO implement
    }


    @OnClick(R.id.button4)
    void onButton4Click() {
        //TODO implement
    }


    @OnClick(R.id.button5)
    void onButton5Click() {
        //TODO implement
    }

}
