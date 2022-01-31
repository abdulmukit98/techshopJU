package com.example.cart.Model.ui;


import com.example.cart.Model.common.ShoppingItem;

import java.io.Serializable;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.ArrayList;

/**
 * <p>
 * Creating this class to fetch the data from Firebase and display in the Screen. <br>
 * </p>
 */

public class ExpandShopGroup implements Comparable<ExpandShopGroup>, Serializable {

    private String shoppingId;
    private String date;
    private String name;
    private ArrayList<ShoppingItem> Items;

    /**
     * <p>
     * @param expandShopGroup <br>
     * @return <br>
     * </p>
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
     * <p>
     * Using default Constructor. <br>
     * </p>
     */

    public ExpandShopGroup() {
    }

    /**
     * <p>
     * ExpanShopGroup is a function ... <br>
     * @param shoppingId <br>
     * @param date <br>
     * @param name <br>
     * @param items <br>
     * </p>
     */

    public ExpandShopGroup(String shoppingId, String date, String name, ArrayList<ShoppingItem> items) {
        this.shoppingId = shoppingId;
        this.date = date;
        this.name = name;
        Items = items;
    }

    /**
     * <p>
     * @return "shoppingId". <br>
     * </p>
     */

    public String getShoppingId()
    {
        return shoppingId;
    }

    /**
     * <p>
     * @param shoppingId <br>
     * </p>
     */

    public void setShoppingId(String shoppingId)
    {
        this.shoppingId = shoppingId;
    }

    /**
     * <p>
     * @return "Date". <br>
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

    /**
     * <p>
     * @return " Name". <br>
     * </p>
     */

    public String getName()
    {
        return name;
    }

    /**
     * <p>
     * @param name
     * </p>
     */

    public void setName(String name)
    {
        this.name = name;
    }

    /**
     * <p>
     * @return "Items". <br>
     * </p>
     */

    public ArrayList<ShoppingItem> getItems()
    {
        return Items;
    }

    /**
     * <p>
     * @param items
     * </p>
     */

    public void setItems(ArrayList<ShoppingItem> items)
    {
        Items = items;
    }
}
