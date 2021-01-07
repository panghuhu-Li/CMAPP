package com.lxz.entity;

/**
 * @program: :CMAPP
 * @description :订单实体类
 * @author: 李星泽
 * @create: 2020-07-15 17:21
 **/
public class Order {

    private String orderNumber;//订单编号
    private String productName;//产品名称
    private String orderAmount;//订购数量
    private String dayOfDeliver;//交付日期
    private String dayOfDecline;//投标截止日期
    private String consignee;//收货人
    private String contactWay;//收货人联系方式
    private String placeOfReceive;//收货地点

    public Order(String orderNumber, String productName, String orderAmount, String dayOfDeliver, String dayOfDecline, String consignee, String contactWay, String placeOfReceive) {
        this.orderNumber = orderNumber;
        this.productName = productName;
        this.orderAmount = orderAmount;
        this.dayOfDeliver = dayOfDeliver;
        this.dayOfDecline = dayOfDecline;
        this.consignee = consignee;
        this.contactWay = contactWay;
        this.placeOfReceive = placeOfReceive;
    }

    public String getOrderNumber() {
        return orderNumber;
    }

    public String getProductName() {
        return productName;
    }

    public String getOrderAmount() {
        return orderAmount;
    }

    public String getDayOfDeliver() {
        return dayOfDeliver;
    }

    public String getDayOfDecline() {
        return dayOfDecline;
    }

    public String getConsignee() {
        return consignee;
    }

    public String getContactWay() {
        return contactWay;
    }

    public String getPlaceOfReceive() {
        return placeOfReceive;
    }

    public void setOrderNumber(String orderNumber) {
        this.orderNumber = orderNumber;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public void setOrderAmount(String orderAmount) {
        this.orderAmount = orderAmount;
    }

    public void setDayOfDeliver(String dayOfDeliver) {
        this.dayOfDeliver = dayOfDeliver;
    }

    public void setDayOfDecline(String dayOfDecline) {
        this.dayOfDecline = dayOfDecline;
    }

    public void setConsignee(String consignee) {
        this.consignee = consignee;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public void setPlaceOfReceive(String placeOfReceive) {
        this.placeOfReceive = placeOfReceive;
    }
}
