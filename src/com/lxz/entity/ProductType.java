package com.lxz.entity;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-15 18:09
 **/
public class ProductType {
//    -number: int
//-typeName: String
    private static int number;
    private String typeName;

    public ProductType() {
        number++;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        ProductType.number = number;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "ProductType{" +
                "typeName='" + typeName + '\'' +
                '}';
    }
}
