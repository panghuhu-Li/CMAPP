package com.lxz.entity;

/**
 * @program: CMAPP
 * @description 产品信息实体类
 * @author: 李星泽
 * @create: 2020-07-15 18:05
 **/
public class ProductInfo {
    private static int number;//序号
    private String productNumber;//产品序号
    private String productName;//产品名称
    private String productType;//产品类别
    private String productSpec;//产品规格
    private String productDesc;//产品描述

    public ProductInfo() {
        number++;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        ProductInfo.number = number;
    }

    public String getProductNumber() {
        return productNumber;
    }

    public void setProductNumber(String productNumber) {
        this.productNumber = productNumber;
    }

    public String getProductName() {
        return productName;
    }

    public void setProductName(String productName) {
        this.productName = productName;
    }

    public String getProductType() {
        return productType;
    }

    public void setProductType(String productType) {
        this.productType = productType;
    }

    public String getProductSpec() {
        return productSpec;
    }

    public void setProductSpec(String productSpec) {
        this.productSpec = productSpec;
    }

    public String getProductDesc() {
        return productDesc;
    }

    public void setProductDesc(String productDesc) {
        this.productDesc = productDesc;
    }

    @Override
    public String toString() {
        return "ProductInfo{" +
                "productNumber='" + productNumber + '\'' +
                ", productName='" + productName + '\'' +
                ", productType='" + productType + '\'' +
                ", productSpec='" + productSpec + '\'' +
                ", productDesc='" + productDesc + '\'' +
                '}';
    }
}
