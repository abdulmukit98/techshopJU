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

    Handler handler;
    private static String uniqueID=null;
    private static final String PREF_UNIQUE_ID= "PREFER_UNIQUE_ID";


    /**
     * <p>
     * <H1> For Splash Screen </H1>
     * 1. Delaying Splash Screen for 10000 mili second. <br>
     * 2. Finding  "activity_main" from Resource layout <br>
     * 3. Using Handler to send and process the Messages and Runnable objects associated with the thread's MessageQueue .<br>
     * <B> @param savedInstanceState </B> <br>
     * </p>
     */
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
     * <p>
     * @param context
     * @return
     * 1. Using "if" statement for "Unique_Id". <br>
     * </p>
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