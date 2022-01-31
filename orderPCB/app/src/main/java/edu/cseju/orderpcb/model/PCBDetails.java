package edu.cseju.orderpcb.model;

import java.util.HashMap;
import java.util.Map;

/**
 * This is our model <br>
 * This class contain the information of the pcb we are going to order <br>
 * this model is uploaded in database.
 */
public class PCBDetails
{

    int cost, quantity;
    private String orderId;
    private boolean isSingle, isMasking;
    private double width, height;
    private String fileURL;

    /**
     * null constructor
     */
    public PCBDetails()
    {

    }

    /**
     * Constructor with requirments of pcb.
     *
     * @param orderId   Unique identification key for the pcb
     * @param isSingle  if true then pcb is single layered, false --> double layer
     * @param isMasking true --> green masking enable in pcb.
     * @param width     dimension
     * @param height    dimension
     * @param quantity  no of pcb
     */
    public PCBDetails(String orderId, boolean isSingle, boolean isMasking, double width, double height, int quantity)
    {
        this.orderId = orderId;
        this.isSingle = isSingle;
        this.isMasking = isMasking;
        this.width = width;
        this.height = height;
        this.quantity = quantity;
        this.cost = (int) (width * height * 20 * quantity);

    }

    /**
     * receive order id from another class
     *
     * @return give the order id
     */
    public String getOrderId()
    {
        return orderId;
    }

    /**
     * To set order if for a order
     *
     * @param orderId the id which will be assigned with the product
     */
    public void setOrderId(String orderId)
    {
        this.orderId = orderId;
    }

    /**
     * get if pcb is single or not
     *
     * @return
     */
    public boolean isSingle()
    {
        return isSingle;
    }

    /**
     * define pcb single or double
     *
     * @param single true or false
     */
    public void setSingle(boolean single)
    {
        isSingle = single;
    }

    /**
     * get the masking condition of pcb
     *
     * @return
     */
    public boolean isMasking()
    {
        return isMasking;
    }

    /**
     * define if masking will be implemented in pcb
     *
     * @param masking
     */
    public void setMasking(boolean masking)
    {
        isMasking = masking;
    }

    /**
     * get dimension
     *
     * @return double format of width
     */
    public double getWidth()
    {
        return width;
    }

    /**
     * define width of pcb
     *
     * @param width a non zero double
     */
    public void setWidth(double width)
    {
        this.width = width;
    }

    /**
     * To get height of the pcb
     *
     * @return height
     */
    public double getHeight()
    {
        return height;
    }

    /**
     * To define height of the pcb
     *
     * @param height a non zero double
     */
    public void setHeight(double height)
    {
        this.height = height;
    }

    /**
     * Get cost required in the order
     *
     * @return integer represent cost
     */
    public int getCost()
    {
        return cost;
    }

    /**
     * define cost of the order
     *
     * @param cost nonzero integer
     */
    public void setCost(int cost)
    {
        this.cost = cost;
    }

    /**
     * get the number of pcb ordered
     *
     * @return
     */
    public int getQuantity()
    {
        return quantity;
    }

    /**
     * define quntity of pcb in an order
     *
     * @param quantity
     */
    public void setQuantity(int quantity)
    {
        this.quantity = quantity;
    }

    /**
     * retrive the http location of the schematic uploaded to database
     *
     * @return Url
     */
    public String getFileURL()
    {
        return fileURL;
    }

    /**
     * set the url of schematic
     *
     * @param fileURL web url
     */
    public void setFileURL(String fileURL)
    {
        this.fileURL = fileURL;
    }

    /**
     * Show all the information of the orderPCB
     *
     * @return
     */
    @Override
    public String toString()
    {
        return "PCBDetails{" +
                "orderId='" + orderId + '\'' +
                ", isSingle=" + isSingle +
                ", isMasking=" + isMasking +
                ", width=" + width +
                ", height=" + height +
                ", cost=" + cost +
                ", quantity=" + quantity +
                ", fileURL='" + fileURL + '\'' +
                '}';
    }
}
