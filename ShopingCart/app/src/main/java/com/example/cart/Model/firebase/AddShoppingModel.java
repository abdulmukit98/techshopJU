package com.example.cart.Model.firebase;

import com.example.cart.Model.common.ShoppingItem;

import java.util.Map;

/**
 * Creating this class "to add the Items" in Firebase while clicking the "Submit" Button
 */


public class AddShoppingModel {

    map <String, ShoppingItem>Sales;



    String date;

    public AddShoppingModel() {
    }


    public AddShoppingModel(String, ShoppingItem> Sales, String date) {
        Sales = sales;
        this.date = date;
    }

    public map<String, ShoppingItem> getSales()
    {
        return Sales;
    }

    public void setSales(map<String, ShoppingItem> sales)
    {
        Sales = sales;
    }

    /**
     *
     * @return
     */
    public String getDate()
    {
        return date;
    }

    /**
     *
     * @param date
     */
    public void setDate(String date)
    {
        this.date = date;
    }
}
