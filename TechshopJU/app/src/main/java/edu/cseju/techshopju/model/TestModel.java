package edu.cseju.techshopju.model;

import android.content.Intent;
import android.net.Uri;

/**
 * This model is for testing purpose only
 */
public class TestModel {
    public TestModel() {
    }

    /**
     * Test cases will be made by the logic of these functions
     * @param name  a string containing product name
     * @return      check if string is empty or not
     */
    public boolean checkName(String name) {
        if (name.isEmpty())
            return false;
        return true;
    }

    public boolean checkPrice(String price) {
        if (price.isEmpty() || Integer.parseInt(price) == 0) {
            return false;
        }
        return true;
    }

    public boolean checkFileUri(Uri uri) {
        if (uri == null)
            return false;
        return true;
    }

}
