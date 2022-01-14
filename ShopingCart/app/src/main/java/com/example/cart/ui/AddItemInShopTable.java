package com.example.cart.ui;


import android.content.Context;
import android.text.InputType;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import androidx.annotation.NonNull;

import com.example.cart.R;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.ValueEventListener;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

/**
 * Creating this class to create the rows of product and filling the data in that rows
 */


public class AddItemInShopTable {

    /**
     * For Adding Rows
     * @param context
     * @param adddalTableLayout
     * @param databaseReference
     */


    public static void addRow(final Context context, final TableLayout adddalTableLayout, final DatabaseReference databaseReference)
    {
        final TableRow tr=new TableRow(context);
        TableLayout.LayoutParams paramsForRow=new TableLayout.LayoutParams(0, TableLayout.LayoutParams.WRAP_CONTENT, 1f);
        paramsForRow.setMargins(10,10,0,20);
        tr.setLayoutParams(paramsForRow);
         final TableRow.LayoutParams paramsForCategoryAndProduct=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.4f);
         final TableRow.LayoutParams paramsForQuantity =new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,.9f);
         final TableRow.LayoutParams paramsForUnit=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.4f);


         final Spinner catSpinner=new Spinner(context);
         catSpinner.setLayoutParams(paramsForCategoryAndProduct);


         final Spinner productSpinner=new Spinner(context);
         catSpinner.setLayoutParams(paramsForCategoryAndProduct);


         final EditText quantity=new EditText(context);
         quantity.setLayoutParams(paramsForQuantity);
         quantity.setInputType(InputType.TYPE_CLASS_NUMBER);

         final Spinner unitSpinner=new Spinner(context);
         catSpinner.setLayoutParams(paramsForUnit);

        /**
         * For new Category
         */

        final EditText etNewCategory=new EditText(context);
         etNewCategory.setLayoutParams(paramsForCategoryAndProduct);
         etNewCategory.setHint("New Category");

        /**
         * For new product
         */

        final EditText etNewProduct=new EditText(context);
        etNewCategory.setLayoutParams(paramsForCategoryAndProduct);
        etNewCategory.setHint("New Product");


        final TableRow.LayoutParams rowParamsMinus=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,.5f);
        ImageButton minusButton=new ImageButton(context);
        minusButton.setImageResource(R.drawable.ic_baseline_indeterminate_check_box_24);
        minusButton.setLayoutParams(rowParamsMinus);

        fetchUnitInSpinner(databaseReference,context,unitSpinner);
        fetchCategoryAndProduct(databaseReference,context,catSpinner,productSpinner,tr,etNewCategory,etNewProduct);
        addOrRemoveProductSpinner(productSpinner,tr,etNewProduct);


    }

    private static void addOrRemoveProductSpinner(Spinner productSpinner, final TableRow tr, final EditText etNewProduct) {

        productSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                if (productSpinner.getSelectedItem().equals("Others"))
                {
                    tr.addView(etNewProduct,2);
                }

                else {
                    tr.removeView(etNewProduct);
                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });

    }

    private static void fetchCategoryAndProduct(DatabaseReference databaseReference, final Context context, final Spinner catSpinner, final Spinner productSpinner, final TableRow tr, final EditText etNewCategory, final EditText etNewProduct) {

        fetchCategoriesInSpinner(databaseReference,context,catSpinner);
        catSpinner.setOnItemSelectedListener(new AdapterView.OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> adapterView, View view, int i, long l) {
                String selectedItem=(String) catSpinner.getSelectedItem();
                if (selectedItem.equals("Others"))
                {
                    tr.removeView(productSpinner);
                    tr.addView(etNewCategory,1);
                    tr.addView(etNewProduct,2);
                }

                else {
                    tr.removeView(etNewCategory);
                    if (!tr.getChildAt(1).equals(productSpinner))
                    {
                        tr.addView(productSpinner,1);
                    }
                    databaseReference.child("categories").child(selectedItem)
                            .addValueEventListener(new ValueEventListener() {
                                @Override
                                public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                                    final ArrayList<String> productlist=new ArrayList<>();
                                    for(DataSnapshot areaSnapshot:dataSnapshot.getChildren())
                                    {
                                        productlist.add(areaSnapshot.getValue(String.class));
                                    }

                                    Collections.sort(productlist);
                                    productlist.add("Others");

                                    final ArrayAdapter<String> productAdapter=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,productlist);
                                    productAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                                    productSpinner.setAdapter(productAdapter);



                                }

                                @Override
                                public void onCancelled(@NonNull DatabaseError error) {

                                }
                            });

                }


            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {

            }
        });
        
    }

    private static void fetchCategoriesInSpinner(DatabaseReference databaseReference, final Context context, final Spinner catSpinner) {

        databaseReference.child("categories").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<String> catlist=new ArrayList<>();
                for(DataSnapshot areaSnapshot:dataSnapshot.getChildren())
                {
                    catlist.add(areaSnapshot.getKey());
                }

                Collections.sort(catlist);
                catlist.add("Others");

                final ArrayAdapter<String> catAdapter=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,catlist);
                catAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                catSpinner.setAdapter(catAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });


    }


    /**
     *
     * @param databaseReference
     * @param context
     * @param unitSpinner
     */

    private static void fetchUnitInSpinner(DatabaseReference databaseReference, final Context context, final Spinner unitSpinner) {

        databaseReference.child("Unit").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
                final ArrayList<String> unitlist=new ArrayList<>();
                for(DataSnapshot areaSnapshot:dataSnapshot.getChildren())
                {
                    unitlist.add(areaSnapshot.getValue(String.class));
                }

                Collections.sort(unitlist);

                final ArrayAdapter<String> unitAdapter=new ArrayAdapter<>(context, android.R.layout.simple_spinner_item,unitlist);
                unitAdapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
                unitSpinner.setAdapter(unitAdapter);



            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });



    }


}
