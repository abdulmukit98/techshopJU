package com.example.mywishlist;

import android.arch.persistence.room.Room;
import android.content.Intent;
import android.support.v4.view.GravityCompat;
import android.support.v7.app.AppCompatActivity;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;

import com.example.mywishlist.adapter.MyAdapter;
import com.example.mywishlist.database.Wishlist;
import com.example.mywishlist.database.WishlistDatabase;
import com.example.mywishlist.modal.List_Data;
import com.example.mywishlist.retrofit.MyApi;
import com.example.mywishlist.retrofit.RetrofitInstance;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class MainActivity extends AppCompatActivity {
    private List<List_Data>list_data;
    private RecyclerView recyclerView;
    private MyApi myApi;
    MyAdapter adapter;
    public static WishlistDatabase myDatabase;
    private Button btn;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(android.R.layout.activity_list_item);

        recyclerView = (RecyclerView) findViewById(int b);
        recyclerView.setHasFixedSize(true);
        GridLayoutManager gridLayoutManager=new GridLayoutManager(this,2);
        recyclerView.setLayoutManager(gridLayoutManager);
        list_data=new ArrayList<>();
        myDatabase= Room.databaseBuilder(getApplicationContext(),WishlistDatabase.class,"Wish_Data").allowMainThreadQueries().build();

        btn=(Button)findViewById(btn.getImeActionId());
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                startActivity(new Intent(MainActivity.this, Wishlist.class));

            }
        });
        getdata();
    }

    private void getdata() {
        myApi= RetrofitInstance.getRetrofitInstance().create(MyApi.class);
        Call<List<List_Data>>call=myApi.geData();

        call.enqueue(new Callback<List<List_Data>>() {
            @Override
            public void onResponse(Call<List<List_Data>> call, Response<List<List_Data>> response) {
                list_data=response.body();
                adapter=new MyAdapter(list_data,getApplicationContext());
                recyclerView.setAdapter(adapter);
            }

            @Override
            public void onFailure(Call<List<List_Data>> call, Throwable t) {

            }
        });
    }



}
