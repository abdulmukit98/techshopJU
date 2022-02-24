package com.example.cart.Model.common;


/**
 * To Fetch and write the data into Firebase
 */

public class ShoppingItem {
    String category;
    String product;
    int quantity;
    String unit;

    /**
     * Using default costructor <br>
     */
    public ShoppingItem() {
    }


    /**
     *<p>
     * @param category <br>
     * @param product <br>
     * @param quantity <br>
     * @param unit <br>
     * </p>
     */

    public ShoppingItem(String category, String product, int quantity, String unit) {
        this.category = category;
        this.product = product;
        this.quantity = quantity;
        this.unit = unit;
    }

    /**
     * <p>
     * 1. Using getter for "Category". <br>
     * 2. Will return Category. <br>
     * @return <br>
     * </p>
     */

    public String getCategory()
    {

        return category;
    }


    /**
     * <p>
     * Using Seeter for "Category" . <br>
     * @param category <br>
     * </p>
     */

    public void setCategory(String category)
    {
        this.category = category;
    }

    /**
     * <p>
     * 1. Using getter for "Product". <br>
     * 2. Return "Product". <br>
     * @return <br>
     * </p>
     */

    public String getProduct()
    {

        return product;
    }

    /**
     * <p>
     * Using setter for " Product". <br>
     * @param product <br>
     * </p>
     */

    public void setProduct(String product)
    {

        this.product = product;
    }

    /**
     * <p>
     * Using getter for "Quantity"<br>
     * Return "Quantity". <br>
     * @return <br>
     * </p>
     */

    public int getQuantity()
    {
        return quantity;
    }

    /**
     * <p>
     * Using Setter for "Quantity". <br>
     * @param quantity <br>
     * </p>
     */

    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }


    /**
     * <p>
     * Using getter for "Unit" . <br>
     * @return <br>
     * </p>
     */

    public String getUnit()
    {
        return unit;
    }


    /**
     * <p>
     * Using setter for "Unit" .<br>
     * @param unit <br>
     * </p>
     */

    public void setUnit(String unit)
    {
        this.unit = unit;
    }
}
