package com.lxz.entity;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-09 09:18
 **/
public class Tender {
    private String orderNumber;
    private String orderName;
    private String tenderPeo;
    private String tenderFactor;
    private String tenderDate;
    private int price;
    private String tenderNumber;
    private String tenderState;

    public Tender(String orderNumber, String orderName, String tenderPeo, String tenderFactor,
                  String tenderDate, int price, String tenderNumber, String tenderState) {
        this.orderNumber = orderNumber;
        this.orderName = orderName;
        this.tenderPeo = tenderPeo;
        this.tenderFactor = tenderFactor;
        this.tenderDate = tenderDate;
        this.price = price;
        this.tenderNumber = tenderNumber;
        this.tenderState = tenderState;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public String getOrderName() {
        return orderName;
    }

    public void setOrderName(String orderName) {
        this.orderName = orderName;
    }

    public String getTenderPeo() {
        return tenderPeo;
    }

    public void setTenderPeo(String tenderPeo) {
        this.tenderPeo = tenderPeo;
    }

    public String getTenderFactor() {
        return tenderFactor;
    }

    public void setTenderFactor(String tenderFactor) {
        this.tenderFactor = tenderFactor;
    }

    public String getTenderDate() {
        return tenderDate;
    }

    public void setTenderDate(String tenderDate) {
        this.tenderDate = tenderDate;
    }

    public int getPrice() {
        return price;
    }

    public void setPrice(int price) {
        this.price = price;
    }

    public String getTenderNumber() {
        return tenderNumber;
    }

    public void setTenderNumber(String tenderNumber) {
        this.tenderNumber = tenderNumber;
    }

    public String getTenderState() {
        return tenderState;
    }

    public void setTenderState(String tenderState) {
        this.tenderState = tenderState;
    }

    @Override
    public String toString() {
        return "Tender{" +
                "orderNumber='" + orderNumber + '\'' +
                ", orderName='" + orderName + '\'' +
                ", tenderPeo='" + tenderPeo + '\'' +
                ", tenderFactor='" + tenderFactor + '\'' +
                ", tenderDate='" + tenderDate + '\'' +
                ", price=" + price +
                ", tenderNumber='" + tenderNumber + '\'' +
                ", tenderState='" + tenderState + '\'' +
                '}';
    }
}
