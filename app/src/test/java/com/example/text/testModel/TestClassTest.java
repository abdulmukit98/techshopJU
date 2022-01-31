package com.example.text.testModel;

import static org.junit.Assert.*;

import org.junit.Test;

import javax.xml.validation.Validator;

public class TestClassTest {

    TestClass testClass = new TestClass();



    @Test
    public void Phone()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }

    @Test
    public void messagCheck2()
    {
        String s = " This is False";
        assertEquals(false, testClass.msgCheck(s));
    }

    @Test
    public void Email()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }

    @Test
    public void Name()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }


    @Test
    public void Address1()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }


    @Test
    public void Quantity()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }


    @Test
    public void PostCode()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }


    



}