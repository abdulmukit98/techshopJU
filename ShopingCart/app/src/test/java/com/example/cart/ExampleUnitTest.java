package com.example.cart;

import org.junit.Test;

import static org.junit.Assert.*;

import com.example.cart.Model.common.ShoppingItem;

/**
 * Example local unit test, which will execute on the development machine (host).
 *
 * @see <a href="http://d.android.com/tools/testing">Testing documentation</a>
 */
public class ExampleUnitTest {
    @Test
    public void addition_isCorrect() {
        assertEquals(4, 2 + 2);
    }

    @Test
    public  void demoTest(){
        ShoppingItem shoppingItem = new ShoppingItem();
        shoppingItem.setCategory("Hello");
        shoppingItem.setProduct("Ok");

        assertEquals(true, shoppingItem.getCategory().equals("Hello"));
        assertEquals(true, shoppingItem.getProduct().equals("Ok"));

    }
}