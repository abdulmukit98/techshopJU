package edu.cseju.orderpcb.model;

public class PCBDetails {

    private String orderId;
    private boolean isSingle, isMasking;
    private double width, height;
    int cost, quantity;
    private String fileURL;


    public PCBDetails() {
    }

    public PCBDetails(String orderId, boolean isSingle, boolean isMasking, double width, double height, int quantity) {
        this.orderId = orderId;
        this.isSingle = isSingle;
        this.isMasking = isMasking;
        this.width = width;
        this.height = height;
        this.quantity = quantity;
        this.cost = (int) (width * height * 20 * quantity);

    }

    public String getOrderId() {
        return orderId;
    }

    public void setOrderId(String orderId) {
        this.orderId = orderId;
    }

    public boolean isSingle() {
        return isSingle;
    }

    public void setSingle(boolean single) {
        isSingle = single;
    }

    public boolean isMasking() {
        return isMasking;
    }

    public void setMasking(boolean masking) {
        isMasking = masking;
    }

    public double getWidth() {
        return width;
    }

    public void setWidth(double width) {
        this.width = width;
    }

    public double getHeight() {
        return height;
    }

    public void setHeight(double height) {
        this.height = height;
    }

    public int getCost() {
        return cost;
    }

    public void setCost(int cost) {
        this.cost = cost;
    }

    public int getQuantity() {
        return quantity;
    }

    public void setQuantity(int quantity) {
        this.quantity = quantity;
    }

    public String getFileURL() {
        return fileURL;
    }

    public void setFileURL(String fileURL) {
        this.fileURL = fileURL;
    }

    @Override
    public String toString() {
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
