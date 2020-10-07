package com.lxz.entity;

/**
 * @program: CMAPP
 * @description 订单管理实体类
 * @author: 李星泽
 * @create: 2020-07-16 06:33
 **/
public class OrderManage {
    private String equipmentCode;//设备编号
    private String startTime;//开始时间
    private String endTime;//结束时间

    public OrderManage() {
    }

    public String getEquipmentCode() {
        return equipmentCode;
    }

    public void setEquipmentCode(String equipmentCode) {
        this.equipmentCode = equipmentCode;
    }

    public String getStartTime() {
        return startTime;
    }

    public void setStartTime(String startTime) {
        this.startTime = startTime;
    }

    public String getEndTime() {
        return endTime;
    }

    public void setEndTime(String endTime) {
        this.endTime = endTime;
    }

    @Override
    public String toString() {
        return "OrderManage{" +
                "equipmentCode='" + equipmentCode + '\'' +
                ", startTime='" + startTime + '\'' +
                ", endTime='" + endTime + '\'' +
                '}';
    }
}
