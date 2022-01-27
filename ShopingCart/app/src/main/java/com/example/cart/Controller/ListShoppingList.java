package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cart.R;

public class ListShoppingList extends AppCompatActivity {

    ImageButton addShoppingButton;



    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shopping_list);

        addShoppingButton=findViewById(R.id.addButton);
        addShoppingButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListShoppingList.this, CreateShoppingList.class);
                startActivity(intent);
            }
        });
    }
}