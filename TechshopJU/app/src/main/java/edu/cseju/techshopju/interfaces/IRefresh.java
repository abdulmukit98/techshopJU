package edu.cseju.techshopju.interfaces;

import edu.cseju.techshopju.model.Product;

/**
 * This interface is used to process instruction even outside the activities
 */
public interface IRefresh {
    /**
     * It will reload the activity which implement it
     */
    public void RefreshActivity();

    /**
     * It will update a product
     * @param product   Product object that need to be updated in firebase
     */
    public void UpdateProduct(Product product);
}
