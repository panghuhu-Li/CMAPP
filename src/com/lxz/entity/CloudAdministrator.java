package com.lxz.entity;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 06:20
 **/
public class CloudAdministrator extends Administrator {
    private String factorName;//工厂名称
    private String factorDesc;//工厂描述

    public CloudAdministrator() {
    }

    public String getFactorName() {
        return factorName;
    }

    public void setFactorName(String factorName) {
        this.factorName = factorName;
    }

    public String getFactorDesc() {
        return factorDesc;
    }

    public void setFactorDesc(String factorDesc) {
        this.factorDesc = factorDesc;
    }

    @Override
    public String toString() {
        return "CloudAdministrator{" +
                "factorName='" + factorName + '\'' +
                ", factorDesc='" + factorDesc + '\'' +
                "AccountNumber='" + super.getaccountNumber() + '\'' +
                ", password='" + super.getPassword() + '\'' +
                ", name='" + super.getName() + '\'' +
                ", contactWay='" + super.getContactWay() + '\'' +
                ", registerType='" + super.getRegisterType() + '\'' +
                '}';
    }
}
