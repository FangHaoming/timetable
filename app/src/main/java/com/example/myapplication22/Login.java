package com.example.myapplication22;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Looper;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import java.io.IOException;

import okhttp3.Callback;
import okhttp3.FormBody;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
/////
public class Login extends AppCompatActivity {
    private String acc;
    private String pwd;
    private EditText password;
    private EditText account;
    private SharedPreferences send;
    private SharedPreferences.Editor editor;
    //private Bundle send=new Bundle();
    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.login);
        account=(EditText)findViewById(R.id.account);
        password=(EditText)findViewById(R.id.password);
        send=getSharedPreferences("data", Context.MODE_PRIVATE);

        account.setText(send.getString("txtUserID",""));
        password.setText(send.getString("txtUserPwd",""));
        Button login=(Button)findViewById(R.id.button);
        getSupportActionBar().hide();
        login.setOnClickListener(mListener);


    }

    OnClickListener mListener=new OnClickListener(){
        @Override
        public void onClick(View v) {
            switch (v.getId()){
                case R.id.button:
                    login();
                    break;
            }
        }
    };

    public void login(){
        editor=send.edit();
        acc=account.getText().toString();
        pwd=password.getText().toString();
        editor.putString("txtUserID",acc);
        editor.putString("txtUserPwd",pwd);
        editor.putBoolean("isLogin",false);
        editor.putString("color","");
        editor.apply();

        //send=new Bundle();
        //send.putString("txtUserID",acc);
        //send.putString("txtUserPwd",pwd);
        if(acc.length()!=0&&pwd.length()!=0){
            //sendByPost(acc,pwd,"2022-2023学年","1");
            new Thread(new Runnable() {
                @Override
                public void run() {
                    String info=http.login(acc,pwd);
                    if(info.equals("success")){
                        editor.putBoolean("isLogin",true);
                        editor.putBoolean("isLoad",false);
                        editor.putString("info","");
                        editor.apply();
                        Intent intent = new Intent(Login.this,MainActivity.class);
                        //intent.putExtras(send);
                        startActivity(intent);
                        finish();
                    }
                    else if(info.equals("error")){
                        Looper.prepare();
                        Toast.makeText(Login.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                    else if(info.equals("failed")){
                        Looper.prepare();
                        Toast.makeText(Login.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                        Looper.loop();
                    }
                }
            }).start();
        }
        else if(acc.length()==0){
            Toast.makeText(this, "请输入账号", Toast.LENGTH_SHORT).show();
        }
        else if(pwd.length()==0){
            Toast.makeText(this, "请输入密码", Toast.LENGTH_SHORT).show();
        }

    }



    private void sendByPost(String txtUserID, String txtUserPwd,String xn,String xq) {
        //172.16.226.68
        //10.22.32.85
        String Url = "http://172.16.226.68:8080/servlet.loginServlet";
        String path = Url ;
        OkHttpClient client = new OkHttpClient();
        final FormBody formBody = new FormBody.Builder()
                .add("txtUserID", txtUserID)
                .add("txtUserPwd", txtUserPwd)
                .add("xn",xn)
                .add("xq",xq)
                .build();
        Request request = new Request.Builder()
                .url(path)
                .post(formBody)
                .build();
        client.newCall(request).enqueue(new Callback() {
            @Override
            public void onFailure(okhttp3.Call call, IOException e) {
                Looper.prepare();
                Toast.makeText(Login.this, "服务器连接失败", Toast.LENGTH_SHORT).show();
                Looper.loop();
                e.printStackTrace();
            }

            @Override
            public void onResponse(okhttp3.Call call, Response response) throws IOException {
                    //登录成功，跳转到MainActivity.class课程表页面

                String info=response.body().string();
                System.out.println("********************"+info);
                String success="success\r\n";
                String error="error\r\n";

                if(info.equals(success)){
                    editor.putBoolean("isLogin",true);
                    editor.putBoolean("isLoad",false);
                    editor.putString("info","");
                    editor.apply();
                    Intent intent = new Intent(Login.this,MainActivity.class);
                    //intent.putExtras(send);
                    startActivity(intent);
                    finish();
                }
                else if(info.equals(error)){
                    Looper.prepare();
                    Toast.makeText(Login.this, "账号密码错误", Toast.LENGTH_SHORT).show();
                    Looper.loop();
                }


            }
        });
    }


}
