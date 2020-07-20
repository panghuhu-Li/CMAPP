package com.lxz.controllers;

import com.lxz.services.EquipmentService;
import com.lxz.servicesimpl.EquipmentServiceImpl;

import java.io.IOException;
import java.util.List;

/**
 * @program: CMAPP
 * @description 设备操作数据和结果接受
 * @author: 李星泽
 * @create: 2020-07-19 09:48
 **/
public class EquipmentController {
    private EquipmentService equipmentService = new EquipmentServiceImpl();

    // 添加设备信息
    public boolean add(Object object) throws IOException {
        return equipmentService.add(object);
    }

    // 得到所有的设备信息
    public List<Object> getEquipmentInfo() throws IOException {
        return equipmentService.getEquipmentInfo();
    }

    // 删除相应设备
    public boolean delete(String productNumber) throws IOException {
        return equipmentService.delete(productNumber);
    }

    // 查找设备信息
    public Object searchEquipment(String name) throws IOException {
        return equipmentService.searchEquipment(name);
    }

    // 远程开关机
    public boolean remoteControll(String name) throws IOException {
        return equipmentService.remoteControll(name);
    }

    // 修改设备信息
    public boolean modify(String equipmentNumber, String equipmentName, String equipmentType, String equipmentSpec,
            String equipmentDesc, String equipmentState, String isRented, String affiliation) throws IOException {
        return equipmentService.modify(equipmentNumber, equipmentName, equipmentType, equipmentSpec, equipmentDesc,
                equipmentState, isRented, affiliation);
    }

    // 租用设备
    public boolean rent(String equipmentNumber, String aff) throws IOException {
        return equipmentService.rent(equipmentNumber, aff);
    }

    // 产能配置
    public boolean setCapacity(String equipmentNumber, String productName, Integer capa) throws IOException {
        return equipmentService.setCapacity(equipmentNumber, productName, capa);
    }
}
