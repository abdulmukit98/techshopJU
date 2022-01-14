package com.example.cart.ui;


import android.content.Context;
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
         TableRow.LayoutParams paramsForCategoryAndProduct=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.4f);
         final TableRow.LayoutParams paramsForQuantity =new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,.9f);
         final TableRow.LayoutParams paramsForUnit=new TableRow.LayoutParams(0,TableRow.LayoutParams.WRAP_CONTENT,1.4f);






    }


}
