package com.shellcore.android.reminders;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;

import butterknife.ButterKnife;
import butterknife.OnClick;

public class MainActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        ButterKnife.bind(this);
    }

    @OnClick(R.id.btn_create)
    public void onClickBtnCreate() {
        Intent intent = new Intent(this, Reminder.class);
        startService(intent);
    }

    @OnClick(R.id.btn_cancel)
    public void onClickBtnCancel() {
        Intent intent = new Intent(this, Reminder.class);
        stopService(intent);
    }
}
