package com.lxz.entity;

/**
 * @program: CMAPP
 * @description 设备类型实体类
 * @author: 李星泽
 * @create: 2020-07-15 17:59
 **/
public class EquipmentType {
    private static int number;// 序号
    private String typeName;// 类别名称

    public EquipmentType() {
        number++;
    }

    public static int getNumber() {
        return number;
    }

    public static void setNumber(int number) {
        EquipmentType.number = number;
    }

    public String getTypeName() {
        return typeName;
    }

    public void setTypeName(String typeName) {
        this.typeName = typeName;
    }

    @Override
    public String toString() {
        return "EquipmentType{" + "typeName='" + typeName + '\'' + '}';
    }

}
