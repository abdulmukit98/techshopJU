package com.example.cart.Model.ui;


import com.example.cart.Model.common.ShoppingItem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * Creating this class to fetch the data from Firebase and display in the Screen
 */

public class ExpandShopGroup implements Comparable<ExpandShopGroup>, Serializable {

    private String shoppingId;
    private String date;
    private String name;
    private ArrayList<ShoppingItem> Items;

    /**
     *
     * @param expandShopGroup
     * @return
     */


    @Override
    public int compareTo(ExpandShopGroup expandShopGroup) {
        String pattern;
        SimpleDateFormat sdf=new SimpleDateFormat(  "dd-MM-yyyy'T'HH:mm:ssZ" );
                try{
                    return sdf.parse(getDate().compareTo(sdf.parse(expandShopGroup.getDate())));
                }
                catch(ParseException e)
                {
                    return 1;
                }
    }

    /**
     *
     */

    public ExpandShopGroup() {
    }

    /**
     * ExpanShopGroup is a function ...
     * @param shoppingId
     * @param date
     * @param name
     * @param items
     */

    public ExpandShopGroup(String shoppingId, String date, String name, ArrayList<ShoppingItem> items) {
        this.shoppingId = shoppingId;
        this.date = date;
        this.name = name;
        Items = items;
    }

    /**
     * @return
     */

    public String getShoppingId()
    {
        return shoppingId;
    }

    public void setShoppingId(String shoppingId)
    {
        this.shoppingId = shoppingId;
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

    public String getName()
    {
        return name;
    }

    /**
     *
     * @param name
     */

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     *
     * @return
     */

    public ArrayList<ShoppingItem> getItems()
    {
        return Items;
    }

    /**
     *
     * @param items
     */

    public void setItems(ArrayList<ShoppingItem> items)
    {
        Items = items;
    }
}
