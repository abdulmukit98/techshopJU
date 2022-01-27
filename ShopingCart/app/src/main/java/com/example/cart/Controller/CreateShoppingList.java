package com.example.cart.Controller;

import androidx.appcompat.app.AppCompatActivity;
import androidx.dynamicanimation.animation.DynamicAnimation;

import android.os.Bundle;
import android.text.DynamicLayout;
import android.text.style.DynamicDrawableSpan;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;
import android.widget.Toast;

import com.example.cart.Model.common.ShoppingItem;
import com.example.cart.Model.firebase.AddShoppingModel;
import com.example.cart.R;
import com.example.cart.ui.AddItemInShopTable;
import com.google.android.gms.common.util.DynamiteApi;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.pranavpandey.android.dynamic.toasts.DynamicToast;

import java.text.SimpleDateFormat;
import java.util.Calendar;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.UUID;

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
                Map<String, String> newCatandProd=new HashMap<>();
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

                            quantityIndex=3;
                            unitIndex=4;

                        }

                        EditText quantityEditText=(EditText) row.getChildAt(quantityIndex);
                        Spinner unitSpinner=(Spinner) row.getChildAt(unitIndex);
                        if (quantityEditText.getText().length()==0 || Integer.valueOf(quantityEditText.getText().toString())<1))
                        {
                            quantityEditText.setError("Quantity must be provided and greater than 0");
                            error=true;
                            break;
                        }

                        ShoppingItem shoppingItem=new ShoppingItem(category,product,Integer.valueOf(quantityEditText.getText().toString()),(String) unitSpinner.getSelectedItem());
                        sales.put(UUID.randomUUID().toString(),shoppingItem);
                        if (newCategory!=null)
                        {
                            newCatandProd.put(newCategory,newProduct);
                        }

                        else if (newProduct !=null)
                        {
                            newCatandProd.put(category,newProduct);
                        }


                    }
                }
                if (sales.size()==0)
                {
                    DynamicToast.makeError(getApplicationContext(), "Provide sale", Toast.LENGTH_LONG).show();

                }

                else if (!error)
                {
                    String saleId="start_splash_cart"+UUID.randomUUID().toString();
                    Date c= Calendar.getInstance().getTime();
                    SimpleDateFormat df=new SimpleDateFormat("dd-MM-yyyy 'T' HH:mm:ssz");
                    String formattedDate=df.format(c);
                    AddShoppingModel shoppingmodel=new AddShoppingModel(sales,formattedDate);
                    databaseReference.child("shopping").child()


                }

            }
        });





    }
}