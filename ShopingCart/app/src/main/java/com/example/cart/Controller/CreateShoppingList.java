package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.example.cart.Model.common.ShoppingItem;
import com.example.cart.R;
import com.example.cart.ui.AddItemInShopTable;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

import java.util.HashMap;
import java.util.Map;

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
                AddItemInShopTable.addRow(view.getContext(), tableLayoutForCreateShoppingList, databaseReference);

            }
        });

        submitDB=findViewById(R.id.submit);
        submitDB.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Map<String, ShoppingItem> sales=new HashMap<>();
                Map<String, ShoppingItem> newCatandProd=new HashMap<>();
                boolean error=false;
                for (int i=1, j=tableLayoutForCreateShoppingList.getChildCount()-2;i<j;i++)
                {
                    View rowView=tableLayoutForCreateShoppingList.getChildAt(i);
                    if (rowView instanceof TableRow)
                    {
                        TableRow row=(TableRow) rowView;
                        String category, product;
                        int itemDetailChildCount=row.getChildCount();
                        String newCategory=null;
                        String newProduct=null;
                        Spinner categorySpinner=(Spinner) row.getChildAt(0);
                        int quantityIndex;
                        int unitIndex;
                        category=(String) categorySpinner.getSelectedItem();
                        if (itemDetailChildCount==5)
                        {
                            Spinner productSpinner=(Spinner) row.getChildAt(1);
                            product=(String) productSpinner.getSelectedItem();
                            quantityIndex=2;
                            unitIndex=3;
                        }

                        else {
                            if (category.equals("Others"))
                            {
                                EditText newCategoryEditText=(EditText) row.getChildAt(1);
                                category=newCategoryEditText.getText().toString();
                                newCategory=category;


                                EditText newProductEditText=(EditText) row.getChildAt(2);
                                product=newProductEditText.getText().toString();
                                newProduct=product;

                            }

                            else {
                                EditText newProductEditText=(EditText) row.getChildAt(2);
                                product=newProductEditText.getText().toString();
                                newProduct=product;
                            }

                        }


                    }
                }
            }
        });





    }
}