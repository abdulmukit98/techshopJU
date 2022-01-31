package edu.cseju.orderpcb.TestModel;

import static org.junit.Assert.assertEquals;

import org.junit.Test;

public class TestClassTesting
{

    TestClass testClass = new TestClass();

    /**
     * check negetive
     * possible test case "boundary testing"
     * <p>
     * quantity = 0  false
     * quantity > 0  false
     * quantity < 0  true
     */
    @Test
    public void checkNegetive()
    {
        int quantity = -5;
        assertEquals(true, testClass.checkNegetive(quantity));
    }

    /**
     * valid quantity, check_negetive false
     */
    @Test
    public void checkNegetive2()
    {
        int quantity = 6;
        assertEquals(false, testClass.checkNegetive(quantity));
    }

    /**
     * null quantity
     */
    @Test
    public void checkNegetive3()
    {
        int quantity = 0;
        assertEquals(false, testClass.checkNegetive(quantity));
    }

    /**
     * For validMessage, possible test case
     * message = null       false
     * message = ""         empty false
     * message = "something"  true
     */
    @Test
    public void validMessage1()
    {
        String msg = null;
        assertEquals(false, testClass.validMessage(msg));
    }

    /**
     * empty message , expected false
     */
    @Test
    public void validMessage2()
    {
        String msg = "";
        assertEquals(false, testClass.validMessage(msg));
    }

    /**
     * for valid message
     */
    @Test
    public void validMessage3()
    {
        String msg = "message msg1";
        assertEquals(true, testClass.validMessage(msg));
    }


}