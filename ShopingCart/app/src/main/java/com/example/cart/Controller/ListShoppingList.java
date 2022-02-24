package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;

import com.example.cart.R;

public class ListShoppingList extends AppCompatActivity {

    ImageButton addShoppingButton;


    /**
     * <p>
     * <H1> @param savedInstanceState </H1> <br>
     * Finding or getting "activity_list_shopping_list" from Resource layout <br>
     * Finding "addButton" for adding more item Button <br>
     * </p>
     */

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_list_shopping_list);

        addShoppingButton=findViewById(R.id.addButton);
        addShoppingButton.setOnClickListener(new View.OnClickListener() {

            /**
             * <br>
             * <p>
             * <H1> @param view </H1>   <br>
             * To start an activity, using the method startActivity(intent) <br>
             * <I> "ListshoppingList.this" refers to the current class we are in (CreateShoppingList.java) </I> <br>
             * </p>
             */

            @Override
            public void onClick(View view) {
                Intent intent=new Intent(ListShoppingList.this, CreateShoppingList.class);
                startActivity(intent);
            }
        });
    }
}