package edu.cseju.orderpcb.TestModel;

/**
 * This class is created to perform testing
 * basically unit testing will perform here. <br>
 * method in this class will be tested with possible test cases.
 */
public class TestClass
{

    /**
     * null constructor
     */
    public TestClass()
    {
    }


    /**
     * Check if an integer is negative or not
     *
     * @param quantity the input parameter
     * @return true if quentitu -ve , false if +ve
     */
    public boolean checkNegetive(int quantity)
    {
        return (quantity < 0) ? true : false;
    }

    public boolean validMessage(String msg)
    {
        return (msg == null || msg == "")? false: true;
    }
}
