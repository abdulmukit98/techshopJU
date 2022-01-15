package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.cart.R;
import com.google.firebase.database.DatabaseReference;

public class CreateShoppingList extends AppCompatActivity {

    TableLayout tableLayoutForCreateShoppingList;
    DatabaseReference databaseReference;
    Button submitDB;
    Button addMoreItem;
    




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);
    }
}