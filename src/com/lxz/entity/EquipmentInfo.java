package com.lxz.entity;

import java.util.HashMap;
import java.util.Map;

/**
 * @program: CMAPP
 * @description 设备信息实体类
 * @author: 李星泽
 * @create: 2020-07-15 17:51
 **/
public class EquipmentInfo {

    private int number = 0;//序号
    private String equipmentNumber;//设备编号
    private String equipmentName;//设备名称
    private String equipmentType;//设备类型
    private String equipmentSpec;//设备规格
    private String equipmentState;//设备状态
    private String equipmentDesc;//设备描述
    private String isRented;//租用状态
    private String affiliation;//所属工厂
    private Map<String, Integer> capacity = new HashMap<String, Integer>();//产能

    public EquipmentInfo() {
        number++;
    }

    public int getNumber() {
        return number;
    }

    public void setNumber(int number) {
        this.number = number;
    }

    public String getEquipmentNumber() {
        return equipmentNumber;
    }

    public void setEquipmentNumber(String equipmentNumber) {
        this.equipmentNumber = equipmentNumber;
    }

    public String getEquipmentName() {
        return equipmentName;
    }

    public void setEquipmentName(String equipmentName) {
        this.equipmentName = equipmentName;
    }

    public String getEquipmentType() {
        return equipmentType;
    }

    public void setEquipmentType(String equipmentType) {
        this.equipmentType = equipmentType;
    }

    public String getEquipmentSpec() {
        return equipmentSpec;
    }

    public void setEquipmentSpec(String equipmentSpec) {
        this.equipmentSpec = equipmentSpec;
    }

    public String getEquipmentState() {
        return equipmentState;
    }

    public void setEquipmentState(String equipmentState) {
        this.equipmentState = equipmentState;
    }

    public String isRented() {
        return isRented;
    }

    public void setRented(String rented) {
        isRented = rented;
    }

    public String getAffiliation() {
        return affiliation;
    }

    public void setAffiliation(String affiliation) {
        this.affiliation = affiliation;
    }

    public void add(String equipmentName, Integer capacitySpeed) {
        capacity.put(equipmentName, capacitySpeed);
    }

    public void delete(String equipmentName) {
        capacity.remove(equipmentName);
    }

    public String getEquipmentDesc() {
        return equipmentDesc;
    }

    public void setEquipmentDesc(String equipmentDesc) {
        this.equipmentDesc = equipmentDesc;
    }

    public void addMap(String productName, Integer count) {
        capacity.put(productName, count);
    }

    public void deleteMap(String productName) {
        capacity.remove(productName);
    }

    public Map<String, Integer> getMap() {
        return capacity;
    }

    @Override
    public String toString() {
        return "EquipmentInfo{" +
                "number=" + number +
                ", equipmentNumber='" + equipmentNumber + '\'' +
                ", equipmentName='" + equipmentName + '\'' +
                ", equipmentType='" + equipmentType + '\'' +
                ", equipmentSpec='" + equipmentSpec + '\'' +
                ", equipmentState='" + equipmentState + '\'' +
                ", isRented=" + isRented +
                ", affiliation='" + affiliation + '\'' +
                '}';
    }
}
