package com.lxz.entity;

/**
 * @program: CMAPP
 * @description 云工厂实体类
 * @author: 李星泽
 * @create: 2020-07-16 06:23
 **/
public class CloudFactory {
    private String factoryNameString;// 工厂名称
    private String factoryDescString;// 工厂简介
    private String principal;// 负责人
    private String linkman;// 联系人
    private String linkway;// 联系方式
    private String accountNumber;// 工厂负责人的登录账号
    private boolean status;// ״工厂状态

    public String getAccountNumber() {
        return accountNumber;
    }

    public void setAccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public CloudFactory() {
    }

    public String getFactoryNameString() {
        return factoryNameString;
    }

    public void setFactoryNameString(String factoryNameString) {
        this.factoryNameString = factoryNameString;
    }

    public String getFactoryDescString() {
        return factoryDescString;
    }

    public void setFactoryDescString(String factoryDescString) {
        this.factoryDescString = factoryDescString;
    }

    public String getPrincipal() {
        return principal;
    }

    public void setPrincipal(String principal) {
        this.principal = principal;
    }

    public String getLinkman() {
        return linkman;
    }

    public void setLinkman(String linkman) {
        this.linkman = linkman;
    }

    public String getLinkway() {
        return linkway;
    }

    public void setLinkway(String linkway) {
        this.linkway = linkway;
    }

    public boolean isStatus() {
        return status;
    }

    public void setStatus(boolean status) {
        this.status = status;
    }

    @Override
    public String toString() {
        return "CloudFactory [factoryNameString=" + factoryNameString + ", factoryDescString=" + factoryDescString
                + ", principal=" + principal + ", linkman=" + linkman + ", linkway=" + linkway + ", status=" + status
                + "]";
    }
}
