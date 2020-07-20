package com.lxz.entity;

/**
 * @program: CMAPP
 * @description
 * @author: YourName
 * @create: 2020-07-16 06:13
 **/
public class Administrator {

    private String accountNumber;//账号
    private String password;//密码
    private String name;//姓名
    private String contactWay;//联系方式
    private String registerType;//类型

    public Administrator() {
    }

    public String getaccountNumber() {
        return accountNumber;
    }

    public void setaccountNumber(String accountNumber) {
        this.accountNumber = accountNumber;
    }

    public String getPassword() {
        return password;
    }

    public void setPassword(String password) {
        this.password = password;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getContactWay() {
        return contactWay;
    }

    public void setContactWay(String contactWay) {
        this.contactWay = contactWay;
    }

    public String getRegisterType() {
        return registerType;
    }

    public void setRegisterType(String registerType) {
        this.registerType = registerType;
    }

    @Override
    public String toString() {
        return "Administrator{" +
                "accountNumber='" + accountNumber + '\'' +
                ", password='" + password + '\'' +
                ", name='" + name + '\'' +
                ", contactWay='" + contactWay + '\'' +
                ", registerType='" + registerType + '\'' +
                '}';
    }
}
