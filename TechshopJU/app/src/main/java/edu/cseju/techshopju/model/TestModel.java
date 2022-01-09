package edu.cseju.techshopju.model;

import android.content.Intent;
import android.net.Uri;

public class TestModel {
    public TestModel() {
    }

    public boolean checkName(String name)
    {
        if (name.isEmpty())
            return false;
        return true;
    }

    public boolean checkPrice(String price)
    {
        if (price.isEmpty() || Integer.parseInt(price) == 0)
        {
            return false;
        }
        return true;
    }

    public boolean checkFileUri(Uri uri)
    {
        if (uri == null)
            return false;
        return true;
    }

}
