package edu.cseju.techshopju.model;

import static org.junit.Assert.*;

import android.net.Uri;

import org.junit.Test;

public class TestModelTest {

    private final TestModel testModel = new TestModel();

    @Test
    public void empty_name() {
        String name = "";
        assertEquals(false, testModel.checkName(name));
    }

    @Test
    public void non_empty_name() {
        String name = "Arduio Uno";
        assertEquals(true, testModel.checkName(name));
    }

    @Test
    public void nullPrice() {
        String price = "";
        assertEquals(false, testModel.checkPrice(price));
    }

    @Test
    public void zeroPrice() {
        String price = "0";
        assertEquals(false, testModel.checkPrice(price));
    }

    @Test
    public void validPrice() {
        String price = "55";
        assertEquals(true, testModel.checkPrice(price));
    }

    @Test
    public void unvalidUriTest() {
        Uri uri = null;
        assertEquals(false, testModel.checkFileUri(uri));
    }


}