package com.example.cart.ui;


import android.content.Context;
import android.text.InputType;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Spinner;
import android.widget.TableLayout;
import android.widget.TableRow;

import com.google.firebase.database.DatabaseReference;

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

        






























    }


}
