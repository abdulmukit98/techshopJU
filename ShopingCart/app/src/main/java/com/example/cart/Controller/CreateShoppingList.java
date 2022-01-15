package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TableLayout;

import com.example.cart.R;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class CreateShoppingList extends AppCompatActivity {

    TableLayout tableLayoutForCreateShoppingList;
    DatabaseReference databaseReference;
    Button submitDB;
    Button addMoreItem;





    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_create_shopping_list);


        tableLayoutForCreateShoppingList=findViewById(R.id.addOrderinTable);
        databaseReference= FirebaseDatabase.getInstance().getReference();
        addMoreItem=findViewById(R.id.addMoreItem);
        addMoreItem.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                
            }
        });





    }
}