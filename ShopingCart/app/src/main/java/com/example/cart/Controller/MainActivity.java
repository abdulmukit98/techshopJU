package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.os.Handler;

import com.example.cart.R;

import java.net.ContentHandler;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {
    /**
     * For Splash Screen
     */

    Handler handler;
    private static String uniqueID=null;
    private static final String PREF_UNIQUE_ID= "PREFER_UNIQUE_ID";



//    I am testing commit 02

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

    /**
     *
     * @param context
     * @return
     */

    public synchronized static String getSubscriberId(Context context)
    {
        if (uniqueID==null)
        {
            SharedPreferences sharedPreferences=context.getSharedPreferences(PREF_UNIQUE_ID, Context.MODE_PRIVATE);
            uniqueID=sharedPreferences.getString(PREF_UNIQUE_ID,null);
            if (uniqueID==null)
            {
                uniqueID="subscriber"+ UUID.randomUUID().toString();
                SharedPreferences.Editor editor=sharedPreferences.edit();
                editor.putString(PREF_UNIQUE_ID,uniqueID);
                editor.commit();

            }
        }
        return uniqueID;
    }
}