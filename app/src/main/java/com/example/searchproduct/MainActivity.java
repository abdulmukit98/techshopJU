package com.example.searchproduct;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.SearchView;

import android.os.Bundle;
import android.widget.ArrayAdapter;
import android.widget.ListView;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {
   SearchView mySearchView;
   ListView myList;
   ArrayList<String> list;
   ArrayAdapter<String> adapter;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        mySearchView=findViewById(R.id.searchView);
        myList=findViewById(R.id.mylist);
        list=new ArrayList<String>();
        list.add("Computer");
        list.add("Laptop");
        list.add("Mobile");
        list.add("Ram");
        list.add("PCB");
        list.add("Rom");
        list.add("CPU");
        list.add("HardDisk");
        adapter=new ArrayAdapter<>(this,android.R.layout.simple_list_item_1,list);
        myList.setAdapter(adapter);
        mySearchView.setOnQueryTextListener(new SearchView.OnQueryTextListener() {
            @Override
            public boolean onQueryTextSubmit(String query) {
                return false;
            }

            @Override
            public boolean onQueryTextChange(String newText) {
                adapter.getFilter().filter(newText);
                return false;
            }
        });


    }
}