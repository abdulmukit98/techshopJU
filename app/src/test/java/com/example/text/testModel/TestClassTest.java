package com.example.text.testModel;

import static org.junit.Assert.*;

import org.junit.Test;

public class TestClassTest {

    TestClass testClass = new TestClass();

    @Test
    public void messageCheck1()
    {
        String s = "";
        assertEquals(true, testClass.msgCheck(s));
    }

    @Test
    public void messagCheck2()
    {
        String s = "ksdkgjhsa sdfglkhjn";
        assertEquals(false, testClass.msgCheck(s));
    }

}