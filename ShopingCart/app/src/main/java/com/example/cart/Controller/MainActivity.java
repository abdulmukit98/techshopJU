package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;

import com.example.cart.R;

public class MainActivity extends AppCompatActivity {
    /**
     * For Splash Screen
     */\

    Handler handler;


//    I am testing commit

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        handler= new Handler();
        handler.postDelayed(new Runnable() {
            @Override
            public void run() {
                Intent intent=new Intent(MainActivity.this,ListShoppingList.class);
                startActivity(intent);
                finish();


            }
        },10000);




    }
}