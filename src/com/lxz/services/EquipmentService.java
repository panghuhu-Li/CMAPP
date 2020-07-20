package com.lxz.services;

import java.io.IOException;
import java.util.List;

public interface EquipmentService {
    // 添加设备
    boolean add(Object object) throws IOException;

    // 得到所有设备信息
    List<Object> getEquipmentInfo() throws IOException;

    // 删除相应设备
    boolean delete(String productNumber) throws IOException;

    // 查找设备信息
    Object searchEquipment(String name) throws IOException;

    // 远程开关机
    boolean remoteControll(String name) throws IOException;

    // 修改设备信息
    boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec,
            String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException;

    // 租用设备
    boolean rent(String equipmentNumber, String aff) throws IOException;

    // 产品产能配置
    boolean setCapacity(String equipmentNumber, String productName, Integer capa) throws IOException;
}
