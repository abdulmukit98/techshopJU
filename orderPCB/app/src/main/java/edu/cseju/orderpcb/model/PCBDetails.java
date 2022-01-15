package edu.cseju.orderpcb.model;

public class PCBDetails {

    private String orderId;
    private boolean isSingle, isMasking;
    private int width, height, cost, quantity;
    private String fileName;


    public PCBDetails() {
    }

    public PCBDetails(String orderId, boolean isSingle, boolean isMasking, int width, int height, int quantity, String fileName) {
        this.orderId = orderId;
        this.isSingle = isSingle;
        this.isMasking = isMasking;
        this.width = width;
        this.height = height;
        this.quantity = quantity;
        this.fileName = fileName;
    }
}
