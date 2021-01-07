package com.lxz.entity;

/**
 * @program: CMAPP
 * @description
 * @author: 李星泽
 * @create: 2021-01-05 21:28
 **/
public class Dictionary {
    private String dictionaryNumber;
    private String typeCode;
    private String name;
    private int number;

    public Dictionary(String dictionaryNumber, String typeCode, String name, int number) {
        this.dictionaryNumber = dictionaryNumber;
        this.typeCode = typeCode;
        this.name = name;
        this.number = number;
    }

    public String getDictionaryNumber() {
        return dictionaryNumber;
    }

    public void setDictionaryNumber(String dictionaryNumber) {
        this.dictionaryNumber = dictionaryNumber;
    }

    public String getTypeCode() {
        return typeCode;
    }

    public void setTypeCode(String typeCode) {
        this.typeCode = typeCode;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    @Override
    public String toString() {
        return "Dictionary{" +
                "dictionaryNumber='" + dictionaryNumber + '\'' +
                ", typeCode='" + typeCode + '\'' +
                ", name='" + name + '\'' +
                ", number=" + number +
                '}';
    }
}
