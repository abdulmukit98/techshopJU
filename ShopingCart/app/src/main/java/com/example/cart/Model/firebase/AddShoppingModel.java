package com.example.cart.Model.firebase;

import com.example.cart.Model.common.ShoppingItem;

import java.util.Map;

/**
 * <p>
 * Creating this class "to add the Items" in Firebase while clicking the "Submit" Button. <br>
 * </p>
 */
public class AddShoppingModel {

    map <String, ShoppingItem>Sales;
    String date;

    /**
     * <p>
     * Using default Constructor. <br>
     * </p>
     */
    public AddShoppingModel() {
    }


    /**
     * <p>
     * @param date <br>
     * </p>
     */
    public AddShoppingModel(String, ShoppingItem> Sales, String date) {
        Sales = sales;
        this.date = date;
    }

    /**
     * <p>
     * Returning "Sales" <br>
     * @return <br>
     * </p>
     */
    public map<String, ShoppingItem> getSales()
    {
        return Sales;
    }


    /**
     *<p>
     * @param sales <br>
     * </p>
     */
    public void setSales(map<String, ShoppingItem> sales)
    {
        Sales = sales;
    }

    /**
     * <p>
     * Returning "Date" <br>
     * @return
     * </p>
     */
    public String getDate()
    {
        return date;
    }

    /**
     * <p>
     * @param date
     * </p>
     */
    public void setDate(String date)
    {
        this.date = date;
    }
}
