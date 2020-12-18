package com.example.myapplication22;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;

import androidx.annotation.Nullable;

public class SplashActivity extends Activity {
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash);
        final SharedPreferences receive=getSharedPreferences("data", Context.MODE_PRIVATE);
        Thread myThread = new Thread() {//创建子线程
            @Override
            public void run() {
                try {
                    sleep(700);
                    if(!receive.getBoolean("isLogin",false)){
                        Intent intent = new Intent(SplashActivity.this,Login.class);
                        //intent.putExtras(send);
                        startActivity(intent);
                        finish();
                    }
                    else{
                        Intent intent = new Intent(SplashActivity.this,MainActivity.class);
                        //intent.putExtras(send);
                        startActivity(intent);
                        finish();
                    }
                } catch (Exception e) {
                    e.printStackTrace();
                }
            }
        };
        myThread.start();//启动线程
    }
}
